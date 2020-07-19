package com.partos.beerbox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.models.Player
import com.partos.flashback.db.DataBaseHelper
import kotlinx.android.synthetic.main.row_mafia_role.view.*

class DayPanelRecyclerViewAdapter(val playersList: ArrayList<Player>) :
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
        val db = DataBaseHelper(holder.view.context)
        holder.view.row_mafia_role_text.text = playersList[position].name
        holder.view.row_mafia_name_text.text = playersList[position].role
        if (playersList[position].isAlive == 0) {
            holder.view.row_mafia_role_card.setCardBackgroundColor(
                holder.view.context.getColor(
                    R.color.colorOrangeDark
                )
            )
            holder.view.row_mafia_name_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeDark))
            holder.view.row_mafia_role_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeDark))
        } else {
            holder.view.row_mafia_role_card.setCardBackgroundColor(
                holder.view.context.getColor(
                    R.color.colorOrangeLightLight
                )
            )
            holder.view.row_mafia_name_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeLightLight))
            holder.view.row_mafia_role_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeLightLight))
        }

        holder.view.row_mafia_role_card.setOnClickListener {
            if (playersList[position].isAlive == 1) {
                holder.view.row_mafia_role_card.setCardBackgroundColor(
                    holder.view.context.getColor(
                        R.color.colorOrangeDark
                    )
                )
                holder.view.row_mafia_name_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeDark))
                holder.view.row_mafia_role_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeDark))
                playersList[position].isAlive = 0
                db.updatePLayer(playersList[position])
            } else {
                holder.view.row_mafia_role_card.setCardBackgroundColor(
                    holder.view.context.getColor(
                        R.color.colorOrangeLightLight
                    )
                )
                holder.view.row_mafia_name_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeLightLight))
                holder.view.row_mafia_role_text.setBackgroundColor(holder.view.context.getColor(R.color.colorOrangeLightLight))
                playersList[position].isAlive = 1
                db.updatePLayer(playersList[position])
            }
        }
    }

}


class DayPanelViewHolder(val view: View) : RecyclerView.ViewHolder(view)