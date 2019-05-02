package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.facades.ShootingFacade
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

class ShootingController(private val shootingFacade: ShootingFacade) {
    @RequestMapping(value = ["/shoot"], method = [RequestMethod.POST])
    fun shoot(playerId: Int, gameId:Int, position: BoardPosition) {
        shootingFacade.shootPosition(playerId, gameId, position)
    }
}