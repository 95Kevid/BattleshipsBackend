package uk.gov.ukho.battleshipsboot.service;

import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.model.game.GameArena;
import uk.gov.ukho.battleshipsboot.model.game.Player;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;
import uk.gov.ukho.battleshipsboot.repositorys.PlayerRepository;

public class PlayerService {

    public void setArenaToPlayer(GameArena gameArena, Player player) {
        player.setGameArena(gameArena);
    }

    public int createPlayer(String playerName, PlayerRepository playerRepository) {
        Player player = playerRepository.save(new Player(playerName));
        return player.getId();
    }

}
