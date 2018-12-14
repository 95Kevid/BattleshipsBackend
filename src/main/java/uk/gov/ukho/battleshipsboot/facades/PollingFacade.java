package uk.gov.ukho.battleshipsboot.facades;

import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ukho.battleshipsboot.model.game.PlayersToPlayersNotReady;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;
import uk.gov.ukho.battleshipsboot.service.GameService;

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
