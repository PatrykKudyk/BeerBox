package com.partos.gamebox.fragments.dices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.partos.gamebox.R
import com.partos.gamebox.logic.dices.logic.DicesLogic

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DicesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DicesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var number: Int? = null
    private var param2: String? = null
    private lateinit var rootView: View
    private lateinit var logic: DicesLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            number = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_dices, container, false)
        logic = DicesLogic()
        logic.initFragment(rootView, number as Int)
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DicesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(number: Int) =
            DicesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, number)
                }
            }
    }
}