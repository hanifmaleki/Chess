package de.tabit.chess;

import de.tabit.chess.controller.BoardController;
import de.tabit.chess.controller.BoardControllerFactory;
import de.tabit.chess.controller.validators.BoardNotValidException;
import de.tabit.chess.view.ChessBoard;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Hanif Maleki on 5/9/18. The main frame of the Chess It is also entry point of the
 * Chess and is responsible for initializing and wiring controller and viewr classes
 */
@Slf4j
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
    Dimension preferredSize = board.getPreferredSize();
    setSize(new Dimension(preferredSize.width, preferredSize.height + 50));
    setResizable(false);
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
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          try {
            controller.makeBoardFromInputFile(fc.getSelectedFile());
          } catch (BoardNotValidException e1) {
            JOptionPane.showMessageDialog(
                this, "The board is invalid", "Loading unsuccessful", JOptionPane.ERROR_MESSAGE);
            log.error(e1.getMessage());
          } catch (IOException e1) {
            JOptionPane.showMessageDialog(
                this,
                "Could not read from selected file",
                "Loading unsuccessful",
                JOptionPane.ERROR_MESSAGE);
            log.error(e1.getMessage());
          }
        }

        break;
      case ACTION_SAVE:
        final JFileChooser saveFileChooser = new JFileChooser();
        workingDirectory = new File(System.getProperty("user.dir") + "/src/test/resources");
        saveFileChooser.setCurrentDirectory(workingDirectory);
        returnVal = saveFileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          try {
            controller.saveBoradToFile(saveFileChooser.getSelectedFile());
          } catch (IOException e1) {
            JOptionPane.showMessageDialog(
                this,
                "The board can not saved to the selected file",
                "Saving unsuccessful",
                JOptionPane.ERROR_MESSAGE);
            log.error(e1.getMessage());
          }
          JOptionPane.showMessageDialog(this, "The board has been saved successfully");
        }
        break;
      case ACTION_RESET:
        controller.resetBorad();
        break;
      default:
        break;
    }
  }

  public static void main(String[] args) {
    BoardController boardController = BoardControllerFactory.getDefaultBoardController();
    ChessBoard board = new ChessBoard(boardController);
    ChessFrame frame = new ChessFrame(board, boardController);
    frame.setTitle("Tabit Chess");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }


}
