package com.partos.gamebox.logic.dices.listeners

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.partos.gamebox.R
import com.partos.gamebox.activities.DicesActivity
import com.partos.gamebox.logic.ToastHelper

class DicesChoiceListeners () {

    private lateinit var startButton: Button
    private lateinit var dicesTextView: TextView

    fun initListeners(rootView: View) {
        attachViews(rootView)
        startButton.setOnClickListener {
            if (dicesTextView.text.toString() != "") {
                if (dicesTextView.text.toString().toInt() >= 1) {
                    if (dicesTextView.text.toString().toInt() <= 5) {
                        val intent = Intent(rootView.context, DicesActivity::class.java)
                        intent.putExtra("number", dicesTextView.text.toString().toInt())
                        rootView.context.startActivity(intent)
                    } else {
                        ToastHelper().toastTooMuchDices(rootView.context)
                    }
                } else {
                    ToastHelper().toastTooFewDices(rootView.context)
                }
            } else {
                ToastHelper().toastNoDices(rootView.context)
            }
        }
    }

    private fun attachViews(rootView: View) {
        startButton = rootView.findViewById(R.id.dices_choice_button)
        dicesTextView = rootView.findViewById(R.id.dices_choice_edit_text)
    }
}