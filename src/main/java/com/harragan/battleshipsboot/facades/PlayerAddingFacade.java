package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.repositorys.GameRepository;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.harragan.battleshipsboot.model.game.Player;

@Service
public class PlayerAddingFacade {

    private PlayerService playerService;
    private GameService gameService;
    private PlayerRepository playerRepository;
    private GameRepository gameRepository;


    @Autowired
    public PlayerAddingFacade(PlayerService playerService, GameService gameService
            , PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.gameService = gameService;
        this.playerService = playerService;
    }


    public int createPlayerAndJoinToGame(String playerName, int gameId) {
        Player player = playerService.createPlayer(playerName, playerRepository);
        gameService.joinPlayerToGame(gameId, player);
        return player.getId();
    }
}
