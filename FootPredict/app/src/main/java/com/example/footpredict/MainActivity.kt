package com.example.footpredict

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var newButton = findViewById<Button>(R.id.btnNew)
        newButton.setOnClickListener{
            val intent = Intent(this,LeaguesActivity::class.java)
            startActivity(intent)
        }
    }
}