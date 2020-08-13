package com.partos.gamebox.recycler

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.R
import com.partos.gamebox.logic.TimeFormatter
import com.partos.gamebox.logic.TimerThread
import kotlinx.android.synthetic.main.row_timer.view.*

class TimersRecyclerViewAdapter() : RecyclerView.Adapter<TimersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_timer, parent, false)
        return TimersViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: TimersViewHolder, position: Int) {

        val image1 = holder.view.row_timer_image
        val image2 = holder.view.row_timer_image2
        val context = holder.view.context
        val timeText = holder.view.row_timer_time
        var state = 0
        var time = 0
        lateinit var threadHandler: Handler
        when (position) {
            0 -> time = 15000
            1 -> time = 30000
            2 -> time = 45000
            3 -> time = 60000
            4 -> time = 90000
            5 -> time = 120000
            6 -> time = 300000
        }
        showTime(timeText, time)
        image2.setImageDrawable(context.getDrawable(R.drawable.ic_play_yellow))
        image2.setOnClickListener {
            if (state == 0) {
                val looperThread = TimerThread()
                looperThread.start()
                state = 1
                image1.visibility = View.VISIBLE
                image1.setImageDrawable(context.getDrawable(R.drawable.ic_stop_yellow))
                image2.setImageDrawable(context.getDrawable(R.drawable.ic_pause_yellow))
                Handler().postDelayed({
                    threadHandler = Handler(looperThread.looper)
                    threadHandler.post(object : Runnable {
                        override fun run() {
                            if (state == 1) {
                                time -= 50
                                showTime(timeText, time)
                            }
                            if (time > 0) {
                                threadHandler.postDelayed(this, 50)
                            } else {
                                threadHandler.looper.quitSafely()
                                state = 0
                                image1.visibility = View.INVISIBLE
                                image2.setImageDrawable(context.getDrawable(R.drawable.ic_play_yellow))
                                when (position) {
                                    0 -> time = 15000
                                    1 -> time = 30000
                                    2 -> time = 45000
                                    3 -> time = 60000
                                    4 -> time = 90000
                                    5 -> time = 120000
                                    6 -> time = 300000
                                }
                                showTime(timeText, time)
//                        looperThread.looper.quitSafely()
                            }
                        }
                    })
                }, 300)
            } else if (state == 1) {
                image2.setImageDrawable(context.getDrawable(R.drawable.ic_play_yellow))
                state = 2
            } else if (state == 2) {
                image2.setImageDrawable(context.getDrawable(R.drawable.ic_pause_yellow))
                state = 1
            }
        }
        image1.setOnClickListener {
            state = 0
            image1.visibility = View.INVISIBLE
            image2.setImageDrawable(context.getDrawable(R.drawable.ic_play_yellow))
            when (position) {
                0 -> time = 15000
                1 -> time = 30000
                2 -> time = 45000
                3 -> time = 60000
                4 -> time = 90000
                5 -> time = 120000
                6 -> time = 300000
            }
            showTime(timeText, time)
            threadHandler.looper.quitSafely()
        }
    }


    private fun showTime(textView: TextView, time: Int) {
        if (time < 10000) {
            textView.text = TimeFormatter().formatShortTime(time)
        } else {
            textView.text = TimeFormatter().formatNormalTime(time / 1000)
        }

    }
}

class TimersViewHolder(val view: View) : RecyclerView.ViewHolder(view)