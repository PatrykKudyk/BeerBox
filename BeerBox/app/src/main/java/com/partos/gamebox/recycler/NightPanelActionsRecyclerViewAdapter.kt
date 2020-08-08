package com.partos.gamebox.recycler

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.MyApp
import com.partos.gamebox.R
import com.partos.gamebox.logic.TimerThread
import com.partos.gamebox.models.Action
import kotlinx.android.synthetic.main.row_mafia_action_night.view.*

class NightPanelActionsRecyclerViewAdapter(val actionsList: ArrayList<String>) :
    RecyclerView.Adapter<NightPanelActionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NightPanelActionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_mafia_action_night, parent, false)
        return NightPanelActionsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return actionsList.size
    }

    override fun onBindViewHolder(holder: NightPanelActionsViewHolder, position: Int) {
        holder.view.row_mafia_action_text.text = actionsList[position]
        loop(holder, position)
    }

    private fun loop(holder: NightPanelActionsViewHolder, position: Int) {
        var looperThread = TimerThread()
        looperThread.start()
        Handler().postDelayed({
            var threadHandler = Handler(looperThread.looper)
            threadHandler.post(object : Runnable {
                override fun run() {
                    if (MyApp.nightEnd) {
                        if (holder.view.row_mafia_action_edit.text.toString() != ""){
                            MyApp.currentActionList.add(
                                Action(
                                    0,
                                    MyApp.round.number,
                                    holder.view.row_mafia_action_edit.text.toString(),
                                    actionsList[position]
                                )
                            )
                        }
                        Handler().postDelayed({
                            MyApp.nightEnd = false
                        }, 110)
//                        looperThread.looper.quitSafely()
                    } else {
                        threadHandler.postDelayed(this, 50)
                    }
                }
            })
        }, 300)

    }
}

class NightPanelActionsViewHolder(val view: View) : RecyclerView.ViewHolder(view)