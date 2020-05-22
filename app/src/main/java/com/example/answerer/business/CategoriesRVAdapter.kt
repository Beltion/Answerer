package com.example.answerer.business

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.R
import com.example.answerer.data.Category

class CategoriesRVAdapter(val categories: ArrayList<Category>) : RecyclerView.Adapter<CategoriesRVAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.categories_card, parent,false)
        return CategoriesViewHolder(v)
    }

    override fun getItemCount() = categories.size


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.name.text = categories[position].name
        holder.count.text = categories[position].count.toString()
    }

    class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name_cat_cv)
        val count = itemView.findViewById<TextView>(R.id.count_cat_cv)


    }
}