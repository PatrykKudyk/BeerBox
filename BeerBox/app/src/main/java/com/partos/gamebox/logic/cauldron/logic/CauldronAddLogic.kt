package com.partos.gamebox.logic.cauldron.logic

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.MyApp
import com.partos.gamebox.R
import com.partos.gamebox.logic.cauldron.listeners.CauldronAddListeners
import com.partos.gamebox.models.Alcohol
import com.partos.gamebox.recycler.CauldronAddRecyclerViewAdapter
import com.partos.gamebox.recycler.MarginItemDecoration

class CauldronAddLogic {

    private lateinit var recyclerView: RecyclerView

    fun initFragment(rootView: View, fragmentManager: FragmentManager ) {
        attachViews(rootView)
        attachRecyclerAdapter(rootView)
        CauldronAddListeners().initListeners(rootView, fragmentManager)
    }

    private fun attachRecyclerAdapter(rootView: View) {
        val layoutManager = LinearLayoutManager(rootView.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = CauldronAddRecyclerViewAdapter(MyApp.alcoholList)
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.cauldron_add_recycler)
    }
}