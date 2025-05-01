package com.tappy.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Tappy!", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Are you ready to test your knowledge?", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            navController.navigate("quiz")
        }) {
            Text("Start Quiz")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            FirebaseAuth.getInstance().signOut()
            navController.navigate("login") {
                popUpTo("welcome") { inclusive = true } // Optional: clear backstack
            }
        }) {
            Text("Logout")
        }
    }
}