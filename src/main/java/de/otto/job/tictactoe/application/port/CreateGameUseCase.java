package de.otto.job.tictactoe.application.port;

import de.otto.job.tictactoe.domain.Game;
import de.otto.job.tictactoe.domain.PlayerName;

public interface CreateGameUseCase {

  Game startGame(PlayerName playerName1, PlayerName playerName2);

}
