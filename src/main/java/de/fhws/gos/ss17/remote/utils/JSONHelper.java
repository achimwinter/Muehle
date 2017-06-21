
package de.fhws.gos.ss17.remote.utils;

import com.owlike.genson.Genson;
import de.fhws.gos.ss17.remote.utils.models.OfferEntity;
import de.fhws.gos.ss17.remote.utils.models.ServerEntity;
import de.fhws.gos.ss17.remote.utils.models.Turn;

public class JSONHelper {
  private Genson genson = new Genson();

  public JSONHelper() {
  }

  public String serialize(Turn turn) {
    return this.genson.serialize(turn);
  }

  public ServerEntity deserialize(String entity) {
    return (ServerEntity)this.genson.deserialize(entity, ServerEntity.class);
  }

  public OfferEntity deserializeOffer(String entity) {
    return (OfferEntity)this.genson.deserialize(entity, OfferEntity.class);
  }
}
