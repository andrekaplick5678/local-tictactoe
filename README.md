# Tic Tac Toe game server

## How to start locally

```bash
./gradlew clean bootRun
```


## How to access

- open http://localhost:8080/actuator/info

## REST API

```bash
## start a new game
curl -s -X PUT http://localhost:8080/game/start
```

Response:
```json
{
  "gameId": "c380f8b2-0958-4235-888d-1d642c9a8d76"
}
```

```bash
## show game state
curl -s -X GET http://localhost:8080/game?gameId=c380f8b2-0958-4235-888d-1d642c9a8d76
```

```bash
## make move
curl -s -X POST \
  -d '{player:1, x: 3, y: 2}' \
  http://localhost:8080/game?gameId=c380f8b2-0958-4235-888d-1d642c9a8d76
```
