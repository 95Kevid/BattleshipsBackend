package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.service.GameService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class GameController(private val gameService: GameService) {

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = ["/creategame/{numberOfPlayers}/{arenaSize}"], method = [RequestMethod.POST])
    fun createGame(@PathVariable numberOfPlayers: Int, @PathVariable arenaSize: Int): Int =
            gameService.createGame(numberOfPlayers, arenaSize)
}
