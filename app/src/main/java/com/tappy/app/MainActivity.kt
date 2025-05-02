package com.tappy.app

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tappy.app.navigation.AppNavGraph
import com.tappy.data.local.QuestionEntity
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.Composable
import androidx.work.Configuration
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import com.tappy.app.di.appModule
import org.koin.androidx.compose.get
import org.koin.android.ext.android.inject
import com.tappy.data.local.QuestionDao
import com.tappy.data.repository.QuizRepository
import kotlinx.coroutines.CoroutineScope


class MainActivity : ComponentActivity(), Configuration.Provider {

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.INFO)
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            AppNavGraph(navController = navController)
        }
    }
}


@Composable
fun MainApp() {
    val navController = rememberNavController()
    AppNavGraph(navController = navController)
}
@Composable
fun QuizScreen(navController: NavController, category: String? = null) {
    val context = LocalContext.current
    val dao: QuestionDao = get()
    val quizRepo: QuizRepository = get()

    var questions by remember { mutableStateOf<List<QuestionEntity>>(emptyList()) }
    val scrollState = rememberScrollState()

    // Категория: грузим из локального, иначе с API
    LaunchedEffect(category) {
        if (category != null) {
            withContext(Dispatchers.IO) {
                questions = dao.getByCategory(category)
            }
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val apiQuestions = quizRepo.getQuestions()
                    val entities = apiQuestions.map {
                        QuestionEntity(
                            id = it.id,
                            questionText = it.questionText,
                            options = it.options,
                            correctAnswerIndex = it.correctAnswerIndex,
                            category = "API"
                        )
                    }
                    dao.deleteAll()
                    dao.insertAll(entities)
                    withContext(Dispatchers.Main) {
                        questions = entities
                        Toast.makeText(context, "Loaded ${entities.size} questions", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("API", "Error loading questions", e)
                }
            }
        }
    }

    val selectedAnswers = remember(questions) {
        mutableStateListOf<Int?>().apply { repeat(questions.size) { add(null) } }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        if (questions.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Quiz - ${category ?: "Random"}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                questions.forEachIndexed { qIndex, question ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "${qIndex + 1}. ${question.questionText}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            question.options.forEachIndexed { oIndex, option ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { selectedAnswers[qIndex] = oIndex }
                                        .padding(vertical = 4.dp)
                                ) {
                                    RadioButton(
                                        selected = selectedAnswers[qIndex] == oIndex,
                                        onClick = { selectedAnswers[qIndex] = oIndex }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(option)
                                }
                            }
                        }
                    }
                }

                Button(
                    onClick = {
                        val correctCount = selectedAnswers.withIndex().count {
                            it.value == questions[it.index].correctAnswerIndex
                        }
                        navController.navigate("result/$correctCount")
                    },
                    enabled = selectedAnswers.all { it != null },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Submit")
                }
            }
        }
    }
}