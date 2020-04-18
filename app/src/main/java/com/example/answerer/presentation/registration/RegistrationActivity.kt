package com.example.answerer.presentation.registration

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.answerer.R
import com.example.answerer.data.RegUser

class RegistrationActivity : AppCompatActivity(), RegistrationView {

    private lateinit var nameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var regBtn: Button
    private lateinit var presenter: RegistrationPresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        Log.d("RegistrationLog", "start Activity")

        init()

    }

    private fun init() {

        nameEt = findViewById(R.id.nameEtReg)
        emailEt = findViewById(R.id.emailEtReg)
        passEt = findViewById(R.id.passEtReg)
        regBtn = findViewById(R.id.regBtnReg)
        progressBar = findViewById(R.id.progress)

        presenter = RegistrationPresenter(this)
        presenter.model.initFAuth()

        regBtn.setOnClickListener{
            presenter.onButtonRegClick()
        }
    }

    override fun getRegUserData() : RegUser{
        val user = RegUser()
        user.email = emailEt.text.toString().trim()
        user.password = passEt.text.toString().trim()
        return user
    }

    override fun showEmailEmptyError(){ emailEt.error = "Введите Email"}
    override fun showPasswordEmptyError(){ passEt.error = "Введите Пароль"}
    override fun showPasswordLenghtError(){ passEt.error = "Пароль должен содержать не меньше 6 символов"}

    override fun showToast(regStatus: String) {
        Toast.makeText(this, "Регистрация:$regStatus", Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}

