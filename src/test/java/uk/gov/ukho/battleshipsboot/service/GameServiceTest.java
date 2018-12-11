package uk.gov.ukho.battleshipsboot.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.ukho.battleshipsboot.model.game.BoardPosition;
import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.game.Player;
import uk.gov.ukho.battleshipsboot.model.ships.Column;
import uk.gov.ukho.battleshipsboot.model.ships.Destroyer;
import uk.gov.ukho.battleshipsboot.model.ships.Ship;
import uk.gov.ukho.battleshipsboot.repositorys.GameRepository;
import uk.gov.ukho.battleshipsboot.repositorys.PlayerRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class GameServiceTest {

    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private Game game1;

    @Mock
    private Game game2;

    @Mock
    private Player player;

    @Before
    public void initTest() {
       gameService = new GameService();
       MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenWhenAGameIsInstantiatedAGameIDIsReturned() {
        when(game1.getId()).thenReturn(12);
        when(gameRepository.save(any(Game.class))).thenReturn(game1);
        int gameId1 = gameService.createGame(gameRepository);
        verify(gameRepository, times(1)).save(any(Game.class));
        assertThat(gameId1).as("1rst run, the gameId should be 12").isEqualTo(12);


        when(game2.getId()).thenReturn(189);
        when(gameRepository.save(any(Game.class))).thenReturn(game2);
        int gameId2 = gameService.createGame(gameRepository);
        verify(gameRepository, times(2)).save(any(Game.class));

        assertThat(gameId2).isEqualTo(189).as("2nd run gameId should be 189");
    }
    @Test
    public void givenThatAGameIsCreatedAPlayerIsLinkedToIt() {
        Game game = new Game(player);
        assertThat(game.getPlayers().contains(player))
                .as("Player should be the same player " +
                "that was supplied to its constructor")
                .isEqualTo(true);
    }

    @Test
    public void givenAPlayerIdAndShipAnOrientationAndPositionThenAShipIsPositionedWithTheOrientationAndPositionProvidedOnThePlayersGameArena() {
        Player player = new Player();
        BoardPosition boardPosition = new BoardPosition(Column.A, 1);
        Ship destroyer = new Destroyer(Orientation.HORIZONTAL, boardPosition);

        when(playerRepository.findById(0)).thenReturn(Optional.of(player));
        gameService.placeShip(player.getId(), destroyer, playerRepository);

        assertThat(player.getGameArena().getShipsOnBoard().contains(destroyer))
                .as("The ship provided should be placed in the position provided" +
                        " in the player's game arena.").isEqualTo(true);
    }


}