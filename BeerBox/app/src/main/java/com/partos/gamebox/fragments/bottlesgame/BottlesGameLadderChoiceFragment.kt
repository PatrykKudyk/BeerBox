package com.partos.beerbox.fragments.bottlesgame

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
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
class BottlesGameLadderChoiceFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var teams: ArrayList<String>? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var groupButton: Button
    private lateinit var championshipButton: Button

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
        rootView = inflater.inflate(R.layout.fragment_bottles_game_ladder_choice, container, false);
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
            BottlesGameLadderChoiceFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_PARAM1, teams)
                }
            }
    }

    private fun initFragment() {
        groupButton = rootView.findViewById(R.id.bottles_game_ladder_group)
        championshipButton = rootView.findViewById(R.id.bottles_game_ladder_championship)

        groupButton.setOnClickListener {
            if ((teams as ArrayList<String>).size >= 5) {
                val fragment = BottlesGameGroupFragment.newInstance(teams as ArrayList<String>)
                fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                        R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                        R.anim.enter_left_to_right, R.anim.exit_right_to_left
                    )
                    ?.replace(R.id.bottles_game_frame_layout, fragment)
                    ?.commit()
            } else {
                Toast.makeText(
                    rootView.context,
                    rootView.context.getString(R.string.toast_too_few_teams),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        championshipButton.setOnClickListener {
            val fragment = BottlesGameChampionshipFragment.newInstance(teams as ArrayList<String>)
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                ?.replace(R.id.bottles_game_frame_layout, fragment)
                ?.commit()
        }
    }
}