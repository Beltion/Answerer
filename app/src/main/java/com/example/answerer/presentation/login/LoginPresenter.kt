package com.example.answerer.presentation.login

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.answerer.R
import com.example.answerer.presentation.container.ContainerActivity
import com.example.answerer.presentation.registration.RegistrationActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException


class LoginPresenter(_view: LoginView) {


    private val LOG_TAG = "Login"

    private val view = _view
    private val model = LoginModel()

    fun onViewCreate(context: Context) {
        try {
            model.initModel()
            if(model.isLogged()){
               toContainerActivity(context)
            }
        }catch (e: Exception){
            Log.e(LOG_TAG, e.toString())
        }

    }

    fun onAuthBtnClick(context: Context) {

        val user = view.getLoginUserData()

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


        model.authorization(user.email,user.password,object : LoginModel.CompleteCallback {
            override fun onComplete(task: Task<AuthResult>) {
                view.hideProgressBar()
                view.showCardViewContainer()
                if(task.isSuccessful) {
                    toContainerActivity(context)
                } else {
                    Log.e(LOG_TAG, task.exception.toString())
                    val errorString: String = when(task.exception){
                        is FirebaseAuthInvalidCredentialsException ->
                            "Email или пароль введены не верно"
                        is FirebaseAuthInvalidUserException ->
                            "Аккаунт с данным email не зарегистрирован"
                        else ->
                            "Не удалось пройти авторизацию, проверьте соединение с интернет и попробуйте ещё раз"
                    }
                    view.showToast(errorString)
                }

            }

        })

    }

    private fun toContainerActivity(context: Context){
        val intent = Intent(context, ContainerActivity::class.java)
        context.startActivity(intent)
        view.finishLoginActivity()
    }

    private fun isEmailValid(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun onTextCreateAccClick(context: Context) {
        val intent = Intent(context, RegistrationActivity::class.java)
        context.startActivity(intent)
        view.finishLoginActivity()
    }
}