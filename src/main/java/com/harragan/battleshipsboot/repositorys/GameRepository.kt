package com.harragan.battleshipsboot.repositorys

import com.harragan.battleshipsboot.model.game.Game
import org.springframework.data.repository.CrudRepository

interface GameRepository : CrudRepository<Game, Int>