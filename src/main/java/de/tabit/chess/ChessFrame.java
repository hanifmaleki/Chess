package de.tabit.chess;

import de.tabit.chess.controller.BoardController;
import de.tabit.chess.controller.BoardControllerImplementation;
import de.tabit.chess.controller.BoardNotValidException;
import de.tabit.chess.controller.BoardStatusFileManagerImpl;
import de.tabit.chess.controller.BoardValidatorImpl;
import de.tabit.chess.controller.MoveValidatorImpl;
import de.tabit.chess.view.ChessBoard;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** Created by e1528895 on 5/9/18. */
public class ChessFrame extends JFrame implements ActionListener {

  private static final int ACTION_NEW = 1;
  private static final int ACTION_SAVE = 2;
  private static final int ACTION_OPEN = 3;
  private static final int ACTION_RESET = 4;

  private final ChessBoard board;
  private final BoardController controller;

  public ChessFrame(ChessBoard board, BoardController controller) throws HeadlessException {
    this.board = board;
    this.controller = controller;
    add(board);
    JPanel southPanel = makeSouthPanel();

    add(southPanel, BorderLayout.SOUTH);
    this.pack();
  }

  private JPanel makeSouthPanel() {
    JPanel southPanel = new JPanel();
    JButton newButton = new JButton("New");
    newButton.addActionListener(this);
    newButton.setActionCommand("" + ACTION_NEW);
    southPanel.add(newButton);

    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(this);
    saveButton.setActionCommand("" + ACTION_SAVE);
    southPanel.add(saveButton);

    JButton openButton = new JButton("Open");
    openButton.addActionListener(this);
    openButton.setActionCommand("" + ACTION_OPEN);
    southPanel.add(openButton);

    JButton clearButton = new JButton("Clear");
    clearButton.addActionListener(this);
    clearButton.setActionCommand("" + ACTION_RESET);
    southPanel.add(clearButton);
    return southPanel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int command = Integer.parseInt(e.getActionCommand());
    switch (command) {
      case ACTION_NEW:
        controller.freshGame();
        break;
      case ACTION_OPEN:
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        File workingDirectory = new File(System.getProperty("user.dir") + "/src/test/resources");
        fc.setCurrentDirectory(workingDirectory);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
          try {
            controller.makeBoardFromInputFile(fc.getSelectedFile());
          } catch (BoardNotValidException e1) {
            //TODO suitable message
            e1.printStackTrace();
          } catch (FileNotFoundException e1) {
            //TODO handle the exception
            e1.printStackTrace();
          } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
          }

        break;
      case ACTION_SAVE:
        final JFileChooser saveFileChooser = new JFileChooser();
        workingDirectory = new File(System.getProperty("user.dir") + "/src/test/resources");
        saveFileChooser.setCurrentDirectory(workingDirectory);
        returnVal = saveFileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
          try {
            controller.saveBoradToFile(saveFileChooser.getSelectedFile());
          } catch (IOException e1) {
          //TODO handle the exception
            e1.printStackTrace();
          }
        break;
      case ACTION_RESET:
        controller.resetBorad();
        break;
    }
  }

  public static void main(String[] args) {
    // Wiring
    BoardController boardController = new BoardControllerImplementation();
    boardController.setBoardValidator(new BoardValidatorImpl());
    boardController.setMoveValidator(new MoveValidatorImpl());
    boardController.setFileManager(new BoardStatusFileManagerImpl());
    ChessBoard board = new ChessBoard(boardController);

    ChessFrame frame = new ChessFrame(board, boardController);
    frame.setTitle("Tabit Chess");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

}
