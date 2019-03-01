package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.ships.Ship;
import com.harragan.battleshipsboot.service.GameArenaService;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class ShipPlacingFacade {

  private GameArenaService gameArenaService;
  private GameService gameService;
  private PlayerService playerService;

  public ShipPlacingFacade(
      GameArenaService gameArenaService, GameService gameService, PlayerService playerService) {
    this.gameService = gameService;
    this.playerService = playerService;
    this.gameArenaService = gameArenaService;
  }

  public void placeShip(int playerId, int gameId, Ship ship) {
    Player player = playerService.getPlayerById(playerId);
    GameArena gameArena = player.getGameArena();
    gameArenaService.addShip(ship, gameArena);
    Game game = gameService.getGame(gameId);
    gameService.saveGame(game);
  }
}
