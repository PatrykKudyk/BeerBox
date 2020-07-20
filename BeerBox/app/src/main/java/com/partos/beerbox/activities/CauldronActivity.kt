package com.partos.beerbox.activities

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.partos.beerbox.R
import com.partos.beerbox.fragments.cauldron.CauldronChoicesFragment
import com.partos.beerbox.fragments.cauldron.CauldronMakeFragment

class CauldronActivity : AppCompatActivity(),
    CauldronChoicesFragment.OnFragmentInteractionListener,
    CauldronMakeFragment.OnFragmentInteractionListener {

    private lateinit var fragment: CauldronChoicesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cauldron)

        fragment = CauldronChoicesFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.cauldron_frame_layout, fragment)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onBackPressed() {
        supportFragmentManager
            .popBackStack()

        super.onBackPressed()
    }
}