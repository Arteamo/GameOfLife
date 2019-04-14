package aivanutin;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BorderlessUniverse {
    private Set<Cell> universe;
    private Map<Cell, Integer> neighbourCounter;

    public BorderlessUniverse() {
        universe = new HashSet<>();
        neighbourCounter = new HashMap<>();
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(0, 800);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.disableDoubleBuffering();
    }

    public void createGlider(int i, int j) {
        universe.add(new Cell(i, j));
        universe.add(new Cell(i + 1, j + 1));
        universe.add(new Cell(i + 1, j + 2));
        universe.add(new Cell(i, j + 2));
        universe.add(new Cell(i - 1, j + 2));
    }

    public void getNextGen() {
        int cnt;
        for (Cell c : universe) {
            getNeighbours(c);
        }
        for (Cell c : neighbourCounter.keySet()) {
            cnt = neighbourCounter.get(c);
            if ((!universe.contains(c)) && (cnt == 3)) {
                universe.add(c);
            } else {
                if ((universe.contains(c)) && !((cnt == 2) || (cnt == 3))) {
                    universe.remove(c);
                }
            }
        }
        neighbourCounter.clear();
    }

    private void getNeighbours(Cell p) {
        int cnt = 0;
        int x;
        int y;
        for (Cell c : p.localRegion()) {
            x = c.getX();
            y = c.getY();
            if (universe.contains(new Cell(x - 1, y - 1))) {
                cnt++;
            }
            if (universe.contains(new Cell(x - 1, y))) {
                cnt++;
            }
            if (universe.contains(new Cell(x - 1, y + 1))) {
                cnt++;
            }
            if (universe.contains(new Cell(x, y - 1))) {
                cnt++;
            }
            if (universe.contains(new Cell(x, y + 1))) {
                cnt++;
            }
            if (universe.contains(new Cell(x + 1, y - 1))) {
                cnt++;
            }
            if (universe.contains(new Cell(x + 1, y))) {
                cnt++;
            }
            if (universe.contains(new Cell(x + 1, y + 1))) {
                cnt++;
            }
            neighbourCounter.put(c, cnt);
            cnt = 0;
        }
    }

    public void draw() {
        StdDraw.clear(Color.WHITE);
        int x;
        int y;
        for (Cell c : universe) {
            x = c.getX();
            y = c.getY();
            StdDraw.filledRectangle(x, y, 0.5, 0.5);
        }
        StdDraw.show();
    }
}