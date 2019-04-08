package com.harragan.battleshipsboot.repositorys

import com.harragan.battleshipsboot.model.game.Player
import org.springframework.data.repository.CrudRepository

interface PlayerRepository : CrudRepository<Player, Int>