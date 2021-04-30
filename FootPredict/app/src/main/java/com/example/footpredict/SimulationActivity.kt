package com.example.footpredict

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.footpredict.data.Fixture
import com.example.footpredict.data.Prediction
import com.example.footpredict.database.DbManager
import com.example.footpredict.models.Simulation
import com.example.footpredict.models.SimulationEvent
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class SimulationActivity : AppCompatActivity() {
    lateinit var fixturePrediction: Prediction.Api.Prediction
    lateinit var fixtureInfo: Fixture.Api.Fixture
    var simulation = Simulation("","",0,0);
    var dbManager = DbManager(this)

    lateinit var firstTeamScore : TextView
    lateinit var secondTeamScore : TextView
    lateinit var minute : TextView
    lateinit var event_log : TextView
    var minuteValue = 0

    var homeTeamScoreChance : Double = 1.0;
    var awayTeamScoreChance : Double = 1.0;

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)

        setSimulationProterties()
        bindViewsToProperties()
        renderView()
        calculateChances()
    }

    fun calculateChances(){
        calculateHomeChance()
        calculateAwayChance()
    }

    fun calculateHomeChance(){
        homeTeamScoreChance += getValueFromPercentage(fixturePrediction.comparison.goals_h2h.home)
        homeTeamScoreChance += getValueFromPercentage(fixturePrediction.comparison.att.home)
        homeTeamScoreChance += getValueFromPercentage(fixturePrediction.comparison.def.home)
        homeTeamScoreChance += getValueFromPercentage(fixturePrediction.comparison.h2h.home)
        homeTeamScoreChance /= 270
        homeTeamScoreChance *= 1.05
        homeTeamScoreChance *= 100
    }

    fun calculateAwayChance(){
        awayTeamScoreChance += getValueFromPercentage(fixturePrediction.comparison.goals_h2h.away)
        awayTeamScoreChance += getValueFromPercentage(fixturePrediction.comparison.att.away)
        awayTeamScoreChance += getValueFromPercentage(fixturePrediction.comparison.def.away)
        awayTeamScoreChance += getValueFromPercentage(fixturePrediction.comparison.h2h.away)
        awayTeamScoreChance /= 270
        awayTeamScoreChance *= 100
    }

    fun getValueFromPercentage(percentage : String) : Double{
        var value = percentage.substring(0,percentage.length-1).toDouble()
        var result = value / 100;
        return result;
    }

    fun bindViewsToProperties(){
        firstTeamScore = findViewById(R.id.firstTeamScore)
        secondTeamScore = findViewById(R.id.secondTeamScore)
        minute = findViewById(R.id.minute)
        event_log = findViewById(R.id.event_log)
    }

    fun setSimulationProterties(){
        val gson = Gson()
        fixturePrediction = gson.fromJson(intent.getStringExtra(R.string.predictionContent.toString()),
                Prediction.Api.Prediction::class.java)
        fixtureInfo = gson.fromJson(intent.getStringExtra(R.string.fixtureContent.toString()),
                Fixture.Api.Fixture::class.java)
        simulation.firstTeamName = fixtureInfo.homeTeam.team_name
        simulation.secondTeamName = fixtureInfo.awayTeam.team_name
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun renderView(){
        setLogos(fixtureInfo.homeTeam.logo, fixtureInfo.awayTeam.logo)
        setTeamNames(fixtureInfo.homeTeam.team_name, fixtureInfo.awayTeam.team_name)
        setEventDate(fixtureInfo.event_date)
        firstTeamScore.text = simulation.secondTeamScore.toString()
        secondTeamScore.text = simulation.firstTeamScore.toString()
        minute.text = ""
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

    fun getEvent(minute : Int) : SimulationEvent{
        var event =  SimulationEvent(false,false,"");

        if(minute == 45)
        {
            event.appendText = "Half time. Score is ${simulation.firstTeamScore} : ${simulation.secondTeamScore}.\n"
        }

        if(minute == 90)
        {
            event.appendText = "Full time. Score is ${simulation.firstTeamScore} : ${simulation.secondTeamScore}.\n"
        }

        if(getRandomBoolean(homeTeamScoreChance))
        {
            event.firstTeamScored = true;
        }

        if(getRandomBoolean(awayTeamScoreChance))
        {
            event.secondTeamScored = true;
        }

        return event;
    }

    fun getRandomBoolean(probability: Double): Boolean {
        val randomValue = Math.random() * 100 //0.0 to 99.9
        return randomValue <= probability
    }

    fun applyEventResult(event : SimulationEvent){
        if(event.firstTeamScored) {
            simulation.firstTeamScore++;
            firstTeamScore.text = simulation.firstTeamScore.toString();
            event_log.append("Goal! " + fixtureInfo.homeTeam.team_name + " scored!\n")
        }

        if(event.secondTeamScored) {
            simulation.secondTeamScore++;
            secondTeamScore.text = simulation.secondTeamScore.toString();
            event_log.append("Goal! " + fixtureInfo.awayTeam.team_name + " scored!\n")
        }

        if(!event.appendText.isNullOrEmpty()){
            event_log.append(event.appendText);
        }
    }

    fun onShare(view: View){
        dbManager.openDb()
        var matches = dbManager.getMatches()
        var text = matches[0].firstTeamName +" " + matches[0].firstTeamScore + "-" + matches[0].secondTeamName +" " + matches[0].secondTeamScore

        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }


    fun onSave(view: View){
        dbManager.openDb()
        dbManager.insertToDatabase(simulation)
        dbManager.closeDb()
        view.isEnabled = false
    }

    fun onStart(view: View){
        val timer = object: CountDownTimer(18000,200) {
            override fun onTick(millisUntilFinished: Long) {
                minuteValue++;
                var event = getEvent(minuteValue)
                runOnUiThread {
                    minute.text = "$minuteValue'"
                    applyEventResult(event)
                }
            }

            override fun onFinish() {
                runOnUiThread {
                    var shareButton = findViewById<TextView>(R.id.share)
                    var saveButton = findViewById<TextView>(R.id.save)
                    shareButton.isVisible = true;
                    saveButton.isVisible = true;
                }
            }
        }
        timer.start()
        view.isVisible = false
    }
}