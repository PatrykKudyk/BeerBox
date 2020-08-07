package com.partos.gamebox.logic.dices.logic

import android.view.View
import com.partos.gamebox.logic.dices.listeners.DicesChoiceListeners

class DicesChoiceLogic () {

    fun initFragment(rootView: View) {
        DicesChoiceListeners().initListeners(rootView)
    }
}