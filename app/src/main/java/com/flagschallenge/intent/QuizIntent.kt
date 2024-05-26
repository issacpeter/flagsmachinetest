package com.flagschallenge.intent

sealed class QuizIntent {
    object LoadQuestions : QuizIntent()
    data class StartChallenge(val timeInSeconds: Int) : QuizIntent()
    data class SelectAnswer(val countryId: Int) : QuizIntent()
    object NextQuestion : QuizIntent()
    object TimerTick : QuizIntent()
}
