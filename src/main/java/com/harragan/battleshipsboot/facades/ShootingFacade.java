package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.controllers.ShootRequest;
import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import com.harragan.battleshipsboot.service.exceptions.IllegalShotException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class ShootingFacade {

  private final GameService gameService;
  private final PlayerService playerService;
  private final GameArenaService gameArenaService;

  public ShootingFacade(final GameService gameService, final GameArenaService gameArenaService,
                        final PlayerService playerService) {
    this.gameArenaService = gameArenaService;
    this.gameService = gameService;
    this.playerService = playerService;
  }

  public void shootPosition(final ShootRequest shootRequest) {
    final Player shooter = playerService.getPlayerById(shootRequest.getPlayerId());
    final Game game = gameService.getGame(shootRequest.getGameId());
    final Player playerTurn = gameService.checkForTurn(game.getId());
    if(playerTurn == shooter) {
      final LinkedList<Player> players = game.getPlayers();
      players.stream()
          .filter(player -> player != shooter)
          .map(player -> player.getGameArena())
          .forEach(gameArena -> gameArenaService.registerHit(shootRequest.getBoardPosition(), gameArena));
      gameService.nextTurn(game);
      gameService.saveGame(game);
    }
    else {
      throw new IllegalShotException("It is currently player with name of  " + playerTurn.getName()
          + " who's turn it is.");
    }
  }
}
