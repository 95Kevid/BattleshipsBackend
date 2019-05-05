package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition

data class ShootRequest(
        val playerId: Int,
        val gameId: Int,
        val boardPosition: BoardPosition
)
