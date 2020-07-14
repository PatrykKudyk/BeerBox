package com.partos.beerbox.fragments.mafia

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.partos.beerbox.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "size"
private const val ARG_PARAM2 = "isStatic"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AccountFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MafiaRolesChoiceFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var size: Int? = null
    private var isStatic: Boolean? = null
    private var listener: OnFragmentInteractionListener? = null
    private var players = 0

    private lateinit var rootView: View
    private lateinit var textView: TextView
    private lateinit var cattaniCheckBox: CheckBox
    private lateinit var medicCheckBox: CheckBox
    private lateinit var sniperCheckBox: CheckBox
    private lateinit var courtesanCheckBox: CheckBox
    private lateinit var judeCheckBox: CheckBox
    private lateinit var lawyerCheckBox: CheckBox
    private lateinit var judgeCheckBox: CheckBox
    private lateinit var playButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            size = it.getInt(ARG_PARAM1)
            isStatic = it.getBoolean(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_mafia_roles_choice, container, false);
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
        fun newInstance(size: Int, isStatic: Boolean) =
            MafiaRolesChoiceFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, size)
                    putBoolean(ARG_PARAM2, isStatic)
                }
            }
    }

    private fun initFragment() {
        attachViews()
        setTextView()
        setCheckboxesClickListeners()
        playButton.setOnClickListener {
            val fragment =
                MafiaGameFragment.newInstance(size as Int, isStatic as Boolean, getRolesArray())
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

    private fun getRolesArray(): ArrayList<Int> {
        var array = ArrayList<Int>()
        if (cattaniCheckBox.isChecked) {
            array.add(1)
        } else {
            array.add(0)
        }
        if (medicCheckBox.isChecked) {
            array.add(1)
        } else {
            array.add(0)
        }
        if (sniperCheckBox.isChecked) {
            array.add(1)
        } else {
            array.add(0)
        }
        if (courtesanCheckBox.isChecked) {
            array.add(1)
        } else {
            array.add(0)
        }
        if (judeCheckBox.isChecked) {
            array.add(1)
        } else {
            array.add(0)
        }
        if (lawyerCheckBox.isChecked) {
            array.add(1)
        } else {
            array.add(0)
        }
        if (judgeCheckBox.isChecked) {
            array.add(1)
        } else {
            array.add(0)
        }
        return array
    }

    private fun setTextView() {
        val playersAmount = size as Int
        if (playersAmount == 7 || playersAmount == 8) {
            players = playersAmount - 3
        } else if (playersAmount == 9 || playersAmount == 11) {
            players = playersAmount - 4
        } else if (playersAmount >= 12 || playersAmount <= 14) {
            players = playersAmount - 5
        } else if (playersAmount >= 15 || playersAmount <= 17) {
            players = playersAmount - 6
        }
        val text = if (players < 7) {
            if (players == 3 || players == 4) {
                rootView.context.getString(R.string.mafia_choose_roles1) + " " + players.toString() + " " +
                        rootView.context.getString(R.string.mafia_choose_roles21)
            } else {
                rootView.context.getString(R.string.mafia_choose_roles1) + " " + players.toString() + " " +
                        rootView.context.getString(R.string.mafia_choose_roles2)
            }

        } else {
            rootView.context.getString(R.string.mafia_choose_roles1) + " 7 " +
                    rootView.context.getString(R.string.mafia_choose_roles2)
        }
        textView.text = text
    }

    private fun setCheckboxesClickListeners() {
        cattaniCheckBox.setOnClickListener {
            if (cattaniCheckBox.isChecked) {
                if (isCheckPossible()) {
                    cattaniCheckBox.isChecked = true
                } else {
                    cattaniCheckBox.isChecked = false
                    Toast.makeText(
                        rootView.context,
                        rootView.context.getText(R.string.toast_max_roles_reached),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                cattaniCheckBox.isChecked = false
            }
        }
        medicCheckBox.setOnClickListener {
            if (medicCheckBox.isChecked) {
                if (isCheckPossible()) {
                    medicCheckBox.isChecked = true
                } else {
                    medicCheckBox.isChecked = false
                    Toast.makeText(
                        rootView.context,
                        rootView.context.getText(R.string.toast_max_roles_reached),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                medicCheckBox.isChecked = false
            }
        }
        sniperCheckBox.setOnClickListener {
            if (sniperCheckBox.isChecked) {
                if (isCheckPossible()) {
                    sniperCheckBox.isChecked = true
                } else {
                    sniperCheckBox.isChecked = false
                    Toast.makeText(
                        rootView.context,
                        rootView.context.getText(R.string.toast_max_roles_reached),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                sniperCheckBox.isChecked = false
            }
        }
        courtesanCheckBox.setOnClickListener {
            if (courtesanCheckBox.isChecked) {
                if (isCheckPossible()) {
                    courtesanCheckBox.isChecked = true
                } else {
                    courtesanCheckBox.isChecked = false
                    Toast.makeText(
                        rootView.context,
                        rootView.context.getText(R.string.toast_max_roles_reached),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                courtesanCheckBox.isChecked = false
            }
        }
        judeCheckBox.setOnClickListener {
            if (judeCheckBox.isChecked) {
                if (isCheckPossible()) {
                    judeCheckBox.isChecked = true
                } else {
                    judeCheckBox.isChecked = false
                    Toast.makeText(
                        rootView.context,
                        rootView.context.getText(R.string.toast_max_roles_reached),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                judeCheckBox.isChecked = false
            }
        }
        lawyerCheckBox.setOnClickListener {
            if (lawyerCheckBox.isChecked) {
                if (isCheckPossible()) {
                    lawyerCheckBox.isChecked = true
                } else {
                    lawyerCheckBox.isChecked = false
                    Toast.makeText(
                        rootView.context,
                        rootView.context.getText(R.string.toast_max_roles_reached),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                lawyerCheckBox.isChecked = false
            }
        }
        judgeCheckBox.setOnClickListener {
            if (judgeCheckBox.isChecked) {
                if (isCheckPossible()) {
                    judgeCheckBox.isChecked = true
                } else {
                    judgeCheckBox.isChecked = false
                    Toast.makeText(
                        rootView.context,
                        rootView.context.getText(R.string.toast_max_roles_reached),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                judgeCheckBox.isChecked = false
            }
        }
    }

    private fun isCheckPossible(): Boolean {
        var checked = 0
        if (cattaniCheckBox.isChecked) {
            checked++
        }
        if (medicCheckBox.isChecked) {
            checked++
        }
        if (sniperCheckBox.isChecked) {
            checked++
        }
        if (courtesanCheckBox.isChecked) {
            checked++
        }
        if (judeCheckBox.isChecked) {
            checked++
        }
        if (lawyerCheckBox.isChecked) {
            checked++
        }
        if (judgeCheckBox.isChecked) {
            checked++
        }
        if (checked <= players) {
            return true
        }
        return false
    }

    private fun attachViews() {
        textView = rootView.findViewById(R.id.mafia_roles_choice_text)
        cattaniCheckBox = rootView.findViewById(R.id.mafia_roles_choice_check_cattani)
        medicCheckBox = rootView.findViewById(R.id.mafia_roles_choice_check_medic)
        sniperCheckBox = rootView.findViewById(R.id.mafia_roles_choice_check_sniper)
        courtesanCheckBox = rootView.findViewById(R.id.mafia_roles_choice_check_courtesan)
        judeCheckBox = rootView.findViewById(R.id.mafia_roles_choice_check_jude)
        lawyerCheckBox = rootView.findViewById(R.id.mafia_roles_choice_check_lawyer)
        judgeCheckBox = rootView.findViewById(R.id.mafia_roles_choice_check_judge)
        playButton = rootView.findViewById(R.id.mafia_roles_choice_button_play)
    }
}