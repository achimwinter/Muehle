package de.fhws.gos.ss17.exceptions;

/**
 * Created by Achim on 22.04.2017.
 */
public class checkedExceptions extends Exception {

  public static void catchGameException(Exception ex) {
    System.out.println("GameException was catched");
    ex.printStackTrace();
    System.exit(0);
  }

  public static void catchGameIOException(Exception ex) {
    System.out.println("IOExceptions was catched");
    ex.printStackTrace();
    System.exit(0);
  }

}
