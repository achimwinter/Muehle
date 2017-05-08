package de.fhws.gos.ss17.exceptions;

/**
 * Created by Achim on 22.04.2017.
 */
public class CheckedExceptions extends Exception {

  public static void catchGameException(Exception ex) {
    System.out.println("GameException was catched");
    ex.printStackTrace();
  }

  public static void catchGameIOException(Exception ex) {
    System.out.println("IOExceptions was catched");
    ex.printStackTrace();
  }

}
