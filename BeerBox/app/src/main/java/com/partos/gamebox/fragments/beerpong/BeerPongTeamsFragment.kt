package com.partos.beerbox.fragments.beerpong

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.MyApp
import com.partos.beerbox.R
import com.partos.beerbox.models.Team
import com.partos.beerbox.recycler.BeerPongTeamsRecyclerViewAdapter
import com.partos.beerbox.recycler.MarginItemDecoration
import com.partos.flashback.db.DataBaseHelper


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
class BeerPongTeamsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var nameEditText: EditText
    private lateinit var addButton: Button
    private lateinit var playButton: Button
    private lateinit var teamsTextView: TextView
    private lateinit var teamsList: ArrayList<Team>

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
        rootView = inflater.inflate(R.layout.fragment_beer_pong_teams, container, false);
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
            BeerPongTeamsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun initFragment() {
        attachViews()

        val db = DataBaseHelper(rootView.context)
        teamsList = db.getTeamsList()

        teamsTextView.text = getString(R.string.teams) + " " + teamsList.size.toString() + "/8"

        val mLayoutManager: LinearLayoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))


        recyclerView.adapter = BeerPongTeamsRecyclerViewAdapter(teamsList)

        addButton.setOnClickListener {
            if (db.getTeamsList().size < 8) {
                if (nameEditText.text.toString() == "") {
                    Toast.makeText(
                        rootView.context,
                        R.string.toast_name_not_null,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    db.addTeam(nameEditText.text.toString())
                    teamsList = db.getTeamsList()
//                    teamsList.add(nameEditText.text.toString())
                    nameEditText.setText("")
                    recyclerView.adapter = BeerPongTeamsRecyclerViewAdapter(teamsList)
                    hideKeyboard()
                }
            } else {
                Toast.makeText(
                    rootView.context,
                    R.string.toast_max_teams_reached,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        playButton.setOnClickListener {
            if (db.getTeamsList().size >= 2) {
                var teams = ArrayList<String>()
                for (team in teamsList) {
                    teams.add(team.name)
                }

                val fragment = BeerPongLadderChoiceFragment.newInstance(teams)
                fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                        R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                        R.anim.enter_left_to_right, R.anim.exit_right_to_left
                    )
                    ?.replace(R.id.beer_pong_frame_layout, fragment)
                    ?.addToBackStack(BeerPongLadderChoiceFragment.toString())
                    ?.commit()
            } else {
                Toast.makeText(
                    rootView.context,
                    R.string.toast_no_teams_given,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        MyApp.areTeamsOpened = true
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                if (MyApp.areTeamsOpened) {
                    teamsTextView.text =
                        rootView.context.getString(R.string.teams) + " " + db.getTeamsList().size.toString() + "/8"
                }
                mainHandler.postDelayed(this, 500)
            }
        })

    }

    private fun attachViews() {
        addButton = rootView.findViewById(R.id.beer_pong_teams_button_add)
        nameEditText = rootView.findViewById(R.id.beer_pong_teams_edit_add)
        teamsTextView = rootView.findViewById(R.id.beer_pong_teams_text_teams)
        playButton = rootView.findViewById(R.id.beer_pong_teams_button_start)
        recyclerView = rootView.findViewById(R.id.beer_pong_teams_recycler_teams)

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