package uk.gov.ukho.battleshipsboot.service;
import org.junit.Before;
import org.junit.Test;
import uk.gov.ukho.battleshipsboot.model.game.GameArena;
import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.BoardPosition;
import uk.gov.ukho.battleshipsboot.model.ships.*;
import uk.gov.ukho.battleshipsboot.service.exceptions.IllegalBoardPlacementException;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uk.gov.ukho.battleshipsboot.model.ships.Column.*;

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
        gameArena = new GameArena();
        gameArenaService = new GameArenaService();
    }

    @Test
    public void ships_can_be_placed_in_valid_locations_in_the_arena() {
        gameArena.clearArena();

        BoardPosition positionA1 = new BoardPosition(A, 1);
        BoardPosition positionF1 = new BoardPosition(Column.F, 1);
        BoardPosition positionG6 = new BoardPosition(Column.G, 6);
        BoardPosition positionF10 = new BoardPosition(Column.F, 10);
        BoardPosition positionA10 = new BoardPosition(A, 10);

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


    @Test(expected = IllegalArgumentException.class)
    public void invalid_positions_can_not_be_created() {
        gameArena.clearArena();

        BoardPosition positionA1Horizontal = new BoardPosition(A, 0);
        BoardPosition positionF6Horizontal = new BoardPosition(Column.F, 30);
        BoardPosition positionD3Horizontal = new BoardPosition(D, 3000);
        BoardPosition positionE5Horizontal = new BoardPosition(Column.E, 29000);
        BoardPosition positionC2Horizontal = new BoardPosition(Column.C, 3000012);
    }

    @Test(expected = IllegalBoardPlacementException.class)
    public void ships_can_not_be_placed_on_another_ship_when_placing_vertically() {
        gameArena.clearArena();

        BoardPosition positionA1 = new BoardPosition(A, 1);
        BoardPosition positionA3 = new BoardPosition(A, 3);
        BoardPosition positionF4 = new BoardPosition(Column.F, 4);
        BoardPosition positionF6 = new BoardPosition(Column.F, 6);

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
    public void ships_can_not_be_placed_on_another_ship_when_placing_horizontally() {
        gameArena.clearArena();

        BoardPosition positionA1 = new BoardPosition(A, 1);
        BoardPosition positionC1 = new BoardPosition(Column.C, 1);
        BoardPosition positionF4 = new BoardPosition(Column.F, 4);
        BoardPosition positionD4 = new BoardPosition(D, 4);

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
    public void can_not_be_placed_on_each_other_vertically_or_horizontally() {
        gameArena.clearArena();

        BoardPosition positionA1 = new BoardPosition(A, 1);
        BoardPosition positionA3 = new BoardPosition(A, 3);

        battleship = new Battleship(Orientation.VERTICAL, new BoardPosition(A, 7));
        submarine = new Submarine(Orientation.HORIZONTAL, new BoardPosition(A, 7));
        cruiser = new Cruiser(Orientation.VERTICAL, new BoardPosition(Column.G, 3));
        carrier = new Carrier(Orientation.HORIZONTAL, new BoardPosition(Column.E, 4));

        gameArenaService.addShip(carrier, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        gameArenaService.addShip(cruiser, gameArena);
        gameArenaService.addShip(submarine, gameArena);
    }

    @Test(expected = IllegalBoardPlacementException.class)
    public void only_one_of_each_ship_can_be_positioned() {
        gameArena.clearArena();

        BoardPosition positionA1 = new BoardPosition(A, 1);
        BoardPosition positionF10 = new BoardPosition(Column.F, 10);

        carrier = new Carrier(Orientation.HORIZONTAL, positionF10);
        submarine = new Submarine(Orientation.VERTICAL, positionA1);

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(carrier, gameArena);

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(carrier, gameArena);
    }

    @Test
    public void positions_the_ship_occupies_that_are_hit_are_set_to_hit() {
        gameArena.clearArena();

        submarine = new Submarine(Orientation.VERTICAL, new BoardPosition(A, 1));
        battleship = new Battleship(Orientation.HORIZONTAL, new BoardPosition(B, 4));

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        BoardPosition position1 = new BoardPosition(A, 3);
        BoardPosition position2 = new BoardPosition(D, 4);
        BoardPosition position3 = new BoardPosition(C, 4);
        BoardPosition position4 = new BoardPosition(E, 4);

        gameArenaService.registerHit(position1, gameArena);
        gameArenaService.registerHit(position2, gameArena);
        gameArenaService.registerHit(position4, gameArena);

        assertTrue(gameArenaService.getOccupiedPositionsOfShip(position1, submarine).isHit());
        assertFalse(gameArenaService.getOccupiedPositionsOfShip(position3, battleship).isHit());
        assertTrue(gameArenaService.getOccupiedPositionsOfShip(position4, battleship).isHit());
    }

    @Test
    public void ships_sunk_when_all_hit_points_have_been_hit() {
        gameArena.clearArena();

        submarine = new Submarine(Orientation.VERTICAL, new BoardPosition(A, 1));
        battleship = new Battleship(Orientation.HORIZONTAL, new BoardPosition(B, 4));

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        gameArenaService.registerHit(new BoardPosition(A, 1), gameArena);
        gameArenaService.registerHit(new BoardPosition(A, 2), gameArena);

        assertFalse(submarine.isSunk());

        gameArenaService.registerHit(new BoardPosition(A, 3), gameArena);
        assertTrue(submarine.isSunk());
    }

    @Test
    public void list_of_sunk_ships_are_kept() {
        gameArena.clearArena();

        submarine = new Submarine(Orientation.VERTICAL, new BoardPosition(A, 1));
        battleship = new Battleship(Orientation.HORIZONTAL, new BoardPosition(B, 4));
        cruiser = new Cruiser(Orientation.VERTICAL, new BoardPosition(Column.G, 3));
        carrier = new Carrier(Orientation.HORIZONTAL, new BoardPosition(A, 10));

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(battleship, gameArena);
        gameArenaService.addShip(cruiser, gameArena);
        gameArenaService.addShip(carrier, gameArena);

        gameArenaService.registerHit(new BoardPosition(B, 4), gameArena);
        gameArenaService.registerHit(new BoardPosition(C, 4), gameArena);
        gameArenaService.registerHit(new BoardPosition(D, 4), gameArena);
        gameArenaService.registerHit(new BoardPosition(E, 4), gameArena);

        gameArenaService.registerHit(new BoardPosition(A, 1), gameArena);
        gameArenaService.registerHit(new BoardPosition(A, 2), gameArena);
        gameArenaService.registerHit(new BoardPosition(A, 3), gameArena);
        gameArenaService.registerHit(new BoardPosition(A, 4), gameArena);

        assertTrue(gameArena.getSunkShips().contains(battleship));
        assertTrue(gameArena.getSunkShips().contains(submarine));
        assertFalse(gameArena.getSunkShips().contains(cruiser));
    }
}
