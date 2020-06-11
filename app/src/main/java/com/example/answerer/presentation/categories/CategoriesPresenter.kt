package com.example.answerer.presentation.categories

import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.business.CategoriesRVAdapter
import com.example.answerer.data.Category

class CategoriesPresenter(_view: CategoriseView) {


    private val LOG_TAG = "Categories"
    private val view  = _view
    private  val model = CategoriesModel()
    lateinit var categories: ArrayList<Category>


    fun onCreateView(rv: RecyclerView?, clickListener: CategoriesRVAdapter.OnCategoriesClickListener) {

        model.initModel()

        model.getCategories(object: CategoriesModel.CategoriesCompleteCallback{
            override fun onComplete(categories: ArrayList<Category>) {
                val rvAdapter = CategoriesRVAdapter(categories, clickListener)
                rv?.let {
                    it.adapter = rvAdapter
                }
                view.hideProgressBar()
                view.showCardViewContainer()
            }
        })

    }


    fun onCategoryClick(category: Category) {
        view.showToast("Идентификатор категории: ${category.id}")
        model.getCategoriSolution()
    }


}