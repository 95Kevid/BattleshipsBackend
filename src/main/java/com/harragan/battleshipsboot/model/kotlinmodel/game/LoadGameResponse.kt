package com.harragan.battleshipsboot.model.kotlinmodel.game

import com.harragan.battleshipsboot.model.game.GameArena
import com.harragan.battleshipsboot.model.game.Player

data class LoadGameResponse(
        val gameId: Int,
        val player: Player,
        val gameStatus: GameStatus,
        val gameArena: GameArena)