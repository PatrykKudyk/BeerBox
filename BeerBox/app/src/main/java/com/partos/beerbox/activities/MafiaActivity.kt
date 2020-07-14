package com.partos.beerbox.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.partos.beerbox.R
import com.partos.beerbox.fragments.mafia.MafiaGameSizeFragment

class MafiaActivity : AppCompatActivity(),
    MafiaGameSizeFragment.OnFragmentInteractionListener {
    private lateinit var gameSizeFragment:  MafiaGameSizeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mafia)

        gameSizeFragment = MafiaGameSizeFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.mafia_frame_layout, gameSizeFragment)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}