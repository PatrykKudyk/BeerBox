package com.partos.gamebox.recycler

import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.R
import kotlinx.android.synthetic.main.row_sound.view.*

class SoundsRecyclerViewAdapter(
    val soundPool: SoundPool,
    val soundArray: ArrayList<Int>
) :
    RecyclerView.Adapter<SoundsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_sound, parent, false)
        return SoundsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: SoundsViewHolder, position: Int) {
        val context = holder.view.context
        val name = holder.view.row_sound_name
        val image = holder.view.row_sound_image
        image.setImageDrawable(context.getDrawable(R.drawable.ic_play_yellow))
        when (position) {
            0 -> name.text = context.getText(R.string.announcement)
            1 -> name.text = context.getText(R.string.city_wins)
            2 -> name.text = context.getText(R.string.mafia_wins)
            3 -> name.text = context.getText(R.string.miasto_budzi)
//            4 -> name.text = context.getText(R.string.orchestra_power)
//            5 -> name.text = context.getText(R.string.orchestra_normal)
//            6 -> name.text = context.getText(R.string.orchestra_sad)
        }
        var stream = 0
        var isUsed = false
        holder.view.row_sound_card.setOnClickListener {
            when (position) {
                in 0..3 -> {
                    if (!isUsed) {
                        isUsed = true
                        image.setImageDrawable(context.getDrawable(R.drawable.ic_pause_yellow))
                        var delay = 0
                        when (position) {
                            0 -> delay = 5200
                            1 -> delay = 6000
                            2 -> delay = 5700
                            3 -> delay = 3300
                        }
                        stream = soundPool.play(soundArray[position], 1F, 1F, 0, 0, 1F)
                        Handler().postDelayed({
                            image.setImageDrawable(context.getDrawable(R.drawable.ic_play_yellow))
                            isUsed = false
                        }, delay.toLong())
                    } else {
                        isUsed = false
                        image.setImageDrawable(context.getDrawable(R.drawable.ic_play_yellow))
                        soundPool.stop(stream)
                    }
                }
            }
        }
    }

}


class SoundsViewHolder(val view: View) : RecyclerView.ViewHolder(view)