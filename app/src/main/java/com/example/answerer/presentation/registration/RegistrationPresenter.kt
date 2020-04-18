package com.example.answerer.presentation.registration

import android.content.ContentValues


class RegistrationPresenter(_view: RegistrationView) {

    private val view: RegistrationView = _view
    val model = RegistrationModel()

    fun onButtonRegClick() {
        val user = view.getRegUserData()
        if(user.email.isEmpty()) {
            view.showEmailEmptyError()
            return
        }
        if(user.password.isEmpty()) {
            view.showPasswordEmptyError()
            return
        }
        if(user.password.length < 6) {
            view.showPasswordLenghtError()
            return
        }

        model.createUser(user.email,user.password,object : RegistrationModel.CompleteCallback {
            override fun onComplete(regStatus: Boolean) {
                view.showToast(regStatus.toString())
            }

        })

    }

}