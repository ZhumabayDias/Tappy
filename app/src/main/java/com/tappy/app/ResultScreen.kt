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
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ResultScreen(score: Int, navController: NavController? = null) {
    val user = FirebaseAuth.getInstance().currentUser
    val db = FirebaseFirestore.getInstance()

    LaunchedEffect(score) {
        user?.let {
            val result = hashMapOf(
                "uid" to it.uid,
                "email" to it.email,
                "score" to score,
                "timestamp" to System.currentTimeMillis()
            )

            db.collection("quiz_results")
                .add(result)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quiz Completed!", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Your Score: $score", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(24.dp))
        navController?.let {
            Button(onClick = { it.navigate("welcome") }) {
                Text("Play Again")
            }
        }
    }
}