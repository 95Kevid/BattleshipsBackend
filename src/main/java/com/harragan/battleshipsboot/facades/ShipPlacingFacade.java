package com.harragan.battleshipsboot.facades;

import com.harragan.battleshipsboot.model.game.Game;
import com.harragan.battleshipsboot.model.ships.Ship;
import com.harragan.battleshipsboot.repositorys.GameRepository;
import com.harragan.battleshipsboot.service.GameService;
import com.harragan.battleshipsboot.service.PlayerService;
import org.springframework.stereotype.Service;
import com.harragan.battleshipsboot.model.game.GameArena;
import com.harragan.battleshipsboot.model.game.Player;

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


