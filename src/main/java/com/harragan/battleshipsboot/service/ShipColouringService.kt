package com.harragan.battleshipsboot.service

import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship
import com.harragan.battleshipsboot.model.kotlinmodel.ships.ShipType
import org.springframework.stereotype.Service

@Service
class ShipColouringService{

        fun colourShip(ship: Ship) {
            when(ship.type) {
                ShipType.BATTLESHIP -> colour(ship, "Orange")
                ShipType.CARRIER -> colour(ship, "Green")
                ShipType.DESTROYER -> colour(ship, "Pink")
                ShipType.CRUISER -> colour(ship, "#42f5cb")
                ShipType.SUBMARINE -> colour(ship, "Yellow")
            }

        }

    private fun colour(ship: Ship, colour: String) {
        ship.occupiedBoardPositions.forEach { position -> position.colour = colour }
    }
}