package com.example.answerer.presentation.container

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.answerer.R
import com.example.answerer.presentation.categories.CategoriesFragment
import com.example.answerer.presentation.usersolution.UserSolutionFragment
import com.example.answerer.presentation.login.LoginActivity
import java.lang.Exception

class ContainerPresenter(_view: ContainerView) {

    private val LOG_TAG = "Container"
    private val view  = _view
    private  val model = ContainerModel()
    private lateinit var ctx: Context

    fun onCreateView(context: Context) {
        ctx = context
        try {
            model.initFAuth()
            if(!model.isLogged()){
                toLoginActivity(ctx)
            }
        }catch (e: Exception){
            Log.e(LOG_TAG, e.toString())
        }
        val fr = CategoriesFragment()

        view.setSelectedBottomNavItem(R.id.bottom_nav_categories)
        view.setSelectedDrawerNavItem(R.id.nav_categories)

        view.changeFragment(fr)
        view.showContainer()
        view.hideProgressBar()
    }

    private fun toLoginActivity(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
        view.finishContainerActivity()
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
                fragment = CategoriesFragment()
            }

            R.id.bottom_nav_create ->{
                Log.d(LOG_TAG, "Create Bottom")
                view.setSelectedDrawerNavItem(R.id.nav_create)
                fragment = UserSolutionFragment()
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
                fragment = CategoriesFragment()
            }
            R.id.nav_create ->{
                Log.e(LOG_TAG, "Create Drawer")
                view.setSelectedBottomNavItem(R.id.bottom_nav_create)
                fragment = UserSolutionFragment()
            }
            R.id.nav_error ->
                Log.e(LOG_TAG, "Nav error")
            R.id.nav_settings ->
                Log.e(LOG_TAG, "Nav settings")
            R.id.nav_logout ->{
                Log.d(LOG_TAG, "Nav logout")
                model.logUot()
                toLoginActivity(ctx)

            }


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

//      Надо так делать синхроноость шавигации, так ошибка
//    хотя если делать тоже самое не в отдельной функции всё работает
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
