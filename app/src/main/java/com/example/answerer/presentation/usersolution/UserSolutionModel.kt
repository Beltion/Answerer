package com.example.answerer.presentation.usersolution

import android.util.Log
import com.example.answerer.data.UserSolutionTitle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserSolutionModel {

    private lateinit var fAuth: FirebaseAuth
    private lateinit var db: DatabaseReference

    interface UserSolutionCompleteCallback {
        fun onComplete(solutions: ArrayList<UserSolutionTitle>?)
    }


    fun initModel(){
        fAuth = FirebaseAuth.getInstance()
        db = Firebase.database.reference
    }

    fun getUserSolutions(callbackCategories: UserSolutionCompleteCallback) {

        val ref = db.child("users_solution").child(fAuth.currentUser!!.uid )
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.d("UserSol","Solution: $snapshot")
                if (snapshot.value == null){
                    callbackCategories.onComplete(null)
                } else {
                    val solutionTitles = ArrayList<UserSolutionTitle>()

                    for (item in snapshot.children){
                        val solutionTitle = UserSolutionTitle(
                            item.child("cat_id").value.toString(),
                            item.child("sol_id").value.toString(),
                            item.child("solution_title").value.toString()
                        )
                        solutionTitles.add(solutionTitle)
                    }
                    Log.d("UserSol","Solutions: $solutionTitles")
                    callbackCategories.onComplete(solutionTitles)
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("UserSol","The read failed: " + databaseError.message)
            }
        })

    }

}