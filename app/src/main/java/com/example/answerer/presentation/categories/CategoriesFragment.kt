package com.example.answerer.presentation.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.answerer.R
import com.example.answerer.business.CategoriesRVAdapter
import com.example.answerer.business.TitleRVAdapter
import com.example.answerer.data.Category
import com.example.answerer.data.SolutionTitle

class CategoriesFragment : Fragment(),
    CategoriesRVAdapter.OnCategoriesClickListener,
    TitleRVAdapter.OnTitleClickListener,
    CategoriseView {

    lateinit var rootView: View
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    lateinit var presenter: CategoriesPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.categories_fragment, container, false)
        init()
        return rootView
    }

    override fun init() {
        recyclerView = rootView.findViewById(R.id.categories_rv)
        recyclerView.layoutManager = LinearLayoutManager(rootView.context, RecyclerView.VERTICAL, false)
        progressBar = rootView.findViewById(R.id.progress_categories)

        presenter = CategoriesPresenter(this)

        presenter.onCreateView(recyclerView, this)
    }

    override fun showToast(s: String) {
        Toast.makeText(rootView.context, s, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun hideCardViewContainer() {
        recyclerView.visibility = View.GONE
    }

    override fun showCardViewContainer() {
        recyclerView.visibility = View.VISIBLE
    }

    override fun onCatItemClick(category: Category) {
        presenter.onCategoryClick(category.id, recyclerView, this)
    }

    override fun onTitleItemClick(solutionTitle: SolutionTitle) {
        this.context?.let { presenter.onSolutionClick(solutionTitle.id, it) }
    }


}
