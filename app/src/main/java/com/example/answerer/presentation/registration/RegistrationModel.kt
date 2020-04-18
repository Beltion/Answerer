package com.example.answerer.presentation.registration

import android.content.ContentValues
import android.os.AsyncTask
import com.example.answerer.data.RegUser
import com.google.firebase.auth.FirebaseAuth


class RegistrationModel {
    private lateinit var fAuth: FirebaseAuth

    interface CompleteCallback {
        fun onComplete(regStatus: Boolean)
    }

    fun initFAuth(){
        fAuth = FirebaseAuth.getInstance()
    }

    fun createUser(email:String, pass: String,callback: CompleteCallback) {
        fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
           callback.onComplete(it.isSuccessful)
        }
    }

}

