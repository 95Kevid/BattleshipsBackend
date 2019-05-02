package com.harragan.battleshipsboot.model.kotlinmodel.game

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class BoardPosition @JvmOverloads constructor(

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        var id: Int? = null,
        val col: Char,
        val row: Int,
        var isHit: Boolean = false
) {
    fun positionEqual(position: BoardPosition): Boolean {
        print("equals dadsfaadsf")
        return position.col.equals(this.col) && position.row == this.row
    }
}


