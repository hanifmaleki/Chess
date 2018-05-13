package de.tabit.chess.model;

import de.tabit.chess.model.Piece.Name;
import de.tabit.chess.model.Piece.Side;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanif Maleki on 5/9/18. This class is a factory of {@link Piece} class. It generates
 * different pieces as well as a list of pieces for the game start. It also maintain related symbol
 * of each piece that is necessray for reading/writing from/to files.
 */
public class PieceFactory {

  public static final String BLACK_KING_SYMBOL = "ıʞ";
  public static final String BLACK_QUEEN_SYMBOL = "nb";
  public static final String BLACK_BISHOP_SYMBOL = "ıq";
  public static final String BLACK_KNIGHT_SYMBOL = "uʞ";
  public static final String BLACK_CASTLE_SYMBOL = "oɹ";
  public static final String BLACK_PAWN_SYMBOL = "ɐd";

  public static final String WHITE_KING_SYMBOL = "ki";
  public static final String WHITE_QUEEN_SYMBOL = "qu";
  public static final String WHITE_BISHOP_SYMBOL = "bi";
  public static final String WHITE_KNIGHT_SYMBOL = "kn";
  public static final String WHITE_CASTLE_SYMBOL = "ro";
  public static final String WHITE_PAWN_SYMBOL = "pa";

  public static Piece getWhitePawn() {
    return new Piece(Name.PAWN, Side.WHITE);
  }

  public static Piece getWhiteQueen() {
    return new Piece(Name.QUEEN, Side.WHITE);
  }

  public static Piece getWhiteKing() {
    return new Piece(Name.KING, Side.WHITE);
  }

  public static Piece getWhiteCastle() {
    return new Piece(Name.CASTLE, Side.WHITE);
  }

  public static Piece getWhiteKnight() {
    return new Piece(Name.KNIGHT, Side.WHITE);
  }

  public static Piece getWhiteBishop() {
    return new Piece(Name.BISHOP, Side.WHITE);
  }

  public static Piece getBlackPawn() {
    return new Piece(Name.PAWN, Side.BLACK);
  }

  public static Piece getBlackQueen() {
    return new Piece(Name.QUEEN, Side.BLACK);
  }

  public static Piece getBlackKing() {
    return new Piece(Name.KING, Side.BLACK);
  }

  public static Piece getBlackCastle() {
    return new Piece(Name.CASTLE, Side.BLACK);
  }

  public static Piece getBlackKnight() {
    return new Piece(Name.KNIGHT, Side.BLACK);
  }

  public static Piece getBlackBishop() {
    return new Piece(Name.BISHOP, Side.BLACK);
  }

  public static List<PieceLocation> getWhitePices() {
    List<PieceLocation> pieceLocations = new ArrayList<>(16);
    pieceLocations.add(new PieceLocation(getWhiteCastle(), new Location(7, 0)));
    pieceLocations.add(new PieceLocation(getWhiteCastle(), new Location(7, 7)));
    pieceLocations.add(new PieceLocation(getWhiteKnight(), new Location(7, 1)));
    pieceLocations.add(new PieceLocation(getWhiteKnight(), new Location(7, 6)));
    pieceLocations.add(new PieceLocation(getWhiteBishop(), new Location(7, 2)));
    pieceLocations.add(new PieceLocation(getWhiteBishop(), new Location(7, 5)));
    pieceLocations.add(new PieceLocation(getWhiteKing(), new Location(7, 4)));
    pieceLocations.add(new PieceLocation(getWhiteQueen(), new Location(7, 3)));

    for (int i = 0; i < 8; i++)
      pieceLocations.add(new PieceLocation(getWhitePawn(), new Location(6, i)));

    return pieceLocations;
  }

