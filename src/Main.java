import aivanutin.UI;
import aivanutin.Universe;

public class Main {

    public static void main(String[] args) {

        final int UNIVERSE_X = 1200;
        final int UNIVERSE_Y = 800;
        Universe world = new Universe(UNIVERSE_Y, UNIVERSE_X);
        UI control = new UI();
        long Frame = 0;

        world.Randomize(0);
        while (true) {
            control.Pause();
            long Start = System.currentTimeMillis();
            world.Draw(Frame);
            world.GetNextGen();
            Frame = System.currentTimeMillis() - Start;
        }
    }
}

