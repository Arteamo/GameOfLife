import aivanutin.Universe;
import aivanutin.Utility;

public class Main {

    public static void main(String[] args) {

        final int UNIVERSE_X = 1200;
        final int UNIVERSE_Y = 800;
        Universe world = new Universe(UNIVERSE_Y, UNIVERSE_X);
        long frame = 0;

        world.randomize(0);
        while (true) {
            Utility.pause();
            long Start = System.currentTimeMillis();
            world.draw(frame);
            world.getNextGen();
            frame = System.currentTimeMillis() - Start;
        }
    }
}

