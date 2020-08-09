package com.partos.gamebox.logic.cauldron.logic

import android.content.Intent
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.partos.gamebox.R
import com.partos.gamebox.activities.CauldronActivity
import com.partos.gamebox.fragments.cauldron.CauldronHowToMakeFragment
import com.partos.gamebox.logic.AnimationHelper
import com.partos.gamebox.logic.cauldron.listeners.CauldronMenuListeners

class CauldronMenuLogic {
    private lateinit var makeCauldronButton: Button
    private lateinit var howToMakeButton: Button
    private lateinit var savedButton: Button

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        makeAnimations(rootView)
        CauldronMenuListeners().initListeners(rootView, fragmentManager)
    }


    private fun attachViews(rootView: View) {
        makeCauldronButton = rootView.findViewById(R.id.cauldron_menu_make)
        howToMakeButton = rootView.findViewById(R.id.cauldron_menu_how_to_make)
        savedButton = rootView.findViewById(R.id.cauldron_menu_saved)
    }

    private fun makeAnimations(rootView: View) {
        Handler().postDelayed({
            AnimationHelper().enterLeft(makeCauldronButton, rootView.context)
            AnimationHelper().enterRight(savedButton, rootView.context)
            AnimationHelper().enterLeft(howToMakeButton, rootView.context)
        }, 400)
    }

}