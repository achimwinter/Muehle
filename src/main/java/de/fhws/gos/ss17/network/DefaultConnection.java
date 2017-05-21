package de.fhws.gos.ss17.network;

import de.fhws.gos.core.network.Connection;
import de.fhws.gos.ss17.main.Config;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;


/**
 * Created by awinter on 18.05.17.
 */
public class DefaultConnection implements Connection{

  HttpClient httpClient = HttpClientBuilder.create().build();
  private final String BASE_URL = "http://" + Config.HOST + ":" + Config.PORT + "/";
  private final String SIGN_IN = "signin/";
  private final String BOTGAME_URL = "botgame/";
  public static String authorizationToken;

  public DefaultConnection() {
  }

  public void signIn() throws IOException {
    HttpPost request = new HttpPost(BASE_URL + SIGN_IN);
    request.addHeader("Authorization", Config.BASE64Token);
    HttpResponse response = httpClient.execute(request);
    Header authorizationHeader = response.getLastHeader("authorization");
    this.authorizationToken = authorizationHeader.getValue();
  }

  public String joinBotgame() throws IOException{
    String response  = null;
    URL joinGameURL = new URL(BASE_URL + BOTGAME_URL + playBotgame("test"));
    HttpURLConnection conn = (HttpURLConnection) joinGameURL.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoInput( true );
    conn.setDoOutput( true );
    conn.setUseCaches( false );
    conn.setRequestProperty( "Authorization" , this.authorizationToken);

    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
    writer.write(response);
    writer.flush();
    writer.close();
    return response;
  }

  public String readResponse(InputStream var1) throws IOException{
    BufferedReader br = null;
    StringBuilder sb = new StringBuilder();

    String line;
    try {

      br = new BufferedReader(new InputStreamReader(var1));
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return sb.toString();

  }

  public String playBotgame(String var1) throws IOException{
    String gameId = null;
    URL getBotgameURL = new URL(BASE_URL + BOTGAME_URL);
    HttpURLConnection conn = (HttpURLConnection) getBotgameURL.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoInput( true );
    conn.setDoOutput( true );
    conn.setUseCaches( false );
    conn.setRequestProperty( "Authorization" , this.authorizationToken);

    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
    writer.write(gameId);
    writer.flush();
    writer.close();
    return gameId;
  }

  public String offerGame() throws IOException{
    return "not implemented";
  }

  public String joinGame() throws IOException{
    return "not implemented";
  }

  public String playGame(String var1) throws IOException{
    return "not implemented";
  }

  public void setGameId(String var1){
    this.authorizationToken = var1;
  }

  //Liefert 404 error code
  public void signOut() throws IOException {
    URL logoutURL = new URL(BASE_URL + "signout");
    HttpURLConnection conn = (HttpURLConnection) logoutURL.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoInput( true );
    conn.setDoOutput( true );
    conn.setUseCaches( false );
    conn.setRequestProperty( "Authorization" , this.authorizationToken);

    conn.connect();
  }
}
