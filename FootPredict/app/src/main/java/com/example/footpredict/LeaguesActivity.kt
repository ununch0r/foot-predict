package com.example.footpredict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footpredict.adapters.LeagueAdapter
import com.example.footpredict.models.League

class LeaguesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leagues)

        var leagues = ArrayList<League>()
        leagues.add(League(1,"English Premier League"))
        leagues.add(League(2,"Bundesliga"))
        leagues.add(League(3,"Seria A"))

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LeagueAdapter(leagues,this)
    }
}