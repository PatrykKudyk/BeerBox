package com.partos.gamebox.fragments.cauldron

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.partos.gamebox.R
import com.partos.gamebox.logic.cauldron.logic.CauldronMenuLogic

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CauldronMenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rootView: View
    private lateinit var logic: CauldronMenuLogic


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_cauldron_menu, container, false)
        logic = CauldronMenuLogic()
        logic.initFragment(rootView, fragmentManager as FragmentManager)
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CauldronMenuFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}