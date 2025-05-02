package com.tappy.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.tappy.app.data.QuestionSeeder
import com.tappy.domain.usecase.InsertQuestionsUseCase
import org.koin.androidx.compose.get
import com.example.logger.Logger



@Composable
fun WelcomeScreen(navController: NavController) {
    Logger.log("Logger подключен!")
    val insertUseCase: InsertQuestionsUseCase = get()
    val questions = QuestionSeeder.getInitialQuestions()

    LaunchedEffect(Unit) {
        insertUseCase(questions)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to Tappy!",
                fontSize = 28.sp,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ready to test your knowledge?",
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(32.dp))

            ElevatedButton(
                onClick = { navController.navigate("quiz") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Quiz")
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { navController.navigate("categories") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Choose Category")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                FirebaseAuth.getInstance().signOut()
                navController.navigate("login") {
                    popUpTo("welcome") { inclusive = true }
                }
            }) {
                Text("Logout", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}