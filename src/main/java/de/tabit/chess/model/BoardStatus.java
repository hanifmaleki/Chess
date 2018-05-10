package de.tabit.chess.model;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class BoardStatus {
    List<PiecePoint> piecePoints ;
    HashMap<Piece, List<Point>> pieceLocationMAp ; //size of 10

    List<Point> getLocationsOf(Piece piece){
        return null;
    }

    int getNumberOf(Piece piece){
        List<Point> points = pieceLocationMAp.get(piece);
        //TODO should not happen
        if(points==null)
            return 0 ;
        return points.size();
    }




}
