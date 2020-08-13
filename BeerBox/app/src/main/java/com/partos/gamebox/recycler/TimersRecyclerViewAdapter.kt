package com.partos.gamebox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.R

class TimersRecyclerViewAdapter() : RecyclerView.Adapter<TimersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_sound, parent, false)
        return TimersViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: TimersViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}

class TimersViewHolder(val view: View) : RecyclerView.ViewHolder(view)