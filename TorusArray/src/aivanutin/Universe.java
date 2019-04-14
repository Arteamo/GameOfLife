package aivanutin;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Universe {
    private int width;
    private int height;
    private int[][] gen;
    private int[][] nextGen;

    public Universe(int height, int width) {
        this.width = width;
        this.height = height;
        gen = new int[height][width];
        nextGen = new int[height][width];
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.enableDoubleBuffering();
    }

    public void randomize(double generationKey) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gen[i][j] = (int) (Math.round(Math.random() - generationKey));
            }
        }
    }

    public void createGlider(int x) {
        gen[x][x] = 1;
        gen[x + 1][x + 1] = 1;
        gen[x + 1][x + 2] = 1;
        gen[x][x + 2] = 1;
        gen[x - 1][x + 2] = 1;
    }

    private int getNeighbours(int iTop, int iMid, int iBot, int jLeft, int jMid, int jRight) {
        int cnt = 0;
        cnt += gen[iTop][jLeft];
        cnt += gen[iTop][jMid];
        cnt += gen[iTop][jRight];
        cnt += gen[iMid][jLeft];
        cnt += gen[iMid][jRight];
        cnt += gen[iBot][jLeft];
        cnt += gen[iBot][jMid];
        cnt += gen[iBot][jRight];
        return cnt;
    }

    private int getNeighboursTorus(int i, int j) {
        int cnt = 0;
        if ((i != 0) && (i != height - 1) && (j != 0) && (j != width - 1)) {
            cnt = getNeighbours(i - 1, i, i + 1, j - 1, j, j + 1);
        }
        if ((i == 0) && (j == 0)) {
            cnt = getNeighbours(height - 1, 0, i + 1, width - 1, 0, j + 1);
        }
        if ((i == 0) && ((j != 0) && (j != width - 1))) {
            cnt = getNeighbours(height - 1, 0, i + 1, j - 1, j, j + 1);
        }
        if ((i == 0) && (j == width - 1)) {
            cnt = getNeighbours(height - 1, 0, i + 1, j - 1, 0, 0);
        }
        if (((i != 0) && (i != height - 1)) && (j == 0)) {
            cnt = getNeighbours(i + 1, i, i - 1, width - 1, 0, 1);
        }
        if (((i != 0) && (i != height - 1)) && (j == width - 1)) {
            cnt = getNeighbours(i + 1, i, i - 1, j - 1, j, 0);
        }
        if ((i == height - 1) && (j == 0)) {
            cnt = getNeighbours(i - 1, i, 0, width - 1, 0, j + 1);
        }
        if ((i == height - 1) && ((j != 0) && (j != width - 1))) {
            cnt = getNeighbours(i - 1, i, 0, j - 1, j, j + 1);
        }
        if ((i == height - 1) && (j == width - 1)) {
            cnt = getNeighbours(i - 1, i, 0, j - 1, j, 0);
        }
        return cnt;
    }

    public void getNextGen() {
        int neighbours;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                neighbours = getNeighboursTorus(i, j);
                if (((gen[i][j] == 1) && (neighbours == 2)) || (neighbours == 3)) {
                    nextGen[i][j] = 1;
                } else {
                    nextGen[i][j] = 0;
                }
            }
        }
        update();
    }

    private void update() {
        for (int i = 0; i < height; i++) {
            System.arraycopy(nextGen[i], 0, gen[i], 0, width);
        }
    }

    public void draw(long frame) {
        StdDraw.clear(Color.WHITE);
        StdDraw.setPenColor(Color.BLACK);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (gen[i][j] == 1) {
                    StdDraw.filledRectangle(j, i, 0.5, 0.5);
                }
            }
        }
        Utility.showFps(frame);
        StdDraw.show();
    }
}