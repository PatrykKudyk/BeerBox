package com.partos.beerbox.fragments.beerpong

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
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.models.Team
import com.partos.beerbox.recycler.BeerPongTeamsRecyclerViewAdapter
import com.partos.beerbox.recycler.MainMenuRecyclerViewAdapter
import com.partos.beerbox.recycler.MarginItemDecoration


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
        addButton = rootView.findViewById(R.id.beer_pong_teams_button_add)
        nameEditText = rootView.findViewById(R.id.beer_pong_teams_edit_add)

        var teamsList = ArrayList<Team>()
        var id = 0
        recyclerView = rootView.findViewById(R.id.beer_pong_teams_recycler_teams)

        val mLayoutManager: LinearLayoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))

        recyclerView.adapter = BeerPongTeamsRecyclerViewAdapter(teamsList)

        addButton.setOnClickListener {
            if (nameEditText.text.toString() == "") {
                Toast.makeText(rootView.context, R.string.toast_name_not_null, Toast.LENGTH_SHORT)
                    .show()
            } else {
                teamsList.add(Team(id, nameEditText.text.toString()))
                id++
                nameEditText.setText("")
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

//    private fun hideKeyboard() {
//        val view = activity?.currentFocus
//        if (view != null) {
//            val inputManager =
//                rootView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
//        }
//    }
}