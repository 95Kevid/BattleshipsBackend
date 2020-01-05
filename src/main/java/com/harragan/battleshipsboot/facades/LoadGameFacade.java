package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.model.kotlinmodel.game.GameStatus;
import com.harragan.battleshipsboot.model.kotlinmodel.game.LoadGameRequest;
import com.harragan.battleshipsboot.model.kotlinmodel.game.LoadGameResponse;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class LoadGameFacade {
    private final GameService gameService;
    private final PlayerService playerService;

    public LoadGameFacade(final GameService gameService, final PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }

    public LoadGameResponse loadGame(final LoadGameRequest loadGameRequest) {
        final Player player = playerService.getPlayerById(loadGameRequest.getPlayerId());
        final int gameId = loadGameRequest.getGameId();
        final Game game =  gameService.getGame(gameId);
        final GameStatus gameStatus = game.getStatus();
        return new LoadGameResponse(gameId, player, gameStatus, player.getGameArena());
    }
}
