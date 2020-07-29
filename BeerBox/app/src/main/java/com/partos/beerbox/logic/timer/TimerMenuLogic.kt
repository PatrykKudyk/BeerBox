package com.partos.beerbox.logic.timer

import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.beerbox.R
import com.partos.beerbox.activities.TimerActivity

class TimerMenuLogic (val rootView: View, val fragmentManager: FragmentManager) {

    private lateinit var addingButton: Button
    private lateinit var noAddingButton: Button

    fun initFragment() {
        attachViews()
        attachListeners()
        makeAnimations()
    }

    private fun attachListeners() {
        addingButton.setOnClickListener {
            val intent = Intent(rootView.context, TimerActivity::class.java)
            intent.putExtra("type", 1)
            rootView.context.startActivity(intent)
        }

        noAddingButton.setOnClickListener {
            val intent = Intent(rootView.context, TimerActivity::class.java)
            intent.putExtra("type", 2)
            rootView.context.startActivity(intent)
        }
    }

    private fun attachViews() {
        addingButton = rootView.findViewById(R.id.timer_button_adding)
        noAddingButton = rootView.findViewById(R.id.timer_button_no_adding)
    }

    private fun makeAnimations() {
        val animLeft = AnimationUtils.loadAnimation(rootView.context, R.anim.enter_left_to_right)
        val animRight = AnimationUtils.loadAnimation(rootView.context, R.anim.enter_right_to_left)

        Handler().postDelayed({
            addingButton.visibility = View.VISIBLE
            noAddingButton.visibility = View.VISIBLE
            addingButton.startAnimation(animLeft)
            noAddingButton.startAnimation(animRight)
        }, 400)
    }
}