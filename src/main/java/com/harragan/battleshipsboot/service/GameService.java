package com.harragan.battleshipsboot.service;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.game.PlayersToPlayersReady;
import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import com.harragan.battleshipsboot.repositorys.GameRepository;
import org.aspectj.apache.bcel.util.Play;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

  private final GameRepository gameRepository;

  @Autowired
  public GameService(final GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public int createGame(final int numberOfPlayers, final int gameArenaSize) {
    final Game game = new Game(numberOfPlayers, gameArenaSize);
    final Game savedGame = saveGame(game);
    return savedGame.getId();
  }

  public void joinPlayerToGame(final int gameId, final Player player) {
    final Game game = getGame(gameId);
    final LinkedList<Player> players = getPlayersFromGame(game);
    checkIfMaximumPlayerLimitReached(game, players);
    players.add(player);
    game.setPlayers(players);
    saveGame(game);
  }

  private LinkedList<Player> getPlayersFromGame(final Game game) {
    return game.getPlayers();
  }

  private void checkIfGameIdExistsInRepository(final Optional<Game> gameOptional) {
    if (!gameOptional.isPresent()) {
      throw new IllegalArgumentException(
          "The game ID provided does not correspond " + "to a game record in the repository");
    }
  }

  private void checkIfMaximumPlayerLimitReached(final Game game, final LinkedList<Player> players) {
    if (players.size() > game.getMaxPlayers()) {
      throw new IllegalStateException(
          "The maximum number of players (" + game.getMaxPlayers() + ") has been reached.");
    }
  }

  public Game getGame(final int gameId) {
    final Optional<Game> optionalGame = gameRepository.findById(gameId);
    checkIfGameIdExistsInRepository(optionalGame);
    return optionalGame.get();
  }

  public void nextTurn(final Game game) {
    final Queue<Player> playersInGame = getPlayersFromGame(game);
    final Player currentPlayersTurn = playersInGame.remove();
    playersInGame.add(currentPlayersTurn);
  }

  public PlayersToPlayersReady getPlayersToPlayersReady(final int gameId) {
    final Game game = getGame(gameId);
    final List<Player> playersInGame = getPlayersFromGame(game);
    final PlayersToPlayersReady noOfPlayersToReadyPlayers =
        new PlayersToPlayersReady(game.getMaxPlayers(), getReadyPlayers(playersInGame));
    return noOfPlayersToReadyPlayers;
  }

  private int getReadyPlayers(final List<Player> players) {
    return (int) players.stream().filter(p -> p.isReadyToStartGame()).count();
  }

  public Game saveGame(final Game game) {
    return gameRepository.save(game);
  }

  public int checkForTurn(final int gameId) {
    final Game game = getGame(gameId);
    final LinkedList<Player> players = game.getPlayers();
    final Player player = players.get(game.getTurnIndex());
    return player.getId();
  }

}


