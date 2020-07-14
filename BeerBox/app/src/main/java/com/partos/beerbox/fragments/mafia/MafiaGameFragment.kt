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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.recycler.DayPanelRecyclerView
import com.partos.beerbox.recycler.MarginItemDecoration
import com.partos.beerbox.recycler.NightPanelRecyclerView
import java.lang.RuntimeException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "size"
private const val ARG_PARAM2 = "isStatic"
private const val ARG_PARAM3 = "rolesList"

/**
 * A simple [Fragment] subclass.
 * Use the [MafiaGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MafiaGameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var size: Int? = null
    private var isStatic: Boolean? = null
    private var rolesList: ArrayList<Int>? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var dayPanel: RecyclerView
    private lateinit var nightPanel: RecyclerView
    private lateinit var changePanelButton: Button
    private lateinit var changePanelButton2: Button

    private lateinit var rolesAssignedList: ArrayList<String>
    private lateinit var nightRoles: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            size = it.getInt(ARG_PARAM1)
            isStatic = it.getBoolean(ARG_PARAM2)
            rolesList = it.getIntegerArrayList(ARG_PARAM3)
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
        attachViews()
        assignRoles()
        assightNightRoles()
        val dayLayoutManager = LinearLayoutManager(this.context)
        val nightLayoutManager = LinearLayoutManager(this.context)
        dayPanel.layoutManager = dayLayoutManager
        dayPanel.addItemDecoration(MarginItemDecoration(12))
        dayPanel.adapter = DayPanelRecyclerView(rolesAssignedList)
        nightPanel.layoutManager = nightLayoutManager
        nightPanel.addItemDecoration(MarginItemDecoration(12))

        changePanelButton.setOnClickListener {
            changePanelButton.visibility = View.GONE
            changePanelButton2.visibility = View.VISIBLE
            dayPanel.visibility = View.GONE
            nightPanel.visibility = View.VISIBLE
            nightPanel.adapter = NightPanelRecyclerView(nightRoles)
            hideKeyboard()
        }
        changePanelButton2.setOnClickListener {
            changePanelButton.visibility = View.VISIBLE
            changePanelButton2.visibility = View.GONE
            dayPanel.visibility = View.VISIBLE
            nightPanel.visibility = View.GONE
            hideKeyboard()
        }

    }

    private fun assightNightRoles() {
        nightRoles = ArrayList()
        if (rolesList?.get(1) == 1) {
            nightRoles.add(rootView.context.getString(R.string.healed))
        }
        if (rolesList?.get(3) == 1) {
            nightRoles.add(rootView.context.getString(R.string.hooked_up))
        }
        nightRoles.add(rootView.context.getString(R.string.killed))
    }

    private fun assignRoles() {
        rolesAssignedList = ArrayList()
        if (rolesList?.get(0) == 1) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_cattani))
        }
        if (rolesList?.get(1) == 1) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_medic))
        }
        if (rolesList?.get(2) == 1) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_sniper))
        }
        if (rolesList?.get(3) == 1) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_courtesan))
        }
        if (rolesList?.get(4) == 1) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_jude))
        }
        if (rolesList?.get(5) == 1) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_lawyer))
        }
        if (rolesList?.get(6) == 1) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_judge))
        }
        if (size == 7 || size == 8) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
        } else if (size!! >= 9 && size!! <= 11) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
        } else if (size!! >= 12 && size!! <= 14) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
        } else if (size!! >= 15 && size!! <= 17) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_mafia))
        }
        for (i in (rolesAssignedList.size)..(size as Int) - 2) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_city))
        }
        rolesAssignedList.shuffle()
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