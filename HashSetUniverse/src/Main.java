/*
 * Developed by Artyom Ivanyutin on 15.04.19 20:05.
 * Copyright (c) 2019.
 * All rights reserved.
 */

import aivanutin.BorderlessUniverse;

public class Main {
    public static void main(String[] args) {
        long start;
        long frame;
        BorderlessUniverse universe = new BorderlessUniverse();

        universe.randomize();

        while (true) {
            universe.getNextGen();
            start = System.currentTimeMillis();
            universe.draw();
            frame = System.currentTimeMillis() - start;
            universe.fps(frame);
        }
    }
}
