package com.harragan.battleshipsboot.model.game;

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameStatusResponse {

  private final int playersTurnId;
  private final Set<BoardPosition> hitPositions;
  private final Map<Player, Set<Ship>> playersToSunkShips;

  public GameStatusResponse(int playersId, Set<BoardPosition> hitPositions,
                            Map<Player, Set<Ship>> playersToSunkShips) {
    this.playersTurnId = playersId;
    this.hitPositions = hitPositions;
    this.playersToSunkShips = playersToSunkShips;
  }

  public int getPlayersTurnId() {
    return playersTurnId;
  }

  public Set<BoardPosition> getHitBoardPositions() {
    return new HashSet<>();
  }

  public Map<Player, Set<Ship>> getPlayersToSunkShips() {
    return playersToSunkShips;
  }
}
