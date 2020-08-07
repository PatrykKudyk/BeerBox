package com.partos.gamebox.logic

import android.content.Context
import android.os.Handler
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
}