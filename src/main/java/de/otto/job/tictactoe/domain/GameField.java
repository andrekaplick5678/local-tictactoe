package de.otto.job.tictactoe.domain;

public record GameField(
    PlayerName[][] value
) {

  public static GameField ofNew() {
    PlayerName[][] newValue = new PlayerName[3][3];
    return new GameField(newValue);
  }

}
