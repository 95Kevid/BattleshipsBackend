package com.harragan.battleshipsboot.controllers;

import com.harragan.battleshipsboot.model.game.BoardPosition;
import com.harragan.battleshipsboot.model.game.Orientation;

public class ShipPlaceRequestWrapper {
  private int playerId;
  private int gameId;
  private BoardPosition boardPosition;
  private Orientation orientation;

  public ShipPlaceRequestWrapper() {}

  public ShipPlaceRequestWrapper(
      int playerId, int gameId, BoardPosition boardPosition, Orientation orientation) {
    this.playerId = playerId;
    this.gameId = gameId;
    this.boardPosition = boardPosition;
    this.orientation = orientation;
  }

  public int getPlayerId() {
    return playerId;
  }

  public int getGameId() {
    return gameId;
  }

  public BoardPosition getBoardPosition() {
    return boardPosition;
  }

  public Orientation getOrientation() {
    return orientation;
  }
}
