package com.example.answerer.data

data class ConstructorQuestion(
    var id: String = "",
    var question: String = "",
    var answers : ArrayList<Answer>
)