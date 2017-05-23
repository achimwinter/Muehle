package de.fhws.gos.ss17.network;

import com.owlike.genson.Genson;
import com.owlike.genson.ext.jsr353.GensonJsonParser;
import com.owlike.genson.stream.JsonReader;
import de.fhws.gos.core.network.Connection;
import de.fhws.gos.remote.utils.JSONHelper;
import de.fhws.gos.ss17.main.Config;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


/**
 * Created by awinter on 18.05.17.
 */
public class DefaultConnection implements Connection{

  HttpClient httpClient = HttpClientBuilder.create().build();
  private final String BASE_URL = "http://" + Config.HOST + ":" + Config.PORT + "/";
  private final String SIGN_IN = "signin/";
  private final String BOTGAME_URL = "botgame/";
  public static String authorizationToken;
  public static String gameId;


  public DefaultConnection() throws IOException {
    signIn();
  }

  public void signIn() throws IOException {
    HttpPost request = new HttpPost(BASE_URL + SIGN_IN);
    request.addHeader("Authorization", Config.BASE64Token);
    HttpResponse response = httpClient.execute(request);
    Header authorizationHeader = response.getFirstHeader("authorization");
    this.authorizationToken = authorizationHeader.getValue();
  }

  public void createBotgame() throws IOException{
    HttpResponse response = getPostResponse(BASE_URL + BOTGAME_URL);
    String jsonString = EntityUtils.toString(response.getEntity());
    JSONObject json = new JSONObject(jsonString);
    String parsedGameID = (String) json.get("gameId");
    setGameId(parsedGameID);
  }

  public String joinBotgame() throws IOException{
    HttpResponse response = getPostResponse(BASE_URL + BOTGAME_URL + gameId);
    String jsonString = EntityUtils.toString(response.getEntity());
    return jsonString;
  }

  public String readResponse(InputStream var1) throws IOException{
    //not used
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

  public String playBotgame(String turn) throws IOException{
    HttpPost request = new HttpPost(BASE_URL + BOTGAME_URL + gameId + "/turn");
    request.addHeader("Authorization" , this.authorizationToken);
    request.addHeader("content-type", "application/x-www-form-urlencoded");
    StringEntity params = new StringEntity(turn);
    request.setEntity(params);
    HttpResponse response = httpClient.execute(request);
    String jsonString = EntityUtils.toString(response.getEntity());
    return jsonString;
  }

  private HttpResponse getPostResponse(String url) throws IOException {
    HttpPost request = new HttpPost(url);
    request.addHeader("Authorization" , this.authorizationToken);
    return httpClient.execute(request);
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
    this.gameId = var1;
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
