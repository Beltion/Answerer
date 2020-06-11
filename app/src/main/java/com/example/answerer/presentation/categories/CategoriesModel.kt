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

    interface CategoriesCompleteCallback {
        fun onComplete(categories: ArrayList<Category>)
    }

    interface SolutionsCompleteCallback {
        fun onComplete(categories: ArrayList<Category>)
    }


    fun initModel(){
        fAuth = FirebaseAuth.getInstance()
        db = Firebase.database.reference
    }

    fun getCategories(callbackCategories: CategoriesCompleteCallback) {

        val ref = db.child("categories")
        val categories: ArrayList<Category> = ArrayList()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                categories.clear()
                for (postSnapshot in snapshot.children) {
                    val count = Integer.parseInt(postSnapshot.child("count").value.toString())
                    if(count > 0){
                        val cat = Category(
                            postSnapshot.key,
                            postSnapshot.child("title").value.toString(),
                            count
                        )
                        categories.add(cat)
                    }

                }
                callbackCategories.onComplete(categories)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("CATMODEL","The read failed: " + databaseError.message)
            }
        })

    }

    fun getCategoriSolution() {
        TODO("Not yet implemented")
    }


}


