package com.example.answerer.business.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.R
import com.example.answerer.data.ConstructorQuestion

class ConstructorParentRVAdapter(
    val questions: ArrayList<ConstructorQuestion>,
    val questionClickListener: OnQuestionClickListener,
    private val answerClickListener: ConstructorChildRVAdapter.OnAnswerClickListener
) : RecyclerView.Adapter<ConstructorParentRVAdapter.ConstructorQuestionViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConstructorQuestionViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.constructor_card, parent, false)
        return ConstructorQuestionViewHolder(
            v
        )
    }

    override fun getItemCount() = questions.size

    override fun onBindViewHolder(
        holder: ConstructorQuestionViewHolder,
        position: Int
    ) {
        val question = questions[position]
        holder.question.text = question.question
        val childLayout = LinearLayoutManager(holder.rvAnswers.context,  RecyclerView.VERTICAL, false)
        childLayout.initialPrefetchItemCount = question.answers.count()
        Log.d("RVAdapter", "Constr question: ${question.question}")
        holder.rvAnswers.apply{
            layoutManager = childLayout
            adapter = ConstructorChildRVAdapter(question.answers, answerClickListener )
            setRecycledViewPool(viewPool)
        }
        holder.itemClick(question, questionClickListener)
    }

    interface OnQuestionClickListener {
        fun onConstructorQuestionClick(question: ConstructorQuestion)
    }


    class ConstructorQuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val question = itemView.findViewById<TextView>(R.id.questionConstructor)
        val rvAnswers = itemView.findViewById<RecyclerView>(R.id.rvConstructorChild)

        fun itemClick(question: ConstructorQuestion, clickListener: OnQuestionClickListener){
            itemView.setOnClickListener {
                clickListener.onConstructorQuestionClick(question)
            }
        }
    }


}