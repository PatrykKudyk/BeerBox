package com.partos.beerbox.pager


import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.partos.beerbox.MyApp
import com.partos.beerbox.R
import com.partos.beerbox.models.Match
import com.partos.beerbox.models.Points
import kotlinx.android.synthetic.main.pager_cell_beer_pong_championship.view.*
import kotlinx.android.synthetic.main.pager_cell_beer_pong_group.view.*
import kotlin.random.Random

class BeerPongGroupViewPagerAdapter : PagerAdapter {

    var context: Context
    var teams = ArrayList<String>()
    private var matchList = ArrayList<Match>()
    private var teamsPoints = HashMap<String, Points>()


    private lateinit var rootView: View

    private lateinit var matchCard1: CardView
    private lateinit var matchCard2: CardView
    private lateinit var matchText1: TextView
    private lateinit var matchText2: TextView

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

    private lateinit var cardMatch1: CardView
    private lateinit var cardMatch2: CardView
    private lateinit var textMatch1: TextView
    private lateinit var textMatch2: TextView

    private lateinit var groupLayout11: LinearLayout
    private lateinit var groupLayout12: LinearLayout
    private lateinit var groupLayout13: LinearLayout
    private lateinit var groupLayout14: LinearLayout
    private lateinit var groupLayout21: LinearLayout
    private lateinit var groupLayout22: LinearLayout
    private lateinit var groupLayout23: LinearLayout
    private lateinit var groupLayout24: LinearLayout
    private lateinit var groupText11: TextView
    private lateinit var groupText12: TextView
    private lateinit var groupText13: TextView
    private lateinit var groupText14: TextView
    private lateinit var groupText21: TextView
    private lateinit var groupText22: TextView
    private lateinit var groupText23: TextView
    private lateinit var groupText24: TextView


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
            inflater.inflate(R.layout.pager_cell_beer_pong_group, container, false)

        MyApp.endMatch = false
        MyApp.matchEnded = true
        MyApp.tourEnd = false
        MyApp.ladderStart = false


        rootView = view
        if (position == 0) {
            view.pager_cell_beer_pong_group_match_constraint.visibility = View.VISIBLE
            handleMatches()
        } else if (position == 1) {
            view.pager_cell_beer_pong_group.visibility = View.VISIBLE
            handleGroups()
        }

        container!!.addView(view)

