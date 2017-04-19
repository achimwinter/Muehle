package de.fhws.gos.ss17.players.utils;

/**
 * Created by Auron on 19.04.2017.
 */
public class NotSoRandomBoard {
  public static int give(int input){
    int[] array = {0,1,2,14,23,22,21,9,3,4,5,13,20,19,18,10,6,7,8,12,17,16,15,11};
    return array[input];
  }
}
