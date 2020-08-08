package com.partos.gamebox.recycler

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.activities.MainActivity
import com.partos.gamebox.R
import com.partos.gamebox.fragments.beerpong.BeerPongMenuFragment
import com.partos.gamebox.fragments.bottlesgame.BottlesGameMenuFragment
import com.partos.gamebox.fragments.cauldron.CauldronMenuFragment
import com.partos.gamebox.fragments.dices.DicesChoiceFragment
import com.partos.gamebox.fragments.mafia.MafiaMenuFragment
import com.partos.gamebox.fragments.timer.TimerMenuFragment
import kotlinx.android.synthetic.main.row_main_menu.view.*

class MainMenuRecyclerViewAdapter() : RecyclerView.Adapter<MainMenuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.row_main_menu, parent, false)
        return MainMenuViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        val cardView = holder.view.row_menu_card_view
        val image = holder.view.menu_image_view
        val text = holder.view.menu_text_view

        when (position) {
            0 -> {
                setColor(0, holder)
                animRight(holder)
                image.setImageResource(R.drawable.beer_pong)
                text.setText(R.string.beer_pong)
            }
            1 -> {
                setColor(1, holder)
                animLeft(holder)
                image.setImageResource(R.drawable.bottles)
                text.setText(R.string.bottles)
            }
            2 -> {
                setColor(2, holder)
                animRight(holder)
                image.setImageResource(R.drawable.hat)
                text.setText(R.string.mafia)
            }
            3 -> {
                setColor(3, holder)
                animLeft(holder)
                image.setImageResource(R.drawable.cook)
                text.setText(R.string.cauldron)
            }
            4 -> {
                setColor(4, holder)
                animRight(holder)
                image.setImageResource(R.drawable.timer)
                text.setText(R.string.chess_timer)
            }
            5 -> {
                setColor(5, holder)
                animLeft(holder)
                image.setImageResource(R.drawable.dice_4)
                text.setText(R.string.dices_simulator)
            }

        }

        cardView.setOnClickListener {
            lateinit var fragment: Fragment
            when (position) {
                0 -> {
                    fragment = BeerPongMenuFragment.newInstance()
                }
                1 -> {
                    fragment = BottlesGameMenuFragment.newInstance()
                }
                2 -> {
                    fragment = MafiaMenuFragment.newInstance()
                }
                3 -> {
                    fragment = CauldronMenuFragment.newInstance()
                }
                4 -> {
                    fragment = TimerMenuFragment.newInstance()
                }
                5 -> {
                    fragment = DicesChoiceFragment.newInstance()
                }
            }
            val manager = (holder.itemView.context as MainActivity).supportFragmentManager
            manager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack("game chosen")
                .commit()
        }
    }

    private fun setColor(color: Int, holder: MainMenuViewHolder) {
        val cardView = holder.view.row_menu_card_view
        when (color) {
            0 -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorRedLight
                    )
                )
                cardView.setStrokeColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorRedDark
                    )
                )
            }
            1 -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorGreenLight
                    )
                )
                cardView.setStrokeColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorGreenDark
                    )
                )
            }
            2 -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorYellowLight
                    )
                )
                cardView.setStrokeColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorYellowDark
                    )
                )
            }
            3 -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorPurpleLight
                    )
                )
                cardView.setStrokeColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorPurpleDark
                    )
                )
            }
            4 -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorBlueLight
                    )
                )
                cardView.setStrokeColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorBlueDark
                    )
                )
            }
            5 -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorOrangeLight
                    )
                )
                cardView.setStrokeColor(
                    ContextCompat.getColor(
                        holder.view.context,
                        R.color.colorOrangeDark
                    )
                )
            }
        }
    }

    private fun animLeft(holder: MainMenuViewHolder) {
        val cardView = holder.view.row_menu_card_view
        val animation =
            AnimationUtils.loadAnimation(holder.view.context, R.anim.enter_left_to_right)
        Handler().postDelayed({
            cardView.visibility = View.VISIBLE
            cardView.startAnimation(animation)
        }, 900)
    }

    private fun animRight(holder: MainMenuViewHolder) {
        val cardView = holder.view.row_menu_card_view
        val animation =
            AnimationUtils.loadAnimation(holder.view.context, R.anim.enter_right_to_left)
        Handler().postDelayed({
            cardView.visibility = View.VISIBLE
            cardView.startAnimation(animation)
        }, 900)
    }
}


class MainMenuViewHolder(val view: View) : RecyclerView.ViewHolder(view)