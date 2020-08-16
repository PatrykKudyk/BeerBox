package com.partos.gamebox.fragments.cauldron

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.partos.gamebox.R
import com.partos.gamebox.logic.cauldron.logic.CauldronSavedLogic

private const val ARG_PARAM1 = "cauldronId"
private const val ARG_PARAM2 = "param2"

class CauldronSavedFragment : Fragment() {
    private var cauldronId: Long? = null
    private var param2: String? = null
    private lateinit var rootView: View
    private lateinit var logic: CauldronSavedLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cauldronId = it.getLong(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_cauldron_saved, container, false)
        logic = CauldronSavedLogic()
        logic.initFragment(rootView, cauldronId as Long)
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(cauldronId: Long) =
            CauldronSavedFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_PARAM1, cauldronId)
                }
            }
    }
}