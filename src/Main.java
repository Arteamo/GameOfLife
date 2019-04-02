import aivanutin.Universe;

public class Main {

    public static void main(String[] args) {

        final int UNIVERSE_X = 1200;
        final int UNIVERSE_Y = 800;
        Universe world = new Universe(UNIVERSE_Y, UNIVERSE_X);
        world.Randomize();
        while (true)
        {
            world.Draw();
            world.GetNextGen();
        }
    }
}

