package com.harragan.battleshipsboot.service;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.ShipType;
import com.harragan.battleshipsboot.service.exceptions.IllegalBoardPlacementException;
import org.junit.Before;
import org.junit.Test;

public class GameArenaServiceTest {
  private GameArena gameArena;
  private GameArenaService gameArenaService;
  private Ship ship;

  @Before
  public void initateTest() {
    gameArena = new GameArena(10);
    gameArenaService = new GameArenaService();
  }

  @Test
  public void whenAGameArenaIsInstantiatedThenItIsSetToTheCorrectSize() {
    final GameArena gameArena = gameArenaService.createGameArena(10);
    assertSame(10, gameArena.getGameArenaSize());
  }

  @Test
  public void whenShipsArePlacedInValidLocationsInTheArenaThenTheyAccessibleFromTheGameArena() {
    gameArena.clearArena();

    final BoardPosition positionA1 = new BoardPosition('A', 1);
    final BoardPosition positionF1 = new BoardPosition('F', 1);
    final BoardPosition positionG6 = new BoardPosition('G', 6);
    final BoardPosition positionF10 = new BoardPosition('F', 10);
    final BoardPosition positionA10 = new BoardPosition('A', 10);

    final Ship battleship = new Ship(Orientation.VERTICAL, positionF1, ShipType.BATTLESHIP);
    final Ship carrier = new Ship(Orientation.HORIZONTAL, positionF10, ShipType.CARRIER);
    final Ship submarine = new Ship(Orientation.VERTICAL, positionG6, ShipType.SUBMARINE);
    final Ship destroyer = new Ship(Orientation.VERTICAL, positionA1, ShipType.DESTROYER);
    final Ship cruiser = new Ship(Orientation.HORIZONTAL, positionA10, ShipType.CRUISER);

    gameArenaService.addShip(carrier, gameArena);
    gameArenaService.addShip(battleship, gameArena);
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
  public void
      whenShipsArePlacedOnTheEdgesOfTheBoardInValidPositionsThenAreAccessibleFromTheGameArena() {
    gameArena.clearArena();

    final BoardPosition positionA8 = new BoardPosition('A', 8);
    final BoardPosition positionG1 = new BoardPosition('G', 1);
    final BoardPosition positionJ6 = new BoardPosition('J', 6);
    final Ship submarine = new Ship(Orientation.VERTICAL, positionA8, ShipType.SUBMARINE);
    final Ship cruiser = new Ship(Orientation.HORIZONTAL, positionG1, ShipType.CRUISER);
    final Ship carrier = new Ship(Orientation.VERTICAL, positionJ6, ShipType.CARRIER);
    gameArenaService.addShip(submarine, gameArena);
    gameArenaService.addShip(cruiser, gameArena);
    gameArenaService.addShip(carrier, gameArena);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void
      whenABattleshipIsPlacedVerticallyAndPartlyOffTheGameArenaThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena.clearArena();
    final BoardPosition positionA10 = new BoardPosition('A', 10);
    final Ship battleship = new Ship(Orientation.VERTICAL, positionA10, ShipType.BATTLESHIP);
    gameArenaService.addShip(battleship, gameArena);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void
      whenADestroyerIsPlacedHorizontallyAndPartlyOffTheGameArenaThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena.clearArena();
    final BoardPosition positionA10 = new BoardPosition('J', 1);
    final Ship destroyer = new Ship(Orientation.HORIZONTAL, positionA10, ShipType.DESTROYER);
    gameArenaService.addShip(destroyer, gameArena);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void
      whenACruiserIsPlacedHorizontallyAndPartlyOffTheGameArenaThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena.clearArena();
    final BoardPosition positionI4 = new BoardPosition('I', 4);
    final Ship cruiser = new Ship(Orientation.HORIZONTAL, positionI4, ShipType.CRUISER);
    gameArenaService.addShip(cruiser, gameArena);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void
      whenACarrierIsPlacedHorizontallyAndPartlyOffTheGameArenaThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena.clearArena();
    final BoardPosition positionG5 = new BoardPosition('G', 5);
    final Ship carrier = new Ship(Orientation.HORIZONTAL, positionG5, ShipType.CARRIER);
    gameArenaService.addShip(carrier, gameArena);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void whenAShipIsPlacedOnAnotherVerticallyThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena.clearArena();

    final BoardPosition positionA1 = new BoardPosition('A', 1);
    final BoardPosition positionA3 = new BoardPosition('A', 3);
    final BoardPosition positionF4 = new BoardPosition('F', 4);
    final BoardPosition positionF6 = new BoardPosition('F', 6);

    final Ship battleship = new Ship(Orientation.VERTICAL, positionF4, ShipType.BATTLESHIP);
    final Ship carrier = new Ship(Orientation.VERTICAL, positionA1, ShipType.CARRIER);
    final Ship submarine = new Ship(Orientation.VERTICAL, positionA3, ShipType.SUBMARINE);
    final Ship destroyer = new Ship(Orientation.VERTICAL, positionF6, ShipType.DESTROYER);

    gameArenaService.addShip(carrier, gameArena);
    gameArenaService.addShip(battleship, gameArena);

    gameArenaService.addShip(submarine, gameArena);
    gameArenaService.addShip(destroyer, gameArena);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void whenAShipIsPlacedOnAnotherHorizontallyThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena.clearArena();

    final BoardPosition positionA1 = new BoardPosition('A', 1);
    final BoardPosition positionC1 = new BoardPosition('C', 1);
    final BoardPosition positionF4 = new BoardPosition('F', 4);
    final BoardPosition positionD4 = new BoardPosition('D', 4);

    final Ship destroyer = new Ship(Orientation.HORIZONTAL, positionA1, ShipType.DESTROYER);
    final Ship carrier = new Ship(Orientation.HORIZONTAL, positionF4, ShipType.CARRIER);
    final Ship submarine = new Ship(Orientation.HORIZONTAL, positionC1, ShipType.SUBMARINE);
    final Ship cruiser = new Ship(Orientation.HORIZONTAL, positionD4, ShipType.CRUISER);

    gameArenaService.addShip(carrier, gameArena);
    gameArenaService.addShip(destroyer, gameArena);
    gameArenaService.addShip(cruiser, gameArena);
    gameArenaService.addShip(submarine, gameArena);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void cruiserCanNotBePlacedOnASubmarineThatIsPositionedVerticallyFromA8() {
    gameArena.clearArena();

    final BoardPosition positionA8 = new BoardPosition('A', 8);
    final BoardPosition positionA10 = new BoardPosition('A', 10);
    final Ship submarine = new Ship(Orientation.VERTICAL, positionA8, ShipType.SUBMARINE);
    final Ship cruiser = new Ship(Orientation.HORIZONTAL, positionA10, ShipType.CRUISER);
    gameArenaService.addShip(submarine, gameArena);
    gameArenaService.addShip(cruiser, gameArena);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void cruiserCanNotBePlacedOnACarrierThatIsPositionedHorizontallyFromF3() {
    final BoardPosition positionF3 = new BoardPosition('F', 3);
    final BoardPosition positionG2 = new BoardPosition('G', 2);
    final Ship carrier = new Ship(Orientation.HORIZONTAL, positionF3, ShipType.CARRIER);
    final Ship cruiser = new Ship(Orientation.VERTICAL, positionG2, ShipType.CRUISER);
    gameArenaService.addShip(carrier, gameArena);
    gameArenaService.addShip(cruiser, gameArena);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void canNotBePlacedOnEachOtherVerticallyOrHorizontally() {
    gameArena.clearArena();

    final Ship battleship =
        new Ship(Orientation.VERTICAL, new BoardPosition('A', 7), ShipType.BATTLESHIP);
    final Ship submarine =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('A', 7), ShipType.SUBMARINE);
    final Ship cruiser = new Ship(Orientation.VERTICAL, new BoardPosition('G', 3), ShipType.CRUISER);
    final Ship carrier = new Ship(Orientation.HORIZONTAL, new BoardPosition('E', 4), ShipType.CARRIER);

    gameArenaService.addShip(carrier, gameArena);
    gameArenaService.addShip(battleship, gameArena);

    gameArenaService.addShip(cruiser, gameArena);
    gameArenaService.addShip(submarine, gameArena);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void onlyOneOfEachShipCanBePositioned() {
    gameArena.clearArena();

    final BoardPosition positionA1 = new BoardPosition('A', 1);
    final BoardPosition positionF10 = new BoardPosition('F', 10);

    final Ship carrier = new Ship(Orientation.HORIZONTAL, positionF10, ShipType.CARRIER);
    final Ship submarine = new Ship(Orientation.VERTICAL, positionA1, ShipType.SUBMARINE);

    gameArenaService.addShip(submarine, gameArena);
    gameArenaService.addShip(carrier, gameArena);

    gameArenaService.addShip(submarine, gameArena);
    gameArenaService.addShip(carrier, gameArena);
  }

  @Test
  public void positionsTheShipOccupiesThatAreHitAreSetToHit() {
    gameArena.clearArena();

    final Ship submarine = new Ship(Orientation.VERTICAL, new BoardPosition('A', 1), ShipType.SUBMARINE);
    final Ship battleship =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('B', 4), ShipType.BATTLESHIP);

    gameArenaService.addShip(submarine, gameArena);
    gameArenaService.addShip(battleship, gameArena);

    final BoardPosition position1 = new BoardPosition('A', 3);
    final BoardPosition position2 = new BoardPosition('D', 4);
    final BoardPosition position3 = new BoardPosition('C', 4);
    final BoardPosition position4 = new BoardPosition('E', 4);

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

    final Ship submarine = new Ship(Orientation.VERTICAL, new BoardPosition('A', 1), ShipType.SUBMARINE);
    final Ship battleship =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('B', 4), ShipType.BATTLESHIP);

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

    final Ship submarine = new Ship(Orientation.VERTICAL, new BoardPosition('A', 1), ShipType.SUBMARINE);
    final Ship battleship =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('B', 4), ShipType.BATTLESHIP);
    final Ship cruiser = new Ship(Orientation.VERTICAL, new BoardPosition('G', 3), ShipType.CRUISER);
    final Ship carrier = new Ship(Orientation.HORIZONTAL, new BoardPosition('A', 10), ShipType.CARRIER);

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
    final Ship destroyer =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('A', 1), ShipType.DESTROYER);
    final Ship carrier =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('A', 2), ShipType.CARRIER);
    final Ship cruiser =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('A', 3), ShipType.CRUISER);
    final Ship submarine =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('A', 4), ShipType.SUBMARINE);
    final Ship battleship =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('A', 5), ShipType.BATTLESHIP);

    gameArenaService.addShip(destroyer, gameArena);
    gameArenaService.addShip(carrier, gameArena);
    gameArenaService.addShip(cruiser, gameArena);
    gameArenaService.addShip(submarine, gameArena);

    assertFalse(gameArena.isAllShipsPlaced());
    gameArenaService.addShip(battleship, gameArena);
    assertTrue(gameArena.isAllShipsPlaced());
  }
}
