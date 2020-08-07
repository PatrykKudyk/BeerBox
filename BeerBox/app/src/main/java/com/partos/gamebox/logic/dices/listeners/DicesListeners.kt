package com.partos.gamebox.logic.dices.listeners

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.partos.gamebox.R
import com.partos.gamebox.logic.AnimationHelper
import kotlin.random.Random

class DicesListeners() {

    private lateinit var rollButton: Button
    private lateinit var dice1: ImageView
    private lateinit var dice21: ImageView
    private lateinit var dice22: ImageView
    private lateinit var dice31: ImageView
    private lateinit var dice32: ImageView
    private lateinit var dice33: ImageView
    private lateinit var dice41: ImageView
    private lateinit var dice42: ImageView
    private lateinit var dice43: ImageView
    private lateinit var dice44: ImageView
    private lateinit var dice51: ImageView
    private lateinit var dice52: ImageView
    private lateinit var dice53: ImageView
    private lateinit var dice54: ImageView
    private lateinit var dice55: ImageView
    private lateinit var layout1: ConstraintLayout
    private lateinit var layout2: ConstraintLayout
    private lateinit var layout3: ConstraintLayout
    private lateinit var layout4: ConstraintLayout
    private lateinit var layout5: ConstraintLayout


    fun initListeners(rootView: View, number: Int) {
        when (number) {
            1 -> {
                attachViews1(rootView)
                layout1.visibility = View.VISIBLE
                attachListener1(rootView)
            }
            2 -> {
                attachViews2(rootView)
                layout2.visibility = View.VISIBLE
                attachListener2(rootView)
            }
            3 -> {
                attachViews3(rootView)
                layout3.visibility = View.VISIBLE
                attachListener3(rootView)
            }
            4 -> {
                attachViews4(rootView)
                layout4.visibility = View.VISIBLE
                attachListener4(rootView)
            }
            5 -> {
                attachViews5(rootView)
                layout5.visibility = View.VISIBLE
                attachListener5(rootView)
            }
        }
    }

    private fun attachListener5(rootView: View) {
        rollButton.setOnClickListener {
            Handler().postDelayed({
                attachImage(dice51, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice51, rootView.context)
            Handler().postDelayed({
                attachImage(dice52, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice52, rootView.context)
            Handler().postDelayed({
                attachImage(dice53, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice53, rootView.context)
            Handler().postDelayed({
                attachImage(dice54, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice54, rootView.context)
            Handler().postDelayed({
                attachImage(dice55, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice55, rootView.context)
        }
    }

    private fun attachListener4(rootView: View) {
        rollButton.setOnClickListener {
            Handler().postDelayed({
                attachImage(dice41, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice41, rootView.context)
            Handler().postDelayed({
                attachImage(dice42, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice42, rootView.context)
            Handler().postDelayed({
                attachImage(dice43, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice43, rootView.context)
            Handler().postDelayed({
                attachImage(dice44, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice44, rootView.context)
        }
    }

    private fun attachListener3(rootView: View) {
        rollButton.setOnClickListener {
            Handler().postDelayed({
                attachImage(dice31, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice31, rootView.context)
            Handler().postDelayed({
                attachImage(dice32, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice32, rootView.context)
            Handler().postDelayed({
                attachImage(dice33, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice33, rootView.context)
        }
    }

    private fun attachListener2(rootView: View) {
        rollButton.setOnClickListener {
            Handler().postDelayed({
                attachImage(dice21, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice21, rootView.context)
            Handler().postDelayed({
                attachImage(dice22, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice22, rootView.context)
        }
    }

    private fun attachListener1(rootView: View) {
        rollButton.setOnClickListener {
            Handler().postDelayed({
                attachImage(dice1, Random.nextInt(1, 7), rootView.context)
            },300)
            AnimationHelper().animateDice(dice1, rootView.context)
        }
    }

    private fun attachViews1(rootView: View) {
        layout1 = rootView.findViewById(R.id.dices_layout_1)
        rollButton = rootView.findViewById(R.id.dices_button_roll)
        dice1 = rootView.findViewById(R.id.dices_dice_1)
    }

    private fun attachViews2(rootView: View) {
        layout2 = rootView.findViewById(R.id.dices_layout_2)
        rollButton = rootView.findViewById(R.id.dices_button_roll)
        dice21 = rootView.findViewById(R.id.dices_dice_2_1)
        dice22 = rootView.findViewById(R.id.dices_dice_2_2)
    }

    private fun attachViews3(rootView: View) {
        layout3 = rootView.findViewById(R.id.dices_layout_3)
        rollButton = rootView.findViewById(R.id.dices_button_roll)
        dice31 = rootView.findViewById(R.id.dices_dice_3_1)
        dice32 = rootView.findViewById(R.id.dices_dice_3_2)
        dice33 = rootView.findViewById(R.id.dices_dice_3_3)
    }

    private fun attachViews4(rootView: View) {
        layout4 = rootView.findViewById(R.id.dices_layout_4)
        rollButton = rootView.findViewById(R.id.dices_button_roll)
        dice41 = rootView.findViewById(R.id.dices_dice_4_1)
        dice42 = rootView.findViewById(R.id.dices_dice_4_2)
        dice43 = rootView.findViewById(R.id.dices_dice_4_3)
        dice44 = rootView.findViewById(R.id.dices_dice_4_4)
    }

    private fun attachViews5(rootView: View) {
        layout5 = rootView.findViewById(R.id.dices_layout_5)
        rollButton = rootView.findViewById(R.id.dices_button_roll)
        dice51 = rootView.findViewById(R.id.dices_dice_5_1)
        dice52 = rootView.findViewById(R.id.dices_dice_5_2)
        dice53 = rootView.findViewById(R.id.dices_dice_5_3)
        dice54 = rootView.findViewById(R.id.dices_dice_5_4)
        dice55 = rootView.findViewById(R.id.dices_dice_5_5)
    }

    private fun attachImage(imageView: ImageView, number: Int, context: Context){
        when(number){
            1 -> imageView.setImageDrawable(context.getDrawable(R.drawable.dice_1))
            2 -> imageView.setImageDrawable(context.getDrawable(R.drawable.dice_2))
            3 -> imageView.setImageDrawable(context.getDrawable(R.drawable.dice_3))
            4 -> imageView.setImageDrawable(context.getDrawable(R.drawable.dice_4))
            5 -> imageView.setImageDrawable(context.getDrawable(R.drawable.dice_5))
            6 -> imageView.setImageDrawable(context.getDrawable(R.drawable.dice_6))
        }
    }
}