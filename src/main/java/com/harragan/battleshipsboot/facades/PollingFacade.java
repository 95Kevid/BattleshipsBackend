package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.PlayersToPlayersReady;
import com.harragan.battleshipsboot.repositorys.GameRepository;
import com.harragan.battleshipsboot.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollingFacade {

    private GameRepository gameRepository;
    private GameService gameService;

    @Autowired
    public PollingFacade(final GameService gameService, final GameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    public PlayersToPlayersReady getNumberOfNotReadyPlayersToReadyPlayers(final int gameId) {
        return gameService.getPlayersToPlayersReady(gameId);
    }
}
