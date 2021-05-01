package com.example.footpredict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footpredict.adapters.FixtureAdapter
import com.example.footpredict.adapters.SavedMatchesAdapter
import com.example.footpredict.database.DbManager

class SavedMatchesActivity : AppCompatActivity() {
    var dbManager = DbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_matches)
        dbManager.openDb()
        var matches = dbManager.getMatches()
        dbManager.closeDb()
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSavedMatches)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SavedMatchesAdapter(matches.reversed(), this)
    }

    fun onBack(view: View) {
        onBackPressed()
    }
}