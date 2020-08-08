package com.partos.gamebox.models

data class Action(
    var id: Long,
    var round: Int,
    var name: String,
    var action: String
)