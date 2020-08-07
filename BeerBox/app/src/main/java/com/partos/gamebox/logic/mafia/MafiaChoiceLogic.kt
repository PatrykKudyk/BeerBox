package com.partos.beerbox.logic.mafia

import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.beerbox.R
import com.partos.beerbox.fragments.mafia.MafiaGameFragment
import com.partos.beerbox.fragments.mafia.MafiaGameSizeFragment
import com.partos.flashback.db.DataBaseHelper

class MafiaChoiceLogic(val rootView: View, val fragmentManager: FragmentManager) {

    private lateinit var newButton: Button
    private lateinit var resumeButton: Button

    fun initFragment() {
        attachViews()
        attachListeners()
    }

    private fun attachListeners() {
        newButton.setOnClickListener {
            val fragment = MafiaGameSizeFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.mafia_frame_layout, fragment)
                .commit()
        }
        resumeButton.setOnClickListener {
            val fragment = MafiaGameFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.mafia_frame_layout, fragment)
                .commit()
        }
    }

    private fun attachViews() {
        newButton = rootView.findViewById(R.id.mafia_choice_new)
        resumeButton = rootView.findViewById(R.id.mafia_choice_resume)
    }
}