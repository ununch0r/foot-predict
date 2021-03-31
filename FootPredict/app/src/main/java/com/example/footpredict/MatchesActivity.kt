package com.example.footpredict

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.footpredict.services.FixtureService
import com.example.footpredict.services.ServiceBuilder
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footpredict.adapters.FixtureAdapter
import com.example.footpredict.adapters.LeagueAdapter
import com.example.footpredict.data.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MatchesActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)

        setCurrentDate()
        setLeagueName()
        loadFixtures()
    }

    fun setLeagueName(){
        var leagueName = intent.getStringExtra(R.string.leagueName.toString())
        var tvLeagueHeader = findViewById<TextView>(R.id.tvLeagueHeader)
        tvLeagueHeader.text = leagueName
    }

    public fun onBack(view: View) {
        onBackPressed()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCurrentDate(){
        var tvDate = findViewById<TextView>(R.id.tvDateMatches)
        var currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek: String = sdf.format(d)
        tvDate.text = currentDate.toString() + ", " + dayOfTheWeek
    }

    private fun loadFixtures() {
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMatches)
        val destinationService  = ServiceBuilder.buildService(FixtureService::class.java)

        var fixtures = ArrayList<ApiResponse.Api.Fixture>()
        fixtures.add(
            ApiResponse.Api.Fixture(
            awayTeam = ApiResponse.Api.Fixture.AwayTeam(
                team_name = "Manchester U",
                team_id = 1,
                logo = ""
            ),
            homeTeam = ApiResponse.Api.Fixture.HomeTeam(
                    team_name = "Manchester C",
                    team_id = 1,
                    logo = ""
            ), event_date = ""
        ))

        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FixtureAdapter(fixtures, this)

    }
}