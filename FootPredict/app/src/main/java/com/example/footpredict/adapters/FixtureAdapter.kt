package com.example.footpredict.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footpredict.R
import com.example.footpredict.data.ApiResponse

class FixtureAdapter(fixtures : ArrayList<ApiResponse.Api.Fixture>, context: Context) : RecyclerView.Adapter<FixtureAdapter.ViewHolder>() {

    var fixturesArray = fixtures
    var contextRecycler = context

    class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val tvFirstTeam : TextView = view.findViewById(R.id.tvFirstTeam)
        private val tvSecondTeam : TextView = view.findViewById(R.id.tvSecondTeam)

        fun bind(item : ApiResponse.Api.Fixture, context: Context){
            tvFirstTeam.text = item.homeTeam.team_name
            tvSecondTeam.text = item.awayTeam.team_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(contextRecycler)
        return ViewHolder(inflater.inflate(R.layout.fixture_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = fixturesArray?.get(position)
        holder.bind(item, contextRecycler)
    }

    override fun getItemCount(): Int {
        return fixturesArray.size
    }
}