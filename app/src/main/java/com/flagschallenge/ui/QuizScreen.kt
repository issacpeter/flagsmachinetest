package com.flagschallenge.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.flagschallenge.intent.QuizIntent
import com.flagschallenge.model.QuizState

@Composable
fun QuizScreen(state: QuizState, onIntent: (QuizIntent) -> Unit) {
    val currentQuestion = state.currentQuestion ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Guess the Country by the Flag")
        Text("Question ${state.currentIndex + 1}")
        Image(
            painter = rememberImagePainter("https://www.countryflags.io/${currentQuestion.countryCode}/shiny/64.png"),
            contentDescription = "Flag of ${currentQuestion.countryCode}"
        )
        currentQuestion.countries.forEach { country ->
            val backgroundColor = when {
                state.isAnswerSelected && country.id == state.currentQuestion.answerId -> androidx.compose.ui.graphics.Color.Green
                state.isAnswerSelected && country.id != state.currentQuestion.answerId /*&& country.id == state.isCorrect*/ -> Color.Red
                else -> Color.Transparent
            }
            Button(
                onClick = { onIntent(QuizIntent.SelectAnswer(country.id)) },
                colors = ButtonDefaults.buttonColors(backgroundColor)
            ) {
                Text(country.countryName)
            }
        }
    }
}
