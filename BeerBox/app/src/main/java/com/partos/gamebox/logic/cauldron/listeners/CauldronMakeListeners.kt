package com.partos.gamebox.logic.cauldron.listeners

import android.icu.util.Calendar
import android.view.View
import android.widget.Button
import com.partos.flashback.db.DataBaseHelper
import com.partos.gamebox.R
import com.partos.gamebox.logic.ToastHelper
import com.partos.gamebox.models.Alcohol
import com.partos.gamebox.models.AlcoholDb
import com.partos.gamebox.models.Cauldron
import kotlinx.android.synthetic.main.fragment_cauldron_make.view.*

class CauldronMakeListeners {
    private lateinit var saveButton: Button
    private lateinit var db: DataBaseHelper

    fun initListeners(rootView: View, alcoholList: ArrayList<Alcohol>) {
        db = DataBaseHelper(rootView.context)
        attachViews(rootView)
        attachListeners(rootView, alcoholList)
    }

    private fun attachListeners(rootView: View, alcoholList: ArrayList<Alcohol>) {
        saveButton.setOnClickListener {
            val now = Calendar.getInstance()
            val name = now.get(Calendar.YEAR).toString() + "/" + now.get(Calendar.MONTH).toString() +
                    "/" + now.get(Calendar.DAY_OF_MONTH).toString() + " " + now.get(Calendar.HOUR_OF_DAY).toString() +
                    ":" + now.get(Calendar.MINUTE).toString() + ":" + now.get(Calendar.SECOND).toString()
            db.addCauldron(Cauldron(0, name))
            val cauldron = db.getCauldron(name)
            for (alcohol in alcoholList) {
                db.addAlcohol(
                    AlcoholDb(
                        0,
                        cauldron.id,
                        alcohol.name,
                        (alcohol.amount * 1000).toInt(),
                        alcohol.type
                    )
                )
            }
            saveButton.visibility = View.GONE
            ToastHelper().toastSuccessfullySaved(rootView.context)
        }
    }

    private fun attachViews(rootView: View) {
        saveButton = rootView.findViewById(R.id.cauldron_make_save_button)
    }
}