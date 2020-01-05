package com.harragan.battleshipsboot.model.kotlinmodel.game

data class LoadGameRequest(
        val gameId: Int,
        val playerId: Int
)