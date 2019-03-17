package com.harragan.battleshipsboot.model.kotlinmodel.ships

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Ship(
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        var id: Int? = null,
        val orientation: Orientation,
        val boardPosition: BoardPosition,
        var occupiedBoardPositions: List<BoardPosition>? = null,
        var isSunk: Boolean = false,
        val type: ShipType
)
{
    constructor(orientation: Orientation, boardPosition: BoardPosition) : this(orientation, boardPosition)
}

