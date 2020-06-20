package com.example.answerer.presentation.constructor

interface ConstructorView {

    fun init()

    fun showToast(regStatus: String)

    fun showProgressBar()
    fun hideProgressBar()

    fun hideCardViewContainer()
    fun showCardViewContainer()

    fun setToolbarTitle(s:String)
}