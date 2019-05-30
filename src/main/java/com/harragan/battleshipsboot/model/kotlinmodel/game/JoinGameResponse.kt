package com.harragan.battleshipsboot.model.kotlinmodel.game

data class JoinGameResponse(
        val playerId: Int,
        val gameId: Int,
        val gridSize: Int
)