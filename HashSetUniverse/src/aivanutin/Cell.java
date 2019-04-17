/*
 * Developed by Artyom Ivanyutin on 15.04.19 20:05.
 * Copyright (c) 2019.
 * All rights reserved.
 */

package aivanutin;

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
        return 31 * x + y;
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
