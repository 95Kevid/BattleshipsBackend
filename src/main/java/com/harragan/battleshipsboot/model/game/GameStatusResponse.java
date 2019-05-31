package com.harragan.battleshipsboot.model.game;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.harragan.battleshipsboot.controllers.PlayerSerializer;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;

import java.util.Map;
import java.util.Set;

public class GameStatusResponse {

  private final int playersTurnId;
  private final Map<Player, Set<BoardPosition>> playersToShotPositions;
  private final Map<Player, Set<Ship>> playersToSunkShips;

  public GameStatusResponse(final int playersId, final Map<Player, Set<BoardPosition>> playersToShotPositions,
                            final Map<Player, Set<Ship>> playersToSunkShips) {
    this.playersTurnId = playersId;
    this.playersToShotPositions = playersToShotPositions;
    this.playersToSunkShips = playersToSunkShips;
  }

  public int getPlayersTurnId(){
    return playersTurnId;
  }

  @JsonSerialize(keyUsing = PlayerSerializer.class)
  public Map<Player, Set<BoardPosition>> getPlayersToShotPositions() {
    return playersToShotPositions;
  }

  public Map<Player, Set<Ship>> getPlayersToSunkShips() {
    return playersToSunkShips;
  }
}
