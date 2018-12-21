package com.harragan.battleshipsboot.model.ships;

import com.harragan.battleshipsboot.model.game.Orientation;
import com.harragan.battleshipsboot.model.game.BoardPosition;

import javax.persistence.Entity;

@Entity
public class Carrier extends Ship {
    public Carrier(Orientation orient, BoardPosition boardPosition){
        super(5, orient, boardPosition);
    }
    public Carrier() {}
}
