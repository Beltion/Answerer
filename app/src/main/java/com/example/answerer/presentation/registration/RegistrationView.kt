package com.example.answerer.presentation.registration

import com.example.answerer.data.User

interface RegistrationView {

    fun init()
    fun getRegUserData(): User

    fun showNameError(strId: Int)
    fun showEmailError(strId: Int)
    fun showPasswordError(strId: Int)

    fun showToast(regStatus: String)

    fun showProgressBar()
    fun hideProgressBar()

    fun hideCardViewContainer()
    fun showCardViewContainer()
}