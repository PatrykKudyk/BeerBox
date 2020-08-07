package com.partos.gamebox.fragments.mafia

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.partos.gamebox.R
import com.partos.flashback.db.DataBaseHelper


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
class MafiaAssignRolesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var size: Int? = null
    private var isStatic: Boolean? = null
    private var rolesList: ArrayList<Int>? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rolesAssignedList: ArrayList<String>
    private lateinit var namesAssignedList: ArrayList<String>

    private lateinit var rootView: View
    private lateinit var nextButton: Button
    private lateinit var assignButton: Button
    private lateinit var cardShow: CardView
    private lateinit var cardRole: CardView
    private lateinit var textRole: TextView
    private lateinit var editTextRole: EditText
    private lateinit var enterPanelButton: Button


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
        rootView = inflater.inflate(R.layout.fragment_mafia_assign_roles, container, false);
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
            MafiaAssignRolesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, size)
                    putBoolean(ARG_PARAM2, isStatic)
                    putIntegerArrayList(ARG_PARAM3, rolesArray)
                }
            }
    }

    private fun initFragment() {
        clearPlayersList()
        attachViews()
        assignRoles()
        initListeners()
    }

    private fun clearPlayersList() {
        val db = DataBaseHelper(rootView.context)
        val players = db.getPlayersList()
        for (player in players){
            db.deletePlayer(player.id)
        }
        val round = db.getMafiaRound()
        if (round.size != 0) {
            db.deleteMafiaRound(round[0])
        }
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
        if (!(isStatic as Boolean)) {
            rolesAssignedList.add(rootView.context.getString(R.string.mafia_role_game_master))
        }
        rolesAssignedList.shuffle()
    }

    private fun initListeners() {
        var position = 0
        assignButton.setOnClickListener {
            assignButton.visibility = View.GONE
            cardShow.visibility = View.VISIBLE
            namesAssignedList = ArrayList<String>()
        }
        cardShow.setOnClickListener {
            cardShow.visibility = View.GONE
            textRole.text = rolesAssignedList[position]
            cardRole.visibility = View.VISIBLE
            nextButton.visibility = View.VISIBLE
        }
        nextButton.setOnClickListener {
            if (editTextRole.text.toString() != "") {
                cardRole.visibility = View.GONE
                nextButton.visibility = View.GONE
                cardShow.visibility = View.VISIBLE
                namesAssignedList.add(editTextRole.text.toString().trim())
                editTextRole.setText("")
                position++
                if (position == rolesAssignedList.size) {
                    enterPanelButton.visibility = View.VISIBLE
                    cardShow.visibility = View.GONE
                }
                hideKeyboard()
            } else {
                Toast.makeText(
                    rootView.context,
                    rootView.context.getText(R.string.toast_no_name_given),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        enterPanelButton.setOnClickListener {
            addPlayersToDB()
            val fragment = MafiaGameFragment.newInstance()
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                ?.replace(R.id.mafia_frame_layout, fragment)
                ?.commit()
        }
    }

    private fun addPlayersToDB() {
        val db = DataBaseHelper(rootView.context)
        for (i in 0 until rolesAssignedList.size) {
            if(rolesAssignedList[i] != rootView.context.getString(R.string.mafia_role_game_master)) {
                db.addPlayer(rolesAssignedList[i], namesAssignedList[i], 1)
            }
        }
        db.addMafiaRound(1)
    }

    private fun attachViews() {
        nextButton = rootView.findViewById(R.id.mafia_assign_button_next)
        assignButton = rootView.findViewById(R.id.mafia_assign_button_assign)
        cardRole = rootView.findViewById(R.id.mafia_assign_card_role)
        cardShow = rootView.findViewById(R.id.mafia_assign_card_show)
        textRole = rootView.findViewById(R.id.mafia_assign_text_role)
        editTextRole = rootView.findViewById(R.id.mafia_assign_edit_role)
        enterPanelButton = rootView.findViewById(R.id.mafia_assign_button_panel)
    }

    private fun hideKeyboard() {
        val view = editTextRole
        val inputManager =
            rootView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}