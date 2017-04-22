package de.fhws.gos.ss17.players;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import de.fhws.gos.core.exceptions.GameException;
import de.fhws.gos.core.logic.Board;
import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Neuer on 22.04.2017.
 */
public class LogPlayer extends AdvancedRandomPlayer {

    public LogPlayer(PositionToken playerToken) {
        super(playerToken);
    }

    public Move getNextMove(Board board) throws GameException {
        log(super.getNextMove(board).toString());
        return super.getNextMove(board);
    }

    protected Move getMovingMove(Board board) throws GameException {
        log(super.getMovingMove(board).toString());
        return super.getMovingMove(board);
    }

    protected Move getPlacingMove(Board board) throws GameException{
        log(super.getPlacingMove(board).toString());
        return super.getPlacingMove(board);
    }

    protected Move getFlyingMove(Board board) throws GameException{
        log(super.getFlyingMove(board).toString());
        return super.getFlyingMove(board);
    }
    private void log(String output){
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream("logs/moves.log", true);

            ObjectOutputStream oos = null;

            try{
                oos = new ObjectOutputStream(fos);
                oos.writeObject(output);
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
            finally{
                try{
                    if(oos != null){
                        oos.close();
                    }
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
        catch(FileNotFoundException ex){
            try{
                if(fos != null){
                    fos.close();
                }
            }
            catch(IOException exs){
                exs.printStackTrace();
            }
        }
    }
}
