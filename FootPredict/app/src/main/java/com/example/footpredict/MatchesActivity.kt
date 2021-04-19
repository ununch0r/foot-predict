package com.example.footpredict

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.footpredict.services.FixtureService
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footpredict.adapters.FixtureAdapter
import com.example.footpredict.data.ApiResponse
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class MatchesActivity : AppCompatActivity() {
    var leagueId : Int = 0
    lateinit var tvDate : TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)

        setCurrentDate()
        setLeagueProperties()
        fetchJson(leagueId)
        //fetchMock()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchMock(){
        var mockData : List<ApiResponse.Api.Fixture> = listOf(
                ApiResponse.Api.Fixture(
                        1,
                        ApiResponse.Api.Fixture.AwayTeam("test",1,"Machecster United"),
                        "2020-01-19T16:30:00+00:00",
                        ApiResponse.Api.Fixture.HomeTeam("test",1,"Machecster City")),
                ApiResponse.Api.Fixture(
                    2,
                        ApiResponse.Api.Fixture.AwayTeam("test",1,"Sheffield United"),
                        "2020-01-19T16:30:00+00:00",
                        ApiResponse.Api.Fixture.HomeTeam("test",1,"Leicester City")
                ),
                ApiResponse.Api.Fixture(
                    3,
                        ApiResponse.Api.Fixture.AwayTeam("test",1,"Sheffield United"),
                        "2020-01-19T16:30:00+00:00",
                        ApiResponse.Api.Fixture.HomeTeam("test",1,"Leicester City")
                ),
                ApiResponse.Api.Fixture(
                    4,
                        ApiResponse.Api.Fixture.AwayTeam("test",1,"Sheffield United"),
                        "2020-01-19T16:30:00+00:00",
                        ApiResponse.Api.Fixture.HomeTeam("test",1,"Leicester City")
                ),
                ApiResponse.Api.Fixture(
                    5,
                        ApiResponse.Api.Fixture.AwayTeam("test",1,"Sheffield United"),
                        "2020-01-19T16:30:00+00:00",
                        ApiResponse.Api.Fixture.HomeTeam("test",1,"Leicester City")
                ),
                ApiResponse.Api.Fixture(
                    6,
                        ApiResponse.Api.Fixture.AwayTeam("test",1,"Sheffield United"),
                        "2020-01-19T16:30:00+00:00",
                        ApiResponse.Api.Fixture.HomeTeam("test",1,"Leicester City")
                ),
                ApiResponse.Api.Fixture(
                    7,
                        ApiResponse.Api.Fixture.AwayTeam("test",1,"Sheffield United"),
                        "2020-01-19T16:30:00+00:00",
                        ApiResponse.Api.Fixture.HomeTeam("test",1,"Leicester City")
                ),
                ApiResponse.Api.Fixture(
                    8,
                        ApiResponse.Api.Fixture.AwayTeam("test",1,"Sheffield United"),
                        "2020-01-19T16:30:00+00:00",
                        ApiResponse.Api.Fixture.HomeTeam("test",1,"Leicester City")
                ),
                ApiResponse.Api.Fixture(
                    9,
                        ApiResponse.Api.Fixture.AwayTeam("test",1,"Sheffield United"),
                        "2020-01-19T16:30:00+00:00",
                        ApiResponse.Api.Fixture.HomeTeam("test",1,"Leicester City")
                )
        )

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMatches)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this@MatchesActivity)
        recyclerView.adapter = FixtureAdapter(mockData, this@MatchesActivity)
    }

    fun setLeagueProperties(){
        var leagueName = intent.getStringExtra(R.string.leagueName.toString())
        leagueId = intent.getIntExtra(R.string.leagueId.toString(), 0)
        var tvLeagueHeader = findViewById<TextView>(R.id.tvLeagueHeader)
        tvLeagueHeader.text = leagueName
    }

    fun onBack(view: View) {
        onBackPressed()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCurrentDate(){
        tvDate = findViewById(R.id.tvDateMatches)
        var currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek: String = sdf.format(d)
        tvDate.text = currentDate.toString() + ", " + dayOfTheWeek
    }

    fun fetchJson(id : Int?)  {
        if(id == 0){
            Toast.makeText(this@MatchesActivity, "There aren't any matches.", Toast.LENGTH_SHORT).show()
            onBackPressed()
            return
        }

        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://api-football-v1.p.rapidapi.com/v2/fixtures/league/$id/next/10?timezone=Europe%2FLondon")
                .get()
                .addHeader("x-rapidapi-key", "ce54e36d64msh605fbf99d93fd76p148c19jsnf59cd9c166d8")
                .addHeader("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
                .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                errorToast("Something went wrong: " + e.message)
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                var apiResponse = gson.fromJson(body, ApiResponse::class.java)

                runOnUiThread {
                    var todayFixtures = apiResponse.api.fixtures.filter { fixture ->  true}//isDateSameAsSelected(fixture.event_date)}
                    if(todayFixtures.count() < 1)
                    {
                        errorToast("There aren't any matches on selected date.")
                    } else {
                        var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMatches)
                        recyclerView.hasFixedSize()
                        recyclerView.layoutManager = LinearLayoutManager(this@MatchesActivity)
                        recyclerView.adapter = FixtureAdapter(todayFixtures, this@MatchesActivity)
                    }
                }
            }

            fun errorToast(message: String){
                Toast.makeText(this@MatchesActivity, message, Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isDateSameAsSelected(eventDate : String) : Boolean{
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val date = LocalDateTime.parse(eventDate,formatter)
        var stringDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        return stringDate == tvDate.text.take(10)
    }
}