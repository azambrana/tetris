package util;

import view.piece.Block;
import view.piece.Piece;
import model.Point;

import java.util.Optional;

public class PiecePrinter {

    public static void printPositions(Piece piece, Point[][] positions) {
        Optional<? extends Block>[][] cells = piece.getCells();
        int rows = cells.length;
        int columns = cells[0].length;
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Optional<? extends Block> cell = cells[i][j];
                System.out.print(cell.isPresent()?
                        "["+positions[i][j]+"]" : positions[i][j]);
            }
            System.out.println();
        }
    }

    public static void printPiece(Piece piece) {
        Optional<? extends Block>[][] cells = piece.getCells();
        int rows = cells.length;
        int columns = cells[0].length;

        System.out.println();
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Optional<? extends Block> cell = cells[i][j];
                System.out.print(cell.isPresent()?"X":"0");
            }
            System.out.println();
        }
    }

    public static void prettyPrint(Piece piece) {
        StringBuilder result = new StringBuilder();
        Optional<? extends Block>[][] cells = piece.getCells();
        int rows = cells.length;
        int columns = cells[0].length;
        result.append("╔");
        for (int i = 0; i < columns; i++) {
            result.append("═");
        }
        result.append("╗\n");

        for (int i = 0; i < rows; i++) {
            result.append("║");
            for (int j = 0; j < columns; j++) {
                Optional<? extends Block> cell = cells[i][j];
                if (cell.isPresent() ){
                    result.append("░");
                } else {
                    result.append(" ");
                }
            }
            result.append("║\n");
        }

        result.append("╚");
        for (int i = 0; i < columns; i++) {
            result.append("═");
        }
        result.append("╝\n");

        System.out.println(result.toString());
    }
}
