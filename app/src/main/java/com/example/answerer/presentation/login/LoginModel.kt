package com.example.answerer.presentation.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginModel {
    private lateinit var fAuth: FirebaseAuth

    interface CompleteCallback {
        fun onComplete(task: Task<AuthResult>)

    }



    fun initFAuth(){
        fAuth = FirebaseAuth.getInstance()
    }

    fun authorization(email:String, pass: String,callback: CompleteCallback) {
        fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
            callback.onComplete(it)
        }
    }

    fun isLogged() : Boolean {
        return fAuth.currentUser != null
    }

}