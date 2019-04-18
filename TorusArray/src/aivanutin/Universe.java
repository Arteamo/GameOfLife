package aivanutin;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Universe {
    private int width;
    private int height;
    private int state;
    private int[][][] gen;

    public Universe(int height, int width) {
        this.width = width;
        this.height = height;
        this.state = 0;
        gen = new int[height][width][2];
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
                    gen[i][j][state] = 1;
                }
            }
        }
    }

    public void createGlider(int i, int j) {
        gen[i][j][0] = 1;
        gen[i + 1][j + 1][0] = 1;
        gen[i + 1][j + 2][0] = 1;
        gen[i][j + 2][0] = 1;
        gen[i - 1][j + 2][0] = 1;
    }

    private int getNeighbours(int iTop, int iMid, int iBot, int jLeft, int jMid, int jRight) {
        int cnt = 0;
        cnt += gen[iTop][jLeft][state];
        cnt += gen[iTop][jMid][state];
        cnt += gen[iTop][jRight][state];
        cnt += gen[iMid][jLeft][state];
        cnt += gen[iMid][jRight][state];
        cnt += gen[iBot][jLeft][state];
        cnt += gen[iBot][jMid][state];
        cnt += gen[iBot][jRight][state];
        return cnt;
    }

    private int getNeighboursTorus(int i, int j) {
        int cnt;
        if (i == 0) {
            if (j == 0) {
                cnt = getNeighbours(height - 1, 0, i + 1, width - 1, 0, j + 1);
            } else if (j == width - 1) {
                cnt = getNeighbours(height - 1, 0, i + 1, j - 1, j, 0);
            } else {
                cnt = getNeighbours(height - 1, 0, i + 1, j - 1, j, j + 1);
            }
        } else if (i == height - 1) {
            if (j == 0) {
                cnt = getNeighbours(i - 1, i, 0, width - 1, 0, j + 1);
            } else if (j == width - 1) {
                cnt = getNeighbours(i - 1, i, 0, j - 1, j, 0);
            } else {
                cnt = getNeighbours(i - 1, i, 0, j - 1, j, j + 1);
            }
        } else {
            if (j == 0) {
                cnt = getNeighbours(i + 1, i, i - 1, width - 1, 0, 1);
            } else if (j == width - 1) {
                cnt = getNeighbours(i + 1, i, i - 1, j - 1, j, 0);
            } else {
                cnt = getNeighbours(i - 1, i, i + 1, j - 1, j, j + 1);
            }
        }
        return cnt;
    }

    public void getNextGen() {
        int neighbours;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                neighbours = getNeighboursTorus(i, j);
                if (((gen[i][j][state] == 1) && (neighbours == 2)) || (neighbours == 3)) {
                    gen[i][j][state ^ 1] = 1;
                } else {
                    gen[i][j][state ^ 1] = 0;
                }
            }
        }
        update();
    }

    private void update() {
        state = state ^ 1;
    }

    public void draw(long frame) {
        StdDraw.clear(Color.WHITE);
        StdDraw.setPenColor(Color.BLACK);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (gen[i][j][state] == 1) {
                    StdDraw.filledRectangle(j, i, 0.5, 0.5);
                }
            }
        }
        Utility.showFps(frame);
        StdDraw.show();
    }
}