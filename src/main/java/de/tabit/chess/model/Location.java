package de.tabit.chess.model;

import de.tabit.chess.view.ChessBoard;

/**
 * Created by e1528895 on 5/11/18.
 */
public class Location {

  private int x;

  private int y;

  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Location(Location location) {
    this(location.getX(), location.getY());
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public static String toHumanReadable(Location location){
    String xStr = String.valueOf(ChessBoard.BOARD_LENGTH - location.getX());
    char c = (char)(location.getY()+(int)'a');
    return c+xStr;
  }

  public static Location fromHumanReadable(String string){
    int y = string.charAt(0) - 'a';
    int x = string.charAt(1)- '1';
    x = ChessBoard.BOARD_LENGTH - x - 1 ;
    return new Location(x, y);
  }

  @Override
  public String toString() {
    return "Location{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Location location = (Location) o;

    if (x != location.x) {
      return false;
    }
    return y == location.y;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }

  //TODO remove it
  //TODO move to test class
  public static void main(String[] args) {
    Location location = new Location(7,7);
    String x = toHumanReadable(location);
    System.out.println(x);
    System.out.println(fromHumanReadable(x));
  }


}
