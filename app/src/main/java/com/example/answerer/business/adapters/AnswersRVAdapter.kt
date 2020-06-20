package com.example.answerer.business.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.R
import com.example.answerer.data.Answer

class AnswersRVAdapter(
    val answers: ArrayList<Answer>,
    private val answerClickListener: OnAnswerClickListener
) : RecyclerView.Adapter<AnswersRVAdapter.AnswerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.advice_card, parent, false)
        return AnswerViewHolder(
            v
        )
    }

    override fun getItemCount() = answers.size

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.answer.text = answers[position].content
        holder.itemClick(answers[position], answerClickListener)
    }

    interface OnAnswerClickListener {
        fun onAdviseAnswerClick(answer: Answer)
    }

    class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val answer = itemView.findViewById<TextView>(R.id.name_answer_cv)

        fun itemClick(answer: Answer, clickListener: OnAnswerClickListener){
             itemView.setOnClickListener {
                 clickListener.onAdviseAnswerClick(answer)
             }
        }
    }
}