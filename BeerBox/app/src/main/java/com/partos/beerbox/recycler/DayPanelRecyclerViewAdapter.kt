package com.partos.beerbox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.models.MafiaPlayer
import kotlinx.android.synthetic.main.row_mafia_role.view.*

class DayPanelRecyclerViewAdapter(val playersList: ArrayList<MafiaPlayer>) :
    RecyclerView.Adapter<DayPanelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayPanelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.row_mafia_role, parent, false)
        return DayPanelViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    override fun onBindViewHolder(holder: DayPanelViewHolder, position: Int) {
        holder.view.row_mafia_role_text.text = playersList[position].name
        holder.view.row_mafia_name_text.text = playersList[position].role
        holder.view.row_mafia_role_card.setOnClickListener {
            if (holder.view.row_mafia_role_card.cardBackgroundColor == holder.view.context.getColorStateList(
                    R.color.colorOrangeLightLight
                )
            ) {
                holder.view.row_mafia_role_card.setCardBackgroundColor(holder.view.context.getColor(R.color.colorOrangeDark))
                holder.view.row_mafia_name_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeDark))
                holder.view.row_mafia_role_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeDark))
            } else {
                holder.view.row_mafia_role_card.setCardBackgroundColor(holder.view.context.getColor(R.color.colorOrangeLightLight))
                holder.view.row_mafia_name_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeLightLight))
                holder.view.row_mafia_role_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeLightLight))
            }
        }
    }

}


class DayPanelViewHolder(val view: View) : RecyclerView.ViewHolder(view)