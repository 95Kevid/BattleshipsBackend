package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.facades.ShipPlacingFacade
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
class ShipController(private val shipPlacingFacade: ShipPlacingFacade) {
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = ["/placeShip"], method = [RequestMethod.POST])
    fun placeDestroyer(
            @RequestBody shipPlaceRequestWrapper: ShipPlaceRequestWrapper): Ship {
        val ship = Ship(shipPlaceRequestWrapper.orientation, shipPlaceRequestWrapper.boardPosition, shipPlaceRequestWrapper.shipType)
        shipPlacingFacade.placeShip(shipPlaceRequestWrapper.playerId, shipPlaceRequestWrapper.gameId, ship)
        return ship
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = ["/placecarrier"], method = [RequestMethod.POST])
    fun placeCarrier(
            @RequestBody shipPlaceRequestWrapper: ShipPlaceRequestWrapper):  {
        val carrier = Carrier(shipPlaceRequestWrapper.orientation, shipPlaceRequestWrapper.boardPosition)
        shipPlacingFacade.placeShip(shipPlaceRequestWrapper.playerId, shipPlaceRequestWrapper.gameId, carrier)
        return carrier
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = ["/placesubmarine"], method = [RequestMethod.POST])
    fun placeSubmarine(
            @RequestBody shipPlaceRequestWrapper: ShipPlaceRequestWrapper): Submarine {
        val submarine = Submarine(shipPlaceRequestWrapper.orientation, shipPlaceRequestWrapper.boardPosition)
        shipPlacingFacade.placeShip(shipPlaceRequestWrapper.playerId, shipPlaceRequestWrapper.gameId, submarine)
        return submarine
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = ["/placecruiser"], method = [RequestMethod.POST])
    fun placeCruiser(
            @RequestBody shipPlaceRequestWrapper: ShipPlaceRequestWrapper): Cruiser {
        val cruiser = Cruiser(shipPlaceRequestWrapper.orientation, shipPlaceRequestWrapper.boardPosition)
        shipPlacingFacade.placeShip(shipPlaceRequestWrapper.playerId, shipPlaceRequestWrapper.gameId, cruiser)
        return cruiser
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = ["/placebattleship"], method = [RequestMethod.POST])
    fun placeBattleship(
            @RequestBody shipPlaceRequestWrapper: ShipPlaceRequestWrapper): Battleship {
        val battleship = Battleship(shipPlaceRequestWrapper.orientation, shipPlaceRequestWrapper.boardPosition)
        shipPlacingFacade.placeShip(shipPlaceRequestWrapper.playerId, shipPlaceRequestWrapper.gameId, battleship)
        return battleship
    }
}


