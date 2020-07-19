package com.partos.beerbox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.models.MafiaPlayer
import kotlinx.android.synthetic.main.row_mafia_role.view.*

class NightPanelRolesRecyclerViewAdapter(val playersList: ArrayList<MafiaPlayer>) :
    RecyclerView.Adapter<NightPanelRolesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NightPanelRolesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_mafia_role_night, parent, false)
        return NightPanelRolesViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    override fun onBindViewHolder(holder: NightPanelRolesViewHolder, position: Int) {
        holder.view.row_mafia_role_text.text = playersList[position].name
        holder.view.row_mafia_name_text.text = playersList[position].role
    }


}

class NightPanelRolesViewHolder(val view: View) : RecyclerView.ViewHolder(view)