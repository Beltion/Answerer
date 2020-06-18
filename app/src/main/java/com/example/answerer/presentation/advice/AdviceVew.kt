package com.example.answerer.presentation.advice


interface AdviceVew {

    fun init()

    fun showToast(regStatus: String)

    fun showProgressBar()
    fun hideProgressBar()

    fun hideCardViewContainer()
    fun showCardViewContainer()

    fun setToolbarTitle(s:String)

}