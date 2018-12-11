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
import uk.gov.ukho.battleshipsboot.repositorys.PlayerRepository;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayerServiceTest {
    private PlayerService playerService;

    @Before
    public void initTest() {
        playerService = new PlayerService();
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private PlayerRepository playerRepository;

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
        Player playerJO = new Player("Jo White");
        when(playerRepository.save(any(Player.class))).thenReturn(playerJO);
        int playerIdJo = playerService.createPlayer("Jo White", playerRepository);

        assertThat(playerIdJo == 0).isEqualTo(true);
    }
}
