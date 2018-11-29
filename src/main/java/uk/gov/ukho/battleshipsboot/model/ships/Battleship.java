package uk.gov.ukho.battleshipsboot.model.ships;

import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.Position;

public class Battleship extends Ship {

    public Battleship(Orientation orient, Position position) {
        super(4, orient, position);

    }
}
