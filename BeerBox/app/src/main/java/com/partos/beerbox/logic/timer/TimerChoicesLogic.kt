package com.partos.beerbox.logic.timer

import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import com.partos.beerbox.R

class TimerChoicesLogic(val rootView: View) {

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