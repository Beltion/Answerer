package com.example.answerer.data


data class Question (
    var id: String? = null,
    var question: String? = "",
    val count: ArrayList<String>
)