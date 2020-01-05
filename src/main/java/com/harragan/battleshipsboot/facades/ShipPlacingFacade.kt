package com.harragan.battleshipsboot.facades

import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship
import com.harragan.battleshipsboot.service.GameArenaService
import com.harragan.battleshipsboot.service.PlayerService
import com.harragan.battleshipsboot.service.ShipColouringService
import org.springframework.stereotype.Service

@Service
class ShipPlacingFacade(
        private val gameArenaService: GameArenaService,
        private val playerService: PlayerService,
        private val shipColouringService: ShipColouringService) {

    fun placeShip(playerId: Int, gameId: Int, ship: Ship) {
        val player = playerService.getPlayerById(playerId)
        val gameArena = player.gameArena
        gameArenaService.addShip(ship, gameArena)
        shipColouringService.colourShip(ship)
        playerService.savePlayer(player)
    }
}