/*
 * Developed by Artyom Ivanyutin on 21.04.19 12:54.
 * Copyright (c) 2019.
 * All rights reserved.
 */

import aivanutin.Universe;
import aivanutin.Utility;

public class Main {

    public static void main(String[] args) {

        final int UNIVERSE_X = 1200;
        final int UNIVERSE_Y = 800;
        Universe world = new Universe(UNIVERSE_Y, UNIVERSE_X);
        long frame = 0;
        long start;

        world.randomize(0.5);
        while (true) {
            start = System.currentTimeMillis();
            Utility.pause();
            world.draw(frame);
            world.countUniverse();
            frame = System.currentTimeMillis() - start;
        }
    }
}

