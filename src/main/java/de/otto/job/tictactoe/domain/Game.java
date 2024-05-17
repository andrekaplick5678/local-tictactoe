package de.otto.job.tictactoe.domain;

public record Game(
    GameId gameId,
    PlayerName playerName1,
    PlayerName playerName2,
    PlayerName activePlayer,
    GameField gameField
) {

}
