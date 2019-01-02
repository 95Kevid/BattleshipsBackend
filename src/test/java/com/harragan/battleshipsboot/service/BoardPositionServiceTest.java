package com.harragan.battleshipsboot.service;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardPositionServiceTest {

    @Test
    public void invalid_board_positions_can_not_be_created() {
        BoardPositionService bps = new BoardPositionService();
        assertThrows(IllegalArgumentException.class, () -> bps.createBoardPosition('*', 6));
        assertThrows(IllegalArgumentException.class, () -> bps.createBoardPosition('/', 10));
    }
}
