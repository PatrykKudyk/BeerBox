package com.partos.gamebox.logic.cauldron.logic

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.R
import com.partos.gamebox.models.Alcohol
import com.partos.gamebox.recycler.AlcoholListRecyclerViewAdapter
import com.partos.gamebox.recycler.MarginItemDecoration

class CauldronAlcoholListLogic {

    private lateinit var recyclerView: RecyclerView

    fun initFragment(rootView: View) {
        attachViews(rootView)
        attachRecyclerView(rootView)
    }

    private fun attachRecyclerView(rootView: View) {
        val layoutManager = LinearLayoutManager(rootView.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = AlcoholListRecyclerViewAdapter(getAlcoholList(rootView.context))
    }

    private fun getAlcoholList(context: Context): ArrayList<Alcohol> {
        val alcohols = ArrayList<Alcohol>()
        alcohols.add(
            Alcohol(
                context.getString(R.string.beer),
                0.0,
                context.getString(R.string.beer_lager)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.beer),
                0.0,
                context.getString(R.string.beer_dark)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.beer),
                0.0,
                context.getString(R.string.beer_ipa)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.beer),
                0.0,
                context.getString(R.string.beer_porter)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.beer),
                0.0,
                context.getString(R.string.beer_wheat)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.wine),
                0.0,
                context.getString(R.string.wine_red_dry)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.wine),
                0.0,
                context.getString(R.string.wine_red_sweet)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.wine),
                0.0,
                context.getString(R.string.wine_white_dry)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.wine),
                0.0,
                context.getString(R.string.wine_white_sweet)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.wine),
                0.0,
                context.getString(R.string.wine_red_dry)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.vodka),
                0.0,
                context.getString(R.string.vodka)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.tequila),
                0.0,
                context.getString(R.string.tequila)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.rum),
                0.0,
                context.getString(R.string.rum)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.whiskey),
                0.0,
                context.getString(R.string.whiskey)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.gin),
                0.0,
                context.getString(R.string.gin)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.champagne),
                0.0,
                context.getString(R.string.champagne)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.energy_drink),
                0.0,
                context.getString(R.string.energy_drink)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.juice_raspberry),
                0.0,
                context.getString(R.string.juice_raspberry)
            )
        )
        alcohols.add(
            Alcohol(
                context.getString(R.string.juice_strawberry),
                0.0,
                context.getString(R.string.juice_strawberry)
            )
        )
        return alcohols
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.cauldron_alcohol_list_recycler)
    }
}