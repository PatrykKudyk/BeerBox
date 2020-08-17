package com.partos.gamebox.pager


import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.partos.gamebox.MyApp
import com.partos.gamebox.R
import kotlinx.android.synthetic.main.pager_cell_beer_pong_championship.view.*
import kotlin.random.Random

class   BeerPongChampionshipViewPagerAdapter : PagerAdapter {

    var context: Context
    var teams = ArrayList<String>()

    private lateinit var rootView: View
    private lateinit var constraint2: ConstraintLayout
    private lateinit var constraint4: ConstraintLayout
    private lateinit var constraint8: ConstraintLayout

    private lateinit var nextTeam1: TextView
    private lateinit var nextTeam2: TextView

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

    //    var recyclerViews: Array<RecyclerView>
    lateinit var inflater: LayoutInflater

    constructor(context: Context, teams: ArrayList<String>) : super() {
        this.context = context
        this.teams = teams
//        this.recyclerViews = recyclerViews
    }


    //    override fun getCount(): Int = recyclerViews.size
    override fun getCount(): Int = 2

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == `object` as ConstraintLayout

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view: View =
            inflater.inflate(R.layout.pager_cell_beer_pong_championship, container, false)

        MyApp.endMatch = false
        MyApp.matchEnded = true
        MyApp.tourEnd = false
        MyApp.ladderStart = false

        rootView = view
        if (position == 1) {
            view.pager_cell_beer_pong_ladder_constraint.visibility = View.VISIBLE
            handleLadder()
        } else if (position == 0) {
            view.pager_cell_beer_pong_match_constraint.visibility = View.VISIBLE
            handleMatch()
        }

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    private fun handleLadder() {
        attachViews()
        if (teams.size == 2) {
            handleGame2()
        } else if (teams.size <= 4) {
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
            MyApp.nextTeam1 = ""
            MyApp.nextTeam2 = ""
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
            MyApp.nextTeam1 = ""
            MyApp.nextTeam2 = ""
        }
    }

    private fun handleGame2Start() {
        text21.setText(teams.get(0))
        text22.setText(teams.get(1))
        MyApp.nextTeam1 = teams[0]
        MyApp.nextTeam2 = teams[1]
        constraint2.visibility = View.VISIBLE
    }

    private fun handleGame4() {
        handleGame4Start()
        handleGame41Listeners()
    }

    private fun handleGame4Start() {
        constraint4.visibility = View.VISIBLE
        if (teams.size == 3) {
            teams.add("")
        }
        handleGame4AssignTeams(handleGame4Random())
    }

