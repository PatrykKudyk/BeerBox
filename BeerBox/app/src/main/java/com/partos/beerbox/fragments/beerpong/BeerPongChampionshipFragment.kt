package com.partos.beerbox.fragments.beerpong

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.partos.beerbox.R
import kotlin.random.Random


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "teams"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AccountFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BeerPongChampionshipFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var teams: ArrayList<String>? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var constraint2: ConstraintLayout
    private lateinit var constraint4: ConstraintLayout
    private lateinit var constraint8: ConstraintLayout

    private lateinit var card21: CardView
    private lateinit var card22: CardView
    private lateinit var card2win: CardView
    private lateinit var text21: TextView
    private lateinit var text22: TextView
    private lateinit var text2win: TextView

    private lateinit var card411: CardView
    private lateinit var card412: CardView
    private lateinit var card413: CardView
    private lateinit var card414: CardView
    private lateinit var card421: CardView
    private lateinit var card422: CardView
    private lateinit var card4win: CardView
    private lateinit var text411: TextView
    private lateinit var text412: TextView
    private lateinit var text413: TextView
    private lateinit var text414: TextView
    private lateinit var text421: TextView
    private lateinit var text422: TextView
    private lateinit var text4win: TextView

    private lateinit var card811: CardView
    private lateinit var card812: CardView
    private lateinit var card813: CardView
    private lateinit var card814: CardView
    private lateinit var card815: CardView
    private lateinit var card816: CardView
    private lateinit var card817: CardView
    private lateinit var card818: CardView
    private lateinit var card821: CardView
    private lateinit var card822: CardView
    private lateinit var card823: CardView
    private lateinit var card824: CardView
    private lateinit var card831: CardView
    private lateinit var card832: CardView
    private lateinit var card8win: CardView
    private lateinit var text811: TextView
    private lateinit var text812: TextView
    private lateinit var text813: TextView
    private lateinit var text814: TextView
    private lateinit var text815: TextView
    private lateinit var text816: TextView
    private lateinit var text817: TextView
    private lateinit var text818: TextView
    private lateinit var text821: TextView
    private lateinit var text822: TextView
    private lateinit var text823: TextView
    private lateinit var text824: TextView
    private lateinit var text831: TextView
    private lateinit var text832: TextView
    private lateinit var text8win: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            teams = it.getStringArrayList(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_beer_pong_championship, container, false);
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
        fun newInstance(teams: ArrayList<String>) =
            BeerPongChampionshipFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_PARAM1, teams)
                }
            }
    }

    private fun initFragment() {
        attachViews()
        if (teams?.size == 2) {
            handleGame2()
        } else if (teams?.size!! <= 4) {
            handleGame4()
        } else {
            handleGame8()
        }
    }

    private fun attachViews() {
        constraint2 = rootView.findViewById(R.id.beer_pong_championship_2)
        constraint4 = rootView.findViewById(R.id.beer_pong_championship_4)
        constraint8 = rootView.findViewById(R.id.beer_pong_championship_8)

        card21 = rootView.findViewById(R.id.beer_pong_championship_2_1_card)
        card22 = rootView.findViewById(R.id.beer_pong_championship_2_2_card)
        card2win = rootView.findViewById(R.id.beer_pong_championship_2_win_card)
        text21 = rootView.findViewById(R.id.beer_pong_championship_2_1_text)
        text22 = rootView.findViewById(R.id.beer_pong_championship_2_2_text)
        text2win = rootView.findViewById(R.id.beer_pong_championship_2_win_text)

        card411 = rootView.findViewById(R.id.beer_pong_championship_4_1_1_card)
        card412 = rootView.findViewById(R.id.beer_pong_championship_4_1_2_card)
        card413 = rootView.findViewById(R.id.beer_pong_championship_4_1_3_card)
        card414 = rootView.findViewById(R.id.beer_pong_championship_4_1_4_card)
        card421 = rootView.findViewById(R.id.beer_pong_championship_4_2_1_card)
        card422 = rootView.findViewById(R.id.beer_pong_championship_4_2_2_card)
        card4win = rootView.findViewById(R.id.beer_pong_championship_4_win_card)
        text411 = rootView.findViewById(R.id.beer_pong_championship_4_1_1_text)
        text412 = rootView.findViewById(R.id.beer_pong_championship_4_1_2_text)
        text413 = rootView.findViewById(R.id.beer_pong_championship_4_1_3_text)
        text414 = rootView.findViewById(R.id.beer_pong_championship_4_1_4_text)
        text421 = rootView.findViewById(R.id.beer_pong_championship_4_2_1_text)
        text422 = rootView.findViewById(R.id.beer_pong_championship_4_2_2_text)
        text4win = rootView.findViewById(R.id.beer_pong_championship_4_win_text)

        card811 = rootView.findViewById(R.id.beer_pong_championship_8_1_1_card)
        card812 = rootView.findViewById(R.id.beer_pong_championship_8_1_2_card)
        card813 = rootView.findViewById(R.id.beer_pong_championship_8_1_3_card)
        card814 = rootView.findViewById(R.id.beer_pong_championship_8_1_4_card)
        card815 = rootView.findViewById(R.id.beer_pong_championship_8_1_5_card)
        card816 = rootView.findViewById(R.id.beer_pong_championship_8_1_6_card)
        card817 = rootView.findViewById(R.id.beer_pong_championship_8_1_7_card)
        card818 = rootView.findViewById(R.id.beer_pong_championship_8_1_8_card)
        card821 = rootView.findViewById(R.id.beer_pong_championship_8_2_1_card)
        card822 = rootView.findViewById(R.id.beer_pong_championship_8_2_2_card)
        card823 = rootView.findViewById(R.id.beer_pong_championship_8_2_3_card)
        card824 = rootView.findViewById(R.id.beer_pong_championship_8_2_4_card)
        card831 = rootView.findViewById(R.id.beer_pong_championship_8_3_1_card)
        card832 = rootView.findViewById(R.id.beer_pong_championship_8_3_2_card)
        card8win = rootView.findViewById(R.id.beer_pong_championship_8_win_card)
        text811 = rootView.findViewById(R.id.beer_pong_championship_8_1_1_text)
        text812 = rootView.findViewById(R.id.beer_pong_championship_8_1_2_text)
        text813 = rootView.findViewById(R.id.beer_pong_championship_8_1_3_text)
        text814 = rootView.findViewById(R.id.beer_pong_championship_8_1_4_text)
        text815 = rootView.findViewById(R.id.beer_pong_championship_8_1_5_text)
        text816 = rootView.findViewById(R.id.beer_pong_championship_8_1_6_text)
        text817 = rootView.findViewById(R.id.beer_pong_championship_8_1_7_text)
        text818 = rootView.findViewById(R.id.beer_pong_championship_8_1_8_text)
        text821 = rootView.findViewById(R.id.beer_pong_championship_8_2_1_text)
        text822 = rootView.findViewById(R.id.beer_pong_championship_8_2_2_text)
        text823 = rootView.findViewById(R.id.beer_pong_championship_8_2_3_text)
        text824 = rootView.findViewById(R.id.beer_pong_championship_8_2_4_text)
        text831 = rootView.findViewById(R.id.beer_pong_championship_8_3_1_text)
        text832 = rootView.findViewById(R.id.beer_pong_championship_8_3_2_text)
        text8win = rootView.findViewById(R.id.beer_pong_championship_8_win_text)
    }

    private fun handleGame2() {
        handleGame2Start()
        card21.setOnClickListener {
            card21.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            card22.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLightLight
                )
            )
            card2win.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            text2win.setText(text21.text.toString())
        }

        card22.setOnClickListener {
            card22.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            card21.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLightLight
                )
            )
            card2win.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            text2win.setText(text22.text.toString())
        }
    }

    private fun handleGame2Start() {
        text21.setText(teams?.get(0))
        text22.setText(teams?.get(1))
        constraint2.visibility = View.VISIBLE
    }

    private fun handleGame4() {
        handleGame4Start()
        handleGame41Listeners()
    }

    private fun handleGame4Start() {
        constraint4.visibility = View.VISIBLE
        if (teams?.size == 3) {
            teams?.add("")
        }
        handleGame4AssignTeams(handleGame4Random())
    }

    private fun handleGame4AssignTeams(teamsList: ArrayList<Int>) {
        text411.setText(teams?.get(teamsList[0]))
        if (teams?.get(teamsList[0]) != "") {
            card411.isClickable = true
        }
        text412.setText(teams?.get(teamsList[1]))
        if (teams?.get(teamsList[1]) != "") {
            card412.isClickable = true
        }
        text413.setText(teams?.get(teamsList[2]))
        if (teams?.get(teamsList[2]) != "") {
            card413.isClickable = true
        }
        text414.setText(teams?.get(teamsList[3]))
        if (teams?.get(teamsList[3]) != "") {
            card414.isClickable = true
        }
    }

    private fun handleGame4Random(): ArrayList<Int> {
        var random: Int
        var team = 0
        var teamsArray = arrayListOf<Int>(-1, -1, -1, -1)
        do {
            random = Random.nextInt(0, 4)
            if (teamsArray[random] == -1) {
                teamsArray[random] = team
                team++
            }
        } while (!handleGame4IsRandomEnd(teamsArray))
        return teamsArray
    }

    private fun handleGame4IsRandomEnd(list: ArrayList<Int>): Boolean {
        for (item in list) {
            if (item == -1) {
                return false
            }
        }
        return true
    }

    private fun handleGame41Listeners() {
        if (text411.text.toString() != "") {
            card411.setOnClickListener {
                card411.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card412.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card421.isClickable = true
                text421.setText(text411.text.toString())
                if (card422.isClickable) {
                    handleGame42Listeners()
                }
                if (card4win.isClickable && card421.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text4win.setText(text411.text.toString())
                }
            }
        }

        if (text412.text.toString() != "") {
            card412.setOnClickListener {
                card412.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card411.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card421.isClickable = true
                text421.setText(text412.text.toString())
                if (card422.isClickable) {
                    handleGame42Listeners()
                }
                if (card4win.isClickable && card421.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text4win.setText(text412.text.toString())
                }
            }
        }


        if (text413.text.toString() != "") {
            card413.setOnClickListener {
                card413.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card414.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card422.isClickable = true
                text422.setText(text413.text.toString())
                if (card421.isClickable) {
                    handleGame42Listeners()
                }
                if (card4win.isClickable && card422.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text4win.setText(text413.text.toString())
                }
            }
        }


        if (text414.text.toString() != "") {
            card414.setOnClickListener {
                card414.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card413.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card422.isClickable = true
                text422.setText(text414.text.toString())
                if (card421.isClickable) {
                    handleGame42Listeners()
                }
                if (card4win.isClickable && card422.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text4win.setText(text414.text.toString())
                }
            }
        }

    }

    private fun handleGame42Listeners() {
        card421.setOnClickListener {
            card421.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            card422.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLightLight
                )
            )
            card4win.isClickable = true
            card4win.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            text4win.setText(text421.text.toString())
        }

        card422.setOnClickListener {
            card422.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            card421.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLightLight
                )
            )
            card4win.isClickable = true
            card4win.setCardBackgroundColor(
                ContextCompat.getColor(
                    rootView.context,
                    R.color.colorRedLight
                )
            )
            text4win.setText(text422.text.toString())
        }
    }

    private fun handleGame8() {
        handleGame8Start()
    }

    private fun handleGame8Start() {
        constraint8.visibility = View.VISIBLE
        if (teams?.size == 5) {
            teams?.add("")
            teams?.add("")
            teams?.add("")
        } else if (teams?.size == 6) {
            teams?.add("")
            teams?.add("")
        } else if (teams?.size == 7) {
            teams?.add("")
        }
        handleGame8AssignTeams(handleGame8Random())
        handleGame81Listeners()
    }

    private fun handleGame8AssignTeams(teamsList: ArrayList<Int>) {
        text811.setText(teams?.get(teamsList[0]))
        if (teams?.get(teamsList[0]) != "") {
            card811.isClickable = true
        }
        text812.setText(teams?.get(teamsList[1]))
        if (teams?.get(teamsList[1]) != "") {
            card812.isClickable = true
        }
        text813.setText(teams?.get(teamsList[2]))
        if (teams?.get(teamsList[2]) != "") {
            card813.isClickable = true
        }
        text814.setText(teams?.get(teamsList[3]))
        if (teams?.get(teamsList[3]) != "") {
            card814.isClickable = true
        }
        text815.setText(teams?.get(teamsList[4]))
        if (teams?.get(teamsList[4]) != "") {
            card815.isClickable = true
        }
        text816.setText(teams?.get(teamsList[5]))
        if (teams?.get(teamsList[5]) != "") {
            card816.isClickable = true
        }
        text817.setText(teams?.get(teamsList[6]))
        if (teams?.get(teamsList[6]) != "") {
            card817.isClickable = true
        }
        text818.setText(teams?.get(teamsList[7]))
        if (teams?.get(teamsList[7]) != "") {
            card818.isClickable = true
        }
    }

    private fun handleGame8Random(): ArrayList<Int> {
        var random: Int
        var team = 0
        var teamsArray = arrayListOf<Int>(-1, -1, -1, -1, -1, -1, -1, -1)
        do {
            random = Random.nextInt(0, 8)
            if (teamsArray[random] == -1) {
                teamsArray[random] = team
                team++
            }
        } while (!handleGame8IsRandomEnd(teamsArray))
        return teamsArray
    }

    private fun handleGame8IsRandomEnd(list: ArrayList<Int>): Boolean {
        for (item in list) {
            if (item == -1) {
                return false
            }
        }
        return true
    }

    private fun handleGame81Listeners() {
        if (text811.text.toString() != "") {
            card811.setOnClickListener {
                card811.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card812.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card821.isClickable = true
                text821.setText(text811.text.toString())
                if (card822.isClickable) {
                    handleGame42Listeners()
                }
                if (card8win.isClickable && card831.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text4win.setText(text811.text.toString())
                }
            }
        }
    }

}