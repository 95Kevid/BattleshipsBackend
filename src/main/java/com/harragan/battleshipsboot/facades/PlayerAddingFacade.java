package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.game.JoinGameResponse;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class PlayerAddingFacade {

  private PlayerService playerService;
  private GameService gameService;
  private PlayerRepository playerRepository;
  private GameArenaService gameArenaService;

  public PlayerAddingFacade(
      final PlayerService playerService,
      final GameService gameService,
      final PlayerRepository playerRepository,
      final GameArenaService gameArenaService) {
    this.playerRepository = playerRepository;
    this.gameService = gameService;
    this.playerService = playerService;
    this.gameArenaService = gameArenaService;
  }

  public JoinGameResponse createPlayerAndJoinToGame(final String playerName, final int gameId) {
    final Player player = playerService.createPlayer(playerName, playerRepository);
    final Game game = gameService.getGame(gameId);
    playerService.setArenaToPlayer(
        gameArenaService.createGameArena(game.getGameArenaSize()), player);
    gameService.joinPlayerToGame(gameId, player);
    return new JoinGameResponse(player.getId(), gameId, player.getGameArena().getGameArenaSize());
  }
}