    private fun handleGame4AssignTeams(teamsList: ArrayList<Int>) {
        text411.setText(teams.get(teamsList[0]))
        if (teams.get(teamsList[0]) != "") {
            card411.isClickable = true
        }
        text412.setText(teams.get(teamsList[1]))
        if (teams.get(teamsList[1]) != "") {
            card412.isClickable = true
        }
        text413.setText(teams.get(teamsList[2]))
        if (teams.get(teamsList[2]) != "") {
            card413.isClickable = true
        }
        text414.setText(teams.get(teamsList[3]))
        if (teams.get(teamsList[3]) != "") {
            card414.isClickable = true
        }
        MyApp.nextTeam1 = text411.text.toString()
        MyApp.nextTeam2 = text412.text.toString()
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
                handleGame4NextMatch()
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
                handleGame4NextMatch()
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
                handleGame4NextMatch()
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
                if (card4win.isClickable && card422.cardBackgroundColor == context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text4win.setText(text414.text.toString())
                }
                handleGame4NextMatch()
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
            handleGame4NextMatch()
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
            handleGame4NextMatch()
        }
    }

    private fun handleGame4NextMatch() {
        if (card411.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
            card412.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight)
        ) {
            MyApp.nextTeam1 = text411.text.toString()
            MyApp.nextTeam2 = text412.text.toString()
        } else if (card413.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
            card414.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight)
        ) {
            MyApp.nextTeam1 = text413.text.toString()
            MyApp.nextTeam2 = text414.text.toString()
        } else if (card421.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
            card422.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight)
        ) {
            MyApp.nextTeam1 = text421.text.toString()
            MyApp.nextTeam2 = text422.text.toString()
        } else if (card4win.cardBackgroundColor == context.getColorStateList(R.color.colorRedLight)) {
            MyApp.nextTeam1 = ""
            MyApp.nextTeam2 = ""
            MyApp.endMatch = true
        }
    }

    private fun handleGame8() {
        handleGame8Start()
    }

    private fun handleGame8Start() {
        constraint8.visibility = View.VISIBLE
        if (teams.size == 5) {
            teams.add("")
            teams.add("")
            teams.add("")
        } else if (teams.size == 6) {
            teams.add("")
            teams.add("")
        } else if (teams.size == 7) {
            teams.add("")
        }
        handleGame8AssignTeams(handleGame8Random())
        handleGame81Listeners()
    }

    private fun handleGame8AssignTeams(teamsList: ArrayList<Int>) {
        text811.setText(teams.get(teamsList[0]))
        if (teams.get(teamsList[0]) != "") {
            card811.isClickable = true
        }
        text812.setText(teams.get(teamsList[1]))
        if (teams.get(teamsList[1]) != "") {
            card812.isClickable = true
        }
        text813.setText(teams.get(teamsList[2]))
        if (teams.get(teamsList[2]) != "") {
            card813.isClickable = true
        }
        text814.setText(teams.get(teamsList[3]))
        if (teams.get(teamsList[3]) != "") {
            card814.isClickable = true
        }
        text815.setText(teams.get(teamsList[4]))
        if (teams.get(teamsList[4]) != "") {
            card815.isClickable = true
        }
        text816.setText(teams.get(teamsList[5]))
        if (teams.get(teamsList[5]) != "") {
            card816.isClickable = true
        }
        text817.setText(teams.get(teamsList[6]))
        if (teams.get(teamsList[6]) != "") {
            card817.isClickable = true
        }
        text818.setText(teams.get(teamsList[7]))
        if (teams.get(teamsList[7]) != "") {
            card818.isClickable = true
        }
        handleGame8NextMatch()
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
        handleGame8CheckEmpty()

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
                if (card822.isClickable && card823.isClickable && card824.isClickable) {
                    handleGame82Listeners()
                }
                if (card831.isClickable && card821.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text831.setText(text811.text.toString())
                }
                handleGame8NextMatch()
            }
        }

        if (text812.text.toString() != "") {
            card812.setOnClickListener {
                card812.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card811.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card821.isClickable = true
                text821.setText(text812.text.toString())
                if (card822.isClickable && card823.isClickable && card824.isClickable) {
                    handleGame82Listeners()
                }
                if (card831.isClickable && card821.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text831.setText(text812.text.toString())
                }
                handleGame8NextMatch()
            }
        }

        if (text813.text.toString() != "") {
            card813.setOnClickListener {
                card813.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card814.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card822.isClickable = true
                text822.setText(text813.text.toString())
                if (card821.isClickable && card823.isClickable && card824.isClickable) {
                    handleGame82Listeners()
                }
                if (card831.isClickable && card822.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text831.setText(text813.text.toString())
                }
                handleGame8NextMatch()
            }
        }

        if (text814.text.toString() != "") {
            card814.setOnClickListener {
                card814.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card813.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card822.isClickable = true
                text822.setText(text814.text.toString())
                if (card821.isClickable && card823.isClickable && card824.isClickable) {
                    handleGame82Listeners()
                }
                if (card831.isClickable && card822.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text831.setText(text814.text.toString())
                }
                handleGame8NextMatch()
            }
        }

        if (text815.text.toString() != "") {
            card815.setOnClickListener {
                card815.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card816.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card823.isClickable = true
                text823.setText(text815.text.toString())
                if (card821.isClickable && card822.isClickable && card824.isClickable) {
                    handleGame82Listeners()
                }
                if (card832.isClickable && card823.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text832.setText(text815.text.toString())
                }
                handleGame8NextMatch()
            }
        }

        if (text816.text.toString() != "") {
            card816.setOnClickListener {
                card816.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card815.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card823.isClickable = true
                text823.setText(text816.text.toString())
                if (card821.isClickable && card822.isClickable && card824.isClickable) {
                    handleGame82Listeners()
                }
                if (card832.isClickable && card823.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text832.setText(text816.text.toString())
                }
                handleGame8NextMatch()
            }
        }

        if (text817.text.toString() != "") {
            card817.setOnClickListener {
                card817.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card818.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card824.isClickable = true
                text824.setText(text817.text.toString())
                if (card821.isClickable && card822.isClickable && card823.isClickable) {
                    handleGame82Listeners()
                }
                if (card832.isClickable && card824.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text832.setText(text817.text.toString())
                }
                handleGame8NextMatch()
            }
        }

        if (text818.text.toString() != "") {
            card818.setOnClickListener {
                card818.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card817.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card824.isClickable = true
                text824.setText(text818.text.toString())
                if (card821.isClickable && card822.isClickable && card823.isClickable) {
                    handleGame82Listeners()
                }
                if (card832.isClickable && card824.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text832.setText(text818.text.toString())
                }
                handleGame8NextMatch()
            }
        }
    }

    private fun handleGame8CheckEmpty() {
        if (text811.text.toString() == "" && text812.text.toString() == "") {
            card821.isClickable = true
        } else if (text813.text.toString() == "" && text814.text.toString() == "") {
            card822.isClickable = true
        } else if (text815.text.toString() == "" && text816.text.toString() == "") {
            card823.isClickable = true
        } else if (text817.text.toString() == "" && text818.text.toString() == "") {
            card824.isClickable = true
        }
    }

    private fun handleGame82Listeners() {
        if (text821.text.toString() != "") {
            card821.setOnClickListener {
                card821.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card822.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card831.isClickable = true
                text831.setText(text821.text.toString())
                if (card832.isClickable) {
                    handleGame83Listeners()
                }
                if (card8win.isClickable && card831.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text8win.setText(text821.text.toString())
                }
                handleGame8NextMatch()
            }
        }

        if (text822.text.toString() != "") {
            card822.setOnClickListener {
                card822.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card821.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card831.isClickable = true
                text831.setText(text822.text.toString())
                if (card832.isClickable) {
                    handleGame83Listeners()
                }
                if (card8win.isClickable && card831.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text8win.setText(text822.text.toString())
                }
                handleGame8NextMatch()
            }
        }

        if (text823.text.toString() != "") {
            card823.setOnClickListener {
                card823.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card824.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card832.isClickable = true
                text832.setText(text823.text.toString())
                if (card831.isClickable) {
                    handleGame83Listeners()
                }
                if (card8win.isClickable && card832.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text8win.setText(text823.text.toString())
                }
                handleGame8NextMatch()
            }
        }

        if (text824.text.toString() != "") {
            card824.setOnClickListener {
                card824.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card823.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card832.isClickable = true
                text832.setText(text824.text.toString())
                if (card831.isClickable) {
                    handleGame83Listeners()
                }
                if (card8win.isClickable && card832.cardBackgroundColor == rootView.context.getColorStateList(
                        R.color.colorRedLight
                    )
                ) {
                    text8win.setText(text824.text.toString())
                }
                handleGame8NextMatch()
            }
        }
    }

    private fun handleGame83Listeners() {
        if (text831.text.toString() != "") {
            card831.setOnClickListener {
                card831.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card832.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card8win.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                text8win.setText(text831.text.toString())
                handleGame8NextMatch()
            }
        }

        if (text832.text.toString() != "") {
            card832.setOnClickListener {
                card832.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                card831.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLightLight
                    )
                )
                card8win.setCardBackgroundColor(
                    ContextCompat.getColor(
                        rootView.context,
                        R.color.colorRedLight
                    )
                )
                text8win.setText(text832.text.toString())
                handleGame8NextMatch()
            }
        }
    }

    private fun handleGame8NextMatch() {
        if ((card811.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
                    card812.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
                    (text811.text.toString() != "" || text812.text.toString() != ""))
        ) {
            MyApp.nextTeam1 = text811.text.toString()
            MyApp.nextTeam2 = text812.text.toString()
        } else if ((card813.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
                    card814.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
                    (text813.text.toString() != "" || text814.text.toString() != ""))
        ) {
            MyApp.nextTeam1 = text813.text.toString()
            MyApp.nextTeam2 = text814.text.toString()
        } else if ((card815.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
                    card816.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight)) &&
                    (text815.text.toString() != "" || text816.text.toString() != "")
        ) {
            MyApp.nextTeam1 = text815.text.toString()
            MyApp.nextTeam2 = text816.text.toString()
        } else if ((card817.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
            card818.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
                    (text817.text.toString() != "" || text818.text.toString() != ""))
        ) {
            MyApp.nextTeam1 = text817.text.toString()
            MyApp.nextTeam2 = text818.text.toString()
        } else if (card821.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
            card822.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight)
        ) {
            MyApp.nextTeam1 = text821.text.toString()
            MyApp.nextTeam2 = text822.text.toString()
        } else if (card823.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
            card824.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight)
        ) {
            MyApp.nextTeam1 = text823.text.toString()
            MyApp.nextTeam2 = text824.text.toString()
        } else if (card831.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight) &&
            card832.cardBackgroundColor == context.getColorStateList(R.color.colorRedLightLight)
        ) {
            MyApp.nextTeam1 = text831.text.toString()
            MyApp.nextTeam2 = text832.text.toString()
        } else if (card8win.cardBackgroundColor == context.getColorStateList(R.color.colorRedLight)) {
            MyApp.nextTeam1 = ""
            MyApp.nextTeam2 = ""
            MyApp.endMatch = true
        }

    }

    private fun handleMatch() {
        nextTeam1 = rootView.findViewById(R.id.beer_pong_championship_match_text_1)
        nextTeam2 = rootView.findViewById(R.id.beer_pong_championship_match_text_2)

        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                if (!MyApp.endMatch) {
                    nextTeam1.setText(MyApp.nextTeam1)
                    nextTeam2.setText(MyApp.nextTeam2)
                }

                mainHandler.postDelayed(this, 500)
            }
        })
    }

}