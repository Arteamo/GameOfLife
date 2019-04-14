package aivanutin;

import java.util.Objects;

class Cell {
    private int x;
    private int y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Cell cell = (Cell) other;
        return ((x == cell.x) && (y == cell.y));
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
//        int result = x;
//        result = 31 * result + y;
//        return result;
    }

    Cell[] localRegion() {
        return new Cell[]{
                new Cell(x - 1, y - 1),
                new Cell(x - 1, y),
                new Cell(x - 1, y + 1),
                new Cell(x, y - 1),
                new Cell(x, y),
                new Cell(x, y + 1),
                new Cell(x + 1, y - 1),
                new Cell(x + 1, y),
                new Cell(x + 1, y + 1),
        };
    }
}
