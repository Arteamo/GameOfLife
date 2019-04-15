/*
 * Developed by Artyom Ivanyutin on 15.04.19 20:05.
 * Copyright (c) 2019.
 * All rights reserved.
 */

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
    private int xShift;
    private int yShift;
    private int scale;

    public BorderlessUniverse() {
        universe = new HashSet<>();
        neighbourCounter = new HashMap<>();
        xShift = 0;
        yShift = 0;
        scale = 800;
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(-scale / 2, scale / 2);
        StdDraw.setYscale(-scale / 2, scale / 2);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }

//    public void createGlider(int i, int j) {
//        universe.add(new Cell(i, j));
//        universe.add(new Cell(i + 1, j + 1));
//        universe.add(new Cell(i + 1, j + 2));
//        universe.add(new Cell(i, j + 2));
//        universe.add(new Cell(i - 1, j + 2));
//    }

    public void randomize(double density) {
        double chance;
        for (int i = -200; i < 200; i++) {
            for (int j = -200; j < 200; j++) {
                chance = Math.random();
                if (chance >= (1 - density)) {
                    universe.add(new Cell(i, j));
                }
            }
        }
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

    private void controls() {
        if (StdDraw.isKeyPressed(68)) { // D
            xShift += 10;
        } else {
            if (StdDraw.isKeyPressed(65)) { // A
                xShift -= 10;
            }
        }
        if (StdDraw.isKeyPressed(83)) { // S
            yShift -= 10;
        } else {
            if (StdDraw.isKeyPressed(87)) { // W
                yShift += 10;
            }
        }
        if (StdDraw.isKeyPressed(81)) {  // Q
            scale += 100;
            StdDraw.setXscale(-scale / 2, scale / 2);
            StdDraw.setYscale(-scale / 2, scale / 2);
        } else {
            if (StdDraw.isKeyPressed(69)) { // E
                if (scale > 100) {
                    scale -= 100;
                    StdDraw.setXscale(-scale / 2, scale / 2);
                    StdDraw.setYscale(-scale / 2, scale / 2);
                }
            }
        }
    }

    public void draw() {
        int x;
        int y;
        StdDraw.clear(Color.WHITE);
        controls();
        for (Cell c : universe) {
            x = c.getX();
            y = c.getY();
            StdDraw.filledRectangle(x - xShift, y - yShift, 0.5, 0.5);
        }

        StdDraw.show();
    }

    public void fps(long frame) {
        String time = "Frame: " + frame + "ms ";
        String fps = "Fps: " + (int) (1000.0 / frame);
        System.out.println(time + " " + fps);
    }
}