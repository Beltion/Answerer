package com.example.answerer.presentation.container

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.answerer.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


class ContainerActivity : ContainerView, AppCompatActivity(){

    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var drawerNavView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: MaterialToolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var container: ConstraintLayout

    private lateinit var presenter: ContainerPresenter

    private val LOG_TAG = "ContainerActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_container)

        init()

    }

    override fun init() {
        progressBar = findViewById(R.id.progress_container)
        toolbar = findViewById(R.id.toolbar)
        container = findViewById(R.id.container)
        drawerLayout = findViewById(R.id.drawer_layout)
        bottomNavView = findViewById(R.id.bottom_nav_view)
        drawerNavView = findViewById(R.id.drawer_nav_view)

        presenter = ContainerPresenter(this)
        setToolbarTitle(getString(R.string.categories))


        drawerNavView.setNavigationItemSelectedListener {
            presenter.onNavDrawerItemClick(it)
            true
        }

        bottomNavView.setOnNavigationItemSelectedListener {
            presenter.onBottomItemClick(it)
            true
        }


        presenter.onCreateView(applicationContext)
    }

    override fun setToolbarTitle(s: String) {
        toolbar.title = s
    }

    override fun showToast(str: String) {
       Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun hideContainer() {
        container.visibility = View.GONE
    }

    override fun showContainer() {
        container.visibility = View.VISIBLE
    }

    override fun showNavDrawer(){
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun hideNavDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun changeFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container,fragment)
        fragmentTransaction.commit()
    }

    override fun setSelectedDrawerNavItem(id: Int) {
        drawerNavView.setCheckedItem(id)
    }

    override fun setSelectedBottomNavItem(id: Int){
        bottomNavView.selectedItemId = id
    }



}