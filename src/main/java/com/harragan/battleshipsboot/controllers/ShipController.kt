package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.facades.ShipPlacingFacade
import com.harragan.battleshipsboot.model.ships.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
class ShipController {

    @Autowired
    private val shipPlacingFacade: ShipPlacingFacade? = null

    @RequestMapping(value = ["/placedestroyer"], method = [RequestMethod.POST])
    fun placeDestroyer(
            @RequestBody shipPlaceRequestWrapper: ShipPlaceRequestWrapper): ResponseEntity<Destroyer> {
        val destoyer = Destroyer(shipPlaceRequestWrapper.orientation, shipPlaceRequestWrapper.boardPosition)
        shipPlacingFacade!!.placeShip(shipPlaceRequestWrapper.playerId, shipPlaceRequestWrapper.gameId, destoyer)
        println("End point hit!")
        return ResponseEntity(destoyer, HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/placecarrier"], method = [RequestMethod.POST])
    fun placeCarrier(
            @RequestBody shipPlaceRequestWrapper: ShipPlaceRequestWrapper): ResponseEntity<Carrier> {
        val carrier = Carrier(shipPlaceRequestWrapper.orientation, shipPlaceRequestWrapper.boardPosition)
        shipPlacingFacade!!.placeShip(shipPlaceRequestWrapper.playerId, shipPlaceRequestWrapper.gameId, carrier)
        println("End point hit!")
        return ResponseEntity(carrier, HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/placesubmarine"], method = [RequestMethod.POST])
    fun placeSubmarine(
            @RequestBody shipPlaceRequestWrapper: ShipPlaceRequestWrapper): ResponseEntity<Submarine> {
        val submarine = Submarine(shipPlaceRequestWrapper.orientation, shipPlaceRequestWrapper.boardPosition)
        shipPlacingFacade!!.placeShip(shipPlaceRequestWrapper.playerId, shipPlaceRequestWrapper.gameId, submarine)
        println("End point hit!")
        return ResponseEntity(submarine, HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/placecruiser"], method = [RequestMethod.POST])
    fun placeCruiser(
            @RequestBody shipPlaceRequestWrapper: ShipPlaceRequestWrapper): ResponseEntity<Cruiser> {
        val cruiser = Cruiser(shipPlaceRequestWrapper.orientation, shipPlaceRequestWrapper.boardPosition)
        shipPlacingFacade!!.placeShip(shipPlaceRequestWrapper.playerId, shipPlaceRequestWrapper.gameId, cruiser)
        println("End point hit!")
        return ResponseEntity(cruiser, HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/placebattleship"], method = [RequestMethod.POST])
    fun placeBattleship(
            @RequestBody shipPlaceRequestWrapper: ShipPlaceRequestWrapper): ResponseEntity<Battleship> {
        val battleship = Battleship(shipPlaceRequestWrapper.orientation, shipPlaceRequestWrapper.boardPosition)
        shipPlacingFacade!!.placeShip(shipPlaceRequestWrapper.playerId, shipPlaceRequestWrapper.gameId, battleship)
        println("End point hit!")
        return ResponseEntity(battleship, HttpStatus.CREATED)
    }
}


