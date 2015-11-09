package org.mjjm.phraseword;


import java.util.Random;

public class Utilities {

    /**
     * generates a random int in specific range
     * @param min
     * @param max
     * @return int
     */
    public static int generateRandomInt(int min, int max) {
        Random r = new Random();

        return r.nextInt(max - min) + min + 1;
    }
}
