package de.fhws.gos.ss17.network;

import com.owlike.genson.Genson;
import de.fhws.gos.core.logic.Game;
import de.fhws.gos.core.logic.Move;

/**
 * Created by Auron on 17.05.2017.
 */
public class JsonConverter {
  public Game deserializeGameJSON(String jsonString){
    Genson genson = new Genson();
    return genson.deserialize(jsonString, Game.class);
  }

  public String serializeGameJSON(Game game){
    Genson genson = new Genson();
    return genson.serialize(game);
  }

  public Move deserializeMoveJSON(String jsonString){
    Genson genson = new Genson();
    return genson.deserialize(jsonString, Move.class);
  }

  public String serializeMoveJSON(Move move){
    Genson genson = new Genson();
    return genson.serialize(move);
  }

  

}
