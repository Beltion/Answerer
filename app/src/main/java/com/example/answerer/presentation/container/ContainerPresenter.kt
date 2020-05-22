package com.example.answerer.presentation.container

import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.answerer.R
import com.example.answerer.presentation.categories.CategoriesFrActivity
import com.example.answerer.presentation.creater.CreaterFrActivity
import java.lang.Exception

class ContainerPresenter(_view: ContainerView) {

    private val LOG_TAG = "Container"
    private val view  = _view
    private  val model = ContainerModel()

    fun onCreateView() {
        val fr = CategoriesFrActivity()

        view.setSelectedBottomNavItem(R.id.bottom_nav_categories)
        view.setSelectedDrawerNavItem(R.id.nav_categories)

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
                view.setSelectedDrawerNavItem(R.id.nav_categories)
                view.setSelectedDrawerNavItem(R.id.nav_categories)
                fragment = CategoriesFrActivity()
            }

            R.id.bottom_nav_create ->{
                Log.d(LOG_TAG, "Create Bottom")
                view.setSelectedDrawerNavItem(R.id.nav_create)
                fragment = CreaterFrActivity()
            }

        }

        startFragmentChange(fragment)
    }

    fun onNavDrawerItemClick(item: MenuItem) {
        var fragment: Fragment? = null
        when(item.itemId){
            R.id.nav_categories ->{
                Log.d(LOG_TAG, "Categories Drawer")
                view.setSelectedBottomNavItem(R.id.bottom_nav_categories)
                fragment = CategoriesFrActivity()
            }
            R.id.nav_create ->{
                Log.e(LOG_TAG, "Create Drawer")
                view.setSelectedBottomNavItem(R.id.bottom_nav_create)
                fragment = CreaterFrActivity()
            }
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
//                synchronizedNavButton(it)
            }?: Log.e(LOG_TAG,"New Fragment is NULL and don't changed")

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

//    private fun synchronizedNavButton(fragment: Fragment) {
//        when(fragment){
//            is CategoriesFrActivity -> {
//                view.setSelectedBottomNavItem(R.id.bottom_nav_categories)
//                view.setSelectedDrawerNavItem(R.id.nav_categories)
//            }
//            is CreaterFrActivity -> {
//                view.setSelectedBottomNavItem(R.id.bottom_nav_create)
//                view.setSelectedDrawerNavItem(R.id.nav_create)
//            }
//            else -> view.showToast("Другое Activity")
//        }
//    }
}
