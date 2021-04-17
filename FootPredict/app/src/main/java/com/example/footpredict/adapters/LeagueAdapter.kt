package com.example.footpredict.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footpredict.MatchesActivity
import com.example.footpredict.R
import com.example.footpredict.models.League

class LeagueAdapter(leagues : ArrayList<League>, context: Context) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    var leaguesArray = leagues
    var contextRecycler = context

    class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val tvLeagueName : TextView = view.findViewById(R.id.tvLeagueName)

        fun bind(item : League, context: Context){
            tvLeagueName.text = item.name
            itemView.setOnClickListener{
                val intent = Intent(context, MatchesActivity::class.java)
                intent.putExtra(R.string.leagueName.toString(), item.name)
                intent.putExtra(R.string.leagueId.toString(), item.id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(contextRecycler)
        return ViewHolder(inflater.inflate(R.layout.league_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = leaguesArray?.get(position)
        holder.bind(item, contextRecycler)
    }

    override fun getItemCount(): Int {
        return leaguesArray.size
    }

}