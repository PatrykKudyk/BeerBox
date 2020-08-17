package com.partos.gamebox.logic.cauldron.logic

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.flashback.db.DataBaseHelper
import com.partos.gamebox.MyApp
import com.partos.gamebox.R
import com.partos.gamebox.logic.cauldron.listeners.CauldronSavedAllListeners
import com.partos.gamebox.recycler.CauldronSavedAllRecyclerViewAdapter
import com.partos.gamebox.recycler.MarginItemDecoration

class CauldronSavedAllLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var db: DataBaseHelper

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        MyApp.alcoholList.clear()
        db = DataBaseHelper(rootView.context)
        val layoutManager = LinearLayoutManager(rootView.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = CauldronSavedAllRecyclerViewAdapter(db.getCauldronList())
        CauldronSavedAllListeners().initListeners(rootView, fragmentManager)
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.cauldron_saved_all_recycler)
    }
}