package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController {

    @Autowired
    private val gameService: GameService? = null

    @RequestMapping(value = ["/creategame/{numberOfPlayers}/{arenaSize}"], method = [RequestMethod.POST])
    fun createGame(@PathVariable numberOfPlayers: Int, @PathVariable arenaSize: Int): ResponseEntity<Int> {
        val gameId = gameService!!.createGame(numberOfPlayers, arenaSize)
        return ResponseEntity(gameId, HttpStatus.CREATED)
    }
}