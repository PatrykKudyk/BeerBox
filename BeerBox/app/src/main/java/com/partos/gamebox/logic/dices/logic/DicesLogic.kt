package com.partos.gamebox.logic.dices.logic

import android.view.View
import com.partos.gamebox.logic.dices.listeners.DicesListeners

class DicesLogic () {

    fun initFragment(rootView: View, number: Int) {
        DicesListeners().initListeners(rootView, number)
    }
}