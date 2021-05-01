package com.example.footpredict

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi


class MainActivity : Activity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var newButton = findViewById<Button>(R.id.btnNew)
        var savedButton = findViewById<Button>(R.id.btnSaved)

        newButton.setOnClickListener{
            val intent = Intent(this,LeaguesActivity::class.java)
            startActivity(intent)
        }


        savedButton.setOnClickListener{
            val intent = Intent(this,SavedMatchesActivity::class.java)
            startActivity(intent)
        }
    }
}