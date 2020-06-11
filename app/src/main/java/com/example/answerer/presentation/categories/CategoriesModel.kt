package com.example.answerer.presentation.categories

import android.util.Log
import com.example.answerer.data.Category
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class CategoriesModel {

    private lateinit var fAuth: FirebaseAuth
    private lateinit var db: DatabaseReference

    interface CompleteCallback {
        fun onComplete(categories: ArrayList<Category>)

    }


    fun initModel(){
        fAuth = FirebaseAuth.getInstance()
        db = Firebase.database.reference
    }

    fun getCategories(callback: CompleteCallback) {

        val ref = db.child("categories")
        val categories: ArrayList<Category> = ArrayList()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                categories.clear()
                for (postSnapshot in snapshot.children) {

                    Log.d("CATMODEL","Key: " + postSnapshot.key)
                    val cat = Category(
                        postSnapshot.key,
                        postSnapshot.child("title").value.toString(),
                        postSnapshot.child("count").value.toString()

                    )
                    categories.add(cat)
                }
                callback.onComplete(categories)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("CATMODEL","The read failed: " + databaseError.message)
            }
        })

    }


}


