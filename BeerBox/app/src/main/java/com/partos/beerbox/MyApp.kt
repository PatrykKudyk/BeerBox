package com.partos.beerbox

import android.app.Application

class MyApp: Application() {
    companion object {
        var teamsNumber = 0
        var nextTeam1 = "elo"
        var nextTeam2 = "elo2"
        var endMatch = false
    }
}