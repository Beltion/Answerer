package com.example.answerer.presentation.registration

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.answerer.R
import com.example.answerer.data.User
import com.example.answerer.presentation.container.ContainerActivity
import com.example.answerer.presentation.login.LoginActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthUserCollisionException


class RegistrationPresenter(_view: RegistrationView) {

    private val LOG_TAG = "Registration"

    private val view: RegistrationView = _view
    private val model = RegistrationModel()

    fun onRegBtnClick(context: Context) {
        val user = view.getRegUserData()

        if(user.name.isEmpty()) {
            view.showNameError(R.string.field_must_be_not_null)
            return
        }
        if(user.name.length < 3) {
            view.showNameError(R.string.invalid_name)
            return
        }

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

        model.createUser(user.email,user.password,object : RegistrationModel.CompleteCreateCallback {
            override fun onComplete(task: Task<AuthResult>, id: String?) {
                if(task.isSuccessful) {
                    id?.let{
                        saveUserData(user, it, context)
                    }
                } else {
                    Log.e(LOG_TAG, task.exception.toString())
                     val errorString = when(task.exception){
                        is FirebaseAuthUserCollisionException ->
                            "Аккаунт с данным email уже зарегистрирован"
                        else ->
                            "Не удалось зарегистрировать аккаунт"
                    }
                    view.showToast(errorString)
                }
                view.hideProgressBar()
                view.showCardViewContainer()
            }

        })
    }


    private fun saveUserData(user: User, id: String, context: Context) {
        model.saveUserData(user, id, object  : RegistrationModel.CompleteAddCallback {
            override fun onComplete(task: Task<Void>) {
                if(task.isSuccessful){
                    view.showToast(task.toString())
                    onUserCreated(context)
                } else {
                    Log.e(LOG_TAG, task.exception.toString())
                    val errorString = when(task.exception){
                        else ->
                            "Не удалось сохранить данные на сервере, проверте соединение с интернет и" +
                                    "повторите попытку"
                    }
                    view.showToast(errorString)
                }
            }
        })
    }

    private fun onUserCreated(context: Context) {
        try {
            model.initFAuth()
            if(model.isLogged()){
                toContainerActivity(context)
            }
        }catch (e: Exception){
            Log.e(LOG_TAG, e.toString())
        }
    }

    private fun toContainerActivity(context: Context){
        val intent = Intent(context, ContainerActivity::class.java)
        context.startActivity(intent)
    }


    private fun isEmailValid(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()


    fun onTextHaveAccClick(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }
}

