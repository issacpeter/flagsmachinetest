package com.flagschallenge.model

import com.flagschallenge.data.Question

data class QuizState(
    val currentQuestion: Question? = null,
    val currentIndex: Int = 0,
    val score: Int = 0,
    val timer: Int = 30,
    val isChallengeStarted: Boolean = false,
    val isAnswerSelected: Boolean = false,
    val isCorrect: Boolean? = null
)
