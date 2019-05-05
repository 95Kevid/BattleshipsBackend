package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.facades.ShootingFacade
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class ShootingController(private val shootingFacade: ShootingFacade) {

    @RequestMapping(value = ["/shoot"], method = [RequestMethod.POST])
    fun shoot(@RequestBody shootRequest: ShootRequest) {
        shootingFacade.shootPosition(shootRequest)
    }
}