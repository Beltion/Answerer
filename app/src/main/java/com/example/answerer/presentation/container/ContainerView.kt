package com.example.answerer.presentation.container

import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.answerer.data.User

interface ContainerView {

    fun init()

    fun showToast(str: String)

    //Управление анимацией загрузки
    fun showProgressBar()
    fun hideProgressBar()

    //Управляет отображением экрана для
    fun hideContainer()
    fun showContainer()

    //Управляет отображением NavigationDrawer
    fun showNavDrawer()
    fun hideNavDrawer()

    // Меняет фрагмент в контейнере ContainerActivity
    fun changeFragment(fragment: Fragment)

    fun setSelectedDrawerNavItem(id: Int)
    fun setSelectedBottomNavItem(id: Int)
    fun setToolbarTitle(s: String)
}