package de.otto.job.tictactoe.domain.model;

import java.util.Arrays;

public record Game(
    GameId gameId,
    FieldStone[] gameField,
    GameState state
) {

  public static Game createNewGame() {
    FieldStone[] gameField = new FieldStone[9];
    Arrays.fill(gameField, FieldStone.EMPTY);
    return new Game(GameId.create(), gameField, GameState.MOVE_PLAYER_ONE);
  }

  public Game performMove(int x, int y) {
    if (1 <= x && x <= 3 && 1 <= y && y <= 3) {
      int fieldIndex = (x - 1) * 3 + (y - 1);
      if (gameField[fieldIndex] != FieldStone.EMPTY) {
        throw new IllegalArgumentException("Field is not empty");
      }

      FieldStone[] newGameField = new FieldStone[gameField.length];
      System.arraycopy(gameField, 0, newGameField, 0, gameField.length);

      GameState newGameState;
      if (state() == GameState.MOVE_PLAYER_ONE) {
        newGameField[fieldIndex] = FieldStone.PLAYER_ONE;
        newGameState = GameState.MOVE_PLAYER_TWO;
      } else if (state() == GameState.MOVE_PLAYER_TWO) {
        newGameField[fieldIndex] = FieldStone.PLAYER_TWO;
        newGameState = GameState.MOVE_PLAYER_ONE;
      } else {
        throw new IllegalArgumentException("Cannot make move");
      }

      return new Game(this.gameId, newGameField, newGameState);
    }

    throw new IllegalArgumentException("Wrong field coordinates");
  }

  public enum FieldStone {
    EMPTY,
    PLAYER_ONE,
    PLAYER_TWO
  }

  public enum GameState {
    MOVE_PLAYER_ONE,
    MOVE_PLAYER_TWO,
    DRAW,
    WIN_PLAYER_ONE,
    WIN_PLAYER_TWO
  }
}
