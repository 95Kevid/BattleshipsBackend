package uk.gov.ukho.battleshipsboot.model.ships;

import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.Position;

public class Destroyer extends Ship {
    public Destroyer(Orientation orient, Position position) {
        super(2, orient, position);
    }
}