        return view
    }

    private fun handleGroups() {
        attachViews()
        teams.shuffle()
        makeLayoutsInvisible()
        setGroups()
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                if (MyApp.tourEnd) {
                    MyApp.tourEnd = false
                    removeWorstTeams()
                    teamsPoints.clear()
                    teams.shuffle()
                    makeLayoutsInvisible()
                    setGroups()
                }
                mainHandler.postDelayed(this, 500)
            }
        })
    }

    private fun removeWorstTeams() {
        when (teams.size) {
            5 -> {
                var worstTeamPoints = 10
                var worstTeamName = ""
                for ((teamName, points) in teamsPoints) {
                    if (points.group == 1) {
                        if (points.points < worstTeamPoints) {
                            worstTeamPoints = points.points
                            worstTeamName = teamName
                        }
                    }
                }
                teams.remove(worstTeamName)
            }
            6 -> {
                var worstTeamPoints = 10
                var worstTeamName = ""
                for ((teamName, points) in teamsPoints) {
                    if (points.group == 1) {
                        if (points.points < worstTeamPoints) {
                            worstTeamPoints = points.points
                            worstTeamName = teamName
                        }
                    }
                }
                teams.remove(worstTeamName)
                worstTeamPoints = 10
                worstTeamName = ""
                for ((teamName, points) in teamsPoints) {
                    if (points.group == 2) {
                        if (points.points < worstTeamPoints) {
                            worstTeamPoints = points.points
                            worstTeamName = teamName
                        }
                    }
                }
                teams.remove(worstTeamName)
            }
            7 -> {
                var worstTeamPoints = 10
                var worstTeamName = ""
                for ((teamName, points) in teamsPoints) {
                    if (points.group == 1) {
                        if (points.points < worstTeamPoints) {
                            worstTeamPoints = points.points
                            worstTeamName = teamName
                        }
                    }
                }
                teams.remove(worstTeamName)
                worstTeamPoints = 10
                worstTeamName = ""
                for ((teamName, points) in teamsPoints) {
                    if (points.group == 2) {
                        if (points.points < worstTeamPoints) {
                            worstTeamPoints = points.points
                            worstTeamName = teamName
                        }
                    }
                }
                teams.remove(worstTeamName)
            }
            8 -> {
                var worstTeamPoints = 10
                var worstTeamName = ""
                for ((teamName, points) in teamsPoints) {
                    if (points.group == 1) {
                        if (points.points < worstTeamPoints) {
                            worstTeamPoints = points.points
                            worstTeamName = teamName
                        }
                    }
                }
                teams.remove(worstTeamName)
                worstTeamPoints = 10
                worstTeamName = ""
                for ((teamName, points) in teamsPoints) {
                    if (points.group == 2) {
                        if (points.points < worstTeamPoints) {
                            worstTeamPoints = points.points
                            worstTeamName = teamName
                        }
                    }
                }
                teams.remove(worstTeamName)
            }
        }
    }

    private fun setGroups() {
        when (teams.size) {
            3 -> {
                handleChampionship()
            }
            4 -> {
                handleChampionship()
            }
            5 -> {
                groupLayout11.visibility = View.VISIBLE
                groupLayout12.visibility = View.VISIBLE
                groupLayout13.visibility = View.VISIBLE
                groupLayout21.visibility = View.VISIBLE
                groupLayout22.visibility = View.VISIBLE

                groupText11.setText(teams[0])
                groupText12.setText(teams[1])
                groupText13.setText(teams[2])
                groupText21.setText(teams[3])
                groupText22.setText(teams[4])


                teamsPoints.put(teams[0], Points(0, 1))
                teamsPoints.put(teams[1], Points(0, 1))
                teamsPoints.put(teams[2], Points(0, 1))
                teamsPoints.put(teams[3], Points(0, 2))
                teamsPoints.put(teams[4], Points(0, 2))
                matchList.add(Match(teams[0], teams[1]))
                matchList.add(Match(teams[0], teams[2]))
                matchList.add(Match(teams[1], teams[2]))
                matchList.add(Match(teams[3], teams[4]))

            }
            6 -> {
                groupLayout11.visibility = View.VISIBLE
                groupLayout12.visibility = View.VISIBLE
                groupLayout13.visibility = View.VISIBLE
                groupLayout21.visibility = View.VISIBLE
                groupLayout22.visibility = View.VISIBLE
                groupLayout23.visibility = View.VISIBLE

                groupText11.setText(teams[0])
                groupText12.setText(teams[1])
                groupText13.setText(teams[2])
                groupText21.setText(teams[3])
                groupText22.setText(teams[4])
                groupText23.setText(teams[5])

                teamsPoints.put(teams[0], Points(0, 1))
                teamsPoints.put(teams[1], Points(0, 1))
                teamsPoints.put(teams[2], Points(0, 1))
                teamsPoints.put(teams[3], Points(0, 2))
                teamsPoints.put(teams[4], Points(0, 2))
                teamsPoints.put(teams[5], Points(0, 2))

                matchList.add(Match(teams[0], teams[1]))
                matchList.add(Match(teams[0], teams[2]))
                matchList.add(Match(teams[1], teams[2]))
                matchList.add(Match(teams[3], teams[4]))
                matchList.add(Match(teams[3], teams[5]))
                matchList.add(Match(teams[4], teams[5]))
            }
            7 -> {
                groupLayout11.visibility = View.VISIBLE
                groupLayout12.visibility = View.VISIBLE
                groupLayout13.visibility = View.VISIBLE
                groupLayout14.visibility = View.VISIBLE
                groupLayout21.visibility = View.VISIBLE
                groupLayout22.visibility = View.VISIBLE
                groupLayout23.visibility = View.VISIBLE

                groupText11.setText(teams[0])
                groupText12.setText(teams[1])
                groupText13.setText(teams[2])
                groupText14.setText(teams[3])
                groupText21.setText(teams[4])
                groupText22.setText(teams[5])
                groupText23.setText(teams[6])

                teamsPoints.put(teams[0], Points(0, 1))
                teamsPoints.put(teams[1], Points(0, 1))
                teamsPoints.put(teams[2], Points(0, 1))
                teamsPoints.put(teams[3], Points(0, 1))
                teamsPoints.put(teams[4], Points(0, 2))
                teamsPoints.put(teams[5], Points(0, 2))
                teamsPoints.put(teams[6], Points(0, 2))


                matchList.add(Match(teams[0], teams[1]))
                matchList.add(Match(teams[0], teams[2]))
                matchList.add(Match(teams[0], teams[3]))
                matchList.add(Match(teams[1], teams[2]))
                matchList.add(Match(teams[1], teams[3]))
                matchList.add(Match(teams[2], teams[3]))
                matchList.add(Match(teams[4], teams[5]))
                matchList.add(Match(teams[4], teams[6]))
                matchList.add(Match(teams[5], teams[6]))
            }
            8 -> {
                groupLayout11.visibility = View.VISIBLE
                groupLayout12.visibility = View.VISIBLE
                groupLayout13.visibility = View.VISIBLE
                groupLayout14.visibility = View.VISIBLE
                groupLayout21.visibility = View.VISIBLE
                groupLayout22.visibility = View.VISIBLE
                groupLayout23.visibility = View.VISIBLE
                groupLayout24.visibility = View.VISIBLE

                groupText11.setText(teams[0])
                groupText12.setText(teams[1])
                groupText13.setText(teams[2])
                groupText14.setText(teams[3])
                groupText21.setText(teams[4])
                groupText22.setText(teams[5])
                groupText23.setText(teams[6])
                groupText24.setText(teams[7])


                teamsPoints.put(teams[0], Points(0, 1))
                teamsPoints.put(teams[1], Points(0, 1))
                teamsPoints.put(teams[2], Points(0, 1))
                teamsPoints.put(teams[3], Points(0, 1))
                teamsPoints.put(teams[4], Points(0, 2))
                teamsPoints.put(teams[5], Points(0, 2))
                teamsPoints.put(teams[6], Points(0, 2))
                teamsPoints.put(teams[7], Points(0, 2))


                matchList.add(Match(teams[0], teams[1]))
                matchList.add(Match(teams[0], teams[2]))
                matchList.add(Match(teams[0], teams[3]))
                matchList.add(Match(teams[1], teams[2]))
                matchList.add(Match(teams[1], teams[3]))
                matchList.add(Match(teams[2], teams[3]))
                matchList.add(Match(teams[4], teams[5]))
                matchList.add(Match(teams[4], teams[6]))
                matchList.add(Match(teams[4], teams[7]))
                matchList.add(Match(teams[5], teams[6]))
                matchList.add(Match(teams[5], teams[7]))
                matchList.add(Match(teams[6], teams[7]))
            }
        }
        matchList.shuffle()
    }

    private fun makeLayoutsInvisible() {
        groupLayout11.visibility = View.INVISIBLE
        groupLayout12.visibility = View.INVISIBLE
        groupLayout13.visibility = View.INVISIBLE
        groupLayout14.visibility = View.INVISIBLE
        groupLayout21.visibility = View.INVISIBLE
        groupLayout22.visibility = View.INVISIBLE
        groupLayout23.visibility = View.INVISIBLE
        groupLayout24.visibility = View.INVISIBLE
    }

    private fun handleChampionship() {
        rootView.pager_cell_beer_pong_group.visibility = View.GONE
        rootView.pager_cell_beer_pong_group_ladder_constraint.visibility = View.VISIBLE
        MyApp.ladderStart = true
        handleChampionshipAssignTeams(generateRandomLadder())
        handleGame41Listeners()
    }

    private fun handleChampionshipAssignTeams(teamsList: ArrayList<Int>) {
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

    private fun generateRandomLadder(): ArrayList<Int> {
        var random: Int
        var team = 0
        var teamsArray = arrayListOf<Int>(-1, -1, -1, -1)
        do {
            random = Random.nextInt(0, 4)
            if (teamsArray[random] == -1) {
                teamsArray[random] = team
                team++
            }
        } while (!isRandomEnd(teamsArray))
        return teamsArray
    }

    private fun isRandomEnd(list: ArrayList<Int>): Boolean {
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
                MyApp.nextTeam1 = text413.text.toString()
                MyApp.nextTeam2 = text414.text.toString()
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
                MyApp.nextTeam1 = text413.text.toString()
                MyApp.nextTeam2 = text414.text.toString()
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
                MyApp.nextTeam1 = text421.text.toString()
                MyApp.nextTeam2 = text422.text.toString()
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
                MyApp.nextTeam1 = text421.text.toString()
                MyApp.nextTeam2 = text422.text.toString()
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
            MyApp.nextTeam1 = ""
            MyApp.nextTeam2 = ""
            MyApp.endMatch = true
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
            MyApp.nextTeam1 = ""
            MyApp.nextTeam2 = ""
            MyApp.endMatch = true
        }
    }


    private fun handleMatches() {
        matchCard1 = rootView.findViewById(R.id.beer_pong_group_match_card_1)
        matchCard2 = rootView.findViewById(R.id.beer_pong_group_match_card_2)
        matchText1 = rootView.findViewById(R.id.beer_pong_group_match_text_1)
        matchText2 = rootView.findViewById(R.id.beer_pong_group_match_text_2)

        matchCard1.setOnClickListener {
            if (matchList.size != 0) {
                MyApp.matchEnded = true
                matchList.removeAt(0)
                var points = teamsPoints.get(matchText1.text.toString()) as Points
                points.points++
                teamsPoints.put(matchText1.text.toString(), points)
                if (matchList.size == 0) {
                    MyApp.tourEnd = true
                }
            }
        }

        matchCard2.setOnClickListener {
            if (matchList.size != 0) {
                MyApp.matchEnded = true
                matchList.removeAt(0)
                var points = teamsPoints.get(matchText2.text.toString()) as Points
                points.points++
                teamsPoints.put(matchText2.text.toString(), points)
                if (matchList.size == 0) {
                    MyApp.tourEnd = true
                }
            }
        }

        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                if (MyApp.matchEnded && matchList.size != 0) {
                    MyApp.matchEnded = false
                    matchText1.setText(matchList[0].team1)
                    matchText2.setText(matchList[0].team2)
                } else if (matchList.size == 0) {
                    matchText1.setText("")
                    matchText2.setText("")
                }
                if (MyApp.ladderStart) {
                    if (!MyApp.endMatch) {
                        matchText1.setText(MyApp.nextTeam1)
                        matchText2.setText(MyApp.nextTeam2)
                    }
                }
                mainHandler.postDelayed(this, 500)
            }
        })
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container!!.removeView(`object` as ConstraintLayout)
    }

    private fun attachViews() {
        card411 = rootView.findViewById(R.id.beer_pong_group_4_1_1_card)
        card412 = rootView.findViewById(R.id.beer_pong_group_4_1_2_card)
        card413 = rootView.findViewById(R.id.beer_pong_group_4_1_3_card)
        card414 = rootView.findViewById(R.id.beer_pong_group_4_1_4_card)
        card421 = rootView.findViewById(R.id.beer_pong_group_4_2_1_card)
        card422 = rootView.findViewById(R.id.beer_pong_group_4_2_2_card)
        card4win = rootView.findViewById(R.id.beer_pong_group_4_win_card)
        text411 = rootView.findViewById(R.id.beer_pong_group_4_1_1_text)
        text412 = rootView.findViewById(R.id.beer_pong_group_4_1_2_text)
        text413 = rootView.findViewById(R.id.beer_pong_group_4_1_3_text)
        text414 = rootView.findViewById(R.id.beer_pong_group_4_1_4_text)
        text421 = rootView.findViewById(R.id.beer_pong_group_4_2_1_text)
        text422 = rootView.findViewById(R.id.beer_pong_group_4_2_2_text)
        text4win = rootView.findViewById(R.id.beer_pong_group_4_win_text)

        cardMatch1 = rootView.findViewById(R.id.beer_pong_group_match_card_1)
        cardMatch2 = rootView.findViewById(R.id.beer_pong_group_match_card_2)
        textMatch1 = rootView.findViewById(R.id.beer_pong_group_match_text_1)
        textMatch2 = rootView.findViewById(R.id.beer_pong_group_match_text_2)

        groupLayout11 = rootView.findViewById(R.id.beer_pong_group_1_1_layout)
        groupLayout12 = rootView.findViewById(R.id.beer_pong_group_1_2_layout)
        groupLayout13 = rootView.findViewById(R.id.beer_pong_group_1_3_layout)
        groupLayout14 = rootView.findViewById(R.id.beer_pong_group_1_4_layout)
        groupLayout21 = rootView.findViewById(R.id.beer_pong_group_2_1_layout)
        groupLayout22 = rootView.findViewById(R.id.beer_pong_group_2_2_layout)
        groupLayout23 = rootView.findViewById(R.id.beer_pong_group_2_3_layout)
        groupLayout24 = rootView.findViewById(R.id.beer_pong_group_2_4_layout)

        groupText11 = rootView.findViewById(R.id.beer_pong_group_1_1_text)
        groupText12 = rootView.findViewById(R.id.beer_pong_group_1_2_text)
        groupText13 = rootView.findViewById(R.id.beer_pong_group_1_3_text)
        groupText14 = rootView.findViewById(R.id.beer_pong_group_1_4_text)
        groupText21 = rootView.findViewById(R.id.beer_pong_group_2_1_text)
        groupText22 = rootView.findViewById(R.id.beer_pong_group_2_2_text)
        groupText23 = rootView.findViewById(R.id.beer_pong_group_2_3_text)
        groupText24 = rootView.findViewById(R.id.beer_pong_group_2_4_text)
    }
}