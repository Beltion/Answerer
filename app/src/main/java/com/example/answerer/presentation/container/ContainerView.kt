package com.example.answerer.presentation.container

import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.answerer.data.User

interface ContainerView {

    fun init()

    fun showToast(str: String)

    fun showProgressBar()
    fun hideProgressBar()

    fun hideContainer()
    fun showContainer()

    fun showNavDrawer()
    fun hideNavDrawer()

    fun changeFragment(fragment: Fragment)
}