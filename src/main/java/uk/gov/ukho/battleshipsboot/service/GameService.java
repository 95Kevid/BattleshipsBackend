package uk.gov.ukho.battleshipsboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.model.game.GameArena;
import uk.gov.ukho.battleshipsboot.model.game.Player;
import uk.gov.ukho.battleshipsboot.model.ships.Ship;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;
import uk.gov.ukho.battleshipsboot.repositorys.PlayerRepository;

import java.util.Optional;

@Service
public class GameService {

    public int createGame(GameRepository gameRepository) {
        Game game = new Game();
        Game savedGame = gameRepository.save(game);
        return savedGame.getId();
    }


    public void placeShip(int playerId, Ship ship, PlayerRepository playerRepository) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if(!playerOptional.isPresent()) {
            throw new IllegalArgumentException("The player ID provided does not correspond " +
                    "to in the player repository");
        }
        else {
            Player player = playerOptional.get();
            GameArena gameArena = player.getGameArena();
            gameArena.addShip(ship);
        }
    }
}