package com.example.answerer.presentation.categories

import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.business.CategoriesRVAdapter
import com.example.answerer.data.Category

class CategoriesPresenter(_view: CategoriseView) {


    private val LOG_TAG = "Categories"
    private val view  = _view
    private  val model = CategoriesModel()
    lateinit var categories: ArrayList<Category>


    fun onCreateView(rv: RecyclerView?) {

        model.initModel()

        model.getCategories(object: CategoriesModel.CompleteCallback{
            override fun onComplete(categories: ArrayList<Category>) {
                val rvAdapter = CategoriesRVAdapter(categories)
                rv?.let {
                    it.adapter = rvAdapter
                }

                view.hideProgressBar()
                view.showCardViewContainer()
            }
        })

        }
}