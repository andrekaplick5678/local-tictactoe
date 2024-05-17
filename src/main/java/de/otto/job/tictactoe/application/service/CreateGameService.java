package de.otto.job.tictactoe.application.service;

import de.otto.job.tictactoe.application.port.CreateGameUseCase;
import de.otto.job.tictactoe.domain.Game;
import de.otto.job.tictactoe.domain.PlayerName;

public class CreateGameService implements CreateGameUseCase {

  @Override
  public Game startGame(PlayerName playerName1, PlayerName playerName2) {
    // TODO: implement
    throw new UnsupportedOperationException("TODO");
  }
}
