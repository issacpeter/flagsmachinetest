package com.flagschallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flagschallenge.R

@Composable
fun ChallengeScheduler(onStartChallenge: (Int) -> Unit) {
    var timeInSeconds by remember { mutableStateOf(0) }
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Set Challenge Time (in seconds)")
//        TextField(
//            value = timeInSeconds.toString(),
//            onValueChange = { timeInSeconds = it.toIntOrNull() ?: 0 },
//            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
//        )
//        Button(onClick = { onStartChallenge(timeInSeconds) }) {
//            Text("Save")
//        }
//    }
    var hourFirstDigit by remember { mutableStateOf(TextFieldValue("0")) }
    var hourSecondDigit by remember { mutableStateOf(TextFieldValue("0")) }
    var minuteFirstDigit by remember { mutableStateOf(TextFieldValue("0")) }
    var minuteSecondDigit by remember { mutableStateOf(TextFieldValue("0")) }
    var secondFirstDigit by remember { mutableStateOf(TextFieldValue("0")) }
    var secondSecondDigit by remember { mutableStateOf(TextFieldValue("0")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Text(
//            text = "Flags Challenge",
//            style = MaterialTheme.typography.headlineLarge.copy(color = Color(0xFFFF5722)),
//            modifier = Modifier.padding(bottom = 16.dp)
//        )

        Text(
            text = "SCHEDULE",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        )
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TimeInputField("Hour", hourFirstDigit, hourSecondDigit) { first, second ->
                hourFirstDigit = first
                hourSecondDigit = second
            }
            Spacer(modifier = Modifier.width(8.dp))
            TimeInputField("Minute", minuteFirstDigit, minuteSecondDigit) { first, second ->
                minuteFirstDigit = first
                minuteSecondDigit = second
            }
            Spacer(modifier = Modifier.width(8.dp))
            TimeInputField("Second", secondFirstDigit, secondSecondDigit) { first, second ->
                secondFirstDigit = first
                secondSecondDigit = second
            }
        }

        Button(
            onClick = {
                val hour = "${hourFirstDigit.text}${hourSecondDigit.text}".toIntOrNull() ?: 0
                val minute = "${minuteFirstDigit.text}${minuteSecondDigit.text}".toIntOrNull() ?: 0
                val second = "${secondFirstDigit.text}${secondSecondDigit.text}".toIntOrNull() ?: 0
                timeInSeconds = hour * 3600 + minute * 60 + second
                onStartChallenge(timeInSeconds)
            },
            modifier = Modifier.background(colorResource(id = R.color.orange))
        ) {
            Text(text = "Save", color = Color.White)
        }
    }
}

@Composable
fun TimeInputField(label: String, firstDigit: TextFieldValue, secondDigit: TextFieldValue, onValueChange: (TextFieldValue, TextFieldValue) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            BasicTextField(
                value = firstDigit,
                onValueChange = {
                    if (it.text.length <= 1 && it.text.all { char -> char.isDigit() }) {
                        onValueChange(it, secondDigit)
                    }
                },
                textStyle = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .width(30.dp)
                    .height(60.dp)
                    .background(Color.LightGray)
                    .border(1.dp, Color.Gray)
                    .padding(4.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.width(4.dp))
            BasicTextField(
                value = secondDigit,
                onValueChange = {
                    if (it.text.length <= 1 && it.text.all { char -> char.isDigit() }) {
                        onValueChange(firstDigit, it)
                    }
                },
                textStyle = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .width(30.dp)
                    .height(60.dp)
                    .background(Color.LightGray)
                    .border(1.dp, Color.Gray)
                    .padding(4.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
fun ChallengeSchedulerPreview(){
    ChallengeScheduler(onStartChallenge = {})
}