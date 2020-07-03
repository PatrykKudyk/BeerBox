package com.partos.beerbox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.MyApp
import com.partos.beerbox.R
import com.partos.beerbox.activities.BeerPongActivity
import com.partos.beerbox.activities.MainActivity
import com.partos.beerbox.models.Team
import kotlinx.android.synthetic.main.fragment_beer_pong_teams.*
import kotlinx.android.synthetic.main.fragment_beer_pong_teams.view.*
import kotlinx.android.synthetic.main.row_team.view.*

class BeerPongTeamsRecyclerViewAdapter(var teamList: ArrayList<Team>) :
    RecyclerView.Adapter<BeerPongTeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerPongTeamsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.row_team, parent, false)
        return BeerPongTeamsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: BeerPongTeamsViewHolder, position: Int) {
        val activity = (holder.itemView.context as BeerPongActivity)
        val teamsText = activity.beer_pong_teams_text_teams
        holder.view.row_team_card_view.setCardBackgroundColor(
            ContextCompat.getColor(
                holder.view.context,
                R.color.colorRedLight
            )
        )
        holder.view.row_team_card_view.setStrokeColor(
            ContextCompat.getColor(
                holder.view.context,
                R.color.colorRedDark
            )
        )
        holder.view.row_team_name.setText(teamList[position].name)
        holder.view.row_team_delete.setOnClickListener {
            MyApp.teamsNumber--
            teamsText.text =
                holder.view.context.getString(R.string.teams) + " " + MyApp.teamsNumber.toString() + "/16"
            teamList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, teamList.size)
        }
    }

}

class BeerPongTeamsViewHolder(val view: View) : RecyclerView.ViewHolder(view)