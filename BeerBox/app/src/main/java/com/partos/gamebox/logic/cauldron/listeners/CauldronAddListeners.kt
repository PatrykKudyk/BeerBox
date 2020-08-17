package com.partos.gamebox.logic.cauldron.listeners

import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.flashback.db.DataBaseHelper
import com.partos.gamebox.MyApp
import com.partos.gamebox.R
import com.partos.gamebox.fragments.cauldron.CauldronAddFragment
import com.partos.gamebox.fragments.cauldron.CauldronAlcoholListFragment
import com.partos.gamebox.logic.ToastHelper
import com.partos.gamebox.models.AlcoholDb
import com.partos.gamebox.models.Cauldron

class CauldronAddListeners {

    private lateinit var saveButton: Button
    private lateinit var addButton: CardView
    private lateinit var nameEditText: EditText
    private lateinit var db: DataBaseHelper

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        db = DataBaseHelper(rootView.context)
        attachListeners(rootView)
        saveButton.setOnClickListener {
            if (nameEditText.text.toString() != "") {
                if (MyApp.alcoholList.size != 0) {
                    db.addCauldron(Cauldron(0, nameEditText.text.toString()))
                    val cauldron = db.getCauldron(nameEditText.text.toString())
                    for (alcohol in MyApp.alcoholList) {
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
                } else {
                    ToastHelper().toastNoAlcohols(rootView.context)
                }
            } else {
                ToastHelper().toastNoNameGiven(rootView.context)
            }
        }
        addButton.setOnClickListener {
            val fragment = CauldronAlcoholListFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(CauldronAddFragment.toString())
                .commit()
        }
    }

    private fun attachListeners(rootView: View) {
        saveButton = rootView.findViewById(R.id.cauldron_add_button)
        addButton = rootView.findViewById(R.id.cauldron_add_card)
        nameEditText = rootView.findViewById(R.id.cauldron_add_name)
    }
}