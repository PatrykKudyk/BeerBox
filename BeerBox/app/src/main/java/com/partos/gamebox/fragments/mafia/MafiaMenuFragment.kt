package com.partos.gamebox.fragments.mafia

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.partos.gamebox.R
import com.partos.gamebox.activities.MafiaActivity
import com.partos.gamebox.fragments.bottlesgame.BottlesGameRulesFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AccountFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MafiaMenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var image: ImageView
    private lateinit var playButton: Button
    private lateinit var rulesButton: Button
    private lateinit var rolesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_mafia_menu, container, false);
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
        fun newInstance() =
            MafiaMenuFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun initFragment() {
        playButton = rootView.findViewById(R.id.mafia_menu_play)
        rulesButton = rootView.findViewById(R.id.mafia_menu_rules)
        rolesButton = rootView.findViewById(R.id.mafia_menu_roles)
        image = rootView.findViewById(R.id.mafia_menu_image)

        makeAnimations()


        rulesButton.setOnClickListener {
            val rulesFragment = MafiaRulesFragment.newInstance()
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                ?.replace(R.id.main_frame_layout, rulesFragment)
                ?.addToBackStack(BottlesGameRulesFragment.toString())
                ?.commit()
        }

        rolesButton.setOnClickListener {
            val rolesFragment = MafiaRolesFragment.newInstance()
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                ?.replace(R.id.main_frame_layout, rolesFragment)
                ?.addToBackStack(MafiaRolesFragment.toString())
                ?.commit()
        }

        playButton.setOnClickListener {
            val intent = Intent(rootView.context, MafiaActivity::class.java)
            rootView.context.startActivity(intent)
        }
    }

    private fun makeAnimations(){
        val animLeft = AnimationUtils.loadAnimation(rootView.context, R.anim.enter_left_to_right)
        val animRight = AnimationUtils.loadAnimation(rootView.context, R.anim.enter_right_to_left)

        Handler().postDelayed({
            playButton.visibility = View.VISIBLE
            rulesButton.visibility = View.VISIBLE
            rolesButton.visibility = View.VISIBLE
            playButton.startAnimation(animLeft)
            rulesButton.startAnimation(animRight)
            rolesButton.startAnimation(animLeft)
        }, 400)

    }
}