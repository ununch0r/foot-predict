package com.example.footpredict

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.footpredict.data.Fixture
import com.example.footpredict.data.Prediction
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class SimulationActivity : AppCompatActivity() {
    lateinit var fixturePrediction: Prediction.Api.Prediction
    lateinit var fixtureInfo: Fixture.Api.Fixture

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)

        setSimulationProterties()
        renderView()
    }

    fun setSimulationProterties(){
        val gson = Gson()
        fixturePrediction = gson.fromJson(intent.getStringExtra(R.string.predictionContent.toString()),
                Prediction.Api.Prediction::class.java)
        fixtureInfo = gson.fromJson(intent.getStringExtra(R.string.fixtureContent.toString()),
                Fixture.Api.Fixture::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun renderView(){
        setLogos(fixtureInfo.homeTeam.logo, fixtureInfo.awayTeam.logo)
        setTeamNames(fixtureInfo.homeTeam.team_name, fixtureInfo.awayTeam.team_name)
        setEventDate(fixtureInfo.event_date)
    }

    fun setLogos(firstUrl : String, secondUrl : String){
        var firstLogo = findViewById<ImageView>(R.id.firstLogo);
        var secondLogo = findViewById<ImageView>(R.id.secondLogo);

        Picasso.get().load(firstUrl).into(firstLogo)
        Picasso.get().load(secondUrl).into(secondLogo)
    }

    fun setTeamNames(firstTeamName : String, secondTeamName : String){
        var homeTeamName = findViewById<TextView>(R.id.tvHomeTeamName)
        var awayTeamName = findViewById<TextView>(R.id.tvAwayTeamName)

        homeTeamName.text = firstTeamName
        awayTeamName.text = secondTeamName
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setEventDate(eventDateTime : String){
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val dateTime = LocalDateTime.parse(eventDateTime,formatter)
        var stringDate = dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))

        var tvFixtureDateTime = findViewById<TextView>(R.id.tvFixtureDateTime)
        tvFixtureDateTime.text = stringDate;
    }

    fun onBack(view: View) {
        onBackPressed()
    }
}