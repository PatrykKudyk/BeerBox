package com.partos.beerbox.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.partos.beerbox.R
import com.partos.beerbox.fragments.mafia.*
import com.partos.flashback.db.DataBaseHelper

class MafiaActivity : AppCompatActivity(),
    MafiaGameSizeFragment.OnFragmentInteractionListener,
    MafiaRolesChoiceFragment.OnFragmentInteractionListener,
    MafiaAssignRolesFragment.OnFragmentInteractionListener,
    MafiaGameFragment.OnFragmentInteractionListener {

    private lateinit var gameSizeFragment: MafiaGameSizeFragment
    private lateinit var gameChoiceFragment: MafiaChoiceFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mafia)

        val db = DataBaseHelper(this)
        val players = db.getPlayersList()
        if (players.size == 0) {
            gameSizeFragment = MafiaGameSizeFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.mafia_frame_layout, gameSizeFragment)
                .commit()
        } else {
             gameChoiceFragment = MafiaChoiceFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.mafia_frame_layout, gameChoiceFragment)
                .commit()
        }


    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}