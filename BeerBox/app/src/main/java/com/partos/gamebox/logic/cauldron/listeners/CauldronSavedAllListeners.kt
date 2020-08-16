package com.partos.gamebox.logic.cauldron.listeners

import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.gamebox.R
import com.partos.gamebox.fragments.cauldron.CauldronAddFragment

class CauldronSavedAllListeners {

    private lateinit var addNewCard: CardView

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        addNewCard.setOnClickListener {
            val fragment = CauldronAddFragment.newInstance()
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

    private fun attachViews(rootView: View) {
        addNewCard = rootView.findViewById(R.id.cauldron_saved_all_card)
    }
}