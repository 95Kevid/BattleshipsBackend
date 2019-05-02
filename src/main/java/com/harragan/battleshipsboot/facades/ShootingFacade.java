package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class ShootingFacade {

  private GameService gameService;
  private GameArenaService gameArenaService;

  public ShootingFacade(final GameService gameService, final GameArenaService gameArenaService) {
    this.gameArenaService = gameArenaService;
    this.gameService = gameService;
  }

  public void shootPosition(final int playerId, final int gameId, final @NotNull BoardPosition position) {
    final Game game = gameService.getGame(gameId);
    final LinkedList<Player> players = game.getPlayers();
    players.stream()
        .filter(player -> player.getId() != playerId)
        .map(player -> player.getGameArena())
        .forEach(gameArena -> gameArenaService.registerHit(position, gameArena));
  }
}
