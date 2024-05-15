package de.otto.job.tictactoe.domain.port;

import de.otto.job.tictactoe.domain.model.GameId;

public interface GamingUseCase {

  GameId createNewGame();

  void performMove(GameId gameId, int player, int x, int y);

}
