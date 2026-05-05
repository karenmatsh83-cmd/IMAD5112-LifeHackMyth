package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quizapp.ui.theme.QuizAppTheme

data class HackQuestion(
    val statement: String,
    val isHack: Boolean,
    val explanation: String
)

val questions = listOf(
    HackQuestion("Putting your phone in rice fixes water damage", false, "Rice doesn't absorb moisture well. Use silica gel instead."),
    HackQuestion("Sleeping 8 hours helps memory", true, "Sleep helps your brain store memories."),
    HackQuestion("Cracking your knuckles causes arthritis", false, "No medical evidence shows this causes arthritis.")
)

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                QuizAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        WelcomeScreen()
                    }
                }
            }
        }
    }

    @Composable
    fun WelcomeScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Life Hack or Urban Myth?",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Test your common sense! Learn useful life hacks and spot the myths.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            val context = LocalContext.current
            Button(onClick = {
                val intent = Intent(context, FlashcardActivity::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Start")
            }




        }
    }

