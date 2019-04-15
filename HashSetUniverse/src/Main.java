/*
 * Developed by Artyom Ivanyutin on 15.04.19 1:59.
 * Copyright (c) 2019.
 * All rights reserved.
 */

import aivanutin.BorderlessUniverse;

public class Main {

    public static void main(String[] args) {
        BorderlessUniverse universe = new BorderlessUniverse();

        universe.randomize();

        while (true) {
            universe.draw();
            universe.getNextGen();
        }
    }
}
