package com.partos.beerbox.pager


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
import com.partos.beerbox.MyApp
import com.partos.beerbox.R
import kotlinx.android.synthetic.main.pager_cell_beer_pong_championship.view.*
import kotlinx.android.synthetic.main.pager_cell_beer_pong_group.view.*
import kotlin.random.Random

class BeerPongGroupViewPagerAdapter : PagerAdapter {

    var context: Context
    var teams = ArrayList<String>()

    private lateinit var rootView: View


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
            inflater.inflate(R.layout.pager_cell_beer_pong_group, container, false)
        MyApp.endMatch = false

        rootView = view
        if (position == 0) {
            view.pager_cell_beer_pong_group_match_constraint.visibility = View.VISIBLE
        } else if (position == 1) {
            view.pager_cell_beer_pong_group.visibility = View.VISIBLE
        }

        container!!.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container!!.removeView(`object` as ConstraintLayout)
    }

    private fun attachViews() {


    }
}