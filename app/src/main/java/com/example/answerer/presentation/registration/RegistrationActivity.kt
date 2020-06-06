package com.example.answerer.presentation.registration

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.answerer.R
import com.example.answerer.data.User

class RegistrationActivity : AppCompatActivity(), RegistrationView {

    private lateinit var nameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var regBtn: Button
    private lateinit var haveAccTv: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var container: CardView

    private lateinit var presenter: RegistrationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        Log.d("RegistrationLog", "start Activity")

        init()

    }

    override fun init() {

        nameEt = findViewById(R.id.nameEtReg)
        emailEt = findViewById(R.id.emailEtReg)
        passEt = findViewById(R.id.passEtReg)
        regBtn = findViewById(R.id.regBtnReg)
        haveAccTv = findViewById(R.id.authTvReg)
        progressBar = findViewById(R.id.progressReg)
        container = findViewById(R.id.cvReg)

        presenter = RegistrationPresenter(this)

        regBtn.setOnClickListener{
            presenter.onRegBtnClick(applicationContext)
        }

        haveAccTv.setOnClickListener {
            presenter.onTextHaveAccClick(applicationContext)
        }
    }

    override fun getRegUserData() : User{
        val user = User()
        user.email = emailEt.text.toString().trim()
        user.password = passEt.text.toString().trim()
        user.name = nameEt.text.toString().trim()
        return user
    }

    override fun showNameError(strId: Int) {
        nameEt.error = getString(strId)
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

    override fun finishRegistrationActivity(){
        finish()
    }
}

