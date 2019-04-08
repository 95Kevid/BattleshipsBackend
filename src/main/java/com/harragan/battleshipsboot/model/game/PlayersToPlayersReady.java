package com.harragan.battleshipsboot.model.game;

import java.util.Objects;

public class PlayersToPlayersReady {
  private final int playersInGame;
  private final int playersReady;

  public PlayersToPlayersReady(final int playersInGame, final int playersReady) {
    this.playersInGame = playersInGame;
    this.playersReady = playersReady;
  }

  public int getPlayersInGame() {
    return playersInGame;
  }

  public int getPlayersReady() {
    return playersReady;
  }

  @Override
  public boolean equals(final Object playersToPlayersReady) {
    if (this == playersToPlayersReady) {
      return true;
    }
    if (playersToPlayersReady == null || getClass() != playersToPlayersReady.getClass()) {
      return false;
    }
    final PlayersToPlayersReady that = (PlayersToPlayersReady) playersToPlayersReady;
    return playersInGame == that.playersInGame && playersReady == that.playersReady;
  }

  @Override
  public int hashCode() {
    return Objects.hash(playersInGame, playersReady);
  }
}
