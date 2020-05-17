package com.example.answerer.presentation.login

import com.example.answerer.data.User

interface LoginView {

    fun init()
    fun getLoginUserData(): User

    fun showEmailError(strId: Int)
    fun showPasswordError(strId: Int)

    fun showToast(regStatus: String)

    fun showProgressBar()
    fun hideProgressBar()

    fun hideCardViewContainer()
    fun showCardViewContainer()


}