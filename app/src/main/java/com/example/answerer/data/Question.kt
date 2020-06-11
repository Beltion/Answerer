package com.example.answerer.data


data class Question (
    var id: Int? = null,
    var content: String? = "",
    var count: ArrayList<Int>?
)