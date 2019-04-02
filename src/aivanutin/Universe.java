package aivanutin;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.lang.Math;

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
        StdDraw.setXscale(0, width - 1);
        StdDraw.setYscale(0, height - 1);
        StdDraw.enableDoubleBuffering();
    }

    public void Randomize(double generationKey) {
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                gen[i][j] = (int) (Math.round(Math.random()-generationKey));
            }
        }
    }

    private int GetNeibourghs(int i, int j) {
        int cnt = 0;
        if (gen[i - 1][j - 1] == 1) cnt++;
        if (gen[i - 1][j] == 1) cnt++;
        if (gen[i - 1][j + 1] == 1) cnt++;
        if (gen[i][j - 1] == 1) cnt++;
        if (gen[i][j + 1] == 1) cnt++;
        if (gen[i + 1][j - 1] == 1) cnt++;
        if (gen[i + 1][j] == 1) cnt++;
        if (gen[i + 1][j + 1] == 1) cnt++;

        return cnt;
    }

    public void GetNextGen() {
        int neibourghs;
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                neibourghs = GetNeibourghs(i, j);
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
        String time = "Frame: " + Frame + "ms";
        String fps = "Fps: " + (int) (1000.0 / Frame);
        StdDraw.textLeft(20, 20, time);
        StdDraw.textLeft(20, 40, fps);
        StdDraw.show();
    }
}
