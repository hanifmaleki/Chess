package de.tabit.chess;

import de.tabit.chess.Piece.Name;
import de.tabit.chess.Piece.Side;
import de.tabit.chess.model.PieceFactory;
import de.tabit.chess.model.PiecePoint;
import de.tabit.chess.view.ChessBoard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by e1528895 on 5/9/18.
 */
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
                board.resetBorad();
                board.addPiecesToBoard(PieceFactory.getWhitePices());
                board.addPiecesToBoard(PieceFactory.getBlackPices());
                break;
            case ACTION_OPEN:
                final JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                File workingDirectory = new File(System.getProperty("user.dir") + "/src/test/resources");
                fc.setCurrentDirectory(workingDirectory);
                int returnVal = fc.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                    makeBoardFromInputFile(fc.getSelectedFile());
                break;
            case ACTION_SAVE:
                final JFileChooser saveFileChooser = new JFileChooser();
                workingDirectory = new File(System.getProperty("user.dir") + "/src/test/resources");
                saveFileChooser.setCurrentDirectory(workingDirectory);
                returnVal = saveFileChooser.showSaveDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                    saveBoradToFile(saveFileChooser.getSelectedFile());
                break;
        }
    }

    private void saveBoradToFile(File selectedFile) {
        List<PiecePoint> piecePoints = board.getCurrentPieces();
        String[] lines = getPieceToStrings(piecePoints);
        try (FileOutputStream fileOutputStream = new FileOutputStream(selectedFile);
             OutputStreamWriter bw = new OutputStreamWriter(
                     fileOutputStream)) {
            for (String line : lines)
                bw.write(line + "\n");
            bw.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String[] getPieceToStrings(List<PiecePoint> piecePoints) {
        String[][] strings = new String[ChessBoard.BOARD_LENGTH][ChessBoard.BOARD_LENGTH];
        piecePoints.forEach(pp -> strings[pp.getPoint().x][pp.getPoint().y] =
                PieceFactory.getSymbol(pp.getPiece()));
        String[] lines = new String[ChessBoard.BOARD_LENGTH+1];
        for (int i = 0; i < ChessBoard.BOARD_LENGTH; i++) {
            StringBuffer line = new StringBuffer(String.valueOf(ChessBoard.BOARD_LENGTH-i));
            line.append(" |");
            for (int j = 0; j < ChessBoard.BOARD_LENGTH; j++) {
                if (strings[i][j] == null)
                    line.append("  ");
                else
                    line.append(strings[i][j]);
                line.append("|");
            }
            lines[i] = line.toString();
        }
        lines[ChessBoard.BOARD_LENGTH] = "   a  b  c  d  e  f  g  h";
        return lines;
    }

    private void makeBoardFromInputFile(File selectedFile) {
        List<PiecePoint> piecePoints = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(selectedFile), "UTF-8"))) {
            br.lines().forEach(s -> piecePoints.addAll(getPiecePointOf(s)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        board.resetBorad();
        board.addPiecesToBoard(piecePoints);
    }

    private List<PiecePoint> getPiecePointOf(String line) {
        List<PiecePoint> piecePoints = new ArrayList<>();
        String[] split = line.split("\\|");
        if (split[0].trim().startsWith("a"))
            return piecePoints;
        Integer x = ChessBoard.BOARD_LENGTH - 1 - (Integer.valueOf(split[0].trim()) - 1);
        for (int i = 1; i < 9; i++) {
            String symbol = split[i].trim();
            if (!symbol.equals("")) {
                Piece piece = PieceFactory.getPieceBySymbol(symbol);
                Point point = new Point(x, i - 1);
                PiecePoint piecePoint = new PiecePoint(piece, point);
                piecePoints.add(piecePoint);
            }
        }
        return piecePoints;
    }
}
