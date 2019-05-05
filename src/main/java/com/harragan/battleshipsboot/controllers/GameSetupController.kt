package com.harragan.battleshipsboot.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class GameSetupController(private val setupGame: SetupGame) {

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = ["/setupgame"], method = [RequestMethod.POST])
    fun setupGame() {
        setupGame.setup()
    }
}