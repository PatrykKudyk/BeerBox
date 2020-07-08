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
import kotlinx.android.synthetic.main.pager_cell_beer_pong_championship.view.*
import kotlinx.android.synthetic.main.pager_cell_beer_pong_group.view.*
import kotlin.random.Random

class BeerPongGroupViewPagerAdapter : PagerAdapter {

    var context: Context
    var teams = ArrayList<String>()

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

                MyApp.nextTeam1 = teams[0]
                MyApp.nextTeam2 = teams[1]
                MyApp.nextMatch = true
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

                MyApp.nextTeam1 = teams[0]
                MyApp.nextTeam2 = teams[1]
                MyApp.nextMatch = true
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

                MyApp.nextTeam1 = teams[0]
                MyApp.nextTeam2 = teams[1]
                MyApp.nextMatch = true
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

                MyApp.nextTeam1 = teams[0]
                MyApp.nextTeam2 = teams[1]
                MyApp.nextMatch = true
            }
        }
    }

    private fun makeLayoutsInvisible() {
        groupLayout11.visibility = View.INVISIBLE
        groupLayout12.visibility = View.INVISIBLE
        groupLayout13.visibility = View.INVISIBLE
        groupLayout13.visibility = View.INVISIBLE
        groupLayout21.visibility = View.INVISIBLE
        groupLayout22.visibility = View.INVISIBLE
        groupLayout23.visibility = View.INVISIBLE
        groupLayout23.visibility = View.INVISIBLE
    }

    private fun handleChampionship() {

    }

    private fun handleMatches() {
        attachViews()

        matchCard1.setOnClickListener {
            MyApp.matchEnded = true
            MyApp.winner = 0
        }

        matchCard1.setOnClickListener {
            MyApp.matchEnded = true
            MyApp.winner = 1
        }

        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                if (MyApp.matchEnded) {
                    MyApp.matchEnded = false
                    if (MyApp.nextMatch) {
                        MyApp.nextMatch = false
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
        matchCard1 = rootView.findViewById(R.id.beer_pong_group_match_card_1)
        matchCard2 = rootView.findViewById(R.id.beer_pong_group_match_card_2)
        matchText1 = rootView.findViewById(R.id.beer_pong_group_match_text_1)
        matchText2 = rootView.findViewById(R.id.beer_pong_group_match_text_2)

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