package com.example.answerer.presentation.categories

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.business.CategoriesRVAdapter
import com.example.answerer.business.TitleRVAdapter
import com.example.answerer.data.Category
import com.example.answerer.data.Solution
import com.example.answerer.data.SolutionTitle
import com.example.answerer.presentation.advice.AdviceActivity

class CategoriesPresenter(_view: CategoriseView) {


    private val LOG_TAG = "Categories"
    private val view  = _view
    private  val model = CategoriesModel()
    lateinit var categories: ArrayList<Category>


    fun onCreateView(rv: RecyclerView, clickListener: CategoriesRVAdapter.OnCategoriesClickListener) {

        model.initModel()

        model.getCategories(object: CategoriesModel.CategoriesCompleteCallback{
            override fun onComplete(categories: ArrayList<Category>) {
                val rvAdapter =
                    CategoriesRVAdapter(
                        categories,
                        clickListener
                    )

                rv.adapter = rvAdapter

                view.hideProgressBar()
                view.showCardViewContainer()
            }
        })

    }

    fun onCategoryClick(category: String, rv: RecyclerView, clickListener: TitleRVAdapter.OnTitleClickListener) {
        view.showProgressBar()
        view.hideCardViewContainer()

        view.showToast("Идентификатор категории: $category")
        model.getSolutionInfo(category, object: CategoriesModel.SolutionsTitlesCompleteCallback{
            override fun onComplete(titles: ArrayList<SolutionTitle>) {
                val rvAdapter =
                    TitleRVAdapter(
                        titles,
                        clickListener
                    )
                rv.adapter = rvAdapter

                view.hideProgressBar()
                view.showCardViewContainer()
            }

        })
    }

    fun onSolutionClick(
        solutionId: String,
        context: Context
    ) {
        view.showToast("Идентификатор решения; $solutionId")
        model. getSolutions(solutionId, object : CategoriesModel.SolutionsCompleteCallback {
            override fun onComplete(solutions: ArrayList<Solution>) {
                val id = Integer.parseInt(solutionId)
                val solution: Solution = solutions[id]
                Log.d(LOG_TAG, "Open solution: $solution")
                toAdviceActivity(context)
            }

        })
    }

    private fun toAdviceActivity(context: Context){
        val intent = Intent(context, AdviceActivity::class.java)
        context.startActivity(intent)

    }


}