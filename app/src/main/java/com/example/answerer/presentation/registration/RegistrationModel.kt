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

    //  Создание данных для авторизации в приложении(встроенная аутентификация в Firebase через email)
    fun createUser(email:String, pass: String, createCallback: CompleteCreateCallback) {
        fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
           createCallback.onComplete(it, fAuth.currentUser?.uid)
        }
    }

    //  Сохранение данных в коллекцию users(необходимо для дальнейшей работы с профилем пользователя)
    fun saveUserData(user:User, id: String, createCallback: CompleteAddCallback) {
        database.child("users").child(id).setValue(user).addOnCompleteListener{
            createCallback.onComplete(it)
        }
    }

    //  Проверка на создание текущего пользователя(вызывается после его регистрации)
    fun isLogged() : Boolean {
        return fAuth.currentUser != null
    }
}

