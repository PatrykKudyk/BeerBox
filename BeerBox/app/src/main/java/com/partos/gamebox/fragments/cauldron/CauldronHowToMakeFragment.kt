package com.partos.gamebox.fragments.cauldron


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.partos.gamebox.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CauldronHowToMakeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cauldron_how_to_make, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CauldronHowToMakeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}