package com.partos.gamebox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.R
import com.partos.gamebox.models.Team
import com.partos.flashback.db.DataBaseHelper
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
            val db = DataBaseHelper(holder.view.context)
            db.deleteTeam(teamList[position].id)
            teamList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, teamList.size)
        }
    }

}

class BeerPongTeamsViewHolder(val view: View) : RecyclerView.ViewHolder(view)