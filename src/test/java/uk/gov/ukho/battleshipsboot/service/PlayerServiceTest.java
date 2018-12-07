package uk.gov.ukho.battleshipsboot.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.model.game.GameArena;
import uk.gov.ukho.battleshipsboot.model.game.Player;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayerServiceTest {
    private PlayerService playerService;

    @Before
    public void initTest() {
        playerService = new PlayerService();
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private GameRepository gameRepository;

    @Mock
    private Player player;

    @Mock
    private Game game;

    @Test
    public void playersCanBeAssignedAGameArenaWithAnId() {
        GameArena gameArena = new GameArena();
        Player player = new Player(1);
        playerService.setArenaToPlayer(gameArena, player);
        assertThat(player.getGameArena()).isEqualTo(gameArena);
    }

    @Test
    public void givenWhenAPlayerIsInstantiatedAPlayerIdIsReturned() {
        int playerIdJo = playerService.createPlayer(game, "Jo White", gameRepository);
        int playerIdKev = playerService.createPlayer(game, "Kevin Harragan", gameRepository);

        //when(gameRepository.save())
        assertThat(playerIdJo == 0).isEqualTo(true);
        assertThat(playerIdKev == 1).isEqualTo(true);

    }
}
