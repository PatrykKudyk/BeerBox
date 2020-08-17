package com.partos.gamebox.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.partos.gamebox.R
import com.partos.gamebox.fragments.MainMenuFragment
import com.partos.gamebox.fragments.beerpong.BeerPongMenuFragment
import com.partos.gamebox.fragments.beerpong.BeerPongRulesFragment
import com.partos.gamebox.fragments.bottlesgame.BottlesGameMenuFragment
import com.partos.gamebox.fragments.bottlesgame.BottlesGameRulesFragment
import com.partos.gamebox.fragments.cauldron.CauldronHowToMakeFragment
import com.partos.gamebox.fragments.cauldron.CauldronMenuFragment
import com.partos.gamebox.fragments.mafia.MafiaMenuFragment
import com.partos.gamebox.fragments.mafia.MafiaRolesFragment
import com.partos.gamebox.fragments.mafia.MafiaRulesFragment

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