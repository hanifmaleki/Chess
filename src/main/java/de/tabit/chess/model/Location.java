package de.tabit.chess.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Created by Hanif Maleki on 5/11/18. This model represents a cell in the chess board */
@EqualsAndHashCode
@ToString
public class Location {

  @Setter @Getter private int x;

  @Setter @Getter private int y;

  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Location(Location location) {
    this(location.getX(), location.getY());
  }

  public static String toHumanReadable(Location location) {
    String xStr = String.valueOf(BoardStatus.BOARD_LENGTH - location.getX());
    char c = (char) (location.getY() + (int) 'a');
    return c + xStr;
  }

  public static Location fromHumanReadable(String string) {
    int y = string.charAt(0) - 'a';
    int x = string.charAt(1) - '1';
    x = BoardStatus.BOARD_LENGTH - x - 1;
    return new Location(x, y);
  }
}
