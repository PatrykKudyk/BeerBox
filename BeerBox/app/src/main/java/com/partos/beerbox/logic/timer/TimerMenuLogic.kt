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

    private lateinit var playButton: Button

    fun initFragment() {
        attachViews()
        attachListeners()
        makeAnimations()
    }

    private fun attachListeners() {
        playButton.setOnClickListener {
            val intent = Intent(rootView.context, TimerActivity::class.java)
            rootView.context.startActivity(intent)
        }
    }

    private fun attachViews() {
        playButton = rootView.findViewById(R.id.timer_play)
    }

    private fun makeAnimations() {
        val animLeft = AnimationUtils.loadAnimation(rootView.context, R.anim.enter_left_to_right)
        val animRight = AnimationUtils.loadAnimation(rootView.context, R.anim.enter_right_to_left)

        Handler().postDelayed({
            playButton.visibility = View.VISIBLE
            playButton.startAnimation(animLeft)
        }, 400)
    }
}