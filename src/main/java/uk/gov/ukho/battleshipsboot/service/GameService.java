package uk.gov.ukho.battleshipsboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.model.game.Player;
import uk.gov.ukho.battleshipsboot.model.game.PlayersToPlayersNotReady;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;
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

    public int createGame() {
        Game game = new Game();
        Game savedGame = gameRepository.save(game);
        return savedGame.getId();
    }

    public void joinPlayerToGame(int gameId, Player player) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if(!gameOptional.isPresent()) {
            throw new IllegalArgumentException("The game ID provided does not correspond " +
                    "to in the player repository");
        }
        Game game = gameOptional.get();
        LinkedList<Player> players = game.getPlayers();
        players.add(player);
        game.setPlayers(players);
        gameRepository.save(game);
    }


    public Game getGame(int gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if(optionalGame.isPresent()) {
            return optionalGame.get();
        }
        throw new IllegalArgumentException("The game ID provided does not correspond " +
                "to a game in the game repository");
    }

    public void nextTurn(Game game) {
        Queue<Player> playersInGame = game.getPlayers();
        Player currentPlayersTurn = playersInGame.remove();
        playersInGame.add(currentPlayersTurn);
    }

    public PlayersToPlayersNotReady getNumberOfNotReadyPlayersToReadyPlayers(int gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if(!optionalGame.isPresent()) {
            throw new IllegalArgumentException("The game ID provided does not correspond " +
                    "to a game in the game repository");
        }
        Game game = optionalGame.get();
        List<Player> players = game.getPlayers();
        int notReadyPlayers = (int) players.stream()
                .filter(p -> !p.isReadyToStartGame())
                .count();
        PlayersToPlayersNotReady noOfPlayersToNotReadyPlayers
                = new PlayersToPlayersNotReady(players.size(), notReadyPlayers);
        return noOfPlayersToNotReadyPlayers;
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }
}