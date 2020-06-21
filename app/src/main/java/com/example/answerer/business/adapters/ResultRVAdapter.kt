package com.example.answerer.business.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.R
import com.example.answerer.data.Result

class ResultRVAdapter (
    val res: ArrayList<Result>,
    private val answerClickListener: OnResultClickListener
) : RecyclerView.Adapter<ResultRVAdapter.ConstructorResultViewHolder>() {

    var results = ArrayList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstructorResultViewHolder {
        results = res
        val v = LayoutInflater.from(parent.context).inflate(R.layout.advice_card, parent, false)
        return ConstructorResultViewHolder(
            v
        )
    }

    override fun getItemCount() = results.size


    override fun onBindViewHolder(holder: ConstructorResultViewHolder, position: Int) {
        Log.d("RVAdapter", "Result: ${results[position].content}")
        holder.result.text = results[position].content
        holder.itemClick(results[position], answerClickListener)
    }

    interface OnResultClickListener {
        fun onConstructorResultClick(result: Result)
    }


    class ConstructorResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val result = itemView.findViewById<TextView>(R.id.name_answer_cv)

        fun itemClick(result: Result, clickListener: OnResultClickListener){
            itemView.setOnClickListener {
                clickListener.onConstructorResultClick(result)
            }
        }
    }
}