package de.tabit.chess.controller;

import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.Piece.Side;
import de.tabit.chess.model.PieceFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by e1528895 on 5/11/18.
 */
public class BoardValidationFactory {

  public static final int TOTAL_ALLOWED_PIECE_NUMBER = 2;
  public static final int TOTAL_ALLOWED_QUEEN_NUMBER = 1;

  public static Map<Predicate<BoardStatus>,String> getPredicates(){
    //List<Predicate<BoardStatus>> boardStatusList = new ArrayList<>();
    Map<Predicate<BoardStatus>,String> predicateStringMap = new HashMap<>(20);

    Predicate<BoardStatus> allPiecesLimitation = boardStatus ->
        boardStatus.getCurrentPieces().size() <= 32;
    predicateStringMap.put(allPiecesLimitation, "Number of pieces exceeded.");

    for(Side side : Side.values()) {
      Predicate<BoardStatus> kingLimitation = boardStatus ->
          boardStatus.getNumberOf(PieceFactory.getKing(side)) == 1;
      predicateStringMap.put(kingLimitation, "number of "+ side.name() +" king is incorrect.");

      Predicate<BoardStatus> pawnLimitation = boardStatus ->
          boardStatus.getNumberOf(PieceFactory.getPawn(side))<=8;
      predicateStringMap.put(pawnLimitation, "number of "+ side.name() +" pawn is exceeded.");


      Predicate<BoardStatus> knightLimitation = boardStatus ->
          boardStatus.getNumberOf(PieceFactory.getKnight(side))<=TOTAL_ALLOWED_PIECE_NUMBER;
      predicateStringMap.put(knightLimitation, "number of "+ side.name() +" knight is exceeded.");


      Predicate<BoardStatus> bishopLimitation = boardStatus ->
          boardStatus.getNumberOf(PieceFactory.getBishop(side))<=TOTAL_ALLOWED_PIECE_NUMBER;
      predicateStringMap.put(bishopLimitation, "number of "+ side.name() +" bishops is exceeded.");


      Predicate<BoardStatus> castleLimitation = boardStatus ->
          boardStatus.getNumberOf(PieceFactory.getCastle(side))<=TOTAL_ALLOWED_PIECE_NUMBER;
      predicateStringMap.put(castleLimitation, "number of "+ side.name() +" castles is exceeded.");


      Predicate<BoardStatus> queenLimitation = boardStatus ->
          boardStatus.getNumberOf(PieceFactory.getQueen(side))<=TOTAL_ALLOWED_QUEEN_NUMBER;
      predicateStringMap.put(queenLimitation, "number of "+ side.name() +" queen is exceeded.");
    }

    return predicateStringMap ;
  }

}
