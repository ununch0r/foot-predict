package com.example.footpredict.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.footpredict.FixtureActivity
import com.example.footpredict.MatchesActivity
import com.example.footpredict.R
import com.example.footpredict.data.ApiResponse
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FixtureAdapter(fixtures : List<ApiResponse.Api.Fixture>, context: Context) : RecyclerView.Adapter<FixtureAdapter.ViewHolder>() {

    var fixturesArray = fixtures
    var contextRecycler = context

    class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val tvFirstTeam : TextView = view.findViewById(R.id.tvFirstTeam)
        private val tvSecondTeam : TextView = view.findViewById(R.id.tvSecondTeam)
        private val tvTime : TextView = view.findViewById(R.id.tvTime)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item : ApiResponse.Api.Fixture, context: Context){
            setInfo(item)
            itemView.setOnClickListener{
                val intent = Intent(context, FixtureActivity::class.java)
                intent.putExtra(R.string.fixtureId.toString(), item.fixture_id)
                context.startActivity(intent)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun setInfo(item : ApiResponse.Api.Fixture){
            var totalLength = item.awayTeam.team_name.length + item.homeTeam.team_name.length
            setFontSize(tvFirstTeam, tvSecondTeam, totalLength)
            tvFirstTeam.text = item.homeTeam.team_name
            tvSecondTeam.text = item.awayTeam.team_name
            val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            val date = LocalDateTime.parse(item.event_date,formatter);
            tvTime.text = date.format(DateTimeFormatter.ofPattern("HH:mm"))
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
        return ViewHolder(inflater.inflate(R.layout.fixture_item_layout,parent,false))
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