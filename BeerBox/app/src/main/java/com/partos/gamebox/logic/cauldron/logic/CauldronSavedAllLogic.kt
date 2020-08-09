package com.partos.gamebox.logic.cauldron.logic

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.flashback.db.DataBaseHelper
import com.partos.gamebox.R
import com.partos.gamebox.recycler.CauldronSavedAllRecyclerViewAdapter
import com.partos.gamebox.recycler.MarginItemDecoration

class CauldronSavedAllLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var db: DataBaseHelper

    fun initFragment(rootView: View) {
        attachViews(rootView)
        db = DataBaseHelper(rootView.context)
        val layoutManager = LinearLayoutManager(rootView.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = CauldronSavedAllRecyclerViewAdapter(db.getCauldronList())
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.cauldron_saved_recycler)
    }
}