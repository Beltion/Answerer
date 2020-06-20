package com.example.answerer.presentation.constructor

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.R
import com.example.answerer.business.adapters.AnswersRVAdapter
import com.example.answerer.business.adapters.ConstructorParentRVAdapter
import com.example.answerer.data.Answer
import com.example.answerer.data.ConstructorQuestion
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.constructor_activity.view.*

class ConstructorActivity :AppCompatActivity(),
    AnswersRVAdapter.OnAnswerClickListener,
    ConstructorParentRVAdapter.OnQuestionClickListener, ConstructorView {

    private lateinit var toolbar : MaterialToolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var rvParent : RecyclerView
    private lateinit var rvChild : RecyclerView
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
        rvParent = findViewById(R.id.rvConstructorParent)
        rvParent.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, true)

        presenter = ConstructorPresenter(this)
        presenter.onCreateView(intent,rvParent, this, this)

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
        TODO("Not yet implemented")
    }

    override fun onConstructorQuestionClick(question: ConstructorQuestion) {
        TODO("Not yet implemented")
    }

}