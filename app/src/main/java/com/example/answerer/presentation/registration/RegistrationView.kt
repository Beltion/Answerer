package com.example.answerer.presentation.registration

import com.example.answerer.data.RegUser

interface RegistrationView {
    fun getRegUserData(): RegUser

    fun showEmailEmptyError()

    fun showPasswordEmptyError()

    fun showPasswordLenghtError()

    fun showToast(regStatus: String)
    fun showProgressBar()
    fun hideProgressBar()
}