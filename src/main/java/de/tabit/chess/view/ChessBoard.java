package de.tabit.chess.view;

import de.tabit.chess.controller.BoardController;
import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.Location;
import de.tabit.chess.model.Piece;
import de.tabit.chess.model.PieceLocation;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.extern.slf4j.Slf4j;

/** Created by Hanif Maleki on 5/9/18.
 * The container of chess board */

@Slf4j
public class ChessBoard extends JPanel
    implements MouseListener, MouseMotionListener, BoardStatusChangeListener {

  public static final Color BG = new Color(0x056F13);
  public static final int CELL_WIDTH = 64;
  private final BoardController controller;

  HashMap<Location, ChessCell> cellMap;

  private Piece draggingPiece = null;
  private ChessCell originCell = null;

  public ChessBoard(BoardController controller) {
    this.controller = controller ;
    controller.setChangeListener(this);
    cellMap = new HashMap<>(
        BoardStatus.BOARD_LENGTH * BoardStatus.BOARD_LENGTH);
    initCells();
  }

  private void initCells() {
    int width = BoardStatus.BOARD_LENGTH * CELL_WIDTH;
    setPreferredSize(new Dimension(width, width));

    GridLayout gridLayout = new GridLayout(BoardStatus.BOARD_LENGTH, BoardStatus.BOARD_LENGTH);

    setLayout(gridLayout);

    boolean colorTuggleVariable = true;
    for (int i = 0; i < BoardStatus.BOARD_LENGTH; i++)
      for (int j = 0; j < BoardStatus.BOARD_LENGTH; j++) {
        ChessCell cell = new ChessCell();
        cell.setOpaque(true);
        if (colorTuggleVariable) cell.setBackground(Color.white);
        else cell.setBackground(BG);
        if (j != BoardStatus.BOARD_LENGTH - 1) colorTuggleVariable = !colorTuggleVariable;
        cell.setMinimumSize(new Dimension(CELL_WIDTH, CELL_WIDTH));
        cell.setPreferredSize(new Dimension(CELL_WIDTH, CELL_WIDTH));
        Location location = new Location(i, j);
        cell.setBoardLocation(location);
        cellMap.put(location, cell);
        add(cell);
      }
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    Point point = e.getPoint();
    Component component = getComponentAt(point);
    originCell = (ChessCell) component;
    Location boardLocation = originCell.getBoardLocation();
    log.debug(Location.toHumanReadable(boardLocation));
  }

  @Override
  public void mousePressed(MouseEvent e) {
    Point point = e.getPoint();
    Component component = getComponentAt(point);
    if (component == null) {
      return;
    }
    if (!(component instanceof JLabel)) {
      return;
    }

    originCell = (ChessCell) component;
    draggingPiece = originCell.getPiece();
    originCell.setPiece(null);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (originCell == null) return;
    Point point = e.getPoint();
    Component component = getComponentAt(point);
    if (component == null) {
      return;
    }
    if (!(component instanceof JLabel)) {
      return;
    }

    if (draggingPiece != null) {
      PieceLocation from = new PieceLocation(draggingPiece, originCell.getBoardLocation());
      ChessCell cell = (ChessCell) component;
      PieceLocation to = new PieceLocation(draggingPiece, cell.getBoardLocation());
      log.debug("Validating move "+ originCell.getBoardLocation()+ "-"+ ((ChessCell) component).getBoardLocation());
      if(controller.makeAMove(from, to)) {
        log.debug("Move is valid");
        cell.setPiece(draggingPiece);
      }else {
        log.debug("Move is not valid");
        originCell.setPiece(draggingPiece);
      }
    }

    draggingPiece = null;
    originCell = null;
    setCursor(Cursor.getDefaultCursor());
  }

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseDragged(MouseEvent e) {
    if (draggingPiece != null) {
      BufferedImage image = PieceImageUtil.getBufferedImage(draggingPiece);
      int width = CELL_WIDTH / 2;
      setCursor(
          Toolkit.getDefaultToolkit()
              .createCustomCursor(
                  image, new Point(width, width), "custom cursor"));
    }
  }


  @Override
  public void mouseMoved(MouseEvent e) {}

  @Override
  public void statusChanged(BoardStatus newBoardStatus) {
    clearCells();
    List<PieceLocation> currentPieces = newBoardStatus.getCurrentPieces();
    currentPieces.forEach(pl -> cellMap.get(pl.getLocation()).setPiece(pl.getPiece()));
  }

  private void clearCells() {
    for (Entry<Location, ChessCell> es : cellMap.entrySet())
      es.getValue().setPiece(null);
  }
}
