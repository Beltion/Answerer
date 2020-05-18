package com.example.answerer.presentation.container

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.answerer.R

class ContainerActivity : ConttainerView, AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_container)
    }

}