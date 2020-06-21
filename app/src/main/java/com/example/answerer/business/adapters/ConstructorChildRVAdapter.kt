package com.example.answerer.business.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.R
import com.example.answerer.data.Answer

class ConstructorChildRVAdapter(
    val answers: ArrayList<Answer>,
    private val answerClickListener: OnAnswerClickListener
) : RecyclerView.Adapter<ConstructorChildRVAdapter.ConstructorAnswerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstructorAnswerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.constructor_answer_card, parent, false)
        return ConstructorAnswerViewHolder(
            v
        )
    }

    override fun getItemCount() = answers.size

    override fun onBindViewHolder(holder: ConstructorAnswerViewHolder, position: Int) {
        Log.d("RVAdapter", "Answer: ${answers[position].content}|${answers[position].nextQuestionId}")
        holder.answer.text = answers[position].content
        holder.answerStatus.text = answers[position].nextQuestionId?.let{
            if(it > 0){
                "Q"
            } else {
                "R"
            }
        } ?: "X"

        Log.d("RVAdapter", "Answer: ${holder.answer.text}|${holder.answerStatus.text}")


        holder.itemClick(answers[position], answerClickListener)
    }

    interface OnAnswerClickListener {
        fun onAdviseAnswerClick(answer: Answer)
    }

    class ConstructorAnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val answer = itemView.findViewById<TextView>(R.id.name_answer_constructor_cv)
        val answerStatus = itemView.findViewById<TextView>(R.id.status_answer_constructor_cv)
        fun itemClick(answer: Answer, clickListener: OnAnswerClickListener){
            itemView.setOnClickListener {
                clickListener.onAdviseAnswerClick(answer)
            }
        }
    }
}