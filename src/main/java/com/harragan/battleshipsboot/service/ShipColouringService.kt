package com.harragan.battleshipsboot.service

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship
import org.springframework.stereotype.Service

@Service
class ShipColouringService{

        fun colourShip(ship: Ship, colour: String) {
            ship.occupiedBoardPositions.forEach { boardPosition -> boardPosition }
        }
}