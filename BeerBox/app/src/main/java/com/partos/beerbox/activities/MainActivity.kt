package com.partos.beerbox.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.partos.beerbox.R
import com.partos.beerbox.fragments.MainMenuFragment
import com.partos.beerbox.fragments.beerpong.BeerPongMenuFragment
import com.partos.beerbox.fragments.beerpong.BeerPongRulesFragment
import com.partos.beerbox.fragments.bottlesgame.BottlesGameMenuFragment
import com.partos.beerbox.fragments.bottlesgame.BottlesGameRulesFragment
import com.partos.beerbox.fragments.mafia.MafiaMenuFragment
import com.partos.beerbox.fragments.mafia.MafiaRolesFragment
import com.partos.beerbox.fragments.mafia.MafiaRulesFragment

class MainActivity : AppCompatActivity(),
    MainMenuFragment.OnFragmentInteractionListener,
    BeerPongMenuFragment.OnFragmentInteractionListener,
    BeerPongRulesFragment.OnFragmentInteractionListener,
    BottlesGameMenuFragment.OnFragmentInteractionListener,
    BottlesGameRulesFragment.OnFragmentInteractionListener,
    MafiaMenuFragment.OnFragmentInteractionListener,
    MafiaRulesFragment.OnFragmentInteractionListener,
    MafiaRolesFragment.OnFragmentInteractionListener {

    private lateinit var mainMenuFragment: MainMenuFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainMenuFragment = MainMenuFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_bottom_to_top,
                R.anim.exit_top_to_bottom,
                R.anim.enter_top_to_bottom,
                R.anim.exit_bottom_to_top
            )
            .add(R.id.main_frame_layout, mainMenuFragment)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}