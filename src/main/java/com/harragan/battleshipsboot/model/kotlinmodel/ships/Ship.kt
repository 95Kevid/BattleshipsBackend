package com.harragan.battleshipsboot.model.kotlinmodel.ships

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation
import javax.persistence.MappedSuperclass

@MappedSuperclass
interface Ship {
    val id: Int
    val length: Int
    val boardPosition: BoardPosition
    val occupiedBoardPositions: List<BoardPosition>
    val orientation: Orientation
    val isSunk: Boolean
}