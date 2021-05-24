package com.example.school.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.school.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var accountName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name.text = accountName
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

}