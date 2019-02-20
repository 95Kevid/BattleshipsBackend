package com.harragan.battleshipsboot.service;

import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import com.harragan.battleshipsboot.service.exceptions.IllegalGameStartException;
import org.springframework.stereotype.Service;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;

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

    public void setPlayerIsReady(int playerId, final PlayerRepository playerRepository) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if(!playerOptional.isPresent()) {
            throw new IllegalArgumentException("There is no player in the repository with that Id.");
        }
        Player player = playerOptional.get();

        if(player.getGameArena().isAllShipsPlaced()) {
            player.setReadyToStartGame(true);
            playerRepository.save(player);
        }
        else {
            throw new IllegalGameStartException("The Player has not placed all their ships.");
        }
    }


}
