package aivanutin;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class UI {

    public UI() {
    }

    public static void ShowFps(long Frame) {
        StdDraw.setPenColor(Color.BLUE);
        String time = "Frame: " + Frame + "ms";
        String fps = "Fps: " + (int) (1000.0 / Frame);
        StdDraw.textLeft(10, 785, time);
        StdDraw.textLeft(10, 770, fps);
    }

    public static void Pause() {
        while (StdDraw.isKeyPressed(32)) {
            StdDraw.pause(1);
        }
    }
}
//implement pause with ^