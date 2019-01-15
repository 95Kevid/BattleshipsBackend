package com.harragan.battleshipsboot.service;

import com.harragan.battleshipsboot.repositorys.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.game.PlayersToPlayersReady;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Service
public class GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public int createGame(int numberOfPlayers) {
        Game game = new Game(numberOfPlayers);
        Game savedGame = saveGame(game);
        return savedGame.getId();
    }

    public void joinPlayerToGame(int gameId, Player player) {
        Game game = getGame(gameId);
        LinkedList<Player> players = getPlayersFromGame(game);
        checkIfMaximumPlayerLimitReached(game, players);
        players.add(player);
        game.setPlayers(players);
        saveGame(game);
    }

    private LinkedList<Player> getPlayersFromGame(Game game) {
        return game.getPlayers();
    }

    private void checkIfGameIdExistsInRepository(Optional<Game> gameOptional) {
        if (!gameOptional.isPresent()) {
            throw new IllegalArgumentException("The game ID provided does not correspond " +
                    "to a game record in the repository");
        }
    }


    private void checkIfMaximumPlayerLimitReached(Game game, LinkedList<Player> players) {
        if(players.size() > game.getMaxPlayers())
        {
            throw new IllegalStateException("The maximum number of players ("
                    + game.getMaxPlayers()
                    + ") has been reached.");
        }
    }


    public Game getGame(int gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        checkIfGameIdExistsInRepository(optionalGame);
        return optionalGame.get();
    }

    public void nextTurn(Game game) {
        Queue<Player> playersInGame = getPlayersFromGame(game);
        Player currentPlayersTurn = playersInGame.remove();
        playersInGame.add(currentPlayersTurn);
    }

    public PlayersToPlayersReady getPlayersToPlayersReady(int gameId) {
        Game game = getGame(gameId);
        List<Player> playersInGame = getPlayersFromGame(game);
        PlayersToPlayersReady noOfPlayersToReadyPlayers
                = new PlayersToPlayersReady(game.getMaxPlayers(), getReadyPlayers(playersInGame));
        return noOfPlayersToReadyPlayers;
    }

    private int getReadyPlayers(List<Player> players) {
        return (int) players.stream()
                    .filter(p -> p.isReadyToStartGame())
                    .count();
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }
}