package com.partos.gamebox.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.partos.gamebox.MyApp
import com.partos.gamebox.R
import com.partos.gamebox.fragments.bottlesgame.BottlesGameChampionshipFragment
import com.partos.gamebox.fragments.bottlesgame.BottlesGameGroupFragment
import com.partos.gamebox.fragments.bottlesgame.BottlesGameLadderChoiceFragment
import com.partos.gamebox.fragments.bottlesgame.BottlesGameTeamsFragment

class BottlesGameActivity : AppCompatActivity(),
    BottlesGameTeamsFragment.OnFragmentInteractionListener,
    BottlesGameLadderChoiceFragment.OnFragmentInteractionListener,
    BottlesGameChampionshipFragment.OnFragmentInteractionListener,
    BottlesGameGroupFragment.OnFragmentInteractionListener {

    private lateinit var teamsFragment: BottlesGameTeamsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottles_game)

        teamsFragment = BottlesGameTeamsFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.bottles_game_frame_layout, teamsFragment)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onBackPressed() {
        MyApp.areTeamsOpened = false
        super.onBackPressed()
    }
}