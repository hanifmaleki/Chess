package de.tabit.chess.view;

import de.tabit.chess.model.Piece;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.util.logging.*;

public class PieceImageUtil {

  /**
   * Unicodes for chess pieces.
   */
  static final String[] pieces = {
      "\u2654", "\u2655", "\u2656", "\u2657", "\u2658", "\u2659"
  };

  /*
   * Colors..
   */
  public static final Color outlineColor = Color.DARK_GRAY;
  public static final Color[] pieceColors = {
      new Color(203, 203, 197), new Color(192, 142, 60)
  };


  public static final int WIDTH = 64;

  /*
   * Font. The images use the font sizeXsize.
   */
  static Font font = new Font("Sans-Serif", Font.PLAIN, WIDTH);

  private static ArrayList<Shape> separateShapeIntoRegions(Shape shape) {
    ArrayList<Shape> regions = new ArrayList<Shape>();

    PathIterator pi = shape.getPathIterator(null);
    int ii = 0;
    GeneralPath gp = new GeneralPath();
    while (!pi.isDone()) {
      double[] coords = new double[6];
      int pathSegmentType = pi.currentSegment(coords);
      int windingRule = pi.getWindingRule();
      gp.setWindingRule(windingRule);
      if (pathSegmentType == PathIterator.SEG_MOVETO) {
        gp = new GeneralPath();
        gp.setWindingRule(windingRule);
        gp.moveTo(coords[0], coords[1]);
      } else if (pathSegmentType == PathIterator.SEG_LINETO) {
        gp.lineTo(coords[0], coords[1]);
      } else if (pathSegmentType == PathIterator.SEG_QUADTO) {
        gp.quadTo(coords[0], coords[1], coords[2], coords[3]);
      } else if (pathSegmentType == PathIterator.SEG_CUBICTO) {
        gp.curveTo(
            coords[0], coords[1],
            coords[2], coords[3],
            coords[4], coords[5]);
      } else if (pathSegmentType == PathIterator.SEG_CLOSE) {
        gp.closePath();
        regions.add(new Area(gp));
      } else {
        System.err.println("Unexpected value! " + pathSegmentType);
      }

      pi.next();
    }

    return regions;
  }

  private static BufferedImage getImageForChessPiece(
      int piece, int side, boolean gradient) {
    int sz = font.getSize();
    BufferedImage bi = new BufferedImage(
        sz, sz, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = bi.createGraphics();
    g.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(
        RenderingHints.KEY_DITHERING,
        RenderingHints.VALUE_DITHER_ENABLE);
    g.setRenderingHint(
        RenderingHints.KEY_ALPHA_INTERPOLATION,
        RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

    FontRenderContext frc = g.getFontRenderContext();
    GlyphVector gv = font.createGlyphVector(frc, pieces[piece]);
    //Rectangle2D box1 = gv.getVisualBounds();

    Shape shape1 = gv.getOutline();
    Rectangle r = shape1.getBounds();
    int spaceX = sz - r.width;
    int spaceY = sz - r.height;
    AffineTransform trans = AffineTransform.getTranslateInstance(
        -r.x + (spaceX / 2), -r.y + (spaceY / 2));

    Shape shapeCentered = trans.createTransformedShape(shape1);

    Shape imageShape = new Rectangle2D.Double(0, 0, sz, sz);
    Area imageShapeArea = new Area(imageShape);
    Area shapeArea = new Area(shapeCentered);
    imageShapeArea.subtract(shapeArea);
    ArrayList<Shape> regions = separateShapeIntoRegions(imageShapeArea);
    g.setStroke(new BasicStroke(1));
    g.setColor(pieceColors[side]);
    Color baseColor = pieceColors[side];
    if (gradient) {
      Color c1 = baseColor.brighter();
      Color c2 = baseColor;
      GradientPaint gp = new GradientPaint(
          sz/2-(r.width/4), sz/2-(r.height/4), c1,
          sz/2+(r.width/4), sz/2+(r.height/4), c2,
          false);
      g.setPaint(gp);
    } else {
      g.setColor(baseColor);
    }

    for (Shape region : regions) {
      Rectangle r1 = region.getBounds();
      if (r1.getX() < 0.001 && r1.getY() < 0.001) {
      } else {
        g.fill(region);
      }
    }
    g.setColor(outlineColor);
    g.fill(shapeArea);
    g.dispose();

    return bi;
  }

  public static BufferedImage getBufferedImage(Piece piece) {
    return PieceImageUtil.getImageForChessPiece(
        piece.getName().getValue(), piece.getSide().getValue(), true);
  }


}