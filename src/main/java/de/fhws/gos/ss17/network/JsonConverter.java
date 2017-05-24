package de.fhws.gos.ss17.network;

import com.owlike.genson.Genson;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Game;
import de.fhws.gos.core.logic.Move;

/**
 * Created by Auron on 17.05.2017.
 */
public class JsonConverter {
  public static de.fhws.gos.ss17.network.Game deserializeGameJSON(String jsonString){
    Genson genson = new Genson();
    return genson.deserialize(jsonString, de.fhws.gos.ss17.network.Game.class);
  }

  public static String serializeGameJSON(de.fhws.gos.ss17.network.Game game){
    Genson genson = new Genson();
    return genson.serialize(game);
  }

  public static Move deserializeMoveJSON(String jsonString){
    Genson genson = new Genson();
    return genson.deserialize(jsonString, Move.class);
  }

  public static String serializeMoveJSON(Move move){
    StringBuilder jsonMove = new StringBuilder();
    jsonMove.append("{\"fromId\":");
    if(move.getFromId() == -1){
      jsonMove.append("null");
    }
    else
      jsonMove.append(move.getFromId());
    jsonMove.append(", \"toId\":" + move.getToId() + ", \"removeId\":");
    if(move.getRemoveId() == -1){
      jsonMove.append("null}");
    }
    else
      jsonMove.append(move.getRemoveId() + "}");
    return jsonMove.toString();
  }

  public static Board deserializeBoard(String boardString){
    Genson genson = new Genson();
    return genson.deserialize(boardString, Board.class);
  }

  

}
