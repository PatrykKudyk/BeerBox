package com.partos.beerbox.fragments.cauldron

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.partos.beerbox.R
import com.partos.beerbox.activities.CauldronActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CauldronMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CauldronMenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var image: ImageView
    private lateinit var makeCauldronButton: Button
    private lateinit var howToMakeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cauldron_menu, container, false)
        initFragment()
        return rootView
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
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CauldronMenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            CauldronMenuFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun initFragment() {
        attachViews()
        makeAnimations()
        createListeners()
    }

    private fun createListeners() {
        makeCauldronButton.setOnClickListener {
            val intent = Intent(rootView.context, CauldronActivity::class.java)
            rootView.context.startActivity(intent)
        }

        howToMakeButton.setOnClickListener {
            val howToMakeFragment = CauldronHowToMakeFragment.newInstance()
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                ?.replace(R.id.main_frame_layout, howToMakeFragment)
                ?.addToBackStack(CauldronHowToMakeFragment.toString())
                ?.commit()
        }
    }

    private fun attachViews() {
        image = rootView.findViewById(R.id.cauldron_menu_image)
        makeCauldronButton = rootView.findViewById(R.id.cauldron_menu_make)
        howToMakeButton = rootView.findViewById(R.id.cauldron_menu_how_to_make)
    }

    private fun makeAnimations() {
        val animLeft = AnimationUtils.loadAnimation(rootView.context, R.anim.enter_left_to_right)
        val animRight = AnimationUtils.loadAnimation(rootView.context, R.anim.enter_right_to_left)

        Handler().postDelayed({
            makeCauldronButton.visibility = View.VISIBLE
            howToMakeButton.visibility = View.VISIBLE
            makeCauldronButton.startAnimation(animLeft)
            howToMakeButton.startAnimation(animRight)
        }, 400)

    }

}