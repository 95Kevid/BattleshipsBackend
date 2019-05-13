package com.harragan.battleshipsboot.service

import com.harragan.battleshipsboot.model.game.Game
import com.harragan.battleshipsboot.model.game.Player
import com.harragan.battleshipsboot.repositorys.GameRepository
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*


class GameServiceTestKt {

    private val gameRepository: GameRepository = mock<GameRepository>()

    private val game1 = Game(2, 10)
    private val gameService = GameService(gameRepository)
    private val player1 = Player("John")
    private val player2 = Player("Smith")
    private val player3 = Player("Jones")

    init {
        MockitoAnnotations.initMocks(this)
        player1.id = 1
        player2.id = 2
        player3.id = 3
        game1.id = 1
        `when`(gameRepository.findById(1)).thenReturn(Optional.of(game1))
    }

    @Test
    fun firstPlayerThatJoinsGameIsTheFirstPlayerWhosTurnItIs() {
        val players = LinkedList(Arrays.asList(player1, player2, player3))
        game1.players = players
        assertThat(gameService.checkForTurn(1)).isEqualTo(player1)
    }

    @Test
    fun secondPlayerThatJoinsGameIsTheSecondPlayerWhosTurnItIs() {
        val players = LinkedList(Arrays.asList(player1, player2, player3))
        game1.players = players
        gameService.nextTurn(game1)
        assertThat(gameService.checkForTurn(1)).isEqualTo(player2)
    }

    @Test
    fun thirdPlayerThatJoinsGameIsTheSecondPlayerWhosTurnItIs() {
        val players = LinkedList(Arrays.asList(player1, player2, player3))
        game1.players = players
        gameService.nextTurn(game1)
        gameService.nextTurn(game1)
        assertThat(gameService.checkForTurn(1)).isEqualTo(player3)
    }

    @Test
    fun givenThatAllPlayersHasHadTheirTurnThenThePlayerThatSetTheGameUpHasTheirTurnAgain() {
        val players = LinkedList(Arrays.asList(player1, player2, player3))
        game1.players = players
        gameService.nextTurn(game1)
        gameService.nextTurn(game1)
        gameService.nextTurn(game1)
        assertThat(gameService.checkForTurn(1)).isEqualTo(player1)
    }


}
