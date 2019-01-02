package com.harragan.battleshipsboot.model.kotlinmodel.ships

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation


data class Battleship @JvmOverloads constructor (
        override val orientation : Orientation,
        override val boardPosition: BoardPosition,
        override val occupiedBoardPositions : List<BoardPosition>,
        override val length : Int = 4,
        override val id: Int,
        override val isSunk: Boolean = false
) : Ship

