package com.partos.gamebox.models

data class AlcoholDb (
    var id: Long,
    var cauldronId: Long,
    var name: String,
    var amount: Int,
    var type: String
)