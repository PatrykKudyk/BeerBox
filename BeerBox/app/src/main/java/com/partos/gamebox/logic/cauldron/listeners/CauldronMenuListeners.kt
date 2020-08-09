package com.partos.gamebox.logic.cauldron.listeners

import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.gamebox.R
import com.partos.gamebox.activities.CauldronActivity
import com.partos.gamebox.fragments.cauldron.CauldronHowToMakeFragment
import com.partos.gamebox.fragments.cauldron.CauldronSavedAllFragment

class CauldronMenuListeners {

    private lateinit var makeCauldronButton: Button
    private lateinit var howToMakeButton: Button
    private lateinit var savedButton: Button

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        makeCauldronButton.setOnClickListener {
            val intent = Intent(rootView.context, CauldronActivity::class.java)
            rootView.context.startActivity(intent)
        }

        howToMakeButton.setOnClickListener {
            val howToMakeFragment = CauldronHowToMakeFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, howToMakeFragment)
                .addToBackStack(CauldronHowToMakeFragment.toString())
                .commit()
        }
        savedButton.setOnClickListener {
            val savedCauldronFragment = CauldronSavedAllFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, savedCauldronFragment)
                .addToBackStack(CauldronSavedAllFragment.toString())
                .commit()
        }
    }

    private fun attachViews(rootView: View) {
        makeCauldronButton = rootView.findViewById(R.id.cauldron_menu_make)
        howToMakeButton = rootView.findViewById(R.id.cauldron_menu_how_to_make)
        savedButton = rootView.findViewById(R.id.cauldron_menu_saved)
    }
}