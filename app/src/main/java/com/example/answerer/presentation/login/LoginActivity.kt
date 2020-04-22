package com.example.answerer.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.answerer.R
import com.example.answerer.data.User

class LoginActivity : AppCompatActivity(), LoginView{


    private lateinit var emailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var authBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var container: CardView

    private lateinit var presenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        Log.d("LoginLog", "start Activity")

    }

    override fun init() {
        
        emailEt = findViewById(R.id.emailEtAuth)
        passEt = findViewById(R.id.passEtAuth)
        authBtn = findViewById(R.id.authBtnAuth)
        progressBar = findViewById(R.id.progressAuth)
        container = findViewById(R.id.cvAuth)

        presenter = LoginPresenter(this)

        authBtn.setOnClickListener{
            presenter.onAuthBtnClick()
        }

    }

    override fun getRegUserData() : User {
        val user = User()
        user.email = emailEt.text.toString().trim()
        user.password = passEt.text.toString().trim()
        return user
    }

    override fun showEmailError(strId: Int) {
        emailEt.error = getString(strId)
    }

    override fun showPasswordError(strId: Int) {
        passEt.error = getString(strId)
    }

    override fun showToast(regStatus: String) {
        Toast.makeText(this, regStatus, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showCardViewContainer() {
        container.visibility = View.VISIBLE
    }

    override fun hideCardViewContainer() {
        container.visibility = View.GONE
    }

}