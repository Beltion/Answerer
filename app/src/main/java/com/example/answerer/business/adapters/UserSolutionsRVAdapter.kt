package com.example.answerer.business.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.R
import com.example.answerer.data.UserSolutionTitle

class UserSolutionsRVAdapter (val titles: ArrayList<UserSolutionTitle>,
                              private val titleClickListener: OnTitleClickListener
) : RecyclerView.Adapter<UserSolutionsRVAdapter.UserSolutionTitleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSolutionTitleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.solution_titles_card, parent,false)
        return UserSolutionTitleViewHolder(
            v
        )
    }

    override fun getItemCount() = titles.size


    override fun onBindViewHolder(holder: UserSolutionTitleViewHolder, position: Int) {
        holder.title.text = titles[position].title

        holder.itemClick(titles[position], titleClickListener)
    }

    interface OnTitleClickListener {
        fun onTitleItemClick(solutionTitle: UserSolutionTitle)
    }

    class UserSolutionTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title_titles_cv)


        fun itemClick(solutionTitle: UserSolutionTitle, clickListener: OnTitleClickListener){
            itemView.setOnClickListener {
                clickListener.onTitleItemClick(solutionTitle)
            }
        }
    }
}