package com.candroid.quizapp.module

data class Quiz(

    val id: String = "",
    val title: String = "",
    val question: MutableMap<String, Question> = mutableMapOf()
)
