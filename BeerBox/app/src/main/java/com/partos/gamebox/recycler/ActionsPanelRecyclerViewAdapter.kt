package com.partos.gamebox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.R
import com.partos.gamebox.models.Action
import kotlinx.android.synthetic.main.row_actions.view.*

class ActionsPanelRecyclerViewAdapter(val actionsList: ArrayList<Action>) :
    RecyclerView.Adapter<ActionsPanelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionsPanelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_actions, parent, false)
        return ActionsPanelViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return actionsList.size
    }

    override fun onBindViewHolder(holder: ActionsPanelViewHolder, position: Int) {
        holder.view.row_actions_round.text =
            holder.view.context.getString(R.string.round) + " " + actionsList[position].round.toString()
        holder.view.row_actions_name.text = actionsList[position].name
        holder.view.row_actions_action.text = actionsList[position].action
    }

}

class ActionsPanelViewHolder(val view: View) : RecyclerView.ViewHolder(view)