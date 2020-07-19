package com.partos.beerbox.fragments.mafia

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.models.MafiaPlayer
import com.partos.beerbox.recycler.DayPanelRecyclerView
import com.partos.beerbox.recycler.MarginItemDecoration
import com.partos.beerbox.recycler.NightPanelRecyclerView
import java.lang.RuntimeException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "size"
private const val ARG_PARAM2 = "isStatic"
private const val ARG_PARAM3 = "rolesList"
private const val ARG_PARAM4 = "namesList"

/**
 * A simple [Fragment] subclass.
 * Use the [MafiaGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MafiaGameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var size: Int? = null
    private var isStatic: Boolean? = null
    private var rolesList: ArrayList<String>? = null
    private var namesList: ArrayList<String>? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var dayPanel: RecyclerView
    private lateinit var nightPanel: ConstraintLayout
    private lateinit var changePanelButton: Button
    private lateinit var changePanelButton2: Button

    private lateinit var rolesAssignedList: ArrayList<MafiaPlayer>
    private lateinit var nightRoles: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            size = it.getInt(ARG_PARAM1)
            isStatic = it.getBoolean(ARG_PARAM2)
            rolesList = it.getStringArrayList(ARG_PARAM3)
            namesList = it.getStringArrayList(ARG_PARAM4)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_mafia_game, container, false)
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
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(size: Int, isStatic: Boolean, rolesArray: ArrayList<String>, namesArray: ArrayList<String>) =
            MafiaGameFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, size)
                    putBoolean(ARG_PARAM2, isStatic)
                    putStringArrayList(ARG_PARAM3, rolesArray)
                    putStringArrayList(ARG_PARAM4, namesArray)
                }
            }
    }

    private fun initFragment() {
        attachViews()
        assignRoles()
        assignNightRoles()
        val dayLayoutManager = LinearLayoutManager(this.context)
        dayPanel.layoutManager = dayLayoutManager
        dayPanel.addItemDecoration(MarginItemDecoration(12))
        dayPanel.adapter = DayPanelRecyclerView(rolesAssignedList)

        changePanelButton.setOnClickListener {
            changePanelButton.visibility = View.GONE
            changePanelButton2.visibility = View.VISIBLE
            dayPanel.visibility = View.GONE
            nightPanel.visibility = View.VISIBLE
        }
        changePanelButton2.setOnClickListener {
            changePanelButton.visibility = View.VISIBLE
            changePanelButton2.visibility = View.GONE
            dayPanel.visibility = View.VISIBLE
            nightPanel.visibility = View.GONE
            hideKeyboard()
        }

    }

    private fun assignRoles() {
        rolesAssignedList = ArrayList()
        for (i in 0 until rolesList?.size!!) {
            rolesAssignedList.add(MafiaPlayer(rolesList!![i], namesList!![i]))
        }
    }

    private fun assignNightRoles() {
        nightRoles = ArrayList()
        for (i in rolesList as ArrayList<String>) {
            if (i == rootView.context.getString(R.string.mafia_role_medic)) {
                nightRoles.add(rootView.context.getString(R.string.healed))
            }
            if (i == rootView.context.getString(R.string.mafia_role_courtesan)){
                nightRoles.add(rootView.context.getString(R.string.hooked_up))
            }
        }
        nightRoles.add(rootView.context.getString(R.string.killed))
    }


    private fun attachViews() {
        dayPanel = rootView.findViewById(R.id.mafia_game_day_panel)
        nightPanel = rootView.findViewById(R.id.mafia_game_night_panel)
        changePanelButton = rootView.findViewById(R.id.mafia_game_button_change_panel)
        changePanelButton2 = rootView.findViewById(R.id.mafia_game_button_change_panel2)
    }

    private fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val inputManager =
                rootView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}