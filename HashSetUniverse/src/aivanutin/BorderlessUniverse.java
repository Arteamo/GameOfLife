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

    public void randomize(double density, int size) {
        double chance;
        for (int i = -size; i < size; i++) {
            for (int j = -size; j < size; j++) {
                chance = Math.random();
                if (chance >= (1 - density)) {
                    universe.add(new Cell(i, j));
                }
            }
        }
    }

    public void getNextGen() {
        int cnt;
        getNeighbours();
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

    private void getNeighbours() {
        int cnt;
        int value;
        for (Cell c : universe) {
            for (Cell p : c.localRegion()) {
                if (!neighbourCounter.containsKey(p)) {
                    if (!universe.contains(p)) {
                        value = 1;
                    } else {
                        value = 0;
                    }
                    neighbourCounter.put(p, value);
                } else {
                    cnt = neighbourCounter.get(p);
                    neighbourCounter.put(p, cnt + 1);
                }
            }
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

    public void info(long frame) {
        String time = "Frame: " + frame + "ms ";
        String fps = "Fps: " + (int) (1000.0 / (frame + 1));
        String elements = "Elements: " + universe.size();
        System.out.println(time + " " + fps + " " + elements);
    }
}

