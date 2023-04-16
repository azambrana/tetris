package model;

import java.util.Objects;

public class Point implements Cloneable {
    private int row;
    private int column;

    public Point(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow() {

        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int addRow(int offset) {
        this.row = this.row + offset;
        return this.row;
    }

    public int addColumn(int offset) {
        this.column = this.column + offset;
        return this.column;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "(" + row +
                "," + column +
                ')';
    }

    @Override
    public Point clone() {
        return new Point(row, column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return row == point.row && column == point.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
