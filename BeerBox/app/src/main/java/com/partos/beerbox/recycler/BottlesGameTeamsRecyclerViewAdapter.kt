package com.partos.beerbox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.models.Team
import com.partos.flashback.db.DataBaseHelper
import kotlinx.android.synthetic.main.row_team.view.*

class BottlesGameTeamsRecyclerViewAdapter(var teamList: ArrayList<Team>) :
    RecyclerView.Adapter<BottlesGameTeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottlesGameTeamsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.row_team, parent, false)
        return BottlesGameTeamsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: BottlesGameTeamsViewHolder, position: Int) {
        holder.view.row_team_card_view.setCardBackgroundColor(
            ContextCompat.getColor(
                holder.view.context,
                R.color.colorGreenLight
            )
        )
        holder.view.row_team_card_view.setStrokeColor(
            ContextCompat.getColor(
                holder.view.context,
                R.color.colorGreenDark
            )
        )
        holder.view.row_team_name.setText(teamList[position].name)
        holder.view.row_team_delete.setOnClickListener {
            val db = DataBaseHelper(holder.view.context)
            db.deleteTeam(teamList[position].id)
            teamList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, teamList.size)
        }
    }

}

class BottlesGameTeamsViewHolder(val view: View) : RecyclerView.ViewHolder(view)