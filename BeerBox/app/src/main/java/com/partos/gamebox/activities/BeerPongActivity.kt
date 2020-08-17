package com.partos.gamebox.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.partos.gamebox.MyApp
import com.partos.gamebox.R
import com.partos.gamebox.fragments.beerpong.BeerPongChampionshipFragment
import com.partos.gamebox.fragments.beerpong.BeerPongGroupFragment
import com.partos.gamebox.fragments.beerpong.BeerPongLadderChoiceFragment
import com.partos.gamebox.fragments.beerpong.BeerPongTeamsFragment

class BeerPongActivity : AppCompatActivity(),
    BeerPongTeamsFragment.OnFragmentInteractionListener,
    BeerPongLadderChoiceFragment.OnFragmentInteractionListener,
    BeerPongChampionshipFragment.OnFragmentInteractionListener,
    BeerPongGroupFragment.OnFragmentInteractionListener {

    private lateinit var teamsFragment: BeerPongTeamsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_pong)

        teamsFragment = BeerPongTeamsFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.beer_pong_frame_layout, teamsFragment)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onBackPressed() {
        MyApp.areTeamsOpened = false
        super.onBackPressed()
    }
}