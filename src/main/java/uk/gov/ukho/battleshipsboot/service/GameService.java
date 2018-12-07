package uk.gov.ukho.battleshipsboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;

@Service
public class GameService {

    public int createGame(GameRepository gameRepository) {
        Game game = new Game();
        Game savedGame = gameRepository.save(game);
        return savedGame.getId();
    }
}