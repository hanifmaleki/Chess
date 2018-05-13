package de.tabit.chess.controller.fileManager;

import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.Location;
import de.tabit.chess.model.Piece;
import de.tabit.chess.model.PieceFactory;
import de.tabit.chess.model.PieceLocation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

/** Created by Hanif Maleki on 5/11/18. The implementation of {@link BoardStatusFileManager} */
@Slf4j
public class BoardStatusFileManagerImpl implements BoardStatusFileManager {

  public static final String DILIMITER = "|";

  @Override
  public BoardStatus readFromFile(File file) throws IOException {
    java.util.List<PieceLocation> pieceLocations = new ArrayList<>();

    try (FileInputStream in = new FileInputStream(file)) {
      try (InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8")) {
        try (BufferedReader br = new BufferedReader(inputStreamReader)) {
          br.lines().forEach(s -> pieceLocations.addAll(getPieceLocationOf(s)));
          br.close();
          inputStreamReader.close();
          in.close();
        }
      }
    } catch (IOException e) {
      log.error(e.getMessage());
      throw e;
    }
    return new BoardStatus(pieceLocations);
  }

  @Override
  public void writeToFile(BoardStatus boardStatus, File file) throws IOException {
    java.util.List<PieceLocation> pieceLocations = boardStatus.getCurrentPieces();
    String[] lines = getPieceToStrings(pieceLocations);
    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      try (OutputStreamWriter bw = new OutputStreamWriter(fileOutputStream, "UTF-8")) {
        for (String line : lines) bw.write(line + "\n");
        bw.close();
        fileOutputStream.close();
      }
    } catch (IOException e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  private String[] getPieceToStrings(java.util.List<PieceLocation> pieceLocations) {
    String[][] strings = new String[BoardStatus.BOARD_LENGTH][BoardStatus.BOARD_LENGTH];
    pieceLocations.forEach(
        pp ->
            strings[pp.getLocation().getX()][pp.getLocation().getY()] =
                PieceFactory.getSymbol(pp.getPiece()));
    String[] lines = new String[BoardStatus.BOARD_LENGTH + 1];
    for (int i = 0; i < BoardStatus.BOARD_LENGTH; i++) {
      StringBuilder line = new StringBuilder(String.valueOf(BoardStatus.BOARD_LENGTH - i));
      line.append(" " + DILIMITER);
      for (int j = 0; j < BoardStatus.BOARD_LENGTH; j++) {
        if (strings[i][j] == null) line.append("  ");
        else line.append(strings[i][j]);
        line.append(DILIMITER);
      }
      lines[i] = line.toString();
    }
    lines[BoardStatus.BOARD_LENGTH] = "   a  b  c  d  e  f  g  h";
    return lines;
  }

  private java.util.List<PieceLocation> getPieceLocationOf(String line) {
    java.util.List<PieceLocation> pieceLocations = new ArrayList<>();
    String[] split = line.split("\\"+DILIMITER);
    if (split[0].trim().startsWith("a")) return pieceLocations;
    Integer x = BoardStatus.BOARD_LENGTH - 1 - (Integer.parseInt(split[0].trim()) - 1);
    for (int i = 1; i < 9; i++) {
      String symbol = split[i].trim();
      if (!symbol.equals("")) {
        Piece piece = PieceFactory.getPieceBySymbol(symbol);
        Location location = new Location(x, i - 1);
        PieceLocation pieceLocation = new PieceLocation(piece, location);
        pieceLocations.add(pieceLocation);
      }
    }
    return pieceLocations;
  }
}
