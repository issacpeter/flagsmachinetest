package com.flagschallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.flagschallenge.R
import com.flagschallenge.intent.QuizIntent
import com.flagschallenge.viewmodel.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlagsChallengeApp(viewModel: QuizViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    Column{
        Box(modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.dimen_15dp))
            .background(
                color = colorResource(
                    id = R.color.orange
                )
            )
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.dimen_98dp))
        )
        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.grey_bg))
        ){
            QuizHeader(state = state, onIntent = { viewModel.handleIntent(it) })
            if (state.isChallengeStarted) {
                QuizScreen(state = state, onIntent = { viewModel.handleIntent(it) })
            } else {
                ChallengeScheduler(onStartChallenge = { viewModel.handleIntent(QuizIntent.StartChallenge(it)) })
            }
        }
    }
}

@Preview
@Composable
fun FlagsChallengeAppPreview(){
    FlagsChallengeApp(hiltViewModel())
}
