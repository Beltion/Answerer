package com.example.answerer.presentation.usersolution

interface UserSolutionView {
    fun init()

    fun showToast(s: String)

    fun showProgressBar()
    fun hideProgressBar()

    fun hideViewContainer()
    fun showViewContainer()
}