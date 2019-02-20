package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.facades.PollingFacade
import com.harragan.battleshipsboot.model.game.PlayersToPlayersReady
import org.springframework.beans.factory.annotation .Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation .RequestBody
import org.springframework.web.bind.annotation .RequestMapping
import org.springframework.web.bind.annotation .RequestMethod
import org.springframework.web.bind.annotation .RestController

@RestController
class PollingController {

    @Autowired
    private val pollingFacade: PollingFacade? = null

    @RequestMapping(value = "/waitingplayerpoll", method = [RequestMethod.POST])
    fun checkForWaitingPlayers(@RequestBody gameId: Int): ResponseEntity<PlayersToPlayersReady> {
        val playersToPlayersNotReady = pollingFacade!!
                .getNumberOfNotReadyPlayersToReadyPlayers(gameId)
        return ResponseEntity(playersToPlayersNotReady, HttpStatus.OK)
    }
}
