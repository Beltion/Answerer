package com.example.answerer.presentation.registration

import android.util.Log
import com.example.answerer.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthUserCollisionException


class RegistrationPresenter(_view: RegistrationView) {

    private val LOG_TAG = "Registration"

    private val view: RegistrationView = _view
    private val model = RegistrationModel()

    fun onButtonRegClick() {
        val user = view.getRegUserData()

        if(user.email.isEmpty()) {
            view.showEmailError(R.string.field_must_be_not_null)
            return
        } else {
            if(!isEmailValid(user.email)){
                view.showEmailError(R.string.invalid_email)
                return
            }
        }

        if(user.password.isEmpty()) {

            view.showPasswordError(R.string.field_must_be_not_null)
            return
        }
        if(user.password.length < 6) {
            view.showPasswordError(R.string.invalid_pass)
            return
        }

        view.showProgressBar()
        view.hideCardViewContainer()

        model.initFAuth()
        model.createUser(user.email,user.password,object : RegistrationModel.CompleteCallback {
            override fun onComplete(task: Task<AuthResult>) {
                view.hideProgressBar()
                view.showCardViewContainer()
                if(task.isSuccessful) {
                    view.showToast(task.toString())
                } else {
                    Log.e(LOG_TAG, task.exception.toString())
                    val errorString: String = when(task.exception){
                        is FirebaseAuthUserCollisionException ->
                            "Аккаунт с данным email уже зарегистрирован"
                        else ->
                            "Не удалось зарегистрировать аккаунт"
                    }
                    view.showToast(errorString)
                }

            }

        })

    }

    private fun isEmailValid(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

}

