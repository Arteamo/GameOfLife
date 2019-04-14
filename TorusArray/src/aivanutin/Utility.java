package aivanutin;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Utility {

    public Utility() {
    }

    static void showFps(long frame) {
        StdDraw.setPenColor(Color.BLUE);
        String time = "Frame: " + frame + "ms";
        String fps = "Fps: " + (int) (1000.0 / frame);
        StdDraw.textLeft(10, 785, time);
        StdDraw.textLeft(10, 770, fps);
    }

    public static void pause() {
        while (StdDraw.isKeyPressed(32)) { /*удерживать пробел для паузы*/
            StdDraw.pause(1);
        }
    }
}