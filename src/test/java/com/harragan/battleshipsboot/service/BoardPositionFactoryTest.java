package com.harragan.battleshipsboot.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BoardPositionFactoryTest {

  @Test
  public void invalidBoardPositionsCanNotBeCreated() {
    final BoardPositionFactory bps = new BoardPositionFactory();
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> bps.createBoardPosition('*', 6));
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> bps.createBoardPosition('/', 10));
  }
}
