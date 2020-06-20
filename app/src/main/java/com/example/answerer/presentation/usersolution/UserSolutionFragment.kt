package com.example.answerer.presentation.usersolution

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
import com.example.answerer.business.adapters.UserSolutionsRVAdapter
import com.example.answerer.data.UserSolutionTitle

class UserSolutionFragment : Fragment(), UserSolutionView, UserSolutionsRVAdapter.OnTitleClickListener {

    private lateinit var rootView: View
    private lateinit var container: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: UserSolutionPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.user_solution_fragment, container, false)
        init()
        return rootView
    }

    override fun init() {
        progressBar = rootView.findViewById(R.id.progress_user_solution)
        recyclerView = rootView.findViewById(R.id.user_solution_rv)
        recyclerView.layoutManager = LinearLayoutManager(rootView.context, RecyclerView.VERTICAL, false)
        container = rootView.findViewById(R.id.user_solution_container)

        presenter = UserSolutionPresenter(this)
        presenter.onCreateView(recyclerView, this)
    }

    override fun showToast(s: String) {
        Toast.makeText(this.context, s, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun hideViewContainer() {
        container.visibility = View.GONE
    }

    override fun showViewContainer() {
        container.visibility = View.VISIBLE
    }

    override fun onTitleItemClick(solutionTitle: UserSolutionTitle) {
        context?.let { presenter.onUserTitleItemClick(it, solutionTitle) }
    }

}
