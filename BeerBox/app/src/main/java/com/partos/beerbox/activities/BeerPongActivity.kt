package com.partos.beerbox.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import com.partos.beerbox.R
import com.partos.beerbox.fragments.MainMenuFragment
import com.partos.beerbox.fragments.beerpong.BeerPongChampionshipFragment
import com.partos.beerbox.fragments.beerpong.BeerPongGroupFragment
import com.partos.beerbox.fragments.beerpong.BeerPongLadderChoiceFragment
import com.partos.beerbox.fragments.beerpong.BeerPongTeamsFragment

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

}