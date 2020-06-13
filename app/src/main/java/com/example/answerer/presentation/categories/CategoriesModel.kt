package com.example.answerer.presentation.categories

import android.util.Log
import com.example.answerer.data.*
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
        fun onComplete(solutions: ArrayList<Solution>)
    }

    interface SolutionsTitlesCompleteCallback {
        fun onComplete(titles: ArrayList<SolutionTitle>)
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
                            postSnapshot.key.toString(),
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

    fun getSolutionInfo(categoryId: String, callback: SolutionsTitlesCompleteCallback){
        val ref = db.child("content").child(categoryId).child("solutions")
        val titles: ArrayList<SolutionTitle> = ArrayList()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                titles.clear()
                Log.d("CATMODEL","Sol title: " + snapshot)
                for (item in snapshot.children){
                    Log.d("CATMODEL","Sol title: " + item.child("title").value)
                    Log.d("CATMODEL","Sol title id: " + item.key)
                    val title = SolutionTitle(
                        item.key.toString(),
                        item.child("title").value.toString()

                    )

                    titles.add(title)
                }

                callback.onComplete(titles)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("CATMODEL","The read failed: " + databaseError.message)
            }
        })
    }

    fun getSolutions(categoryId: String, callback: SolutionsCompleteCallback) {
        val ref = db.child("content").child(categoryId.toString()).child("solutions")
        val solutions: ArrayList<Solution> = ArrayList()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                solutions.clear()
                for (postSnapshot in snapshot.children) {

                    val questions = getSolutionQuestions(postSnapshot.child("questions"))
                    val answers = getSolutionAnswers(postSnapshot.child("answers"))
                    val results = getSolutionResults(postSnapshot.child("results"))
                    val solution = Solution(
                        postSnapshot.key,
                        postSnapshot.child("title").value.toString(),
                        postSnapshot.child("avtor").value.toString(),
                        questions,
                        answers,
                        results
                    )
                solutions.add(solution)
                }
                callback.onComplete(solutions)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("CATMODEL","The read failed: " + databaseError.message)
            }
        })
    }

    private fun getSolutionQuestions(questionsSnapshot: DataSnapshot): ArrayList<Question> {
        val questions = ArrayList<Question>()
        for (item in questionsSnapshot.children){
            val answerId = ArrayList<String>()
            val ansCount = item.child("answer_option").childrenCount
            for (i in  0 until ansCount){
                val s = item.child("answer_option").child("$i").value.toString()
                answerId.add(s)
            }

            val question = Question(
                item.key,
                item.child("content").value.toString(),
                answerId
            )
            questions.add(question)
        }
        return questions
    }

    private fun getSolutionAnswers(answersSnapshot: DataSnapshot): ArrayList<Answer> {
        val answers = ArrayList<Answer>()
        for (item in answersSnapshot.children){

            val answer = Answer(
                item.key.toString(),
                item.child("content").value.toString(),
                Integer.parseInt( item.child("next_question_id").value.toString())
            )
            answers.add(answer)
        }
        return answers
    }

    private fun getSolutionResults(resultsSnapshot: DataSnapshot): ArrayList<Result> {
        val results = ArrayList<Result>()
        for (item in resultsSnapshot.children){

            val result = Result(
                Integer.parseInt(item.key.toString()),
                item.child("content").value.toString()
            )
            results.add(result)
        }
        return results
    }


}


