package com.example.footpredict

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footpredict.adapters.LeagueAdapter
import com.example.footpredict.models.League
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class LeaguesActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leagues)

        initializeRecyclerView()
        setCurrentDate()
    }

    fun fillArray(leagues: ArrayList<League>){
        leagues.add(League(2790, "English Premier League"))
        leagues.add(League(2755, "Bundesliga"))
        leagues.add(League(2857, "Seria A"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCurrentDate(){
        var tvDate = findViewById<TextView>(R.id.tvDate)
        var currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek: String = sdf.format(d)
        tvDate.text = currentDate.toString() + ", " + dayOfTheWeek
    }

    fun initializeRecyclerView(){
        var leagues = ArrayList<League>()
        fillArray(leagues)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LeagueAdapter(leagues, this)
    }

    public fun onBack(view: View) {
        onBackPressed()
    }
}