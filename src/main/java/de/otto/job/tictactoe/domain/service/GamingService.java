package de.otto.job.tictactoe.domain.service;

import de.otto.job.tictactoe.domain.model.Game;
import de.otto.job.tictactoe.domain.model.Game.GameState;
import de.otto.job.tictactoe.domain.model.GameId;
import de.otto.job.tictactoe.domain.port.GameRepository;
import de.otto.job.tictactoe.domain.port.GamingUseCase;

public class GamingService implements GamingUseCase {

  private final GameRepository gameRepository;

  public GamingService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Override
  public GameId createNewGame() {
    Game newGame = Game.createNewGame();
    gameRepository.save(newGame);
    return newGame.gameId();
  }

  @Override
  public void performMove(GameId gameId, int player, int x, int y) {
    Game game = gameRepository.findByGameId(gameId)
        .orElseThrow(() -> new IllegalArgumentException("GameId is unknown"));

    GameState state = game.state();
    if (player == 1 && state != GameState.MOVE_PLAYER_ONE) {
      throw new IllegalArgumentException("Player is not allowed to perform a move");
    }
    if (player == 2 && state != GameState.MOVE_PLAYER_TWO) {
      throw new IllegalArgumentException("Player is not allowed to perform a move");
    }
    if (player != 1 && player != 2) {
      throw new IllegalArgumentException("Player is unknown");
    }

    Game updatedGame = game.performMove(x, y);
    gameRepository.save(updatedGame);
  }
}
