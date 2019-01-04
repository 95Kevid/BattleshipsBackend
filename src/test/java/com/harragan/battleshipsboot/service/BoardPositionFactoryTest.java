package com.harragan.battleshipsboot.service;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardPositionFactoryTest {

    @Test
    public void invalid_board_positions_can_not_be_created() {
        BoardPositionFactory bps = new BoardPositionFactory();
        assertThrows(IllegalArgumentException.class, () -> bps.createBoardPosition('*', 6));
        assertThrows(IllegalArgumentException.class, () -> bps.createBoardPosition('/', 10));
    }
}
