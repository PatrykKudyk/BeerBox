package com.partos.beerbox.logic.timer

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.partos.beerbox.R
import com.partos.beerbox.logic.TimeFormatter
import com.partos.beerbox.logic.TimerThread

class TimerLogic(val rootView: View, val time: Int, val isAdding: Boolean) {

    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var timer1: TextView
    private lateinit var timer2: TextView
    private var turn = -1
    private var time1 = 0
    private var time2 = 0
    private var timeFormatter = TimeFormatter()
    private var adding = false
    private lateinit var soundPool: SoundPool
    private var soundEnd = 2

    fun initFragment() {
        attachViews()
        startViews()
        initSoundPool()
        attachListeners()
    }

    private fun attachListeners() {
        image1.setOnClickListener {
            if (!isEnd()) {
                if (turn == 1) {
                    image2.setImageDrawable(rootView.context.getDrawable(R.drawable.ic_pause))
                    image1.setImageDrawable(rootView.context.getDrawable(R.drawable.ic_play))
                    turn = 2
                    if (isAdding) {
                        if (adding) {
                            time1 += 1500
                            adding = false
                        }
                    }
                } else if (turn == -1) {
                    image1.setImageDrawable(rootView.context.getDrawable(R.drawable.ic_pause))
                    turn = 1
                    mainLoop()
                }
            }
        }
        image2.setOnClickListener {
            if (!isEnd()) {
                if (turn == 2) {
                    image1.setImageDrawable(rootView.context.getDrawable(R.drawable.ic_pause))
                    image2.setImageDrawable(rootView.context.getDrawable(R.drawable.ic_play))
                    turn = 1
                    if (isAdding) {
                        if (adding) {
                            time2 += 1500
                            adding = false
                        }
                    }
                } else if (turn == -1) {
                    image2.setImageDrawable(rootView.context.getDrawable(R.drawable.ic_pause))
                    turn = 2
                    mainLoop()
                }
            }
        }
    }

    private fun mainLoop() {
        var looperThread = TimerThread()
        looperThread.start()
        Handler().postDelayed({
            var threadHandler = Handler(looperThread.looper)
            threadHandler.post(object : Runnable {
                override fun run() {
                    if (turn == 1) {
                        time1 -= 50
                    } else {
                        time2 -= 50
                    }
                    showTime()
                    if (!isEnd()) {
                        threadHandler.postDelayed(this, 50)
                    } else {
                        soundPool.play(soundEnd, 1F, 1F, 0, 0, 1F)
                        looperThread.looper.quitSafely()
                    }
                }
            })
        }, 300)

    }

    private fun showTime() {
        if (time1 < 10000) {
            timer1.text = timeFormatter.formatShortTime(time1)
            adding = true
        } else {
            timer1.text = timeFormatter.formatNormalTime(time1 / 1000)
        }
        if (time2 < 10000) {
            timer2.text = timeFormatter.formatShortTime(time2)
            adding = true
        } else {
            timer2.text = timeFormatter.formatNormalTime(time2 / 1000)
        }
    }

    private fun isEnd(): Boolean {
        if (time1 == 0 || time2 == 0) {
            return true
        }
        return false
    }

    private fun initSoundPool() {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(2)
            .setAudioAttributes(audioAttributes)
            .build()

        soundEnd = soundPool.load(rootView.context, R.raw.alarm, 1)
    }

    private fun startViews() {
        when (time) {
            3 -> {
                timer1.text = timeFormatter.formatNormalTime(180)
                timer2.text = timeFormatter.formatNormalTime(180)
                time1 = 180000
                time2 = 180000
//                time1 = 15000
//                time2 = 15000
            }
            5 -> {
                timer1.text = timeFormatter.formatNormalTime(300)
                timer2.text = timeFormatter.formatNormalTime(300)
                time1 = 300000
                time2 = 300000
            }
            10 -> {
                timer1.text = timeFormatter.formatNormalTime(600)
                timer2.text = timeFormatter.formatNormalTime(600)
                time1 = 600000
                time2 = 600000
            }
            20 -> {
                timer1.text = timeFormatter.formatNormalTime(1200)
                timer2.text = timeFormatter.formatNormalTime(1200)
                time1 = 1200000
                time2 = 1200000
            }
        }
    }

    private fun attachViews() {
        image1 = rootView.findViewById(R.id.timer_image_1)
        image2 = rootView.findViewById(R.id.timer_image_2)
        timer1 = rootView.findViewById(R.id.timer_text_1)
        timer2 = rootView.findViewById(R.id.timer_text_2)
    }
}