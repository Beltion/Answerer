package com.example.answerer.presentation.advice

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.R
import com.example.answerer.business.AnswersRVAdapter
import com.example.answerer.data.Answer
import com.google.android.material.appbar.MaterialToolbar

class AdviceActivity : AppCompatActivity(),AnswersRVAdapter.OnAnswerClickListener,  AdviceVew {

    private lateinit var rv: RecyclerView
    private lateinit var cvContainer: CardView
    private lateinit var progressBar: ProgressBar
    private lateinit var questionText: TextView
    private lateinit var toolbar: MaterialToolbar

    private lateinit var presenter: AdvicePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.advice)

        init()
    }

    override fun init() {
        toolbar = findViewById(R.id.toolbarAdvice)
        progressBar = findViewById(R.id.progressAdvice)
        questionText = findViewById(R.id.tvQuestion)
        rv = findViewById(R.id.rvAnswers)
        rv.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        cvContainer = findViewById(R.id.cvAdviceContainer)

        presenter = AdvicePresenter(this)
        presenter.onViewCreate(intent,rv,questionText, this)
    }

    override fun showToast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun hideCardViewContainer() {
        cvContainer.visibility = View.GONE
    }

    override fun showCardViewContainer() {
        cvContainer.visibility = View.VISIBLE
    }

    override fun setToolbarTitle(s: String){
        toolbar.title = s
    }

    override fun onAdviseAnswerClick(answer: Answer) {
        presenter.onAdviseAnswerClick(answer, rv, questionText, this)
    }
}