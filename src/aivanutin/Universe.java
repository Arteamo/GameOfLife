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

    public void Randomize(double generationKey) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gen[i][j] = (int) (Math.round(Math.random() - generationKey));
            }
        }
    }

    /*public void CreateGlider(int i, int j) {
        gen[i][j] = 1;
        gen[i + 1][j + 1] = 1;
        gen[i + 1][j + 2] = 1;
        gen[i][j + 2] = 1;
        gen[i - 1][j + 2] = 1;
    }*/

    private int GetNeibourghs(int iTop, int iMid, int iBot, int jLeft, int jMid, int jRight) {
        int cnt = 0;
        if (gen[iTop][jLeft] == 1) {
            cnt++;
        }
        if (gen[iTop][jMid] == 1) {
            cnt++;
        }
        if (gen[iTop][jRight] == 1) {
            cnt++;
        }
        if (gen[iMid][jLeft] == 1) {
            cnt++;
        }
        if (gen[iMid][jRight] == 1) {
            cnt++;
        }
        if (gen[iBot][jLeft] == 1) {
            cnt++;
        }
        if (gen[iBot][jMid] == 1) {
            cnt++;
        }
        if (gen[iBot][jRight] == 1) {
            cnt++;
        }
        return cnt;
    }

    private int GetNeibourghsTorus(int i, int j) {
        int cnt = 0;
        if ((i != 0) && (i != height - 1) && (j != 0) && (j != width - 1)) {
            cnt = GetNeibourghs(i - 1, i, i + 1, j - 1, j, j + 1);
        }
        if ((i == 0) && (j == 0)) {
            cnt = GetNeibourghs(height - 1, 0, i + 1, width - 1, 0, j + 1);
        }
        if ((i == 0) && ((j != 0) && (j != width - 1))) {
            cnt = GetNeibourghs(height - 1, 0, i + 1, j - 1, j, j + 1);
        }
        if ((i == 0) && (j == width - 1)) {
            cnt = GetNeibourghs(height - 1, 0, i + 1, j - 1, 0, 0);
        }
        if (((i != 0) && (i != height - 1)) && (j == 0)) {
            cnt = GetNeibourghs(i + 1, i, i - 1, width - 1, 0, 1);
        }
        if (((i != 0) && (i != height - 1)) && (j == width - 1)) {
            cnt = GetNeibourghs(i + 1, i, i - 1, j - 1, j, 0);
        }
        if ((i == height - 1) && (j == 0)) {
            cnt = GetNeibourghs(i - 1, i, 0, width - 1, 0, j + 1);
        }
        if ((i == height - 1) && ((j != 0) && (j != width - 1))) {
            cnt = GetNeibourghs(i - 1, i, 0, j - 1, j, j + 1);
        }
        if ((i == height - 1) && (j == width - 1)) {
            cnt = GetNeibourghs(i - 1, i, 0, j - 1, j, 0);
        }
        return cnt;
    }

    public void GetNextGen() {
        int neibourghs;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                neibourghs = GetNeibourghsTorus(i, j);
                if (((gen[i][j] == 1) && (neibourghs == 2)) || (neibourghs == 3)) {
                    nextGen[i][j] = 1;
                } else {
                    nextGen[i][j] = 0;
                }
            }
        }
        Update();
    }

    private void Update() {
        for (int i = 0; i < height; i++) {
            System.arraycopy(nextGen[i], 0, gen[i], 0, width);
        }
    }

    public void Draw(long Frame) {
        StdDraw.clear(Color.WHITE);
        StdDraw.setPenColor(Color.BLACK);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (gen[i][j] == 1) {
                    StdDraw.filledRectangle(j, i, 0.5, 0.5);
                }
            }
        }
        Utility.ShowFps(Frame);
        StdDraw.show();
    }
}