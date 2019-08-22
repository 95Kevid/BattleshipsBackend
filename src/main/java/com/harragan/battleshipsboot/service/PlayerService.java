package com.harragan.battleshipsboot.service;

import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import com.harragan.battleshipsboot.service.exceptions.IllegalGameStartException;
import org.springframework.stereotype.Service;

import java.util.*;

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

  public void savePlayer(final Player player) {
    playerRepository.save(player);
  }

  public Map<Player, Set<BoardPosition>> getPlayersToShotPositions(final Set<Player> players) {
    final Map<Player, Set<BoardPosition>> result = new HashMap<>();
    players.forEach(player -> result.put(player, player.getGameArena().getShotPositions()));
    return result;
  }

  public void setWinner(Player player) {
    player.setWinner(true);
  }

  public void setLoser(Player player) {
    player.setLoser(true);
  }

  public Optional<Player> getWinner(List<Player> allPlayers) {
    final long playersLeftInGame = allPlayers.stream().filter(player ->
            !player.getGameArena().isAllShipsSunk()).count();
    if(playersLeftInGame == 1) {
      final Player winner = allPlayers.stream().filter((player -> !player.getGameArena()
              .isAllShipsSunk())).findAny().get();
      setWinner(winner);
      return Optional.of(winner);
    }
    return Optional.ofNullable(null);
  }

  public void calculateLosers(List<Player> allPlayers) {
    allPlayers.stream().forEach(player -> {
      if(player.getGameArena().isAllShipsSunk()) {
        setLoser(player);
      }
    });
  }


}
