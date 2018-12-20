package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.repositorys.GameRepository;
import com.harragan.battleshipsboot.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import com.harragan.battleshipsboot.model.game.PlayersToPlayersNotReady;

public class PollingFacade {

    GameRepository gameRepository;
    GameService gameService;

    @Autowired
    public PollingFacade(GameService gameService, GameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    public PlayersToPlayersNotReady getNumberOfNotReadyPlayersToReadyPlayers(int gameId) {
        return gameService.getNumberOfNotReadyPlayersToReadyPlayers(gameId);
    }
}
