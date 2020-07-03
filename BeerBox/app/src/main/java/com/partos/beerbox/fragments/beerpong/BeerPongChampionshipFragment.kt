package com.partos.beerbox.fragments.beerpong

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.partos.beerbox.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "teams"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AccountFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BeerPongChampionshipFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var teams: ArrayList<String>? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var constraint2: ConstraintLayout
    private lateinit var constraint4: ConstraintLayout
    private lateinit var constraint8: ConstraintLayout
    private lateinit var constraint16: ConstraintLayout
    private lateinit var card21: CardView
    private lateinit var card22: CardView
    private lateinit var card2win: CardView
    private lateinit var text21: TextView
    private lateinit var text22: TextView
    private lateinit var text2win: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            teams = it.getStringArrayList(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_beer_pong_championship, container, false);
        initFragment()
        return rootView
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(teams: ArrayList<String>) =
            BeerPongChampionshipFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_PARAM1, teams)
                }
            }
    }

    private fun initFragment() {
        attachViews()
        if (teams?.size == 2) {
            handleGame2()
        } else if (teams?.size!! <= 4) {
            handleGame4()
        }
    }

    private fun attachViews() {
        constraint2 = rootView.findViewById(R.id.beer_pong_championship_2)
        constraint4 = rootView.findViewById(R.id.beer_pong_championship_4)
        constraint8 = rootView.findViewById(R.id.beer_pong_championship_8)
        constraint16 = rootView.findViewById(R.id.beer_pong_championship_16)
        card21 = rootView.findViewById(R.id.beer_pong_championship_2_1_card)
        card22 = rootView.findViewById(R.id.beer_pong_championship_2_2_card)
        card2win = rootView.findViewById(R.id.beer_pong_championship_2_win_card)
        text21 = rootView.findViewById(R.id.beer_pong_championship_2_1_text)
        text22 = rootView.findViewById(R.id.beer_pong_championship_2_2_text)
        text2win = rootView.findViewById(R.id.beer_pong_championship_2_win_text)
    }

    private fun handleGame2() {
        handleGame2Start()
        card21.setOnClickListener {
            card21.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            card22.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLightLight
                )
            )
            card2win.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            text2win.setText(text21.text.toString())
        }

        card22.setOnClickListener {
            card22.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            card21.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLightLight
                )
            )
            card2win.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            text2win.setText(text22.text.toString())
        }
    }

    private fun handleGame2Start() {
        text21.setText(teams?.get(0))
        text22.setText(teams?.get(1))
        constraint2.visibility = View.VISIBLE
    }

    private fun handleGame4(){
        handleGame4Start()
    }

    private fun handleGame4Start() {

    }
}