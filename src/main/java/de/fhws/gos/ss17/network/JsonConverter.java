package de.fhws.gos.ss17.network;

import com.owlike.genson.Genson;
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
    Genson genson = new Genson();
    return genson.serialize(move);
  }


  

}
