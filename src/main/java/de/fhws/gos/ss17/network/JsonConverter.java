package de.fhws.gos.ss17.network;

import com.owlike.genson.Genson;
import com.owlike.genson.annotation.JsonIgnore;
import de.fhws.gos.ss17.core.exceptions.GameException;
import de.fhws.gos.ss17.core.utils.PositionToken;
import de.fhws.gos.ss17.core.logic.Move;
import de.fhws.gos.ss17.core.logic.Board;
import de.fhws.gos.ss17.main.Config;

/**
 * Created by Auron on 17.05.2017.
 */
public class JsonConverter {

  public static de.fhws.gos.ss17.network.Game deserializeGameJSON(String jsonString) {
    Genson genson = new Genson();
    return genson.deserialize(jsonString, de.fhws.gos.ss17.network.Game.class);
  }

  public static String serializeGameJSON(de.fhws.gos.ss17.network.Game game) {
    Genson genson = new Genson();
    return genson.serialize(game);
  }

  public static Move deserializeMoveJSON(String jsonString) {
    Genson genson = new Genson();
    return genson.deserialize(jsonString, Move.class);
  }

  public static String serializeMoveJSON(Move move) {
    StringBuilder jsonMove = new StringBuilder();
    jsonMove.append("{\"fromId\":");
    if (move.getFromId() == -1) {
      jsonMove.append("null");
    } else {
      jsonMove.append(move.getFromId());
    }
    jsonMove.append(", \"toId\":" + move.getToId() + ", \"removeId\":");
    if (move.getRemoveId() == -1) {
      jsonMove.append("null}");
    } else {
      jsonMove.append(move.getRemoveId() + "}");
    }
    return jsonMove.toString();
  }

  @JsonIgnore
  public static Board deserializeBoard(String[] boardString) throws GameException {
    Board board = Config.initBoard();
    for (int i = 0; i < boardString.length; i++) {
      if (boardString[i].equals("NO_TOKEN")) {
        //board.executeMove(new Move(-1, i, -1), PositionToken.IS_EMPTY);
        board.getPosition(i).setPositionToken(PositionToken.IS_EMPTY);
      }
      //board.getPosition(i).setPositionToken(PositionToken.IS_EMPTY);
      else if (boardString[i].equals("6997")) {
        board.getPosition(i).setPositionToken(PositionToken.PLAYER_ONE);
        //board.executeMove(new Move(-1, i, -1), PositionToken.PLAYER_ONE);
      }

      else {
        board.getPosition(i).setPositionToken(PositionToken.PLAYER_TWO);
        //board.executeMove(new Move(-1, i, -1), PositionToken.PLAYER_TWO);
      }

    }
    return board;
  }

}
