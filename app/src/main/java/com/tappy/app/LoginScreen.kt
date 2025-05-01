package com.tappy.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import androidx.work.*
import com.tappy.app.worker.NotificationWorker
import java.util.concurrent.TimeUnit

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    // Auto-login if already signed in
    val user = auth.currentUser
    if (user != null) {
        LaunchedEffect(Unit) {
            navController.navigate("welcome") {
                popUpTo("login") { inclusive = true }
            }

            val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(
                15, TimeUnit.SECONDS
            ).build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "quizReminder",
                ExistingPeriodicWorkPolicy.REPLACE,
                workRequest
            )
        }
    }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login to Tappy", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    navController.navigate("welcome") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                .addOnFailureListener {
                    errorMessage = it.localizedMessage
                }
        }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    navController.navigate("welcome") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                .addOnFailureListener {
                    errorMessage = it.localizedMessage
                }
        }) {
            Text("Sign Up")
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}