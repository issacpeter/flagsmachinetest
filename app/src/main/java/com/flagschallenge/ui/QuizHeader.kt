package com.flagschallenge.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.flagschallenge.R
import com.flagschallenge.intent.QuizIntent
import com.flagschallenge.model.QuizState

@Composable
fun QuizHeader(state: QuizState, onIntent: (QuizIntent) -> Unit) {
    val currentQuestion = state.currentQuestion ?: return

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(90.dp)
                .fillMaxHeight()
                .background(colorResource(id = R.color.black))
        ) {
            Text(text = "00:10", color = colorResource(id = R.color.white), fontSize = 18.sp)
        }
        Text(
            modifier = Modifier.fillMaxSize(),
            text = stringResource(id = R.string.app_name).uppercase(),
            color = colorResource(id = R.color.orange),
            fontSize = 18.sp
        )
    }
}
