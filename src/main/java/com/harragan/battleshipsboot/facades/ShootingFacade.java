package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.controllers.ShootRequest;
import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class ShootingFacade {

  private GameService gameService;
  private GameArenaService gameArenaService;

  public ShootingFacade(final GameService gameService, final GameArenaService gameArenaService) {
    this.gameArenaService = gameArenaService;
    this.gameService = gameService;
  }

  public void shootPosition(final ShootRequest shootRequest) {
    final Game game = gameService.getGame(shootRequest.getGameId());
    final LinkedList<Player> players = game.getPlayers();
    players.stream()
        .filter(player -> player.getId() != shootRequest.getPlayerId())
        .map(player -> player.getGameArena())
        .forEach(gameArena -> gameArenaService.registerHit(shootRequest.getBoardPosition(), gameArena));
    gameService.saveGame(game);
  }
}
