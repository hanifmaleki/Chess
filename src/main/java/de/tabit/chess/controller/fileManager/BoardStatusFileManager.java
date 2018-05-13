package de.tabit.chess.controller.fileManager;

import de.tabit.chess.model.BoardStatus;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hanif Maleki on 5/11/18. Any implementation of this interface enables writing a board
 * status to/from text file
 */
public interface BoardStatusFileManager {

  BoardStatus readFromFile(File file) throws IOException;

  void writeToFile(BoardStatus boardStatus, File file) throws IOException;
}
