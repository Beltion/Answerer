package com.example.answerer.data

class RegUser {
    lateinit var name: String
    lateinit var email: String
    lateinit var password: String
    fun trimField(field: String) = field.trim()
}