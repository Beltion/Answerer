package com.example.answerer.presentation.categories

import com.example.answerer.data.Category


interface CategoriseView {

    fun init()

    fun showToast(regStatus: String)

    fun showProgressBar()
    fun hideProgressBar()

    fun hideCardViewContainer()
    fun showCardViewContainer()
}