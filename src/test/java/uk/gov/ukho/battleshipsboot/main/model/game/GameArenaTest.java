package uk.gov.ukho.battleshipsboot.main.model.game;
import org.junit.Before;
import org.junit.Test;
import uk.gov.ukho.battleshipsboot.main.service.GameArenaService;
import uk.gov.ukho.battleshipsboot.model.game.GameArena;
import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.Position;
import uk.gov.ukho.battleshipsboot.model.ships.*;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uk.gov.ukho.battleshipsboot.model.ships.Column.*;

public class GameArenaTest {
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

        Position positionA1 = new Position(A, 1);
        Position positionF1 = new Position(Column.F, 1);
        Position positionG6 = new Position(Column.G, 6);
        Position positionF10 = new Position(Column.F, 10);
        Position positionA10 = new Position(A, 10);

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

        Position positionA1Horizontal = new Position(A, 0);
        Position positionF6Horizontal = new Position(Column.F, 30);
        Position positionD3Horizontal = new Position(D, 3000);
        Position positionE5Horizontal = new Position(Column.E, 29000);
        Position positionC2Horizontal = new Position(Column.C, 3000012);
    }

    @Test
    public void ships_can_not_be_placed_on_another_ship_when_placing_vertically() {
        gameArena.clearArena();

        Position positionA1 = new Position(A, 1);
        Position positionA3 = new Position(A, 3);
        Position positionF4 = new Position(Column.F, 4);
        Position positionF6 = new Position(Column.F, 6);

        battleship = new Battleship(Orientation.VERTICAL, positionF4);
        carrier = new Carrier(Orientation.VERTICAL, positionA1);
        submarine = new Submarine(Orientation.VERTICAL, positionA3);
        destroyer = new Destroyer(Orientation.VERTICAL, positionF6);

        gameArenaService.addShip(carrier, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        assertFalse(gameArenaService.addShip(submarine, gameArena));
        assertFalse(gameArenaService.addShip(destroyer, gameArena));
    }

    @Test
    public void ships_can_not_be_placed_on_another_ship_when_placing_horizontally() {
        gameArena.clearArena();

        Position positionA1 = new Position(A, 1);
        Position positionC1 = new Position(Column.C, 1);
        Position positionF4 = new Position(Column.F, 4);
        Position positionD4 = new Position(D, 4);

        battleship = new Battleship(Orientation.HORIZONTAL, positionA1);
        carrier = new Carrier(Orientation.HORIZONTAL, positionF4);
        submarine = new Submarine(Orientation.HORIZONTAL, positionC1);
        cruiser = new Cruiser(Orientation.HORIZONTAL, positionD4);

        gameArenaService.addShip(carrier, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        assertFalse(gameArenaService.addShip(cruiser, gameArena));
        assertFalse(gameArenaService.addShip(submarine, gameArena));
    }

    @Test
    public void can_not_be_placed_on_each_other_vertically_or_horizontally() {
        gameArena.clearArena();

        Position positionA1 = new Position(A, 1);
        Position positionA3 = new Position(A, 3);

        battleship = new Battleship(Orientation.VERTICAL, new Position(A, 7));
        submarine = new Submarine(Orientation.HORIZONTAL, new Position(A, 7));
        cruiser = new Cruiser(Orientation.VERTICAL, new Position(Column.G, 3));
        carrier = new Carrier(Orientation.HORIZONTAL, new Position(Column.E, 4));

        gameArenaService.addShip(carrier, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        assertFalse(gameArenaService.addShip(cruiser, gameArena));
        assertFalse(gameArenaService.addShip(submarine, gameArena));
    }

    @Test
    public void only_one_of_each_ship_can_be_positioned() {
        gameArena.clearArena();

        Position positionA1 = new Position(A, 1);
        Position positionF10 = new Position(Column.F, 10);

        carrier = new Carrier(Orientation.HORIZONTAL, positionF10);
        submarine = new Submarine(Orientation.VERTICAL, positionA1);

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(carrier, gameArena);

        assertFalse(gameArenaService.addShip(submarine, gameArena));
        assertFalse(gameArenaService.addShip(carrier, gameArena));
    }

    @Test
    public void positions_the_ship_occupies_that_are_hit_are_set_to_hit() {
        gameArena.clearArena();

        submarine = new Submarine(Orientation.VERTICAL, new Position(A, 1));
        battleship = new Battleship(Orientation.HORIZONTAL, new Position(B, 4));

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        Position position1 = new Position(A, 3);
        Position position2 = new Position(D, 4);
        Position position3 = new Position(C, 4);
        Position position4 = new Position(E, 4);

        gameArenaService.registerHit(position1, gameArena);
        gameArenaService.registerHit(position2, gameArena);
        gameArenaService.registerHit(position4, gameArena);

        assertTrue(submarine.getOccupiedPosition(position1).isHit());
        assertFalse(battleship.getOccupiedPosition(position3).isHit());
        assertTrue(battleship.getOccupiedPosition(position4).isHit());
    }

    @Test
    public void ships_sunk_when_all_hit_points_have_been_hit() {
        gameArena.clearArena();

        submarine = new Submarine(Orientation.VERTICAL, new Position(A, 1));
        battleship = new Battleship(Orientation.HORIZONTAL, new Position(B, 4));

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(battleship, gameArena);

        gameArenaService.registerHit(new Position(A, 1), gameArena);
        gameArenaService.registerHit(new Position(A, 2), gameArena);

        assertFalse(submarine.isSunk());

        gameArenaService.registerHit(new Position(A, 3), gameArena);
        assertTrue(submarine.isSunk());
    }

    @Test
    public void list_of_sunk_ships_are_kept() {
        gameArena.clearArena();

        submarine = new Submarine(Orientation.VERTICAL, new Position(A, 1));
        battleship = new Battleship(Orientation.HORIZONTAL, new Position(B, 4));
        cruiser = new Cruiser(Orientation.VERTICAL, new Position(Column.G, 3));
        carrier = new Carrier(Orientation.HORIZONTAL, new Position(A, 10));

        gameArenaService.addShip(submarine, gameArena);
        gameArenaService.addShip(battleship, gameArena);
        gameArenaService.addShip(cruiser, gameArena);
        gameArenaService.addShip(carrier, gameArena);

        gameArenaService.registerHit(new Position(B, 4), gameArena);
        gameArenaService.registerHit(new Position(C, 4), gameArena);
        gameArenaService.registerHit(new Position(D, 4), gameArena);
        gameArenaService.registerHit(new Position(E, 4), gameArena);

        gameArenaService.registerHit(new Position(A, 1), gameArena);
        gameArenaService.registerHit(new Position(A, 2), gameArena);
        gameArenaService.registerHit(new Position(A, 3), gameArena);
        gameArenaService.registerHit(new Position(A, 4), gameArena);

        assertTrue(gameArena.getSunkShips().contains(battleship));
        assertTrue(gameArena.getSunkShips().contains(submarine));
        assertFalse(gameArena.getSunkShips().contains(cruiser));
    }
}
