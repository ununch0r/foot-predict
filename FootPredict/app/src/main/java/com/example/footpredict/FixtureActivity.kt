package com.example.footpredict

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footpredict.adapters.FixtureAdapter
import com.example.footpredict.data.ApiResponse
import com.example.footpredict.data.Fixture
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException

class FixtureActivity : AppCompatActivity() {
    var fixtureId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixture)

        setFixtureProperties()
        fetchJson(fixtureId)
    }

    fun setFixtureProperties(){
        fixtureId = intent.getIntExtra(R.string.fixtureId.toString(), 0)
    }

    fun onBack(view: View) {
        onBackPressed()
    }

    fun fetchJson(id : Int?)  {
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
                var fixture = gson.fromJson(body, Fixture::class.java)

                runOnUiThread {
                    setAdvice(fixture.api.predictions.first().advice)
                    setStatistics(fixture.api.predictions.first())
                }
            }

            fun errorToast(message: String){
                Toast.makeText(this@FixtureActivity, message, Toast.LENGTH_SHORT).show()
                onBackPressed()
            }

            fun setAdvice(advice : String){
                val tvAdvice = findViewById<TextView>(R.id.tvAdvice)
                tvAdvice.text = advice
            }

            fun setStatistics(prediction: Fixture.Api.Prediction){
                val tvHomeForm = findViewById<TextView>(R.id.tvHomeForm)
                val tvAwayForm = findViewById<TextView>(R.id.tvAwayForm)
                tvHomeForm.text = prediction.comparison.forme.home
                tvAwayForm.text = prediction.comparison.forme.away
            }

            fun setLogos(firstUrl : String, secondUrl : String){
                var firstLogo = findViewById<ImageView>(R.id.firstLogo);
                var secondLogo = findViewById<ImageView>(R.id.secondLogo);

                Picasso.get().load(firstUrl).into(firstLogo)
                Picasso.get().load(secondUrl).into(secondLogo)
            }
        })
    }
}