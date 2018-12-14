package uk.gov.ukho.battleshipsboot.facades;

import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ukho.battleshipsboot.model.game.Player;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;
import uk.gov.ukho.battleshipsboot.repositorys.PlayerRepository;
import uk.gov.ukho.battleshipsboot.service.GameService;
import uk.gov.ukho.battleshipsboot.service.PlayerService;

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


    public void createPlayerAndJoinToGame(String playerName, int gameId) {
        Player player = playerService.createPlayer(playerName, playerRepository);
        gameService.joinPlayerToGame(gameId, player);
    }
}
