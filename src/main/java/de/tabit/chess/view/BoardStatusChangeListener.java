package de.tabit.chess.view;

import de.tabit.chess.model.BoardStatus;

/**
 * Created by e1528895 on 5/11/18.
 */
public interface BoardStatusChangeListener {
  void statusChanged(BoardStatus newBoardStatus);
}
