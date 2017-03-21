package de.fhws.gos.ss17.main;

import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Player;
import de.fhws.gos.core.network.Connection;

/**
 * (c) Tobias Fertig, FHWS 2017
 */
public class Config
{
	public final static String HOST = "";

	public final static int PORT = -1;

	public final static String GROUP_ID = "";

	public final static String GAME_MODE = "";

	/**
	 * This method is used to initialize a connection with the server. The DefaultConnection class can be used.
	 *
	 * @return the initialized connection object.
	 */
	public static Connection initConnection( )
	{
		throw new UnsupportedOperationException( "Not yet implemented" );
	}

	/**
	 * This method is used to initialize a nine mens morris board. The BoardImpl class can be used.
	 *
	 * @return the initialized board object.
	 */
	public static Board initBoard( )
	{
		throw new UnsupportedOperationException( "Not yet implemented" );
	}

	/**
	 * This method is used to get a remote player that retrieves the moves from the server. In case of bot games the
	 * RemoteBotPlayer class can be used. In case of versus games the RemoteVersusPlayer class can be used.
	 *
	 * @param connection the connection that should be used by the remote player.
	 * @return the initialized remote player.
	 */
	public static Player getRemotePlayer( Connection connection )
	{
		throw new UnsupportedOperationException( "Not yet implemented" );
	}

	/**
	 * This method is used to get a random player. The RandomPlayerWithRules class can be used.
	 *
	 * @return the initialized player.
	 */
	public static Player getRandomPlayer( )
	{
		throw new UnsupportedOperationException( "Not yet implemented" );
	}
}
