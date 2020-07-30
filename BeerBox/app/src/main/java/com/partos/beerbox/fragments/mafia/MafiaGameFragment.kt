package com.partos.beerbox.fragments.mafia

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.models.Player
import com.partos.beerbox.recycler.DayPanelRecyclerViewAdapter
import com.partos.beerbox.recycler.MarginItemDecoration
import com.partos.beerbox.recycler.NightPanelActionsRecyclerViewAdapter
import com.partos.beerbox.recycler.NightPanelRolesRecyclerViewAdapter
import com.partos.flashback.db.DataBaseHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "size"
private const val ARG_PARAM2 = "isStatic"

/**
 * A simple [Fragment] subclass.
 * Use the [MafiaGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MafiaGameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var size: Int? = null
    private var isStatic: Boolean? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var dayPanel: RecyclerView
    private lateinit var nightPanel: ConstraintLayout
    private lateinit var changePanelButton: Button
    private lateinit var changePanelButton2: Button
    private lateinit var nightPanelRoles: RecyclerView
    private lateinit var nightPanelActions: RecyclerView

    private lateinit var playersList: ArrayList<Player>
    private lateinit var nightRoles: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            size = it.getInt(ARG_PARAM1)
            isStatic = it.getBoolean(ARG_PARAM2)
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
        fun newInstance() =
            MafiaGameFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun initFragment() {
        val db = DataBaseHelper(rootView.context)
        playersList = db.getPlayersList()

        attachViews()
        assignNightRoles()

        val dayLayoutManager = LinearLayoutManager(this.context)
        val nPActionLayoutManager = LinearLayoutManager(this.context)
        val nPRolesLayoutManager = LinearLayoutManager(this.context)

        dayPanel.layoutManager = dayLayoutManager
        dayPanel.addItemDecoration(MarginItemDecoration(12))
        dayPanel.adapter = DayPanelRecyclerViewAdapter(playersList)

        nightPanelActions.layoutManager = nPActionLayoutManager
        nightPanelActions.addItemDecoration(MarginItemDecoration(12))
        nightPanelRoles.layoutManager = nPRolesLayoutManager
        nightPanelRoles.addItemDecoration(MarginItemDecoration(12))

        changePanelButton.setOnClickListener {
            changePanelButton.visibility = View.GONE
            changePanelButton2.visibility = View.VISIBLE
            dayPanel.visibility = View.GONE
            nightPanel.visibility = View.VISIBLE
            playersList = db.getPlayersList()
            nightPanelRoles.adapter = NightPanelRolesRecyclerViewAdapter(playersList)
            nightPanelActions.adapter = NightPanelActionsRecyclerViewAdapter(nightRoles)
        }
        changePanelButton2.setOnClickListener {
            changePanelButton.visibility = View.VISIBLE
            changePanelButton2.visibility = View.GONE
            dayPanel.visibility = View.VISIBLE
            nightPanel.visibility = View.GONE
            playersList = db.getPlayersList()
            dayPanel.adapter = DayPanelRecyclerViewAdapter(playersList)
            hideKeyboard()
        }

    }

    private fun assignNightRoles() {
        nightRoles = ArrayList()
        for (i in playersList) {
            if (i.role == rootView.context.getString(R.string.mafia_role_medic)) {
                nightRoles.add(rootView.context.getString(R.string.healed))
            }
            if (i.role == rootView.context.getString(R.string.mafia_role_courtesan)) {
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
        nightPanelActions = rootView.findViewById(R.id.mafia_game_night_panel_actions)
        nightPanelRoles = rootView.findViewById(R.id.mafia_game_night_panel_roles)
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