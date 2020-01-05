package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation
import com.harragan.battleshipsboot.model.kotlinmodel.ships.ShipType

data class ShipPlaceRequestWrapper(
        val playerId: Int,
        val gameId: Int,
        val boardPosition: BoardPosition,
        val orientation: Orientation,
        val shipType: ShipType
)