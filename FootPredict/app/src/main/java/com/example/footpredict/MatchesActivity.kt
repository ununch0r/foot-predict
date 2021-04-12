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
import java.util.*
import kotlin.collections.ArrayList

class MatchesActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)

        setCurrentDate()
        setLeagueName()
        fetchJson(2790)
    }

    fun setLeagueName(){
        var leagueName = intent.getStringExtra(R.string.leagueName.toString())
        var tvLeagueHeader = findViewById<TextView>(R.id.tvLeagueHeader)
        tvLeagueHeader.text = leagueName
    }

    fun onBack(view: View) {
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

    fun fetchJson(id : Int)  {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://api-football-v1.p.rapidapi.com/v2/fixtures/league/$id/next/10?timezone=Europe%2FLondon")
                .get()
                .addHeader("x-rapidapi-key", "ce54e36d64msh605fbf99d93fd76p148c19jsnf59cd9c166d8")
                .addHeader("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
                .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(this@MatchesActivity, "Something went wrong: " + e.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = GsonBuilder().create()
                var apiResponse = gson.fromJson(body, ApiResponse::class.java)

                runOnUiThread {
                    var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMatches)
                    recyclerView.hasFixedSize()
                    recyclerView.layoutManager = LinearLayoutManager(this@MatchesActivity)
                    recyclerView.adapter = FixtureAdapter(apiResponse.api.fixtures, this@MatchesActivity)
                }
            }
        })

    }
}