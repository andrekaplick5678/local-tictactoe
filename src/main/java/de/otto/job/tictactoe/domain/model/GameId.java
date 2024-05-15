package de.otto.job.tictactoe.domain.model;

import java.util.UUID;

public record GameId(String value) {

  public static GameId create() {
    return new GameId(UUID.randomUUID().toString());
  }
}
