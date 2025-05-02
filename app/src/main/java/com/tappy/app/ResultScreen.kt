package com.tappy.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ResultScreen(score: Int, navController: NavController? = null) {
    val user = FirebaseAuth.getInstance().currentUser
    val db = FirebaseFirestore.getInstance()

    // 🔄 Сохраняем результат в Firebase
    LaunchedEffect(score) {
        user?.let {
            val result = hashMapOf(
                "uid" to it.uid,
                "email" to it.email,
                "score" to score,
                "timestamp" to System.currentTimeMillis()
            )
            db.collection("quiz_results").add(result)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🎉 Quiz Completed!",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Your Score: $score",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(32.dp))

            navController?.let {
                Button(
                    onClick = { it.navigate("welcome") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Play Again")
                }
            }
        }
    }
}