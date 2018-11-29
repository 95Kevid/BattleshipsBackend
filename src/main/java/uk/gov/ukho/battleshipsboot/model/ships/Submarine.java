package uk.gov.ukho.battleshipsboot.model.ships;

import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.Position;

public class Submarine extends Ship {
    public Submarine(Orientation orient, Position position) {
        super(3, orient, position);
    }
}
