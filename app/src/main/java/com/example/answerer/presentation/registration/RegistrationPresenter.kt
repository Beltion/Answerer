package com.example.answerer.presentation.registration

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult


class RegistrationPresenter(_view: RegistrationView) {

    private val view: RegistrationView = _view
    val model = RegistrationModel()

    fun onButtonRegClick() {

        val user = view.getRegUserData()

        if(user.email.isEmpty()) {
            view.showEmailEmptyError()
            return
        } else {
            if(!isEmailValid(user.email)){
                view.showEmailEmptyError()
                return
            }
        }

        if(user.password.isEmpty()) {
            view.showPasswordEmptyError()
            return
        }
        if(user.password.length < 6) {
            view.showPasswordLenghtError()
            return
        }
        view.showProgressBar()
        model.createUser(user.email,user.password,object : RegistrationModel.CompleteCallback {
            override fun onComplete(task: Task<AuthResult>) {
                view.hideProgressBar()
                if(task.isSuccessful) {
                    view.showToast(task.toString())
                } else {

                    view.showToast(task.exception.toString())

                }

            }

        })

    }

    private fun isEmailValid(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

}

