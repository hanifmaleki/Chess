package de.tabit.chess.view;

import de.tabit.chess.model.BoardStatus;

/**
 * Created by Hanif Maleki on 5/11/18. Any view container that wants to make aware of board
 * changes(through loading file) should implement this interface.
 */
public interface BoardStatusChangeListener {
  void statusChanged(BoardStatus newBoardStatus);
}
