package de.fhws.gos.ss17.network;

import de.fhws.gos.core.network.Connection;
import de.fhws.gos.ss17.main.Config;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by awinter on 18.05.17.
 */
public class DefaultConnection implements Connection{

  private final String SIGN_IN = "/signin";
  private final String CREATE_BOTGAME_URL = "/botgame";
  private String token;



  public void signIn() throws IOException {
    URL signInURL = new URL("http://" + Config.HOST + ":" + Config.PORT + SIGN_IN);
    HttpURLConnection conn = (HttpURLConnection) signInURL.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoInput( true );
    conn.setDoOutput( true );
    conn.setUseCaches( false );
    conn.setRequestProperty( "Authorization" , Config.authToken);

    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
    writer.write(token);
    writer.flush();
    writer.close();
  }

  public void String joinBotgame() throws IOException{
    
  }
}
