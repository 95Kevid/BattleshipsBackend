package com.harragan.battleshipsboot.controllers

data class JoinGameResponse(
        val playerId: Int,
        val gameId: Int,
        val gridSize: Int
)