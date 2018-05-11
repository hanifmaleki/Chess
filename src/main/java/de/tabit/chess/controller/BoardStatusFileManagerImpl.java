package de.tabit.chess.controller;

import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.Location;
import de.tabit.chess.model.Piece;
import de.tabit.chess.model.PieceFactory;
import de.tabit.chess.model.PieceLocation;
import de.tabit.chess.view.ChessBoard;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/** Created by e1528895 on 5/11/18. */
public class BoardStatusFileManagerImpl implements BoardStatusFileManager {

  @Override
  public BoardStatus readFromFile(File file)
      throws FileNotFoundException, UnsupportedEncodingException {
    java.util.List<PieceLocation> pieceLocations = new ArrayList<>();
    // try (
    BufferedReader br =
        new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
    // ) {
    br.lines().forEach(s -> pieceLocations.addAll(getPieceLocationOf(s)));
    /*} catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }*/

    return new BoardStatus(pieceLocations);
  }

  @Override
  public void writeToFile(BoardStatus boardStatus, File file) throws IOException {
    java.util.List<PieceLocation> pieceLocations = boardStatus.getCurrentPieces();
    String[] lines = getPieceToStrings(pieceLocations);
    /*try (*/
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    OutputStreamWriter bw = new OutputStreamWriter(fileOutputStream);
    // ) {
    for (String line : lines) bw.write(line + "\n");
    bw.close();
    fileOutputStream.close();
    /*} catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }*/
  }

  private String[] getPieceToStrings(java.util.List<PieceLocation> pieceLocations) {
    String[][] strings = new String[ChessBoard.BOARD_LENGTH][ChessBoard.BOARD_LENGTH];
    pieceLocations.forEach(
        pp ->
            strings[pp.getLocation().getX()][pp.getLocation().getY()] =
                PieceFactory.getSymbol(pp.getPiece()));
    String[] lines = new String[ChessBoard.BOARD_LENGTH + 1];
    for (int i = 0; i < ChessBoard.BOARD_LENGTH; i++) {
      StringBuffer line = new StringBuffer(String.valueOf(ChessBoard.BOARD_LENGTH - i));
      line.append(" |");
      for (int j = 0; j < ChessBoard.BOARD_LENGTH; j++) {
        if (strings[i][j] == null) line.append("  ");
        else line.append(strings[i][j]);
        line.append("|");
      }
      lines[i] = line.toString();
    }
    lines[ChessBoard.BOARD_LENGTH] = "   a  b  c  d  e  f  g  h";
    return lines;
  }

  private java.util.List<PieceLocation> getPieceLocationOf(String line) {
    java.util.List<PieceLocation> pieceLocations = new ArrayList<>();
    String[] split = line.split("\\|");
    if (split[0].trim().startsWith("a")) return pieceLocations;
    Integer x = ChessBoard.BOARD_LENGTH - 1 - (Integer.valueOf(split[0].trim()) - 1);
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
