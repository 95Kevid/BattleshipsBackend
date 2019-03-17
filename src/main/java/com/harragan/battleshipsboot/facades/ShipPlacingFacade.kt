package com.harragan.battleshipsboot.facades

import com.harragan.battleshipsboot.model.game.Player
import com.harragan.battleshipsboot.model.ships.Ship
import com.harragan.battleshipsboot.service.GameArenaService
import com.harragan.battleshipsboot.service.GameService
import com.harragan.battleshipsboot.service.PlayerService
import org.springframework.stereotype.Service

@Service
class ShipPlacingFacade(
        private val gameArenaService: GameArenaService,
        private val gameService: GameService,
        private val playerService: PlayerService) {

    fun placeShip(playerId: Int, gameId: Int, ship: Ship){
        val player = playerService.getPlayerById(playerId);
        val gameArena = player.gameArena
        gameArenaService.addShip(ship, gameArena)
        val game = gameService.getGame(gameId)
        gameService.saveGame(game)
    }
}