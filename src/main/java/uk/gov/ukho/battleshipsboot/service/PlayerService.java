package uk.gov.ukho.battleshipsboot.service;

import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.model.game.GameArena;
import uk.gov.ukho.battleshipsboot.model.game.Player;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;

public class PlayerService {

    public void setArenaToPlayer(GameArena gameArena, Player player) {
        player.setGameArena(gameArena);
    }

    public int createPlayer(Game game, String playerName, GameRepository gameRepository) {
        Player player =  new Player(playerName);
        game.addPlayer(player);
        gameRepository.save(game);
        return player.getId();
    }

}
