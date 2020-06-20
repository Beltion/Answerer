package com.example.answerer.presentation.constructor

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.business.adapters.AnswersRVAdapter
import com.example.answerer.business.adapters.ConstructorParentRVAdapter
import com.example.answerer.data.Answer
import com.example.answerer.data.ConstructorQuestion

class ConstructorPresenter(_view : ConstructorView) {

    private val LOG_TAG = "ConstructorActivity"
    private val view = _view
    private val model = ConstructorModel()

    fun onCreateView(intent: Intent,
                     rv: RecyclerView,
    questionClickListener: ConstructorParentRVAdapter.OnQuestionClickListener,
    answerClickListener: AnswersRVAdapter.OnAnswerClickListener) {

        model.initModel()

        model.getSolutions(intent, object : ConstructorModel.SolutionCompleteCallback{
            override fun onComplete() {
                Log.d(LOG_TAG, "Solution:${model.solution}")
                view.setToolbarTitle(model.solution.title)

                val adapter = ConstructorParentRVAdapter(
                    solutionToConstructorQuestion(),
                    questionClickListener,
                    answerClickListener
                )

                rv.adapter = adapter
            }

        })
    }

    private fun solutionToConstructorQuestion(): ArrayList<ConstructorQuestion> {
        val questions = ArrayList<ConstructorQuestion>()

        model.solution.questions.forEach {
            val answers = ArrayList<Answer>()
            it.count.forEach { item ->
                answers.add(model.solution.answers[Integer.parseInt(item)])
            }
            it.id?.let{id->
                val question = ConstructorQuestion(
                    id,
                    it.question,
                    answers
                )

                questions.add(question)
            }

        }
        Log.d(LOG_TAG, "ConstrQuestion:$questions")
        return questions

    }

}