package com.example.footpredict.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.footpredict.FixtureActivity
import com.example.footpredict.R
import com.example.footpredict.data.ApiResponse
import com.example.footpredict.models.Simulation
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SavedMatchesAdapter(savedMatches : List<Simulation>, context: Context) : RecyclerView.Adapter<SavedMatchesAdapter.ViewHolder>() {

    var fixturesArray = savedMatches
    var contextRecycler = context

    class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val tvFirstTeam : TextView = view.findViewById(R.id.tvFirstTeamName)
        private val tvSecondTeam : TextView = view.findViewById(R.id.tvSecondTeamName)
        private val tvScore : TextView = view.findViewById(R.id.tvScore)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item : Simulation, context: Context){
            setInfo(item)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun setInfo(item : Simulation){
            var totalLength = item.firstTeamName.length + item.secondTeamName.length
            setFontSize(tvFirstTeam, tvSecondTeam, totalLength)
            tvFirstTeam.text = item.firstTeamName
            tvSecondTeam.text = item.secondTeamName
            tvScore.text = item.firstTeamScore.toString() + " - " + item.secondTeamScore
        }

        fun setFontSize(v1: TextView, v2 : TextView, length : Int){
            if(length > 20)
            {
                v1.textSize = 16.0f
                v2.textSize = 16.0f
            }
            if(length > 30)
            {
                v1.textSize = 14.0f
                v2.textSize = 14.0f
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(contextRecycler)
        return ViewHolder(inflater.inflate(R.layout.saved_simulation_layout,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = fixturesArray?.get(position)
        holder.bind(item, contextRecycler)
    }

    override fun getItemCount(): Int {
        return fixturesArray.size
    }
}