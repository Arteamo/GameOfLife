/*
 * Developed by Artyom Ivanyutin on 21.04.19 12:54.
 * Copyright (c) 2019.
 * All rights reserved.
 */

package aivanutin;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Universe {
    private int width;
    private int height;
    private int[][] gen1;
    private int[][] gen2;

    public Universe(int height, int width) {
        this.width = width;
        this.height = height;
        gen1 = new int[height][width];
        gen2 = new int[height][width];
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.enableDoubleBuffering();
    }

    public void randomize(double density) {
        double chance;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                chance = Math.random();
                if (chance >= (1 - density)) {
                    gen1[i][j] = 1;
                }
            }
        }
    }

    public void createGlider(int i, int j) {
        gen1[i][j] = 1;
        gen1[i + 1][j + 1] = 1;
        gen1[i + 1][j + 2] = 1;
        gen1[i][j + 2] = 1;
        gen1[i - 1][j + 2] = 1;
    }

    private int getNeighbours(int iTop, int iMid, int iBot, int jLeft, int jMid, int jRight) {
        int cnt = 0;
        cnt += gen1[iTop][jLeft];
        cnt += gen1[iTop][jMid];
        cnt += gen1[iTop][jRight];
        cnt += gen1[iMid][jLeft];
        cnt += gen1[iMid][jRight];
        cnt += gen1[iBot][jLeft];
        cnt += gen1[iBot][jMid];
        cnt += gen1[iBot][jRight];
        return cnt;
    }

    public void countUniverse() {
        int neighbours;
        for (int j = 1; j < width - 1; j++) {
            neighbours = getNeighbours(height - 1, 0, 1, j - 1, j, j + 1);
            generateNextGen(neighbours, 0, j);
        }
        for (int j = 1; j < width - 1; j++) {
            neighbours = getNeighbours(height - 2, height - 1, 0, j - 1, j, j + 1);
            generateNextGen(neighbours, height - 1, j);
        }
        for (int i = 1; i < height - 1; i++) {
            neighbours = getNeighbours(i - 1, i, i + 1, width - 1, 0, 1);
            generateNextGen(neighbours, i, 0);
        }
        for (int i = 1; i < height - 1; i++) {
            neighbours = getNeighbours(i - 1, i, i + 1, width - 2, width - 1, 0);
            generateNextGen(neighbours, i, width - 1);
        }
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                neighbours = getNeighbours(i - 1, i, i + 1, j - 1, j, j + 1);
                generateNextGen(neighbours, i, j);
            }
        }
        countCorners();
        update();
    }

    private void countCorners() {
        int neighbours;
        neighbours = getNeighbours(height - 1, 0, 1, width - 1, 0, 1);
        generateNextGen(neighbours, 0, 0);
        neighbours = getNeighbours(height - 1, 0, 1, width - 2, width - 1, 0);
        generateNextGen(neighbours, 0, width - 1);
        neighbours = getNeighbours(height - 2, height - 1, 0, width - 1, 0, 1);
        generateNextGen(neighbours, height - 1, 0);
        neighbours = getNeighbours(height - 2, height - 1, 0, width - 2, width - 1, 0);
        generateNextGen(neighbours, height - 1, width - 1);
    }

    private void generateNextGen(int neighbours, int i, int j) {
        if (((gen1[i][j] == 1) && (neighbours == 2)) || (neighbours == 3)) {
            gen2[i][j] = 1;
        } else {
            gen2[i][j] = 0;
        }
    }

    private void update() {
        int[][] tmp = gen1;
        gen1 = gen2;
        gen2 = tmp;
    }

    public void draw(long frame) {
        StdDraw.clear(Color.WHITE);
        StdDraw.setPenColor(Color.BLACK);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (gen1[i][j] == 1) {
                    StdDraw.filledRectangle(j, i, 0.5, 0.5);
                }
            }
        }
        Utility.showFps(frame);
        StdDraw.show();
    }
}