  public static List<PieceLocation> getBlackPices() {
    List<PieceLocation> pieceLocations = new ArrayList<>(16);
    pieceLocations.add(new PieceLocation(getBlackCastle(), new Location(0, 0)));
    pieceLocations.add(new PieceLocation(getBlackCastle(), new Location(0, 7)));
    pieceLocations.add(new PieceLocation(getBlackKnight(), new Location(0, 1)));
    pieceLocations.add(new PieceLocation(getBlackKnight(), new Location(0, 6)));
    pieceLocations.add(new PieceLocation(getBlackBishop(), new Location(0, 2)));
    pieceLocations.add(new PieceLocation(getBlackBishop(), new Location(0, 5)));
    pieceLocations.add(new PieceLocation(getBlackKing(), new Location(0, 4)));
    pieceLocations.add(new PieceLocation(getBlackQueen(), new Location(0, 3)));

    for (int i = 0; i < 8; i++)
      pieceLocations.add(new PieceLocation(getBlackPawn(), new Location(1, i)));

    return pieceLocations;
  }

  public static Piece getPieceBySymbol(String symbol) {
    if (symbol.equalsIgnoreCase(BLACK_KING_SYMBOL)) return getBlackKing();
    if (symbol.equalsIgnoreCase(BLACK_QUEEN_SYMBOL)) return getBlackQueen();
    if (symbol.equalsIgnoreCase(BLACK_BISHOP_SYMBOL)) return getBlackBishop();
    if (symbol.equalsIgnoreCase(BLACK_KNIGHT_SYMBOL)) return getBlackKnight();
    if (symbol.equalsIgnoreCase(BLACK_CASTLE_SYMBOL)) return getBlackCastle();
    if (symbol.equalsIgnoreCase(BLACK_PAWN_SYMBOL)) return getBlackPawn();

    if (symbol.equalsIgnoreCase(WHITE_KING_SYMBOL)) return getWhiteKing();
    if (symbol.equalsIgnoreCase(WHITE_QUEEN_SYMBOL)) return getWhiteQueen();
    if (symbol.equalsIgnoreCase(WHITE_BISHOP_SYMBOL)) return getWhiteBishop();
    if (symbol.equalsIgnoreCase(WHITE_KNIGHT_SYMBOL)) return getWhiteKnight();
    if (symbol.equalsIgnoreCase(WHITE_CASTLE_SYMBOL)) return getWhiteCastle();
    if (symbol.equalsIgnoreCase(WHITE_PAWN_SYMBOL)) return getWhitePawn();

    return null;
  }

  public static String getSymbol(Piece piece) {
    if (piece.equals(getBlackKing())) return BLACK_KING_SYMBOL;
    if (piece.equals(getBlackQueen())) return BLACK_QUEEN_SYMBOL;
    if (piece.equals(getBlackBishop())) return BLACK_BISHOP_SYMBOL;
    if (piece.equals(getBlackKnight())) return BLACK_KNIGHT_SYMBOL;
    if (piece.equals(getBlackCastle())) return BLACK_CASTLE_SYMBOL;
    if (piece.equals(getBlackPawn())) return BLACK_PAWN_SYMBOL;

    if (piece.equals(getWhiteKing())) return WHITE_KING_SYMBOL;
    if (piece.equals(getWhiteQueen())) return WHITE_QUEEN_SYMBOL;
    if (piece.equals(getWhiteBishop())) return WHITE_BISHOP_SYMBOL;
    if (piece.equals(getWhiteKnight())) return WHITE_KNIGHT_SYMBOL;
    if (piece.equals(getWhiteCastle())) return WHITE_CASTLE_SYMBOL;
    if (piece.equals(getWhitePawn())) return WHITE_PAWN_SYMBOL;

    return null;
  }

  public static Piece getKing(Side side) {
    if (side == Side.WHITE) return getWhiteKing();
    return getBlackKing();
  }

  public static Piece getQueen(Side side) {
    if (side == Side.WHITE) return getWhiteQueen();
    return getBlackQueen();
  }

  public static Piece getPawn(Side side) {
    if (side == Side.WHITE) return getWhitePawn();
    return getBlackPawn();
  }

  public static Piece getKnight(Side side) {
    if (side == Side.WHITE) return getWhiteKnight();
    return getBlackKnight();
  }

  public static Piece getBishop(Side side) {
    if (side == Side.WHITE) return getWhiteBishop();
    return getBlackBishop();
  }

  public static Piece getCastle(Side side) {
    if (side == Side.WHITE) return getWhiteCastle();
    return getBlackCastle();
  }
}
