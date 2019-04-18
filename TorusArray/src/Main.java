import aivanutin.Universe;
import aivanutin.Utility;

public class Main {

    public static void main(String[] args) {

        final int UNIVERSE_X = 800;
        final int UNIVERSE_Y = 800;
        Universe world = new Universe(UNIVERSE_Y, UNIVERSE_X);
        long frame = 0;
        long start;

        world.randomize(0.5);
        while (true) {
            Utility.pause();
            start = System.currentTimeMillis();
            world.draw(frame);
            world.getNextGen();
            frame = System.currentTimeMillis() - start;
        }
    }
}

