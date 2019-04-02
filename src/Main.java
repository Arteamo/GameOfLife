import aivanutin.Universe;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        final int UNIVERSE_X = 1200;
        final int UNIVERSE_Y = 800;
        Universe world = new Universe(UNIVERSE_Y, UNIVERSE_X);

        long Frame=0;
        world.Randomize();
        while (true) {
            long Start = System.currentTimeMillis();
            world.Draw(Frame);
            world.GetNextGen();
            Frame = System.currentTimeMillis() - Start;
        }
    }
}

