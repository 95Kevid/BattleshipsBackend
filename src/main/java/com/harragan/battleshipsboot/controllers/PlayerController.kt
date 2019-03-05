package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.facades.PlayerAddingFacade
import com.harragan.battleshipsboot.repositorys.PlayerRepository
import com.harragan.battleshipsboot.service.PlayerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class PlayerController(
        private val playerAddingFacade: PlayerAddingFacade,
        private val playerService: PlayerService,
        private val playerRepository: PlayerRepository
) {
    @RequestMapping(value = ["/addplayer"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createPlayer(@RequestBody playerRequestWrapper: PlayerRequestWrapper): Int {
        return playerAddingFacade.createPlayerAndJoinToGame(playerRequestWrapper.playerName
                , playerRequestWrapper.gameId)
    }

    @RequestMapping(value = ["/readytostart/{playerNo}"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.OK)
    fun playerReady(@PathVariable playerNo: Int) {
        playerService.setPlayerIsReady(playerNo, playerRepository)
    }
}
