package de.fhws.gos.ss17.exceptions;


/**
 * Created by Achim on 22.04.2017.
 */
public class uncheckedExceptions extends RuntimeException {

  public static void getNullPointerException(Exception ex) {
    System.out.println("NullPointerException was thrown");
    ex.printStackTrace();
    System.exit(0);
  }

  public static void getArrayIndexOutOfBoundsException(Exception ex) {
    System.out.println("ArrayIndexOutOfBounds was thrown");
    ex.printStackTrace();
    System.exit(0);
  }

}
