package com.harragan.battleshipsboot.model.kotlinmodel.ships

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation
import javax.persistence.*

@Entity
data class Ship @JvmOverloads constructor(
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        var id: Int = -1,
        val orientation: Orientation,
        @OneToOne
        val boardPosition: BoardPosition,
        @OneToMany
        var occupiedBoardPositions: MutableList<BoardPosition> = ArrayList(),
        var isSunk: Boolean = false,
        val type: ShipType
) {
    init {
        occupiedBoardPositions.add(0, boardPosition)
    }
}
