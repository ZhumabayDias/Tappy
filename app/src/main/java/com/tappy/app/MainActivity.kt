package com.tappy.app


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.tappy.app.navigation.AppNavGraph
import com.tappy.data.local.AppDatabase
import com.tappy.data.local.QuestionEntity
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.Composable
import androidx.work.Configuration


class MainActivity : ComponentActivity(), Configuration.Provider {

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.INFO)
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "quiz.db"
        ).build()

        val dao = db.questionDao()
        val prefilled = listOf(
            QuestionEntity("q1", "What is Android written in?", listOf("Java", "Kotlin", "Swift", "Dart"), 1),
            QuestionEntity("q2", "What is Jetpack Compose used for?", listOf("UI", "Networking", "Databases", "Backend"), 0)
        )

        lifecycleScope.launch {
            dao.insertAll(prefilled)
        }

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
fun QuizScreen(navController: NavController) {
    val context = LocalContext.current

    // Load Room database
    val db = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "quiz.db"
        ).build()
    }

    // Load questions from Room
    var questions by remember { mutableStateOf<List<QuestionEntity>>(emptyList()) }

    LaunchedEffect(true) {
        withContext(Dispatchers.IO) {
            questions = db.questionDao().getAll()
        }
    }

    // ✅ Wait until questions are loaded
    if (questions.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    // Track selected answers
    val selectedAnswers = remember {
        mutableStateListOf<Int?>().apply {
            repeat(questions.size) { add(null) }
        }
    }

    // ✅ Quiz UI
    Column(modifier = Modifier.padding(16.dp)) {
        questions.forEachIndexed { qIndex, question ->
            Text("${qIndex + 1}. ${question.questionText}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))

            question.options.forEachIndexed { oIndex, option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                        .clickable { selectedAnswers[qIndex] = oIndex }
                ) {
                    RadioButton(
                        selected = selectedAnswers[qIndex] == oIndex,
                        onClick = { selectedAnswers[qIndex] = oIndex }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(option)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        Button(
            onClick = {
                val correctCount = selectedAnswers.withIndex().count {
                    val correctIndex = questions[it.index].correctAnswerIndex
                    it.value == correctIndex
                }
                navController.navigate("result/$correctCount")
            },
            enabled = selectedAnswers.size == questions.size && selectedAnswers.all { it != null },
            modifier = Modifier.align(Alignment.End).padding(top = 16.dp)
        ) {
            Text("Submit")
        }
    }
}