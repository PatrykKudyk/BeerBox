package com.partos.gamebox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.R
import com.partos.gamebox.activities.CauldronActivity
import com.partos.gamebox.activities.MainActivity
import com.partos.gamebox.fragments.cauldron.CauldronSavedFragment
import com.partos.gamebox.models.Cauldron
import kotlinx.android.synthetic.main.row_cauldron_saved.view.*

class CauldronSavedAllRecyclerViewAdapter(val cauldronList: ArrayList<Cauldron>) :
    RecyclerView.Adapter<CauldronSavedAllViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CauldronSavedAllViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_cauldron_saved, parent, false)
        return CauldronSavedAllViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return cauldronList.size
    }

    override fun onBindViewHolder(holder: CauldronSavedAllViewHolder, position: Int) {
        holder.view.row_cauldron_saved_text.text = cauldronList[position].name
        holder.view.row_cauldron_saved_card.setOnClickListener {
            val fragment = CauldronSavedFragment.newInstance(cauldronList[position].id)
            (holder.view.context as MainActivity).supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(CauldronSavedFragment.toString())
                .commit()
        }
    }

}


class CauldronSavedAllViewHolder(val view: View) : RecyclerView.ViewHolder(view)