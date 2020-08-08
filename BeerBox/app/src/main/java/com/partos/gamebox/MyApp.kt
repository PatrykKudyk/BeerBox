package com.partos.gamebox

import android.app.Application
import com.partos.gamebox.models.Action
import com.partos.gamebox.models.Round

class MyApp: Application() {
    companion object {
        var nextTeam1 = "elo"
        var nextTeam2 = "elo2"
        var endMatch = false
        var matchEnded = true
        var tourEnd = false
        var ladderStart = false
        var areTeamsOpened = false
        var currentActionList = ArrayList<Action>()
        var round = Round(0,0)
        var nightEnd = false
    }
}