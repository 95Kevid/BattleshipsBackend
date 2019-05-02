package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.facades.GameStatusFacade
import com.harragan.battleshipsboot.facades.PollingFacade
import com.harragan.battleshipsboot.model.game.GameStatusResponse
import com.harragan.battleshipsboot.model.game.PlayersToPlayersReady
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class PollingController(private val pollingFacade: PollingFacade,
                        private val gameStatusFacade: GameStatusFacade) {
    @RequestMapping(value = ["/waitingplayerpoll"], method = [RequestMethod.POST])
    fun checkForWaitingPlayers(@RequestBody gameId: Int): PlayersToPlayersReady {
        return pollingFacade.getNumberOfNotReadyPlayersToReadyPlayers(gameId)
    }

    @RequestMapping(value = ["/gamestatuspoll"], method = [RequestMethod.POST])
    fun getGameStatus(@RequestBody gameId: Int): GameStatusResponse {
        return gameStatusFacade.getGameStatus(gameId)
    }
}
