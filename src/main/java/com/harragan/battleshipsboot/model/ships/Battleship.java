package com.harragan.battleshipsboot.model.ships;

import com.harragan.battleshipsboot.model.game.BoardPosition;
import com.harragan.battleshipsboot.model.game.Orientation;

import javax.persistence.Entity;

@Entity
public class Battleship extends Ship {

  public Battleship(Orientation orient, BoardPosition boardPosition) {
    super(4, orient, boardPosition);
  }

  public Battleship() {}
}
