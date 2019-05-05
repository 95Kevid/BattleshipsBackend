package com.harragan.battleshipsboot.model.kotlinmodel.ships

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation
import javax.persistence.*

@Entity
data class Ship @JvmOverloads constructor(
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        var id: Int? = null,
        val orientation: Orientation,
        @OneToOne(cascade = [CascadeType.ALL])
        val boardPosition: BoardPosition,
        @OneToMany(cascade = [CascadeType.ALL])
        var occupiedBoardPositions: MutableList<BoardPosition> = ArrayList(),
        var isSunk: Boolean = false,
        val type: ShipType
) {
    init {
        occupiedBoardPositions.add(0, boardPosition)
    }
}
