package com.partos.beerbox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import kotlinx.android.synthetic.main.row_mafia_action_night.view.*

class NightPanelActionsRecyclerViewAdapter(val actionsList: ArrayList<String>) :
    RecyclerView.Adapter<NightPanelActionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NightPanelActionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_mafia_action_night, parent, false)
        return NightPanelActionsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return actionsList.size
    }

    override fun onBindViewHolder(holder: NightPanelActionsViewHolder, position: Int) {
        holder.view.row_mafia_action_text.text = actionsList[position]
    }


}

class NightPanelActionsViewHolder(val view: View) : RecyclerView.ViewHolder(view)