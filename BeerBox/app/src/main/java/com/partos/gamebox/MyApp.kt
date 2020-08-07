package com.partos.gamebox

import android.app.Application

class MyApp: Application() {
    companion object {
        var nextTeam1 = "elo"
        var nextTeam2 = "elo2"
        var endMatch = false
        var matchEnded = true
        var tourEnd = false
        var ladderStart = false
        var areTeamsOpened = false
    }
}