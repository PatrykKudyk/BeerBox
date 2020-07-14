package com.partos.beerbox.fragments.mafia

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.recycler.MainMenuRecyclerViewAdapter
import com.partos.beerbox.recycler.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_main_menu.*
import kotlinx.android.synthetic.main.fragment_main_menu.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AccountFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MafiaGameSizeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var sizeEditText: EditText
    private lateinit var playButton: Button
    private lateinit var checkStatic: CheckBox
    private lateinit var checkDynamic: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_mafia_game_size, container, false);
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
        fun newInstance() =
            MafiaGameSizeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun initFragment() {
        sizeEditText = rootView.findViewById(R.id.mafia_game_size_edit_text)
        playButton = rootView.findViewById(R.id.mafia_game_size_button_play)
        checkStatic = rootView.findViewById(R.id.mafia_game_size_checkbox_static)
        checkDynamic = rootView.findViewById(R.id.mafia_game_size_checkbox_dynamic)

        playButton.setOnClickListener {
            if (sizeEditText.text.toString() == "" || sizeEditText.text.toString().toInt() == 0 ||
                sizeEditText.text.toString().toInt() < 7 || sizeEditText.text.toString()
                    .toInt() > 17
            ) {
                Toast.makeText(
                    rootView.context,
                    R.string.toast_incorrect_players_amount,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val fragment: MafiaRolesChoiceFragment = if (checkStatic.isChecked) {
                    MafiaRolesChoiceFragment.newInstance(
                        sizeEditText.text.toString().toInt(),
                        true
                    )
                } else {
                    MafiaRolesChoiceFragment.newInstance(
                        sizeEditText.text.toString().toInt(),
                        false
                    )
                }
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

        checkStatic.setOnClickListener {
            checkStatic.isChecked = true
            checkDynamic.isChecked = false
        }

        checkDynamic.setOnClickListener {
            checkDynamic.isChecked = true
            checkStatic.isChecked = false
        }
    }
}