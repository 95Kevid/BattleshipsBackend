package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.model.kotlinmodel.game.ShootRequest;
import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import com.harragan.battleshipsboot.service.exceptions.IllegalShotException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    final List<Player> allPlayers = game.getPlayers();
    final List<Player> otherPlayers = allPlayers.stream()
        .filter(player -> player != shooter).collect(Collectors.toList());

    checkIfAllPlayersAreReady(allPlayers);
    checkForPlayersTurn(shooter, game);

    otherPlayers.stream()
        .map(player -> player.getGameArena())
        .forEach(gameArena -> gameArenaService.registerHit(shootRequest.getBoardPosition(), gameArena));
    gameService.nextTurn(game);
    gameService.saveGame(game);
  }

  private void checkForPlayersTurn(final Player player, final Game game) {
    final Player playerTurn = gameService.checkForTurn(game.getId());
    if (playerTurn == player) {
      return;
    }
    throw new IllegalShotException("It is currently player with name of " + playerTurn.getName()
        + " who's turn it is.");
  }

  private void checkIfAllPlayersAreReady(final List<Player> players) {
    if (players.stream().allMatch(player -> player.isReadyToStartGame())) {
      return;
    }
    throw new IllegalShotException("Not all the players are ready to start, please retry later.");
  }
}
