package com.partos.gamebox.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.partos.gamebox.R
import com.partos.gamebox.fragments.timer.TimerChoicesFragment

class TimerActivity : AppCompatActivity() {

    private lateinit var choicesFragment: TimerChoicesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        choicesFragment = TimerChoicesFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.timer_frame_layout, choicesFragment)
            .commit()
    }

    override fun onBackPressed() {
        finish()
    }
}