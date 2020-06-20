package com.example.answerer.business.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.R
import com.example.answerer.data.Answer
import com.example.answerer.data.ConstructorQuestion

class ConstructorParentRVAdapter(
    val questions: ArrayList<ConstructorQuestion>,
    val questionClickListener: OnQuestionClickListener,
    private val answerClickListener: AnswersRVAdapter.OnAnswerClickListener
) : RecyclerView.Adapter<ConstructorParentRVAdapter.ConstructorViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConstructorViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.constructor_card, parent, false)
        return ConstructorViewHolder(
            v
        )
    }

    override fun getItemCount() = questions.size

    override fun onBindViewHolder(
        holder: ConstructorViewHolder,
        position: Int
    ) {
        val question = questions[position]
        holder.question.text = question.question
        val childLayout = LinearLayoutManager(holder.rvAnswers.context,  RecyclerView.VERTICAL, false)
        childLayout.initialPrefetchItemCount = question.answers.count()
        Log.d("RVAdapter", "Constr ${question.answers.count()} : ${question.answers}")
        holder.rvAnswers.apply{
            layoutManager = childLayout
            adapter = AnswersRVAdapter(question.answers, answerClickListener )
            setRecycledViewPool(viewPool)
        }
    }

    interface OnQuestionClickListener {
        fun onConstructorQuestionClick(question: ConstructorQuestion)
    }

    class ConstructorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val question = itemView.findViewById<TextView>(R.id.questionConstructor)
        val rvAnswers = itemView.findViewById<RecyclerView>(R.id.rvConstructorChild)

        fun questionClick(){

        }
    }
}