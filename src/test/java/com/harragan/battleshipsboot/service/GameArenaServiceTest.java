package com.harragan.battleshipsboot.service;
import com.harragan.battleshipsboot.model.ships.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Orientation;
import com.harragan.battleshipsboot.model.game.BoardPosition;
import com.harragan.battleshipsboot.service.exceptions.IllegalBoardPlacementException;
import org.mockito.Mock;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GameArenaServiceTest {
    private GameArena gameArena;
    private GameArenaService gameArenaService;
    private Battleship battleship;
    private Carrier carrier;
    private Submarine submarine;
    private Destroyer destroyer;
    private Cruiser cruiser;

    @Before
    public void initateTest() {
        gameArena = new GameArena(10);
        gameArenaService = new GameArenaService();
    }

    @Test
    public void givenAGameArenaSizeAGameArenaIsCreatedWithTheCorrectSize() {
        GameArena gameArena = gameArenaService.createGameArena(10);
        assertSame(10, gameArena.getGameArenaSize());
    }

    @Test
    public void shipsCanBePlacedInValidLocationsInTheArena() {
        gameArena.clearArena();

        BoardPosition positionA1 = new BoardPosition('A', 1);
        BoardPosition positionF1 = new BoardPosition('F', 1);
        BoardPosition positionG6 = new BoardPosition('G', 6);
        BoardPosition positionF10 = new BoardPosition('F', 10);
        BoardPosition positionA10 = new BoardPosition('A', 10);

        battleship = new Battleship(Orientation.VERTICAL, positionF1);
        carrier = new Carrier(Orientation.HORIZONTAL, positionF10);
        submarine = new Submarine(Orientation.VERTICAL, positionG6);
        destroyer = new Destroyer(Orientation.VERTICAL, positionA1);
        cruiser = new Cruiser(Orientation.HORIZONTAL, positionA10);

        gameArenaService.addShip(carrier, gameArena);
        gameArenaService.addShip(battleship,gameArena);
        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(destroyer, gameArena);
        gameArenaService.addShip(cruiser, gameArena);

        assertSame(battleship, gameArenaService.getShipAtPosition(positionF1, gameArena));
        assertSame(carrier, gameArenaService.getShipAtPosition(positionF10, gameArena));
        assertSame(submarine, gameArenaService.getShipAtPosition(positionG6, gameArena));
        assertSame(destroyer, gameArenaService.getShipAtPosition(positionA1, gameArena));
        assertSame(cruiser, gameArenaService.getShipAtPosition(positionA10, gameArena));
    }

    @Test
    public void shipsCanBePlacedOnTheEdgesOfTheBoardInValidPositions () {
        gameArena.clearArena();

        BoardPosition positionA8 = new BoardPosition('A', 8);
        BoardPosition positionG1 = new BoardPosition('G', 1);
        BoardPosition positionJ6 = new BoardPosition('J', 6);
        Submarine submarine = new Submarine(Orientation.VERTICAL, positionA8);
        Cruiser cruiser = new Cruiser(Orientation.HORIZONTAL, positionG1);
        Carrier carrier = new Carrier(Orientation.VERTICAL, positionJ6);
        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(cruiser, gameArena);
        gameArenaService.addShip(carrier, gameArena);
    }

    @Test(expected = IllegalBoardPlacementException.class)
    public void battleshipCanNotBePlacedOffBoardVerticallyFromPositionA10() {
        gameArena.clearArena();
        BoardPosition positionA10 = new BoardPosition('A',10);
        Battleship battleship =  new Battleship(Orientation.VERTICAL, positionA10);
        gameArenaService.addShip(battleship, gameArena);
    }

    @Test(expected = IllegalBoardPlacementException.class)
    public void destroyerCanNotBePlacedOffBoardHorizontallyFromPositionJ1() {
        gameArena.clearArena();
        BoardPosition positionA10 = new BoardPosition('J',1);
        Destroyer destroyer =  new Destroyer(Orientation.HORIZONTAL, positionA10);
        gameArenaService.addShip(destroyer, gameArena);
    }

    @Test(expected = IllegalBoardPlacementException.class)
    public void cruiserCanNotBePlacedOffBoardHorizontallyFromPositionI4() {
        gameArena.clearArena();
        BoardPosition positionI4 = new BoardPosition('I',4);
        Cruiser cruiser =  new Cruiser(Orientation.HORIZONTAL, positionI4);
        gameArenaService.addShip(cruiser, gameArena);
    }

    @Test(expected = IllegalBoardPlacementException.class)
    public void carrierCanNotBePlacedOffBoardHorrizontallyFromPositionG5() {
        gameArena.clearArena();
        BoardPosition positionG5 = new BoardPosition('G',5);
        Carrier carrier =  new Carrier(Orientation.HORIZONTAL, positionG5);
        gameArenaService.addShip(carrier, gameArena);
    }

    @Test(expected = IllegalBoardPlacementException.class)
    public void shipsCanNotBePlacedOnAnotherShipWhenPlacingVertically() {
        gameArena.clearArena();

        BoardPosition positionA1 = new BoardPosition('A', 1);
        BoardPosition positionA3 = new BoardPosition('A', 3);
        BoardPosition positionF4 = new BoardPosition('F', 4);
        BoardPosition positionF6 = new BoardPosition('F', 6);

        battleship = new Battleship(Orientation.VERTICAL, positionF4);
        carrier = new Carrier(Orientation.VERTICAL, positionA1);
        submarine = new Submarine(Orientation.VERTICAL, positionA3);
        destroyer = new Destroyer(Orientation.VERTICAL, positionF6);

        gameArenaService.addShip(carrier, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(destroyer, gameArena);

    }

    @Test(expected = IllegalBoardPlacementException.class)
    public void shipsCanNotBePlacedOnAnotherShipWhenPlacingHorizontally() {
        gameArena.clearArena();

        BoardPosition positionA1 = new BoardPosition('A', 1);
        BoardPosition positionC1 = new BoardPosition('C', 1);
        BoardPosition positionF4 = new BoardPosition('F', 4);
        BoardPosition positionD4 = new BoardPosition('D', 4);

        battleship = new Battleship(Orientation.HORIZONTAL, positionA1);
        carrier = new Carrier(Orientation.HORIZONTAL, positionF4);
        submarine = new Submarine(Orientation.HORIZONTAL, positionC1);
        cruiser = new Cruiser(Orientation.HORIZONTAL, positionD4);

        gameArenaService.addShip(carrier, gameArena);
        gameArenaService.addShip(battleship, gameArena);
        gameArenaService.addShip(cruiser, gameArena);
        gameArenaService.addShip(submarine, gameArena);
    }

    @Test(expected = IllegalBoardPlacementException.class)
    public void cruiserCanNotBePlacedOnASubmarineThatIsPositionedVerticallyFromA8() {
        gameArena.clearArena();

        BoardPosition positionA8 = new BoardPosition('A', 8);
        BoardPosition positionA10 = new BoardPosition('A', 10);
        submarine = new Submarine(Orientation.VERTICAL, positionA8);
        cruiser = new Cruiser(Orientation.HORIZONTAL, positionA10);
        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(cruiser, gameArena);
    }

    @Test(expected = IllegalBoardPlacementException.class)
    public void cruiserCanNotBePlacedOnACarrierThatIsPositionedHorizontallyFromF3() {
        BoardPosition positionF3 = new BoardPosition('F', 3);
        BoardPosition positionG2 = new BoardPosition('G', 2);
        carrier = new Carrier(Orientation.HORIZONTAL, positionF3);
        cruiser = new Cruiser(Orientation.VERTICAL, positionG2);
        gameArenaService.addShip(carrier, gameArena);
        gameArenaService.addShip(cruiser, gameArena);
    }


    @Test(expected = IllegalBoardPlacementException.class)
    public void canNotBePlacedOnEachOtherVerticallyOrHorizontally() {
        gameArena.clearArena();

        BoardPosition positionA1 = new BoardPosition('A', 1);
        BoardPosition positionA3 = new BoardPosition('A', 3);

        battleship = new Battleship(Orientation.VERTICAL, new BoardPosition('A', 7));
        submarine = new Submarine(Orientation.HORIZONTAL, new BoardPosition('A', 7));
        cruiser = new Cruiser(Orientation.VERTICAL, new BoardPosition('G', 3));
        carrier = new Carrier(Orientation.HORIZONTAL, new BoardPosition('E', 4));

        gameArenaService.addShip(carrier, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        gameArenaService.addShip(cruiser, gameArena);
        gameArenaService.addShip(submarine, gameArena);
    }

    @Test(expected = IllegalBoardPlacementException.class)
    public void onlyOneOfEachShipCanBePositioned() {
        gameArena.clearArena();

        BoardPosition positionA1 = new BoardPosition('A', 1);
        BoardPosition positionF10 = new BoardPosition('F', 10);

        carrier = new Carrier(Orientation.HORIZONTAL, positionF10);
        submarine = new Submarine(Orientation.VERTICAL, positionA1);

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(carrier, gameArena);

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(carrier, gameArena);
    }

    @Test
    public void positionsTheShipOccupiesThatAreHitAreSetToHit() {
        gameArena.clearArena();

        submarine = new Submarine(Orientation.VERTICAL, new BoardPosition('A', 1));
        battleship = new Battleship(Orientation.HORIZONTAL, new BoardPosition('B', 4));

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        BoardPosition position1 = new BoardPosition('A', 3);
        BoardPosition position2 = new BoardPosition('D', 4);
        BoardPosition position3 = new BoardPosition('C', 4);
        BoardPosition position4 = new BoardPosition('E', 4);

        gameArenaService.registerHit(position1, gameArena);
        gameArenaService.registerHit(position2, gameArena);
        gameArenaService.registerHit(position4, gameArena);

        assertTrue(gameArenaService.getOccupiedPositionsOfShip(position1, submarine).isHit());
        assertFalse(gameArenaService.getOccupiedPositionsOfShip(position3, battleship).isHit());
        assertTrue(gameArenaService.getOccupiedPositionsOfShip(position4, battleship).isHit());
    }

    @Test
    public void shipsSunkWhenAllHitPointsHaveBeenHit() {
        gameArena.clearArena();

        submarine = new Submarine(Orientation.VERTICAL, new BoardPosition('A', 1));
        battleship = new Battleship(Orientation.HORIZONTAL, new BoardPosition('B', 4));

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        gameArenaService.registerHit(new BoardPosition('A', 1), gameArena);
        gameArenaService.registerHit(new BoardPosition('A', 2), gameArena);

        assertFalse(submarine.isSunk());

        gameArenaService.registerHit(new BoardPosition('A', 3), gameArena);
        assertTrue(submarine.isSunk());
    }

    @Test
    public void listOfSunkShipsAreKept() {
        gameArena.clearArena();

        submarine = new Submarine(Orientation.VERTICAL, new BoardPosition('A', 1));
        battleship = new Battleship(Orientation.HORIZONTAL, new BoardPosition('B', 4));
        cruiser = new Cruiser(Orientation.VERTICAL, new BoardPosition('G', 3));
        carrier = new Carrier(Orientation.HORIZONTAL, new BoardPosition('A', 10));

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(battleship, gameArena);
        gameArenaService.addShip(cruiser, gameArena);
        gameArenaService.addShip(carrier, gameArena);

        gameArenaService.registerHit(new BoardPosition('B', 4), gameArena);
        gameArenaService.registerHit(new BoardPosition('C', 4), gameArena);
        gameArenaService.registerHit(new BoardPosition('D', 4), gameArena);
        gameArenaService.registerHit(new BoardPosition('E', 4), gameArena);

        gameArenaService.registerHit(new BoardPosition('A', 1), gameArena);
        gameArenaService.registerHit(new BoardPosition('A', 2), gameArena);
        gameArenaService.registerHit(new BoardPosition('A', 3), gameArena);
        gameArenaService.registerHit(new BoardPosition('A', 4), gameArena);

        assertTrue(gameArena.getSunkShips().contains(battleship));
        assertTrue(gameArena.getSunkShips().contains(submarine));
        assertFalse(gameArena.getSunkShips().contains(cruiser));
    }

    @Test
    public void givenWhenAllShipsArePlacedTheVariableAllShipsPlacedIsTrue() {
        gameArena.clearArena();
        Destroyer destroyer = new Destroyer(Orientation.HORIZONTAL, new BoardPosition('A', 1));
        Carrier carrier = new Carrier(Orientation.HORIZONTAL, new BoardPosition('A', 2));
        Cruiser cruiser = new Cruiser(Orientation.HORIZONTAL, new BoardPosition('A', 3));
        Submarine submarine = new Submarine(Orientation.HORIZONTAL, new BoardPosition('A', 4));
        Battleship battleship = new Battleship(Orientation.HORIZONTAL, new BoardPosition('A', 5));

        gameArenaService.addShip(destroyer, gameArena);
        gameArenaService.addShip(carrier, gameArena);
        gameArenaService.addShip(cruiser, gameArena);
        gameArenaService.addShip(submarine, gameArena);

        assertFalse(gameArena.isAllShipsPlaced());
        gameArenaService.addShip(battleship, gameArena);
        assertTrue(gameArena.isAllShipsPlaced());
    }

}
