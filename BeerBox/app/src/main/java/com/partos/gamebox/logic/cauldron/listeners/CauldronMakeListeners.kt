package com.partos.gamebox.logic.cauldron.listeners

import android.view.View
import android.widget.Button
import com.partos.gamebox.R
import kotlinx.android.synthetic.main.fragment_cauldron_make.view.*

class CauldronMakeListeners {
    private lateinit var saveButton: Button

    fun initListeners(rootView: View) {
        attachViews(rootView)
        attachListeners()
    }

    private fun attachListeners() {
        saveButton.setOnClickListener {
            
        }
    }

    private fun attachViews(rootView: View) {
        saveButton = rootView.findViewById(R.id.cauldron_make_save_button)
    }
}