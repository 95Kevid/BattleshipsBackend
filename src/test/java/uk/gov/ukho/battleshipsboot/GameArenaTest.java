package uk.gov.ukho.battleshipsboot;
import org.junit.Before;
import org.junit.Test;
import uk.gov.ukho.battleshipsboot.main.Column;
import uk.gov.ukho.battleshipsboot.main.GameArena;
import uk.gov.ukho.battleshipsboot.main.Orientation;
import uk.gov.ukho.battleshipsboot.main.Position;
import uk.gov.ukho.battleshipsboot.ships.*;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uk.gov.ukho.battleshipsboot.main.Column.*;

public class GameArenaTest {
    private GameArena gameArena;
    private Battleship battleship;
    private Carrier carrier;
    private Submarine submarine;
    private Destroyer destroyer;
    private Cruiser cruiser;

    @Before
    public void initateTest () {
        gameArena = new GameArena();
    }

    @Test
    public void ships_can_be_placed_in_valid_locations_in_the_arena() {
        gameArena.clear();

        Position positionA1 = new Position(A, 1);
        Position positionF1 = new Position(Column.F, 1);
        Position positionG6 = new Position(Column.G, 6);
        Position positionF10 = new Position(Column.F, 10);
        Position positionA10 = new Position(A, 10);

        battleship = new Battleship(Orientation.VERTICAL, positionF1);
        carrier = new Carrier(Orientation.HORIZONTAL, positionF10);
        submarine = new Submarine(Orientation.VERTICAL, positionG6);
        destroyer = new Destroyer(Orientation.VERTICAL, positionA1);
        cruiser =  new Cruiser(Orientation.HORIZONTAL, positionA10);

        gameArena.addShip(destroyer);
        gameArena.addShip(battleship);
        gameArena.addShip(submarine);
        gameArena.addShip(carrier);
        gameArena.addShip(cruiser);

        assertSame(battleship, gameArena.getShipAtPosition(positionF1));
        assertSame(carrier, gameArena.getShipAtPosition(positionF10));
        assertSame(submarine, gameArena.getShipAtPosition(positionG6));
        assertSame(destroyer, gameArena.getShipAtPosition(positionA1));
        assertSame(cruiser, gameArena.getShipAtPosition(positionA10));
    }


    @Test(expected = IllegalArgumentException.class)
    public void invalid_positions_can_not_be_created() {
        gameArena.clear();

        Position positionA1Horizontal = new Position(A, 0);
        Position positionF6Horizontal = new Position(Column.F, 30);
        Position positionD3Horizontal = new Position(D, 3000);
        Position positionE5Horizontal = new Position(Column.E, 29000);
        Position positionC2Horizontal = new Position(Column.C, 3000012);
    }

    @Test
    public void ships_can_not_be_placed_on_another_ship_when_placing_vertically(){
        gameArena.clear();

        Position positionA1 = new Position(A, 1);
        Position positionA3 = new Position(A, 3);
        Position positionF4 = new Position(Column.F, 4);
        Position positionF6 = new Position(Column.F, 6);

        battleship = new Battleship(Orientation.VERTICAL, positionF4);
        carrier = new Carrier(Orientation.VERTICAL, positionA1);
        submarine = new Submarine(Orientation.VERTICAL, positionA3);
        destroyer = new Destroyer(Orientation.VERTICAL, positionF6);

        gameArena.addShip(carrier);
        gameArena.addShip(battleship);

        assertFalse(gameArena.addShip(submarine));
        assertFalse(gameArena.addShip(destroyer));
    }

    @Test
    public void ships_can_not_be_placed_on_another_ship_when_placing_horizontally() {
        gameArena.clear();

        Position positionA1 = new Position(A, 1);
        Position positionC1 = new Position(Column.C, 1);
        Position positionF4 = new Position(Column.F, 4);
        Position positionD4 = new Position(D, 4);

        battleship = new Battleship(Orientation.HORIZONTAL, positionA1);
        carrier = new Carrier(Orientation.HORIZONTAL, positionF4);
        submarine = new Submarine(Orientation.HORIZONTAL, positionC1);
        cruiser = new Cruiser(Orientation.HORIZONTAL, positionD4);

        gameArena.addShip(carrier);
        gameArena.addShip(battleship);

        assertFalse(gameArena.addShip(cruiser));
        assertFalse(gameArena.addShip(submarine));
    }

    @Test
    public void can_not_be_placed_on_each_other_vertically_or_horizontally() {
        gameArena.clear();

        Position positionA1 = new Position(A, 1);
        Position positionA3 = new Position(A, 3);

        battleship = new Battleship(Orientation.VERTICAL, new Position(A, 7));
        submarine = new Submarine(Orientation.HORIZONTAL, new Position(A, 7));
        cruiser = new Cruiser(Orientation.VERTICAL, new Position(Column.G, 3));
        carrier = new Carrier(Orientation.HORIZONTAL, new Position(Column.E, 4));

        gameArena.addShip(carrier);
        gameArena.addShip(battleship);

        assertFalse(gameArena.addShip(cruiser));
        assertFalse(gameArena.addShip(submarine));
    }

    @Test
    public void only_one_of_each_ship_can_be_positioned() {
        gameArena.clear();

        Position positionA1 = new Position(A, 1);
        Position positionF10 = new Position(Column.F, 10);

        carrier = new Carrier(Orientation.HORIZONTAL, positionF10);
        submarine = new Submarine(Orientation.VERTICAL, positionA1);

        gameArena.addShip(submarine);
        gameArena.addShip(carrier);

        assertFalse(gameArena.addShip(submarine));
        assertFalse(gameArena.addShip(carrier));
    }

    @Test
    public void positions_the_ship_occupies_that_are_hit_are_set_to_hit() {
        gameArena.clear();

        submarine = new Submarine(Orientation.VERTICAL,new Position(A,1));
        battleship = new Battleship(Orientation.HORIZONTAL, new Position(B, 4));

        gameArena.addShip(submarine);
        gameArena.addShip(battleship);

        Position position1 = new Position(A, 3);
        Position position2 = new Position(D, 4);
        Position position3 = new Position(C, 4);
        Position position4 = new Position(E, 4);

        gameArena.shoot(position1);
        gameArena.shoot(position2);
        gameArena.shoot(position4);

        assertTrue(submarine.getOccupiedPosition(position1).isHit());
        assertFalse(battleship.getOccupiedPosition(position3).isHit());
        assertTrue(battleship.getOccupiedPosition(position4).isHit());
    }

}
