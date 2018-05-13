package de.tabit.chess.controller;

import de.tabit.chess.controller.fileManager.BoardStatusFileManagerImpl;
import de.tabit.chess.controller.validators.BoardNotValidException;
import de.tabit.chess.controller.validators.BoardValidatorImpl;
import de.tabit.chess.controller.validators.MoveValidatorImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TemporaryFolder;

/**
 * Created by Hanif Maleki on 5/11/18. The aim is to testing {@link BoardStatusFileManagerImpl}
 * class
 */
@Category(MyCategory.class)
// @RunWith(Parameterized.class)
public class FileReadWriteTest {

  BoardController boardController;

  @Rule public TemporaryFolder folder = new TemporaryFolder();

  @Before
  public void init() {
    boardController = new BoardControllerImplementation();
    boardController.setMoveValidator(new MoveValidatorImpl());
    boardController.setBoardValidator(new BoardValidatorImpl());
    boardController.setFileManager(new BoardStatusFileManagerImpl());
  }

  // @Parameters
  public static Collection<File> files() {
    return Arrays.asList(
        new File[] {
          new File("src/test/resources/chess-startup.txt"),
          new File("src/test/resources/chess-01.txt"),
          new File("src/test/resources/chess-02.txt"),
          new File("src/test/resources/chess-03.txt")
        });
  }

  // @Parameter
  // public File file1 ;

  @Test
  public void fileOperationTest() {
    files().forEach(this::loadSaveFile);
  }

  // @Test
  public void loadSaveFile(File file1) {
    try {
      boardController.makeBoardFromInputFile(file1);
    } catch (BoardNotValidException | IOException e) {
      e.printStackTrace();
    }
    File file2 = null;
    try {
      file2 = folder.newFile();
      boardController.saveBoradToFile(file2);
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert (isTowFilesHaveSameText(file1, file2));
  }

  private boolean isTowFilesHaveSameText(File file1, File file2) {
    try (BufferedReader br1 =
            new BufferedReader(new InputStreamReader(new FileInputStream(file1), "UTF-8"));
        BufferedReader br2 =
            new BufferedReader(new InputStreamReader(new FileInputStream(file2), "UTF-8"))) {
      String line1;
      while ((line1 = br1.readLine()) != null) {
        String line2 = br2.readLine();
        if (!line1.equals(line2)) return false;
      }
      String s = br2.readLine();
      if (s != null) return false;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }
}
