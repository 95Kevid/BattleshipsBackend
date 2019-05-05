package com.harragan.battleshipsboot.service;

import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.game.Orientation;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.ShipType;
import com.harragan.battleshipsboot.service.exceptions.IllegalBoardPlacementException;
import com.harragan.battleshipsboot.service.exceptions.IllegalShotException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertSame;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameArenaServiceTest {
  private GameArena gameArena1;
  private GameArenaService gameArenaService;

  @Before
  public void initateTest() {
    gameArena1 = new GameArena(10);
    gameArenaService = new GameArenaService();
  }

  @Test
  public void whenAGameArenaIsInstantiatedThenItIsSetToTheCorrectSize() {
    final GameArena gameArena = gameArenaService.createGameArena(10);
    assertSame(10, gameArena.getGameArenaSize());
  }

  @Test
  public void whenShipsArePlacedInValidLocationsInTheArenaThenTheyAccessibleFromTheGameArena() {
    gameArena1.clearArena();

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

    gameArenaService.addShip(carrier, gameArena1);
    gameArenaService.addShip(battleship, gameArena1);
    gameArenaService.addShip(submarine, gameArena1);
    gameArenaService.addShip(destroyer, gameArena1);
    gameArenaService.addShip(cruiser, gameArena1);

    assertSame(battleship, gameArenaService.getShipAtPosition(positionF1, gameArena1));
    assertSame(carrier, gameArenaService.getShipAtPosition(positionF10, gameArena1));
    assertSame(submarine, gameArenaService.getShipAtPosition(positionG6, gameArena1));
    assertSame(destroyer, gameArenaService.getShipAtPosition(positionA1, gameArena1));
    assertSame(cruiser, gameArenaService.getShipAtPosition(positionA10, gameArena1));
  }

  @Test
  public void
  whenShipsArePlacedOnTheEdgesOfTheBoardInValidPositionsThenAreAccessibleFromTheGameArena() {
    gameArena1.clearArena();

    final BoardPosition positionA8 = new BoardPosition('A', 8);
    final BoardPosition positionG1 = new BoardPosition('G', 1);
    final BoardPosition positionJ6 = new BoardPosition('J', 6);
    final Ship submarine = new Ship(Orientation.VERTICAL, positionA8, ShipType.SUBMARINE);
    final Ship cruiser = new Ship(Orientation.HORIZONTAL, positionG1, ShipType.CRUISER);
    final Ship carrier = new Ship(Orientation.VERTICAL, positionJ6, ShipType.CARRIER);
    gameArenaService.addShip(submarine, gameArena1);
    gameArenaService.addShip(cruiser, gameArena1);
    gameArenaService.addShip(carrier, gameArena1);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void
  whenABattleshipIsPlacedVerticallyAndPartlyOffTheGameArenaThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena1.clearArena();
    final BoardPosition positionA10 = new BoardPosition('A', 10);
    final Ship battleship = new Ship(Orientation.VERTICAL, positionA10, ShipType.BATTLESHIP);
    gameArenaService.addShip(battleship, gameArena1);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void
  whenADestroyerIsPlacedHorizontallyAndPartlyOffTheGameArenaThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena1.clearArena();
    final BoardPosition positionA10 = new BoardPosition('J', 1);
    final Ship destroyer = new Ship(Orientation.HORIZONTAL, positionA10, ShipType.DESTROYER);
    gameArenaService.addShip(destroyer, gameArena1);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void
  whenACruiserIsPlacedHorizontallyAndPartlyOffTheGameArenaThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena1.clearArena();
    final BoardPosition positionI4 = new BoardPosition('I', 4);
    final Ship cruiser = new Ship(Orientation.HORIZONTAL, positionI4, ShipType.CRUISER);
    gameArenaService.addShip(cruiser, gameArena1);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void
  whenACarrierIsPlacedHorizontallyAndPartlyOffTheGameArenaThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena1.clearArena();
    final BoardPosition positionG5 = new BoardPosition('G', 5);
    final Ship carrier = new Ship(Orientation.HORIZONTAL, positionG5, ShipType.CARRIER);
    gameArenaService.addShip(carrier, gameArena1);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void whenAShipIsPlacedOnAnotherVerticallyThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena1.clearArena();

    final BoardPosition positionA1 = new BoardPosition('A', 1);
    final BoardPosition positionA3 = new BoardPosition('A', 3);
    final BoardPosition positionF4 = new BoardPosition('F', 4);
    final BoardPosition positionF6 = new BoardPosition('F', 6);

    final Ship battleship = new Ship(Orientation.VERTICAL, positionF4, ShipType.BATTLESHIP);
    final Ship carrier = new Ship(Orientation.VERTICAL, positionA1, ShipType.CARRIER);
    final Ship submarine = new Ship(Orientation.VERTICAL, positionA3, ShipType.SUBMARINE);
    final Ship destroyer = new Ship(Orientation.VERTICAL, positionF6, ShipType.DESTROYER);

    gameArenaService.addShip(carrier, gameArena1);
    gameArenaService.addShip(battleship, gameArena1);

    gameArenaService.addShip(submarine, gameArena1);
    gameArenaService.addShip(destroyer, gameArena1);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void whenAShipIsPlacedOnAnotherHorizontallyThenAnIllegalBoardPlacementExceptionIsThrown() {
    gameArena1.clearArena();

    final BoardPosition positionA1 = new BoardPosition('A', 1);
    final BoardPosition positionC1 = new BoardPosition('C', 1);
    final BoardPosition positionF4 = new BoardPosition('F', 4);
    final BoardPosition positionD4 = new BoardPosition('D', 4);

    final Ship destroyer = new Ship(Orientation.HORIZONTAL, positionA1, ShipType.DESTROYER);
    final Ship carrier = new Ship(Orientation.HORIZONTAL, positionF4, ShipType.CARRIER);
    final Ship submarine = new Ship(Orientation.HORIZONTAL, positionC1, ShipType.SUBMARINE);
    final Ship cruiser = new Ship(Orientation.HORIZONTAL, positionD4, ShipType.CRUISER);

    gameArenaService.addShip(carrier, gameArena1);
    gameArenaService.addShip(destroyer, gameArena1);
    gameArenaService.addShip(cruiser, gameArena1);
    gameArenaService.addShip(submarine, gameArena1);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void cruiserCanNotBePlacedOnASubmarineThatIsPositionedVerticallyFromA8() {
    gameArena1.clearArena();

    final BoardPosition positionA8 = new BoardPosition('A', 8);
    final BoardPosition positionA10 = new BoardPosition('A', 10);
    final Ship submarine = new Ship(Orientation.VERTICAL, positionA8, ShipType.SUBMARINE);
    final Ship cruiser = new Ship(Orientation.HORIZONTAL, positionA10, ShipType.CRUISER);
    gameArenaService.addShip(submarine, gameArena1);
    gameArenaService.addShip(cruiser, gameArena1);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void cruiserCanNotBePlacedOnACarrierThatIsPositionedHorizontallyFromF3() {
    final BoardPosition positionF3 = new BoardPosition('F', 3);
    final BoardPosition positionG2 = new BoardPosition('G', 2);
    final Ship carrier = new Ship(Orientation.HORIZONTAL, positionF3, ShipType.CARRIER);
    final Ship cruiser = new Ship(Orientation.VERTICAL, positionG2, ShipType.CRUISER);
    gameArenaService.addShip(carrier, gameArena1);
    gameArenaService.addShip(cruiser, gameArena1);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void canNotBePlacedOnEachOtherVerticallyOrHorizontally() {
    gameArena1.clearArena();

    final Ship battleship =
        new Ship(Orientation.VERTICAL, new BoardPosition('A', 7), ShipType.BATTLESHIP);
    final Ship submarine =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('A', 7), ShipType.SUBMARINE);
    final Ship cruiser = new Ship(Orientation.VERTICAL, new BoardPosition('G', 3), ShipType.CRUISER);
    final Ship carrier = new Ship(Orientation.HORIZONTAL, new BoardPosition('E', 4), ShipType.CARRIER);

    gameArenaService.addShip(carrier, gameArena1);
    gameArenaService.addShip(battleship, gameArena1);

    gameArenaService.addShip(cruiser, gameArena1);
    gameArenaService.addShip(submarine, gameArena1);
  }

  @Test(expected = IllegalBoardPlacementException.class)
  public void onlyOneOfEachShipCanBePositioned() {
    gameArena1.clearArena();

    final BoardPosition positionA1 = new BoardPosition('A', 1);
    final BoardPosition positionF10 = new BoardPosition('F', 10);

    final Ship carrier = new Ship(Orientation.HORIZONTAL, positionF10, ShipType.CARRIER);
    final Ship submarine = new Ship(Orientation.VERTICAL, positionA1, ShipType.SUBMARINE);

    gameArenaService.addShip(submarine, gameArena1);
    gameArenaService.addShip(carrier, gameArena1);

    gameArenaService.addShip(submarine, gameArena1);
    gameArenaService.addShip(carrier, gameArena1);
  }

  @Test
  public void positionsTheShipOccupiesThatAreHitAreSetToHit() {
    gameArena1.clearArena();

    final Ship submarine = new Ship(Orientation.VERTICAL, new BoardPosition('A', 1), ShipType.SUBMARINE);
    final Ship battleship =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('B', 4), ShipType.BATTLESHIP);

    gameArenaService.addShip(submarine, gameArena1);
    gameArenaService.addShip(battleship, gameArena1);

    final BoardPosition position1 = new BoardPosition('A', 3);
    final BoardPosition position2 = new BoardPosition('D', 4);
    final BoardPosition position3 = new BoardPosition('C', 4);
    final BoardPosition position4 = new BoardPosition('E', 4);

    gameArenaService.registerHit(position1, gameArena1);
    gameArenaService.registerHit(position2, gameArena1);
    gameArenaService.registerHit(position4, gameArena1);

    assertTrue(gameArenaService.getOccupiedPositionsOfShip(position1, submarine).isHit());
    assertFalse(gameArenaService.getOccupiedPositionsOfShip(position3, battleship).isHit());
    assertTrue(gameArenaService.getOccupiedPositionsOfShip(position4, battleship).isHit());
  }

  @Test
  public void shipsSunkWhenAllHitPointsHaveBeenHit() {
    gameArena1.clearArena();

    final Ship submarine = new Ship(Orientation.VERTICAL, new BoardPosition('A', 1), ShipType.SUBMARINE);
    final Ship battleship =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('B', 4), ShipType.BATTLESHIP);

    gameArenaService.addShip(submarine, gameArena1);
    gameArenaService.addShip(battleship, gameArena1);

    gameArenaService.registerHit(new BoardPosition('A', 1), gameArena1);
    gameArenaService.registerHit(new BoardPosition('A', 2), gameArena1);

    assertFalse(submarine.isSunk());

    gameArenaService.registerHit(new BoardPosition('A', 3), gameArena1);
    assertTrue(submarine.isSunk());
  }

  @Test
  public void listOfSunkShipsAreKept() {
    gameArena1.clearArena();

    final Ship submarine = new Ship(Orientation.VERTICAL, new BoardPosition('A', 1), ShipType.SUBMARINE);
    final Ship battleship =
        new Ship(Orientation.HORIZONTAL, new BoardPosition('B', 4), ShipType.BATTLESHIP);
    final Ship cruiser = new Ship(Orientation.VERTICAL, new BoardPosition('G', 3), ShipType.CRUISER);
    final Ship carrier = new Ship(Orientation.HORIZONTAL, new BoardPosition('A', 10), ShipType.CARRIER);

    gameArenaService.addShip(submarine, gameArena1);
    gameArenaService.addShip(battleship, gameArena1);
    gameArenaService.addShip(cruiser, gameArena1);
    gameArenaService.addShip(carrier, gameArena1);

    gameArenaService.registerHit(new BoardPosition('B', 4), gameArena1);
    gameArenaService.registerHit(new BoardPosition('C', 4), gameArena1);
    gameArenaService.registerHit(new BoardPosition('D', 4), gameArena1);
    gameArenaService.registerHit(new BoardPosition('E', 4), gameArena1);

    gameArenaService.registerHit(new BoardPosition('A', 1), gameArena1);
    gameArenaService.registerHit(new BoardPosition('A', 2), gameArena1);
    gameArenaService.registerHit(new BoardPosition('A', 3), gameArena1);
    gameArenaService.registerHit(new BoardPosition('A', 4), gameArena1);

    assertTrue(gameArena1.getSunkShips().contains(battleship));
    assertTrue(gameArena1.getSunkShips().contains(submarine));
    assertFalse(gameArena1.getSunkShips().contains(cruiser));
  }

  @Test
  public void givenWhenAllShipsArePlacedTheVariableAllShipsPlacedIsTrue() {
    gameArena1.clearArena();
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

    gameArenaService.addShip(destroyer, gameArena1);
    gameArenaService.addShip(carrier, gameArena1);
    gameArenaService.addShip(cruiser, gameArena1);
    gameArenaService.addShip(submarine, gameArena1);

    assertFalse(gameArena1.isAllShipsPlaced());
    gameArenaService.addShip(battleship, gameArena1);
    assertTrue(gameArena1.isAllShipsPlaced());
  }

  @Test
  public void whenACollectionOfGameArenasAreSuppliedThenACollectionOfDistinctBoardPositionsThatAreHitAreReturned() {
    gameArena1.clearArena();
    GameArena gameArena2 = new GameArena(26);
    GameArena gameArena3 = new GameArena(18);

    BoardPosition positionA3 = BoardPositionFactory.createBoardPosition('A', 3);
    BoardPosition positionD8 = BoardPositionFactory.createBoardPosition('D', 8);
    BoardPosition positionB8 = BoardPositionFactory.createBoardPosition('B', 8);
    positionA3.setHit(true);
    positionD8.setHit(true);
    positionB8.setHit(true);

    gameArena1.addShotPosition(positionB8);
    gameArena2.addShotPosition(positionA3);
    gameArena3.addShotPosition(positionD8);

    Set<GameArena> gameArenas = new HashSet<>();
    gameArenas.add(gameArena2);
    gameArenas.add(gameArena3);
    Set<BoardPosition> hitBoardPositions = gameArenaService.getShotPositions(gameArenas);

    assertThat(hitBoardPositions.contains(positionA3) && hitBoardPositions.contains(positionD8)
        && !hitBoardPositions.contains(positionB8)).isTrue().as("The hit positions should include "
        + "A3 and D8, but not B8");
  }

  @Test(expected = IllegalShotException.class)
  public void whenSuppliedWithAPositionThatIsHorizontallyOutOfTheGameArenaThenAnIllegalShotExceptionIsThown() {
    gameArena1.clearArena();
    BoardPosition positionK1 = BoardPositionFactory.createBoardPosition('K', 1);
    gameArenaService.registerHit(positionK1, gameArena1);
  }

  @Test(expected = IllegalShotException.class)
  public void whenSuppliedWithAPositionThatIsVerticallyOutOfTheGameArenaThenAnIllegalShotExceptionIsThown() {
    gameArena1.clearArena();
    BoardPosition positionA11 = BoardPositionFactory.createBoardPosition('A', 11);
    gameArenaService.registerHit(positionA11, gameArena1);
  }

  @Test
  public void whenSuppliedWithAPositionThenItIsAddedToHitPositions() {
    gameArena1.clearArena();
    BoardPosition positionA10 = BoardPositionFactory.createBoardPosition('A', 10);
    BoardPosition positionB5 = BoardPositionFactory.createBoardPosition('B', 5);
    gameArenaService.registerHit(positionA10, gameArena1);
    gameArenaService.registerHit(positionB5, gameArena1);
    Set<BoardPosition> hitPositions = gameArena1.getShotPositions();
    System.out.println(hitPositions);
  }
}