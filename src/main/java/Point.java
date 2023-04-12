public class Point {
    private int row;
    private int column;

    public Point(int row, int col) {
        this.row = row;
        this.column = col;
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
        return "Point{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
