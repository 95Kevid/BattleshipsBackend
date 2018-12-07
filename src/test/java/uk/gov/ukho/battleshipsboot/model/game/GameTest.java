package uk.gov.ukho.battleshipsboot.model.game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {

    @Test
    public void whenIAddPlayersToAGameThenItIsTheSameAsTheOnesThatAreRequestedFromIt() {
        Game game = new Game();
        game.printId();

        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Player player3 = new Player(3);
        Player player4 = new Player(4);

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

        List<Player> players = new ArrayList<>();
         players.add(player1);
         players.add(player2);
         players.add(player3);
         players.add(player4);

        assertThat(game.getPlayers().containsAll(players));
    }
}