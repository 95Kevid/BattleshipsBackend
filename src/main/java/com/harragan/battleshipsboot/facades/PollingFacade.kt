package com.harragan.battleshipsboot.facades

import com.harragan.battleshipsboot.model.game.PlayersToPlayersReady
import com.harragan.battleshipsboot.service.GameService
import org.springframework.stereotype.Service

@Service
class PollingFacade(
        private val gameService: GameService
) {
    fun getNumberOfNotReadyPlayersToReadyPlayers(gameId: Int): PlayersToPlayersReady {
        return gameService.getPlayersToPlayersReady(gameId)
    }
}
