package com.harragan.battleshipsboot.model.ships;

import com.harragan.battleshipsboot.model.game.BoardPosition;
import com.harragan.battleshipsboot.model.game.Orientation;

public class Submarine extends Ship {
    public Submarine(Orientation orient, BoardPosition boardPosition) {
        super(3, orient, boardPosition);
    }
}
