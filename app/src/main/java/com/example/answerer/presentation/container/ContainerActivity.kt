package com.example.answerer.presentation.container

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.answerer.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class ContainerActivity : ContainerView, AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: MaterialToolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var container: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_container)

        init()

    }



    override fun init() {
        progressBar = findViewById(R.id.progressContainer)
        container = findViewById(R.id.container)

        toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.categories)

        bottomNavView = findViewById(R.id.bottom_nav_view)
        bottomNavView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.bottom_nav_menu -> showToast("MENU")
                R.id.nav_create -> showToast("CRE")
                R.id.nav_categories -> showToast("CAT")
            }
        }

        drawerLayout = findViewById(R.id.drawer_layout)

    }

    override fun showToast(str: String) {
       Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun hideContainer() {
        container.visibility = View.GONE
    }

    override fun showContainer() {
        container.visibility = View.VISIBLE
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.nav_categories -> showToast("CAT")
                R.id.nav_create -> showToast("CRE")
                R.id.nav_error -> showToast("ER")
                R.id.nav_settings -> showToast("SET")
                R.id.nav_logout -> showToast("LOGOUT")
            }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}