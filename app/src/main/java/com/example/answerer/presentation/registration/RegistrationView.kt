package com.example.answerer.presentation.registration

import com.example.answerer.data.RegUser

interface RegistrationView {
    fun getRegUserData(): RegUser

    fun showEmailError(strId: Int)
    fun showPasswordError(strId: Int)

    fun showToast(regStatus: String)

    fun showProgressBar()
    fun hideProgressBar()

    fun hideCardViewContainer()
    fun showCardViewContainer()
}