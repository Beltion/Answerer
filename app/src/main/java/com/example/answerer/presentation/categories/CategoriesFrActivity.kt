package com.example.answerer.presentation.categories

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.answerer.R

class CategoriesFrActivity : Fragment(), CategoriseView {

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

        presenter.test(recyclerView)

    }

    override fun showToast(regStatus: String) {
        TODO("Not yet implemented")
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


}
