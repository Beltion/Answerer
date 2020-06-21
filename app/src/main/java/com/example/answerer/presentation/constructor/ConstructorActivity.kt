package com.example.answerer.presentation.constructor

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.R
import com.example.answerer.business.adapters.AnswersRVAdapter
import com.example.answerer.business.adapters.ConstructorChildRVAdapter
import com.example.answerer.business.adapters.ConstructorParentRVAdapter
import com.example.answerer.business.adapters.ResultRVAdapter
import com.example.answerer.data.Answer
import com.example.answerer.data.ConstructorQuestion
import com.example.answerer.data.Result
import com.google.android.material.appbar.MaterialToolbar

class ConstructorActivity :AppCompatActivity(),
    ConstructorChildRVAdapter.OnAnswerClickListener,
    ConstructorParentRVAdapter.OnQuestionClickListener,
    ConstructorView {

    private lateinit var toolbar : MaterialToolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var rvQuestion : RecyclerView
    private lateinit var presenter: ConstructorPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.constructor_activity)
        init()
    }

    override fun init() {
        toolbar = findViewById(R.id.toolbarConstructor)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        progressBar = findViewById(R.id.progressConstructor)

        rvQuestion = findViewById(R.id.rvConstructorParent)
        rvQuestion.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, true)

        rvQuestion.isNestedScrollingEnabled = false
        presenter = ConstructorPresenter(this)
        presenter.onCreateView(intent,
            rvQuestion,
            this,
            this
        )

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun showToast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        TODO("Not yet implemented")
    }

    override fun hideProgressBar() {
        TODO("Not yet implemented")
    }

    override fun hideCardViewContainer() {
        TODO("Not yet implemented")
    }

    override fun showCardViewContainer() {
        TODO("Not yet implemented")
    }

    override fun setToolbarTitle(s: String) {
        toolbar.title = s
    }

    override fun onAdviseAnswerClick(answer: Answer) {
        showToast(answer.content)
        presenter.onAnswerClick(rvQuestion,answer.nextQuestionId)
    }

    override fun onConstructorQuestionClick(question: ConstructorQuestion) {
        showToast(question.question)
    }



}