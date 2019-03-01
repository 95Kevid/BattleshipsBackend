package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.facades.PlayerAddingFacade
import com.harragan.battleshipsboot.repositorys.PlayerRepository
import com.harragan.battleshipsboot.service.PlayerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PlayerController {

    @Autowired
    private val playerAddingFacade: PlayerAddingFacade? = null

    @Autowired
    private val playerService: PlayerService? = null

    @Autowired
    private val playerRepository: PlayerRepository? = null

    @RequestMapping(value = "/addplayer", method = [RequestMethod.POST])
    fun createPlayer(@RequestBody playerRequestWrapper: PlayerRequestWrapper): ResponseEntity<Int> {
        val playerId: Int = playerAddingFacade!!.createPlayerAndJoinToGame(playerRequestWrapper.playerName
                , playerRequestWrapper.gameId)
        return ResponseEntity(playerId, HttpStatus.CREATED)
    }

    @RequestMapping(value = "/readytostart/{playerNo}", method = [RequestMethod.POST])
    @ResponseStatus(value = HttpStatus.OK)
    fun playerReady(@PathVariable playerNo: Int) {
        playerService!!.setPlayerIsReady(playerNo, playerRepository)
        println("Ready to start has been set")
    }
}