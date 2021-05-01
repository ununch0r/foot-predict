package com.example.footpredict

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.footpredict.data.Fixture
import com.example.footpredict.data.Prediction
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FixtureActivity : AppCompatActivity() {
    var fixtureId : Int = 0
    lateinit var fixturePrediction: Prediction.Api.Prediction
    lateinit var fixtureInfo: Fixture.Api.Fixture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixture)

        setFixtureProperties()
        fetchFixture(fixtureId)
        fetchPrediction(fixtureId)
    }

    fun setFixtureProperties(){
        fixtureId = intent.getIntExtra(R.string.fixtureId.toString(), 0)
    }

    fun onBack(view: View) {
        onBackPressed()
    }

    fun onSimulate(view: View){
        val gson = Gson()
        val prediction = gson.toJson(fixturePrediction)
        val fixture = gson.toJson(fixtureInfo)
        val intent = Intent(this, SimulationActivity::class.java)
        intent.putExtra(R.string.predictionContent.toString(), prediction)
        intent.putExtra(R.string.fixtureContent.toString(), fixture)
        this.startActivity(intent)
    }

    fun fetchPrediction(id : Int?)  {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api-football-v1.p.rapidapi.com/v2/predictions/$id")
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
                var prediction = gson.fromJson(body, Prediction::class.java)

                runOnUiThread {
                    fixturePrediction = prediction.api.predictions.first()
                    setAdvice(fixturePrediction.advice)
                    setStatistics(fixturePrediction)
                }
            }

            fun errorToast(message: String){
                Toast.makeText(this@FixtureActivity, message, Toast.LENGTH_SHORT).show()
                onBackPressed()
            }

            fun setAdvice(advice : String){
                val tvAdvice = findViewById<TextView>(R.id.tvAdvice)
                tvAdvice.text = advice
                if(advice.length < 35){
                    tvAdvice.textSize = 24.0f
                } else if(advice.length in 36..44){
                    tvAdvice.textSize = 16.0f
                }
                else{
                    tvAdvice.textSize = 14.0f
                }
            }

            fun setStatistics(prediction: Prediction.Api.Prediction){
                val tvHomeForm = findViewById<TextView>(R.id.tvHomeForm)
                val tvAwayForm = findViewById<TextView>(R.id.tvAwayForm)
                val tvHomeAttack = findViewById<TextView>(R.id.tvHomeAttack)
                val tvAwayAttack = findViewById<TextView>(R.id.tvAwayAttack)
                val tvHomeDefend = findViewById<TextView>(R.id.tvHomeDefend)
                val tvAwayDefend = findViewById<TextView>(R.id.tvAwayDefend)
                val tvHomeResult = findViewById<TextView>(R.id.tvHomeResults)
                val tvAwayResult = findViewById<TextView>(R.id.tvAwayResults)
                val tvHomeGoals = findViewById<TextView>(R.id.tvHomeGoals)
                val tvAwayGoals = findViewById<TextView>(R.id.tvAwayGoals)

                tvHomeForm.text = prediction.comparison.forme.home
                tvAwayForm.text = prediction.comparison.forme.away
                tvHomeAttack.text = prediction.comparison.att.home
                tvAwayAttack.text = prediction.comparison.att.away
                tvHomeDefend.text = prediction.comparison.def.home
                tvAwayDefend.text = prediction.comparison.def.away
                tvHomeResult.text = prediction.comparison.h2h.home
                tvAwayResult.text = prediction.comparison.h2h.away
                tvHomeGoals.text = prediction.comparison.goals_h2h.home
                tvAwayGoals.text = prediction.comparison.goals_h2h.away
            }
        })
    }

    fun fetchFixture(id : Int?)  {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://api-football-v1.p.rapidapi.com/v2/fixtures/id/$id?timezone=Europe%2FLondon")
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
                var fixture = gson.fromJson(body, Fixture::class.java)

                runOnUiThread {
                    fixtureInfo = fixture.api.fixtures.first()
                    setLogos(fixtureInfo.homeTeam.logo, fixtureInfo.awayTeam.logo)
                    setTeamNames(fixtureInfo.homeTeam.team_name, fixtureInfo.awayTeam.team_name)
                    setRound(fixtureInfo.round)
                    setEventDate(fixtureInfo.event_date)
                }
            }

            fun errorToast(message: String){
                Toast.makeText(this@FixtureActivity, message, Toast.LENGTH_SHORT).show()
                onBackPressed()
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

            fun setRound(round : String){
                var tvRoundName = findViewById<TextView>(R.id.tvRoundName)
                tvRoundName.text = round
            }

            @RequiresApi(Build.VERSION_CODES.O)
            fun setEventDate(eventDateTime : String){
                val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
                var dateTime = LocalDateTime.parse(eventDateTime,formatter)
                dateTime = dateTime.plusHours(2)
                var stringDate = dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))

                var tvFixtureDateTime = findViewById<TextView>(R.id.tvFixtureDateTime)
                tvFixtureDateTime.text = stringDate;
            }
        })
    }
}