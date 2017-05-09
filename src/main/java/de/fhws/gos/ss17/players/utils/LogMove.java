package de.fhws.gos.ss17.players.utils;

import de.fhws.gos.core.logic.Move;
import de.fhws.gos.core.utils.PositionToken;
import de.fhws.gos.ss17.main.GameOfStonesMain;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Auron on 02.05.2017.
 */
public class LogMove {

  public static void log(Move move, PositionToken playerToken) {
    File file = new File(GameOfStonesMain.logName);
    try {
      if (!file.exists()) {
        file.createNewFile();
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("P,TO,FR,REM\n");
        if (bw != null) {
          bw.close();
        }
      }

      FileWriter fw = new FileWriter(file, true);
      BufferedWriter bw = new BufferedWriter(fw);
      if (playerToken == PositionToken.PLAYER_ONE) {
        bw.write("1,");
      }
      if (playerToken == PositionToken.PLAYER_TWO) {
        bw.write("2,");
      }
      if (move.getToId() < 10) {
        bw.write(move.getToId() + " ,");
      }
      if (move.getToId() > 9) {
        bw.write(move.getToId() + ",");
      }
      if (move.getFromId() == -1) {
        bw.write("  ,");
      }
      if (move.getFromId() < 10) {
        bw.write(move.getFromId() + " ,");
      }
      if (move.getFromId() > 9) {
        bw.write(move.getFromId() + ",");
      }
      if (move.getRemoveId() == -1 ) {
        bw.write(System.lineSeparator());
      } else {
        bw.write(move.getRemoveId() + "\n");
      }

      if (bw != null) {
        bw.close();
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
