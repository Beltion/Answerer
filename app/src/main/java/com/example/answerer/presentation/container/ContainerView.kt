package com.example.answerer.presentation.container

import com.example.answerer.data.User

interface ContainerView {

    fun init()

    fun showToast(str: String)

    fun showProgressBar()
    fun hideProgressBar()

    fun hideContainer()
    fun showContainer()
}