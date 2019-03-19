package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.facades.ShipPlacingFacade
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
class ShipController(private val shipPlacingFacade: ShipPlacingFacade) {
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = ["/placeShip"], method = [RequestMethod.POST])
    fun placeShip(
            @RequestBody shipPlaceRequestWrapper: ShipPlaceRequestWrapper): Ship {
        val ship = Ship(orientation = shipPlaceRequestWrapper.orientation, boardPosition = shipPlaceRequestWrapper.boardPosition, type = shipPlaceRequestWrapper.shipType)
        shipPlacingFacade.placeShip(shipPlaceRequestWrapper.playerId, shipPlaceRequestWrapper.gameId, ship)
        return ship
    }
}


