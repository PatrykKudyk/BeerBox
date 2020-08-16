package com.partos.gamebox.logic.cauldron.logic

import android.view.View
import androidx.fragment.app.FragmentManager
import com.partos.gamebox.logic.cauldron.listeners.CauldronChoicesListeners

class CauldronChoicesLogic {

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        CauldronChoicesListeners().initListeners(rootView, fragmentManager)
    }
}