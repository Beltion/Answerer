package com.example.answerer.presentation.container

import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.answerer.R
import com.example.answerer.presentation.categories.CategoriesFrActivity
import java.lang.Exception

class ContainerPresenter(_view: ContainerView) {

    private val LOG_TAG = "Container"
    private val view  = _view
    private  val model = ContainerModel()

    fun onCreateView() {
        val fr = CategoriesFrActivity()
        view.changeFragment(fr)
        view.showContainer()
        view.hideProgressBar()
    }

    fun onBottomItemClick(item: MenuItem){
        var fragment: Fragment? = null
        when(item.itemId){
            R.id.bottom_nav_menu ->{
                Log.d(LOG_TAG, "Menu Bottom")
                view.showNavDrawer()
            }

            R.id.bottom_nav_categories -> {
                Log.d(LOG_TAG, "Categories Bottom")
                fragment = CategoriesFrActivity()
            }

            R.id.bottom_nav_create ->
                Log.e(LOG_TAG, "create")


        }

        startFragmentChange(fragment)
    }

    fun onNavDrawerItemClick(item: MenuItem) {
        var fragment: Fragment? = null
        Log.d(LOG_TAG, item.itemId.toString())
        when(item.itemId){
            R.id.nav_categories ->{
                Log.d(LOG_TAG, "Categories Drawer")
                fragment = CategoriesFrActivity()
            }
            R.id.nav_create ->
                Log.e(LOG_TAG, "create")
            R.id.nav_error ->
                Log.e(LOG_TAG, "Nav error")
            R.id.nav_settings ->
                Log.e(LOG_TAG, "Nav settings")
            R.id.nav_logout ->
                Log.e(LOG_TAG, "Nav logout")
        }

        view.hideNavDrawer()

        startFragmentChange(fragment)
    }

    private fun startFragmentChange(fragment: Fragment?){
        try {
            fragment?.let {
                view.changeFragment(it)
            }?: Log.e(LOG_TAG,"Fragment don't changed")
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}
