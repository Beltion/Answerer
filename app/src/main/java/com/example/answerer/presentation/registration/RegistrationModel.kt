package com.example.answerer.presentation.registration

import com.example.answerer.data.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegistrationModel {
    private lateinit var fAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    interface CompleteCreateCallback {
        fun onComplete(task: Task<AuthResult>, id: String?)
    }

    interface CompleteAddCallback {
        fun onComplete(task: Task<Void>)
    }

    fun initFAuth(){
        fAuth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
    }

    fun createUser(email:String, pass: String, createCallback: CompleteCreateCallback) {
        fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
           createCallback.onComplete(it, fAuth.currentUser?.uid)
        }
    }

    fun saveUserData(user:User, createCallback: CompleteAddCallback) {
        database.child("users").child(user.id).setValue(user).addOnCompleteListener{
            createCallback.onComplete(it)
        }
    }

}

