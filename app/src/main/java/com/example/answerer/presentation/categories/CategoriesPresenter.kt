package com.example.answerer.presentation.categories

import androidx.recyclerview.widget.RecyclerView
import com.example.answerer.business.CategoriesRVAdapter
import com.example.answerer.data.Category
import java.util.logging.Handler
import kotlin.collections.ArrayList

class CategoriesPresenter(_view: CategoriseView) {


    private val LOG_TAG = "Categories"
    private val view  = _view
    private  val model = CategoriesModel()


    fun test(rv: RecyclerView) {
        val dataList = ArrayList<Category>()
        dataList.add(Category("Первая интересная категория", 22))
        dataList.add(Category("Вторая категория", 21))
        dataList.add(Category("Третья интересная и длинная категория", 7))

        val rvAdapter = CategoriesRVAdapter(dataList)
        rv.adapter = rvAdapter


        view.hideProgressBar()
        view.showCardViewContainer()
    }
}