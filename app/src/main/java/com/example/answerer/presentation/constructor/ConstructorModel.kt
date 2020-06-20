package com.example.answerer.presentation.constructor

import android.content.Intent
import android.util.Log
import com.example.answerer.data.Answer
import com.example.answerer.data.Question
import com.example.answerer.data.Solution
import com.example.answerer.data.Result
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ConstructorModel {

    private lateinit var db: DatabaseReference
    lateinit var solution: Solution
    val questions = ArrayList<Question>()
    val answers = ArrayList<Answer>()
    val results = ArrayList<Result>()
    fun initModel(){
        db = Firebase.database.reference
    }

    interface SolutionCompleteCallback {
        fun onComplete()
    }

    fun getSolutions(
        intent: Intent,
        callback: SolutionCompleteCallback
    ) {
        val categoryId: String = intent.getStringExtra("cat_id")
        val solutionId: String = intent.getStringExtra("sol_id")
        val ref = db.child("content").child(categoryId).child("solutions").child(solutionId)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                questions.addAll( getSolutionQuestions(snapshot.child("questions")))
                answers.addAll( getSolutionAnswers(snapshot.child("answers")))
                results.addAll( getSolutionResults(snapshot.child("results")))
                solution = Solution(
                    snapshot.key,
                    snapshot.child("title").value.toString(),
                    snapshot.child("avtor").value.toString(),
                    questions,
                    answers,
                    results
                )
                callback.onComplete()
            }


            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("Advice","The read failed: " + databaseError.message)
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

    private fun getSolutionResults(resultsSnapshot: DataSnapshot): ArrayList<com.example.answerer.data.Result> {
        val results = ArrayList<com.example.answerer.data.Result>()
        Log.d("Advice","Results")
        for (item in resultsSnapshot.children){

            val result = com.example.answerer.data.Result(
                Integer.parseInt(item.key.toString()),
                item.value.toString()
            )
            Log.d("Advice","Result: $result")
            results.add(result)
        }
        return results
    }


}