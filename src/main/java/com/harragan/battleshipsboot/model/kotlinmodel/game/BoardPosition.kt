package com.harragan.battleshipsboot.model.kotlinmodel.game

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class BoardPosition @JvmOverloads constructor(
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        val id: Int,
        val col: Char,
        val row: Int,
        val isHit: Boolean
)


