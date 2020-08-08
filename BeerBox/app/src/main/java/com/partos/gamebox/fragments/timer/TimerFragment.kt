package com.partos.gamebox.fragments.timer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.partos.gamebox.R
import com.partos.gamebox.logic.timer.TimerLogic

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "time"
private const val ARG_PARAM2 = "isAdding"

/**
 * A simple [Fragment] subclass.
 * Use the [TimerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var time: Int? = null
    private var isAdding: Boolean? = null
    private lateinit var rootView: View
    private lateinit var logic: TimerLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            time = it.getInt(ARG_PARAM1)
            isAdding = it.getBoolean(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_timer, container, false)
        logic = TimerLogic(rootView, time as Int, isAdding as Boolean)
        logic.initFragment()
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TimerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(time: Int, isAdding: Boolean) =
            TimerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, time)
                    putBoolean(ARG_PARAM2, isAdding)
                }
            }
    }
}