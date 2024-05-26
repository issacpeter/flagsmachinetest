package com.flagschallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flagschallenge.QuestionsRepository
import com.flagschallenge.intent.QuizIntent
import com.flagschallenge.model.QuizState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuestionsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(QuizState())
    val state: StateFlow<QuizState> = _state

    init {
        handleIntent(QuizIntent.LoadQuestions)
    }

    fun handleIntent(intent: QuizIntent) {
        when (intent) {
            is QuizIntent.LoadQuestions -> {
                val questions = repository.getQuestions()
                if (questions.isNotEmpty()) {
                    _state.value = _state.value.copy(currentQuestion = questions[0])
                }
            }
            is QuizIntent.StartChallenge -> {
                _state.value = _state.value.copy(isChallengeStarted = true)
                startTimer()
            }
            is QuizIntent.SelectAnswer -> {
                val isCorrect = _state.value.currentQuestion?.answerId == intent.countryId
                _state.value = _state.value.copy(isAnswerSelected = true, isCorrect = isCorrect)
                if (isCorrect) {
                    _state.value = _state.value.copy(score = _state.value.score + 1)
                }
                viewModelScope.launch {
                    delay(10000)
                    handleIntent(QuizIntent.NextQuestion)
                }
            }
            is QuizIntent.NextQuestion -> {
                val nextIndex = _state.value.currentIndex + 1
                val questions = repository.getQuestions()
                if (nextIndex < questions.size) {
                    _state.value = _state.value.copy(
                        currentQuestion = questions[nextIndex],
                        currentIndex = nextIndex,
                        isAnswerSelected = false,
                        timer = 30,
                        isCorrect = null
                    )
                } else {
                    _state.value = _state.value.copy(isChallengeStarted = false)
                }
            }
            is QuizIntent.TimerTick -> {
                if (_state.value.timer > 0) {
                    _state.value = _state.value.copy(timer = _state.value.timer - 1)
                } else {
                    handleIntent(QuizIntent.NextQuestion)
                }
            }
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (_state.value.timer > 0) {
                delay(1000)
                handleIntent(QuizIntent.TimerTick)
            }
        }
    }
}
