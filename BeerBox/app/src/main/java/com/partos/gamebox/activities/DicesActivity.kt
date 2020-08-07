package com.partos.gamebox.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.partos.gamebox.R
import com.partos.gamebox.fragments.dices.DicesChoiceFragment
import com.partos.gamebox.fragments.timer.TimerChoicesFragment

class DicesActivity : AppCompatActivity() {

    private lateinit var dicesFragment: DicesChoiceFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dices)

        dicesFragment = DicesChoiceFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.timer_frame_layout, dicesFragment)
            .commit()
    }
}