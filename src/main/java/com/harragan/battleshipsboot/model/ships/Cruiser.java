package com.harragan.battleshipsboot.model.ships;

import com.harragan.battleshipsboot.model.game.Orientation;
import com.harragan.battleshipsboot.model.game.BoardPosition;

public class Cruiser extends Ship {
    public Cruiser(Orientation orient, BoardPosition boardPosition) {
        super(3, orient, boardPosition);
    }
}
