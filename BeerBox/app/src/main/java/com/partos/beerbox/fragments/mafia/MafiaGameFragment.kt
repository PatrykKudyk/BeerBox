package com.partos.beerbox.fragments.mafia

import android.content.Context
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.recycler.MainMenuRecyclerViewAdapter
import com.partos.beerbox.recycler.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_main_menu.*
import kotlinx.android.synthetic.main.fragment_main_menu.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "size"
private const val ARG_PARAM2 = "isStatic"
private const val ARG_PARAM3 = "rolesList"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AccountFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MafiaGameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var size: Int? = null
    private var isStatic: Boolean? = null
    private var rolesList: ArrayList<Int>? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            size = it.getInt(ARG_PARAM1)
            isStatic = it.getBoolean(ARG_PARAM2)
            rolesList = it.getIntegerArrayList(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_mafia_game, container, false);
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
        fun newInstance(size: Int, isStatic: Boolean, rolesArray: ArrayList<Int>) =
            MafiaGameFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, size)
                    putBoolean(ARG_PARAM2, isStatic)
                    putIntegerArrayList(ARG_PARAM3, rolesArray)
                }
            }
    }

    private fun initFragment() {

    }
}