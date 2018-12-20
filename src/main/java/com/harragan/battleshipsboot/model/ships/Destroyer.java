package com.harragan.battleshipsboot.model.ships;

import com.harragan.battleshipsboot.model.game.BoardPosition;
import com.harragan.battleshipsboot.model.game.Orientation;

public class Destroyer extends Ship {
    public Destroyer(Orientation orient, BoardPosition boardPosition) {
        super(2, orient, boardPosition);
    }
}
