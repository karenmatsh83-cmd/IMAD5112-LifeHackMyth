package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.quizapp.ui.theme.QuizAppTheme

class FlashcardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                FlashcardScreen()
            }
        }
    }
}

@Composable
fun FlashcardScreen() {
    var currentIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var showFeedback by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val question = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Question ${currentIndex + 1}/${questions.size}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = question.statement,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        if (!showFeedback) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    isCorrect = question.isHack
                    showFeedback = true
                    if (isCorrect) score++
                }) {
                    Text("Hack")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    isCorrect =!question.isHack
                    showFeedback = true
                    if (isCorrect) score++
                }) {
                    Text("Myth")
                }
            }
        } else {
            Text(
                text = if (isCorrect) "Correct!" else "Wrong!",
                style = MaterialTheme.typography.titleLarge,
                color = if (isCorrect) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
            Text(
                text = question.explanation,
                modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
            )
            Button(onClick = {
                showFeedback = false
                if (currentIndex < questions.size - 1) {
                    currentIndex++
                } else {
                    val intent = Intent(context, ScoreActivity::class.java)
                    intent.putExtra("score", score)
                    intent.putExtra("total", questions.size)
                    context.startActivity(intent)
                }
            }) {
                Text(text = "Next")
            }
        }
    }
}