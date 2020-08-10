package com.partos.gamebox.logic.cauldron.logic

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.flashback.db.DataBaseHelper
import com.partos.gamebox.R
import com.partos.gamebox.logic.cauldron.listeners.CauldronSavedListeners
import com.partos.gamebox.models.Alcohol
import com.partos.gamebox.models.AlcoholDb
import com.partos.gamebox.recycler.CauldronRecyclerViewAdapter
import com.partos.gamebox.recycler.MarginItemDecoration

class CauldronSavedLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var nameText: TextView
    private lateinit var db: DataBaseHelper

    fun initFragment(rootView: View, cauldronId: Long) {
        attachViews(rootView)
        db = DataBaseHelper(rootView.context)
        attachRecycler(rootView, cauldronId)
        attachName(cauldronId)
        CauldronSavedListeners().initListeners(rootView, cauldronId)
    }

    private fun attachName(cauldronId: Long) {
        val cauldron = db.getCauldron(cauldronId)
        nameText.text = cauldron.name
    }

    private fun attachRecycler(rootView: View, cauldronId: Long) {
        val layoutManager = LinearLayoutManager(rootView.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter =
            CauldronRecyclerViewAdapter(convertAlcoholList(db.getAlcoholList(cauldronId)))    }

    private fun convertAlcoholList(alcoholList: ArrayList<AlcoholDb>): ArrayList<Alcohol> {
        val alcohols = ArrayList<Alcohol>()
        for (alcohol in alcoholList) {
            alcohols.add(
                Alcohol(
                    alcohol.name,
                    (alcohol.amount.toDouble() / 1000.0),
                    alcohol.type
                )
            )
        }
        return alcohols
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.cauldron_saved_recycler)
        nameText = rootView.findViewById(R.id.cauldron_saved_text)
    }
}