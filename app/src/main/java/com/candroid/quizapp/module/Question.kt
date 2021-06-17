package com.candroid.quizapp.module

data class Question(
    val description: String="",
    val option1: String="",
    val option2: String="",
    val option3: String="",
    val option4: String="",
    val correctAnswer: String="",
    var userAnswer: String=""
)
