package com.harragan.battleshipsboot.service;

import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.ships.Ship;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import com.harragan.battleshipsboot.service.exceptions.IllegalGameStartException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class PlayerService {

  private PlayerRepository playerRepository;

  public PlayerService(final PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public void setArenaToPlayer(final GameArena gameArena, final Player player) {
    player.setGameArena(gameArena);
  }

  public Player createPlayer(final String playerName, final PlayerRepository playerRepository) {
    return playerRepository.save(new Player(playerName));
  }

  public Player getPlayerById(final int playerId) {
    final Optional<Player> playerOptional = playerRepository.findById(playerId);
    if (!playerOptional.isPresent()) {
      throw new IllegalArgumentException("There is no player in the repository with that Id.");
    }
    return playerOptional.get();
  }

  public void setPlayerIsReady(final int playerId, final PlayerRepository playerRepository) {
    final Optional<Player> playerOptional = playerRepository.findById(playerId);
    if (!playerOptional.isPresent()) {
      throw new IllegalArgumentException("There is no player in the repository with that Id.");
    }
    final Player player = playerOptional.get();

    if (player.getGameArena().isAllShipsPlaced()) {
      player.setReadyToStartGame(true);
      playerRepository.save(player);
    } else {
      throw new IllegalGameStartException("The Player has not placed all their ships.");
    }
  }
}
