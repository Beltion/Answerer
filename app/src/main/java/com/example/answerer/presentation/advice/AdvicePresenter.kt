package com.example.answerer.presentation.advice

import android.content.Intent
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.business.AnswersRVAdapter
import com.example.answerer.data.Answer

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
                }
            }
        view.hideProgressBar()
        view.showCardViewContainer()
    }
}