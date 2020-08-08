package com.partos.gamebox.logic.timer

import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.gamebox.R
import com.partos.gamebox.fragments.cauldron.CauldronMakeFragment
import com.partos.gamebox.fragments.timer.TimerFragment

class TimerChoicesLogic(val rootView: View, val fragmentManager: FragmentManager) {

    private lateinit var addingYes: CardView
    private lateinit var addingNo: CardView
    private lateinit var time3: CardView
    private lateinit var time5: CardView
    private lateinit var time10: CardView
    private lateinit var time20: CardView
    private lateinit var playButton: Button
    private val context = rootView.context

    fun initFragment() {
        attachViews()
        initStartViews()
        attachListeners()
    }

    private fun attachListeners() {
        addingYes.setOnClickListener {
            if (addingYes.cardBackgroundColor != context.getColorStateList(R.color.colorBlueDark)) {
                addingYes.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueDark))
                addingNo.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
            }
        }
        addingNo.setOnClickListener {
            if (addingNo.cardBackgroundColor != context.getColorStateList(R.color.colorBlueDark)) {
                addingNo.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueDark))
                addingYes.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
            }
        }
        time3.setOnClickListener {
            if (time3.cardBackgroundColor != context.getColorStateList(R.color.colorBlueDark)) {
                time3.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueDark))
                time5.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
                time10.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
                time20.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
            }
        }
        time5.setOnClickListener {
            if (time5.cardBackgroundColor != context.getColorStateList(R.color.colorBlueDark)) {
                time5.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueDark))
                time3.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
                time10.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
                time20.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
            }
        }
        time10.setOnClickListener {
            if (time10.cardBackgroundColor != context.getColorStateList(R.color.colorBlueDark)) {
                time10.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueDark))
                time3.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
                time5.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
                time20.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
            }
        }
        time20.setOnClickListener {
            if (time20.cardBackgroundColor != context.getColorStateList(R.color.colorBlueDark)) {
                time20.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueDark))
                time3.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
                time5.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
                time10.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueLight))
            }
        }
        playButton.setOnClickListener {
            when {
                time3.cardBackgroundColor == context.getColorStateList(R.color.colorBlueDark) -> {
                    val fragment: TimerFragment =
                        if (addingYes.cardBackgroundColor == context.getColorStateList(R.color.colorBlueDark)) {
                            TimerFragment.newInstance(3, true)
                        } else {
                            TimerFragment.newInstance(3, false)
                        }
                    fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(
                            R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                            R.anim.enter_left_to_right, R.anim.exit_right_to_left
                        )
                        .replace(R.id.timer_frame_layout, fragment)
                        .addToBackStack(CauldronMakeFragment.toString())
                        .commit()
                }
                time5.cardBackgroundColor == context.getColorStateList(R.color.colorBlueDark) -> {
                    val fragment: TimerFragment =
                        if (addingYes.cardBackgroundColor == context.getColorStateList(R.color.colorBlueDark)) {
                            TimerFragment.newInstance(5, true)
                        } else {
                            TimerFragment.newInstance(5, false)
                        }
                    fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(
                            R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                            R.anim.enter_left_to_right, R.anim.exit_right_to_left
                        )
                        .replace(R.id.timer_frame_layout, fragment)
                        .addToBackStack(CauldronMakeFragment.toString())
                        .commit()
                }
                time10.cardBackgroundColor == context.getColorStateList(R.color.colorBlueDark) -> {
                    val fragment: TimerFragment =
                        if (addingYes.cardBackgroundColor == context.getColorStateList(R.color.colorBlueDark)) {
                            TimerFragment.newInstance(10, true)
                        } else {
                            TimerFragment.newInstance(10, false)
                        }
                    fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(
                            R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                            R.anim.enter_left_to_right, R.anim.exit_right_to_left
                        )
                        .replace(R.id.timer_frame_layout, fragment)
                        .addToBackStack(CauldronMakeFragment.toString())
                        .commit()
                }
                time20.cardBackgroundColor == context.getColorStateList(R.color.colorBlueDark) -> {
                    val fragment: TimerFragment =
                        if (addingYes.cardBackgroundColor == context.getColorStateList(R.color.colorBlueDark)) {
                            TimerFragment.newInstance(20, true)
                        } else {
                            TimerFragment.newInstance(20, false)
                        }
                    fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(
                            R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                            R.anim.enter_left_to_right, R.anim.exit_right_to_left
                        )
                        .replace(R.id.timer_frame_layout, fragment)
                        .addToBackStack(CauldronMakeFragment.toString())
                        .commit()
                }
            }
        }
    }

    private fun initStartViews() {
        addingYes.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueDark))
        time5.setCardBackgroundColor(context.getColorStateList(R.color.colorBlueDark))
    }

    private fun attachViews() {
        addingYes = rootView.findViewById(R.id.timer_choices_adding_yes)
        addingNo = rootView.findViewById(R.id.timer_choices_adding_no)
        time3 = rootView.findViewById(R.id.timer_choices_time_3)
        time5 = rootView.findViewById(R.id.timer_choices_time_5)
        time10 = rootView.findViewById(R.id.timer_choices_time_10)
        time20 = rootView.findViewById(R.id.timer_choices_time_20)
        playButton = rootView.findViewById(R.id.timer_choices_button_play)
    }
}