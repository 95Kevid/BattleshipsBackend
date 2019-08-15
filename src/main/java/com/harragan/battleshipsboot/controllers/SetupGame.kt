package com.harragan.battleshipsboot.controllers

import com.harragan.battleshipsboot.facades.PlayerAddingFacade
import com.harragan.battleshipsboot.facades.ShipPlacingFacade
import com.harragan.battleshipsboot.facades.ShootingFacade
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation
import com.harragan.battleshipsboot.model.kotlinmodel.game.ShootRequest
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship
import com.harragan.battleshipsboot.model.kotlinmodel.ships.ShipType
import com.harragan.battleshipsboot.repositorys.PlayerRepository
import com.harragan.battleshipsboot.service.BoardPositionFactory
import com.harragan.battleshipsboot.service.GameService
import com.harragan.battleshipsboot.service.PlayerService
import org.springframework.stereotype.Component

@Component
class SetupGame(
        private val gameService: GameService,
        private val playerAddingFacade: PlayerAddingFacade,
        private val shipPlacingFacade: ShipPlacingFacade,
        private val playerService: PlayerService,
        private val playerRepository: PlayerRepository,
        private val shootingFacade: ShootingFacade
) {
    fun setup() {
        gameService.createGame(2, 26)
        playerAddingFacade.createPlayerAndJoinToGame("Kevin", 1)
        playerAddingFacade.createPlayerAndJoinToGame("Helga", 1)

        val battleShip: Ship = Ship(1, orientation = Orientation.HORIZONTAL, boardPosition = BoardPosition(col = 'A', row = 1, isHit = false), type = ShipType.BATTLESHIP)
        val submarine: Ship = Ship(2, orientation = Orientation.HORIZONTAL, boardPosition = BoardPosition(col = 'E', row = 1, isHit = false), type = ShipType.SUBMARINE)
        val destroyer: Ship = Ship(3, orientation = Orientation.HORIZONTAL, boardPosition = BoardPosition(col = 'I', row = 1, isHit = false), type = ShipType.DESTROYER)
        val carrier: Ship = Ship(4, orientation = Orientation.HORIZONTAL, boardPosition = BoardPosition(col = 'K', row = 1, isHit = false), type = ShipType.CARRIER)
        val cruiser: Ship = Ship(5, orientation = Orientation.HORIZONTAL, boardPosition = BoardPosition(col = 'Q', row = 1, isHit = false), type = ShipType.CRUISER)

        val battleShip2: Ship = Ship(6, orientation = Orientation.HORIZONTAL, boardPosition = BoardPosition(col = 'A', row = 1, isHit = false), type = ShipType.BATTLESHIP)
        val submarine2: Ship = Ship(7, orientation = Orientation.HORIZONTAL, boardPosition = BoardPosition(col = 'E', row = 1, isHit = false), type = ShipType.SUBMARINE)
        val destroyer2: Ship = Ship(8, orientation = Orientation.HORIZONTAL, boardPosition = BoardPosition(col = 'I', row = 1, isHit = false), type = ShipType.DESTROYER)
        val carrier2: Ship = Ship(9, orientation = Orientation.HORIZONTAL, boardPosition = BoardPosition(col = 'K', row = 1, isHit = false), type = ShipType.CARRIER)
        val cruiser2: Ship = Ship(10, orientation = Orientation.HORIZONTAL, boardPosition = BoardPosition(col = 'Q', row = 1, isHit = false), type = ShipType.CRUISER)

        shipPlacingFacade.placeShip(1, 1, battleShip)
        shipPlacingFacade.placeShip(1, 1, submarine)
        shipPlacingFacade.placeShip(1, 1, destroyer)
        shipPlacingFacade.placeShip(1, 1, carrier)
        shipPlacingFacade.placeShip(1, 1, cruiser)

        playerService.setPlayerIsReady(1, playerRepository)

        shipPlacingFacade.placeShip(2, 1, battleShip2)
        shipPlacingFacade.placeShip(2, 1, submarine2)
        shipPlacingFacade.placeShip(2, 1, destroyer2)
        shipPlacingFacade.placeShip(2, 1, carrier2)
        shipPlacingFacade.placeShip(2, 1, cruiser2)

        playerService.setPlayerIsReady(2, playerRepository)
        shootSequence()

    }

    fun shootSequence() {
        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('A', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('A', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('B', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('B', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('C', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('C', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('D', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('D', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('E', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('E', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('F', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('F', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('G', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('G', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('G', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('G', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('H', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('H', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('I', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('I', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('J', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('J', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('K', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('K', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('L', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('L', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('M', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('M', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('N', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('N', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('O', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('O', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('P', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('P', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('Q', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('Q', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('R', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('R', 1)))

        shootingFacade.shootPosition(ShootRequest(1, 1, BoardPositionFactory.createBoardPosition('S', 1)))
        shootingFacade.shootPosition(ShootRequest(2, 1, BoardPositionFactory.createBoardPosition('S', 1)))
    }
}

