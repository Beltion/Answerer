package com.example.answerer.presentation.container

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class ContainerModel {
    private lateinit var fAuth: FirebaseAuth

    interface CompleteCallback {
        fun onComplete(task: Task<AuthResult>)

    }

    fun initFAuth(){
        fAuth = FirebaseAuth.getInstance()
    }

    fun logUot(){
        fAuth.signOut()
    }

    fun isLogged() : Boolean {
        return fAuth.currentUser != null
    }
}