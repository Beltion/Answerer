package com.example.answerer.data

data class Solution (
    var id: String? = null,
    var title: String? = null,
    var avtor: String? = null,
    val questions: ArrayList<Question>,
    val answers: ArrayList<Answer>,
    val results: ArrayList<Result>
)