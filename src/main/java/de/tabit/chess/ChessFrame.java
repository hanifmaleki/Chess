package de.tabit.chess;

import de.tabit.chess.Piece.Name;
import de.tabit.chess.Piece.Side;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** Created by e1528895 on 5/9/18. */
public class ChessFrame extends JFrame implements ActionListener {

  private static final int ACTION_NEW = 1;
  private static final int ACTION_SAVE = 2;
  private static final int ACTION_OPEN = 3;
  ChessBoard board = new ChessBoard();

  public ChessFrame() throws HeadlessException {
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
    return southPanel;
  }

  public static void main(String[] args) {
    // JFrame.setDefaultLookAndFeelDecorated(true);
    ChessFrame chessBoard = new ChessFrame();
    chessBoard.setTitle("Tabit Chess");
    chessBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    chessBoard.setVisible(true);
    Piece piece = new Piece(Name.CASTLE, Side.WHITE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int command = Integer.parseInt(e.getActionCommand());
    switch (command) {
      case ACTION_NEW:
        board.addPiecesToBoard(PieceFactory.getWhitePices());
        board.addPiecesToBoard(PieceFactory.getBlackPices());
        break;
      case ACTION_OPEN:
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if(returnVal== JFileChooser.APPROVE_OPTION)
          makeBoardFromInputFile(fc.getSelectedFile());
        break;
      case ACTION_SAVE:
        break;
    }
  }

  private void makeBoardFromInputFile(File selectedFile) {

  }
}
