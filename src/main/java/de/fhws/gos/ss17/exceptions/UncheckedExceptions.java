package de.fhws.gos.ss17.exceptions;


/**
 * Created by Achim on 22.04.2017.
 */
public class UncheckedExceptions extends RuntimeException {

  public UncheckedExceptions() {
  }

  public UncheckedExceptions(String message) {
    super(message);
  }

  public UncheckedExceptions(String message, Throwable cause) {
    super(message, cause);
  }

  public UncheckedExceptions(Throwable cause) {
    super(cause);
  }

  public UncheckedExceptions(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public static void getNullPointerException(Exception ex) {
    System.out.println("NullPointerException was thrown");
    ex.printStackTrace();
  }

  public static void getArrayIndexOutOfBoundsException(Exception ex) {
    System.out.println("ArrayIndexOutOfBounds was thrown");
    ex.printStackTrace();
  }

}
