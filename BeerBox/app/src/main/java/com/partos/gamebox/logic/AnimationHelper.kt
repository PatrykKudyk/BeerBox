package com.partos.gamebox.logic

import android.content.Context
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.partos.gamebox.R

class AnimationHelper {

    fun animateDice(imageView: ImageView, context: Context) {
        val startAnimation = AnimationUtils.loadAnimation(context, R.anim.shake_start)
        val endAnimation = AnimationUtils.loadAnimation(context, R.anim.shake_end)
        imageView.startAnimation(startAnimation)
        Handler().postDelayed({
            imageView.startAnimation(endAnimation)
        }, 300)
    }

    fun enterLeft(view: View, context: Context) {
        val animLeft = AnimationUtils.loadAnimation(context, R.anim.enter_left_to_right)
        view.visibility = View.VISIBLE
        view.startAnimation(animLeft)
    }

    fun enterRight(view: View, context: Context) {
        val animRight = AnimationUtils.loadAnimation(context, R.anim.enter_right_to_left)
        view.visibility = View.VISIBLE
        view.startAnimation(animRight)
    }
}