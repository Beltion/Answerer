package com.example.answerer.data

data class Answer(
    var id: String = "",
    var content: String = "",
    var nextQuestionId: Int? = null
)