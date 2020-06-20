package com.example.answerer.business.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.R
import com.example.answerer.data.SolutionTitle

class TitleRVAdapter(val titles: ArrayList<SolutionTitle>,
                     private val titleClickListener: OnTitleClickListener
) : RecyclerView.Adapter<TitleRVAdapter.SolutionTitleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolutionTitleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.solution_titles_card, parent,false)
        return SolutionTitleViewHolder(
            v
        )
    }

    override fun getItemCount() = titles.size


    override fun onBindViewHolder(holder: SolutionTitleViewHolder, position: Int) {
        holder.title.text = titles[position].title

        holder.itemClick(titles[position], titleClickListener)
    }

    interface OnTitleClickListener {
        fun onTitleItemClick(solutionTitle: SolutionTitle)
    }

    class SolutionTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title_titles_cv)


        fun itemClick(solutionTitle: SolutionTitle, clickListener: OnTitleClickListener){
            itemView.setOnClickListener {
                clickListener.onTitleItemClick(solutionTitle)
            }
        }
    }
}