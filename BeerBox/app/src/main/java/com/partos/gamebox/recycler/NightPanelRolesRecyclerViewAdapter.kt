package com.partos.gamebox.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.flashback.db.DataBaseHelper
import com.partos.gamebox.MyApp
import com.partos.gamebox.R
import com.partos.gamebox.models.Action
import com.partos.gamebox.models.Player
import kotlinx.android.synthetic.main.row_mafia_role.view.*

class NightPanelRolesRecyclerViewAdapter(val playersList: ArrayList<Player>) :
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
        val db = DataBaseHelper(holder.view.context)
        holder.view.row_mafia_role_text.text = playersList[position].name
        holder.view.row_mafia_name_text.text = playersList[position].role
        if (playersList[position].isAlive == 0) {
            holder.view.row_mafia_role_card.setCardBackgroundColor(
                holder.view.context.getColor(
                    R.color.colorYellowDark
                )
            )
            holder.view.row_mafia_name_text.setBackgroundColor(holder.view.context.getColor(R.color.colorYellowDark))
            holder.view.row_mafia_role_text.setBackgroundColor(holder.view.context.getColor(R.color.colorYellowDark))
        } else {
            holder.view.row_mafia_role_card.setCardBackgroundColor(
                holder.view.context.getColor(
                    R.color.colorYellowLightLight
                )
            )
            holder.view.row_mafia_name_text.setBackgroundColor(holder.view.context.getColor(R.color.colorYellowLightLight))
            holder.view.row_mafia_role_text.setBackgroundColor(holder.view.context.getColor(R.color.colorYellowLightLight))
        }

        holder.view.row_mafia_role_card.setOnClickListener {
            if (playersList[position].isAlive == 1) {
                holder.view.row_mafia_role_card.setCardBackgroundColor(
                    holder.view.context.getColor(
                        R.color.colorYellowDark
                    )
                )
                holder.view.row_mafia_name_text.setBackgroundColor(holder.view.context.getColor(R.color.colorYellowDark))
                holder.view.row_mafia_role_text.setBackgroundColor(holder.view.context.getColor(R.color.colorYellowDark))
                playersList[position].isAlive = 0
                db.updatePLayer(playersList[position])
                MyApp.currentActionList.add(
                    Action(
                        0,
                        MyApp.round.number,
                        playersList[position].name,
                        holder.view.context.getString(R.string.killed)
                    )
                )
            } else {
                holder.view.row_mafia_role_card.setCardBackgroundColor(
                    holder.view.context.getColor(
                        R.color.colorYellowLightLight
                    )
                )
                holder.view.row_mafia_name_text.setBackgroundColor(holder.view.context.getColor(R.color.colorYellowLightLight))
                holder.view.row_mafia_role_text.setBackgroundColor(holder.view.context.getColor(R.color.colorYellowLightLight))
                playersList[position].isAlive = 1
                db.updatePLayer(playersList[position])
                deleteAction(playersList[position].name, holder.view.context)
            }
        }
    }

    private fun deleteAction(name: String, context: Context) {
        for (action in MyApp.currentActionList) {
            if (action.name == name && action.action == context.getString(R.string.killed)) {
                MyApp.currentActionList.remove(action)
            }
        }
    }

}

class NightPanelRolesViewHolder(val view: View) : RecyclerView.ViewHolder(view)