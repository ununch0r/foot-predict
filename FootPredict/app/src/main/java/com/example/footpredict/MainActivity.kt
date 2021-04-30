package com.example.footpredict

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.footpredict.database.DbManager
import com.example.footpredict.database.SavedMatchesDatabase

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var dbManager = DbManager(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var newButton = findViewById<Button>(R.id.btnNew)
        var savedButton = findViewById<Button>(R.id.btnSaved)

        newButton.setOnClickListener{
            val intent = Intent(this,LeaguesActivity::class.java)
            startActivity(intent)
        }

        savedButton.setOnClickListener{
            dbManager.openDb()
            var matches = dbManager.getMatches()
            var text = matches[0].firstTeamName +" " + matches[0].firstTeamScore + "-" + matches[0].secondTeamScore +" " + matches[0].secondTeamName

            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
    }
}