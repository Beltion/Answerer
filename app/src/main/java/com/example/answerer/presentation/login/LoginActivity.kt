package com.example.answerer.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.answerer.R
import com.example.answerer.data.User

class LoginActivity : AppCompatActivity(), LoginView{


    private lateinit var emailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var authBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var noAccTv: TextView
    private lateinit var container: CardView

    private lateinit var presenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        Log.d("LoginLog", "start Activity")

        init()


    }

    override fun init() {
        presenter = LoginPresenter(this)
        presenter.onViewCreate(applicationContext)

        emailEt = findViewById(R.id.emailEtAuth)
        passEt = findViewById(R.id.passEtAuth)
        authBtn = findViewById(R.id.authBtnAuth)
        progressBar = findViewById(R.id.progressAuth)
        noAccTv = findViewById(R.id.authTextReg)
        container = findViewById(R.id.cvAuth)


        authBtn.setOnClickListener{
            presenter.onAuthBtnClick(applicationContext)
        }

        noAccTv.setOnClickListener {
            presenter.onTextCreateAccClick(applicationContext)
        }
    }

    override fun getLoginUserData() : User {
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

    override fun finishLoginActivity() {
        finish()
    }
}