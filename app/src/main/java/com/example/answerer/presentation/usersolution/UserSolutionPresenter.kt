package com.example.answerer.presentation.usersolution

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.business.adapters.UserSolutionsRVAdapter
import com.example.answerer.data.UserSolutionTitle
import com.example.answerer.presentation.constructor.ConstructorActivity

class UserSolutionPresenter(_view: UserSolutionView) {

    private val LOG_TAG = "UserSolution"
    private val view = _view
    private val model = UserSolutionModel()

    fun onCreateView(rv: RecyclerView, clickListener: UserSolutionsRVAdapter.OnTitleClickListener) {
        model.initModel()
        model.getUserSolutions(object : UserSolutionModel.UserSolutionCompleteCallback{
            override fun onComplete(solutions: ArrayList<UserSolutionTitle>?) {
                solutions?.let{
                    Log.d(LOG_TAG, "User solution in presenter: $solutions")
                    val rvAdapter = UserSolutionsRVAdapter(
                            it,
                            clickListener
                        )
                    rv.adapter = rvAdapter
                } ?: view.showToast("У пользователя нет собственных решений")
                view.hideProgressBar()
                view.showViewContainer()
            }

        })
    }

    fun onUserTitleItemClick(context: Context, solutionTitle: UserSolutionTitle) {
        Log.d(LOG_TAG, "User solution id cat/sol:${solutionTitle.catId}/${solutionTitle.solId}")
        toConstructorActivity(context, solutionTitle.catId,  solutionTitle.solId)
    }

    private fun toConstructorActivity(context: Context, catId: String, solId: String){
        val intent = Intent(context, ConstructorActivity::class.java)
        intent.putExtra("cat_id", catId)
        intent.putExtra("sol_id", solId)
        context.startActivity(intent)
    }
}