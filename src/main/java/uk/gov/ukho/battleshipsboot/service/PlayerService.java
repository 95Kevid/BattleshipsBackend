package uk.gov.ukho.battleshipsboot.service;

import org.springframework.stereotype.Service;
import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.model.game.GameArena;
import uk.gov.ukho.battleshipsboot.model.game.Player;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;
import uk.gov.ukho.battleshipsboot.repositorys.PlayerRepository;

import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void setArenaToPlayer(GameArena gameArena, Player player) {
        player.setGameArena(gameArena);
    }

    public Player createPlayer(String playerName, PlayerRepository playerRepository) {
        Player player = playerRepository.save(new Player(playerName));
        return player;
    }

    public Player getPlayerById(int playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if(!playerOptional.isPresent()) {
            throw new IllegalArgumentException("There is no player in the repository with that Id.");
        }
        return playerOptional.get();
    }
}
