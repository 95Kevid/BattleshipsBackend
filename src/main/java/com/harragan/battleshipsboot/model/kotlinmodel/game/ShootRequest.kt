package com.harragan.battleshipsboot.model.kotlinmodel.game

data class ShootRequest(
        val playerId: Int,
        val gameId: Int,
        val boardPosition: BoardPosition
)
