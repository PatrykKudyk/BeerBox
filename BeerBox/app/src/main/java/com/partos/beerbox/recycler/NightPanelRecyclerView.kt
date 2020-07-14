package com.partos.beerbox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import kotlinx.android.synthetic.main.row_mafia_role.view.*

class NightPanelRecyclerView(val activeRolesList: ArrayList<String>) :
    RecyclerView.Adapter<NightPanelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NightPanelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_mafia_role, parent, false)
        return NightPanelViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return activeRolesList.size
    }

    override fun onBindViewHolder(holder: NightPanelViewHolder, position: Int) {
        holder.view.row_mafia_role_text.text = activeRolesList[position]
    }


}

class NightPanelViewHolder(val view: View) : RecyclerView.ViewHolder(view)