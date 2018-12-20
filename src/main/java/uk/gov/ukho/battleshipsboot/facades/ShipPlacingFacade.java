package uk.gov.ukho.battleshipsboot.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.model.game.GameArena;
import uk.gov.ukho.battleshipsboot.model.game.Player;
import uk.gov.ukho.battleshipsboot.model.ships.Ship;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;
import uk.gov.ukho.battleshipsboot.service.GameArenaService;
import uk.gov.ukho.battleshipsboot.service.GameService;
import uk.gov.ukho.battleshipsboot.service.PlayerService;

@Service
public class ShipPlacingFacade {

    private GameRepository gameRepository;
    private GameService gameService;
    private PlayerService playerService;



    public ShipPlacingFacade(GameRepository gameRepository, GameService gameService, PlayerService playerService, starthere!) {
        this.gameRepository = gameRepository;
        this.gameService = gameService;
        this.playerService = playerService;
    }


    public void placeShip(int playerId, int gameId, Ship ship){
            Player player = playerService.getPlayerById(playerId);
            GameArena gameArena = player.getGameArena();
            gameArenaService.addShip(ship, gameArena);
            Game game = gameService.getGame(gameId);
            gameService.saveGame(game);
        }
    }


