package de.tabit.chess.controller;

import de.tabit.chess.model.BoardStatus;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by e1528895 on 5/11/18.
 */
public interface BoardStatusFileManager {

  BoardStatus readFromFile(File file)
      throws FileNotFoundException, UnsupportedEncodingException;

  void writeToFile(BoardStatus boardStatus, File file) throws IOException;
}
