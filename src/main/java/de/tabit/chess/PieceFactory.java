package de.tabit.chess;

import de.tabit.chess.Piece.Name;
import de.tabit.chess.Piece.Side;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by e1528895 on 5/9/18.
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

  public static Piece getWhitePawn(){
    return new Piece(Name.PAWN, Side.WHITE);
  }

  public static Piece getWhiteQueen(){
    return new Piece(Name.QUEEN, Side.WHITE);
  }

  public static Piece getWhiteKing(){
    return new Piece(Name.KING, Side.WHITE);
  }

  public static Piece getWhiteCastle(){
    return new Piece(Name.CASTLE, Side.WHITE);
  }

  public static Piece getWhiteKnight(){
    return new Piece(Name.KNIGHT, Side.WHITE);
  }

  public static Piece getWhiteBishop(){
    return new Piece(Name.BISHOP, Side.WHITE);
  }

  public static Piece getBlackPawn(){
    return new Piece(Name.PAWN, Side.BLACK);
  }

  public static Piece getBlackQueen(){
    return new Piece(Name.QUEEN, Side.BLACK);
  }

  public static Piece getBlackKing(){
    return new Piece(Name.KING, Side.BLACK);
  }

  public static Piece getBlackCastle(){
    return new Piece(Name.CASTLE, Side.BLACK);
  }

  public static Piece getBlackKnight(){
    return new Piece(Name.KNIGHT, Side.BLACK);
  }

  public static Piece getBlackBishop(){
    return new Piece(Name.BISHOP, Side.BLACK);
  }

  public static List<PiecePoint> getWhitePices(){
    List<PiecePoint> piecePoints = new ArrayList<>(16);
    piecePoints.add(new PiecePoint(getWhiteCastle(), new Point(7,0)));
    piecePoints.add(new PiecePoint(getWhiteCastle(), new Point(7,7)));
    piecePoints.add(new PiecePoint(getWhiteKnight(), new Point(7,1)));
    piecePoints.add(new PiecePoint(getWhiteKnight(), new Point(7,6)));
    piecePoints.add(new PiecePoint(getWhiteBishop(), new Point(7,2)));
    piecePoints.add(new PiecePoint(getWhiteBishop(), new Point(7,5)));
    piecePoints.add(new PiecePoint(getWhiteKing(), new Point(7,4)));
    piecePoints.add(new PiecePoint(getWhiteQueen(), new Point(7,3)));

    for(int i=0;i < 8 ; i++)
      piecePoints.add(new PiecePoint(getWhitePawn(), new Point(6,i)));

    return piecePoints ;
  }

  public static List<PiecePoint> getBlackPices(){
    List<PiecePoint> piecePoints = new ArrayList<>(16);
    piecePoints.add(new PiecePoint(getBlackCastle(), new Point(0,0)));
    piecePoints.add(new PiecePoint(getBlackCastle(), new Point(0,7)));
    piecePoints.add(new PiecePoint(getBlackKnight(), new Point(0,1)));
    piecePoints.add(new PiecePoint(getBlackKnight(), new Point(0,6)));
    piecePoints.add(new PiecePoint(getBlackBishop(), new Point(0,2)));
    piecePoints.add(new PiecePoint(getBlackBishop(), new Point(0,5)));
    piecePoints.add(new PiecePoint(getBlackKing(), new Point(0,4)));
    piecePoints.add(new PiecePoint(getBlackQueen(), new Point(0,3)));

    for(int i=0;i < 8 ; i++)
      piecePoints.add(new PiecePoint(getBlackPawn(), new Point(1,i)));

    return piecePoints ;
  }

  public static Piece getPieceBySymbol(String symbol){
    if(symbol.equalsIgnoreCase(BLACK_KING_SYMBOL))
      return getBlackKing();
    if(symbol.equalsIgnoreCase(BLACK_QUEEN_SYMBOL))
      return getBlackQueen();
    if(symbol.equalsIgnoreCase(BLACK_BISHOP_SYMBOL))
      return getBlackBishop();
    if(symbol.equalsIgnoreCase(BLACK_KNIGHT_SYMBOL))
      return getBlackKnight();
    if(symbol.equalsIgnoreCase(BLACK_CASTLE_SYMBOL))
      return getBlackCastle();
    if(symbol.equalsIgnoreCase(BLACK_PAWN_SYMBOL))
      return getBlackPawn();

    if(symbol.equalsIgnoreCase(WHITE_KING_SYMBOL))
      return getWhiteKing();
    if(symbol.equalsIgnoreCase(WHITE_QUEEN_SYMBOL))
      return getWhiteQueen();
    if(symbol.equalsIgnoreCase(WHITE_BISHOP_SYMBOL))
      return getWhiteBishop();
    if(symbol.equalsIgnoreCase(WHITE_KNIGHT_SYMBOL))
      return getWhiteKnight();
    if(symbol.equalsIgnoreCase(WHITE_CASTLE_SYMBOL))
      return getWhiteCastle();
    if(symbol.equalsIgnoreCase(WHITE_PAWN_SYMBOL))
      return getWhitePawn();

    return null;
  }

  public static String getSymbol(Piece piece){
    if(piece.equals(getBlackKing()))
      return BLACK_KING_SYMBOL;
    if(piece.equals(getBlackQueen()))
      return BLACK_QUEEN_SYMBOL;
    if(piece.equals(getBlackQueen()))
      return BLACK_BISHOP_SYMBOL ;
    if(piece.equals(getBlackKnight()))
      return BLACK_KNIGHT_SYMBOL ;
    if(piece.equals(getBlackCastle()))
      return BLACK_CASTLE_SYMBOL;
    if(piece.equals(getBlackPawn()))
      return BLACK_PAWN_SYMBOL ;

    if(piece.equals(getWhiteKing()))
      return WHITE_KING_SYMBOL;
    if(piece.equals(getWhiteQueen()))
      return WHITE_QUEEN_SYMBOL;
    if(piece.equals(getWhiteQueen()))
      return WHITE_BISHOP_SYMBOL ;
    if(piece.equals(getWhiteKnight()))
      return WHITE_KNIGHT_SYMBOL ;
    if(piece.equals(getWhiteCastle()))
      return WHITE_CASTLE_SYMBOL;
    if(piece.equals(getWhitePawn()))
      return WHITE_PAWN_SYMBOL ;

    return null ;
  }
}
