package com.partos.gamebox.fragments.cauldron

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.R
import com.partos.gamebox.logic.cauldron.logic.CauldronMakeLogic
import com.partos.gamebox.models.Alcohol
import com.partos.gamebox.recycler.CauldronRecyclerViewAdapter
import com.partos.gamebox.recycler.MarginItemDecoration
import kotlin.random.Random


private const val ARG_PARAM1 = "alcoholArray"
private const val ARG_PARAM2 = "litres"

class CauldronMakeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var alcoholArray: ArrayList<Int>? = null
    private var litres: Int? = null
    private lateinit var rootView: View
    private lateinit var logic: CauldronMakeLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            alcoholArray = it.getIntegerArrayList(ARG_PARAM1)
            litres = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cauldron_make, container, false)
        logic = CauldronMakeLogic()
        logic.initFragment(rootView, alcoholArray as ArrayList<Int>, litres as Int)
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(alcoholsArray: ArrayList<Int>, litres: Int) =
            CauldronMakeFragment().apply {
                arguments = Bundle().apply {
                    putIntegerArrayList(ARG_PARAM1, alcoholsArray)
                    putInt(ARG_PARAM2, litres)
                }
            }
    }

}