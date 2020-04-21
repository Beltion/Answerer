package com.example.answerer.presentation.registration

import android.content.ContentValues
import android.os.AsyncTask
import com.example.answerer.data.RegUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class RegistrationModel {
    private lateinit var fAuth: FirebaseAuth

    interface CompleteCallback {
        fun onComplete(task: Task<AuthResult>)
    }

    fun initFAuth(){
        fAuth = FirebaseAuth.getInstance()
    }

    fun createUser(email:String, pass: String,callback: CompleteCallback) {
        fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
           callback.onComplete(it)
        }
    }

}

