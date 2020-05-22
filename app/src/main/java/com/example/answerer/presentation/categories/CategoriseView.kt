package com.example.answerer.presentation.categories



interface CategoriseView {

    fun init()

    fun showToast(regStatus: String)

    fun showProgressBar()
    fun hideProgressBar()

    fun hideCardViewContainer()
    fun showCardViewContainer()

}