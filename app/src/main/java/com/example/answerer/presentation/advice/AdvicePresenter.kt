package com.example.answerer.presentation.advice

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.business.AnswersRVAdapter
import com.example.answerer.data.Answer
import kotlin.math.absoluteValue

class AdvicePresenter(_view: AdviceVew) {

    private val LOG_TAG = "Advice"
    private val view = _view
    private val model = AdviceModel()

    fun onViewCreate(
        intent: Intent,
        rv: RecyclerView,
        questionText: TextView,
        clickListener: AnswersRVAdapter.OnAnswerClickListener
    ){

        model.initModel()

        model.getSolutions(intent,object : AdviceModel.SolutionCompleteCallback{
            override fun onComplete() {

                val answers = ArrayList<Answer>()
                questionText.text = model.solution.questions[0].question
                model.solution.questions[0].count.forEach {
                    answers.add(model.solution.answers[Integer.parseInt(it)])
                }
                Log.d(LOG_TAG, "Answers: $answers")
               val rvAdapter =
                   AnswersRVAdapter(
                       answers,
                       clickListener
                   )
                rv.adapter = rvAdapter

                view.setToolbarTitle(model.solution.title)
                view.hideProgressBar()
                view.showCardViewContainer()
            }

        })

    }

    fun onAdviseAnswerClick(
        answer: Answer,
        rv: RecyclerView,
        questionText: TextView,
        clickListener: AnswersRVAdapter.OnAnswerClickListener
    ) {
        view.showProgressBar()
        view.hideCardViewContainer()
            answer.nextQuestionId?.let {
                if(it > 0){
                    Log.d(LOG_TAG, "Questionid: $it")
                    questionText.text = model.solution.questions[it].question
                    val answers = ArrayList<Answer>()

                    model.solution.questions[it].count.forEach {item ->
                        answers.add(model.solution.answers[Integer.parseInt(item)])
                    }

                    val rvAdapter =
                        AnswersRVAdapter(
                            answers,
                            clickListener
                        )
                    rv.swapAdapter(rvAdapter,false)
                } else {
                    rv.visibility = View.GONE
                    questionText.text = getResultFromId(it)
                }
            }
        view.hideProgressBar()
        view.showCardViewContainer()
    }

    private fun getResultFromId(id: Int): String {
        var s = ""
        model.solution.results.forEach {
            if(it.id == id){
                s = it.content
            }
        }

        return s
    }
}