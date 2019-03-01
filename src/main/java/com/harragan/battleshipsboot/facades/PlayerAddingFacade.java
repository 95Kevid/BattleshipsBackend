package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.repositorys.GameRepository;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerAddingFacade {

  private PlayerService playerService;
  private GameService gameService;
  private PlayerRepository playerRepository;
  private GameRepository gameRepository;
  private GameArenaService gameArenaService;

  @Autowired
  public PlayerAddingFacade(
      PlayerService playerService,
      GameService gameService,
      PlayerRepository playerRepository,
      GameRepository gameRepository,
      GameArenaService gameArenaService) {
    this.playerRepository = playerRepository;
    this.gameRepository = gameRepository;
    this.gameService = gameService;
    this.playerService = playerService;
    this.gameArenaService = gameArenaService;
  }

  public int createPlayerAndJoinToGame(String playerName, int gameId) {
    Player player = playerService.createPlayer(playerName, playerRepository);
    Game game = gameService.getGame(gameId);
    playerService.setArenaToPlayer(
        gameArenaService.createGameArena(game.getGameArenaSize()), player);
    gameService.joinPlayerToGame(gameId, player);
    return player.getId();
  }
}
