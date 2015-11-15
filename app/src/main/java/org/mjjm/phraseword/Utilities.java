package org.mjjm.phraseword;


import android.content.Context;
import android.widget.Toast;

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

    /**
     * convenience method for displaying Toast
     * @param message
     */
    public static void showMessage(String message, Context context) {
        Toast msg = Toast.makeText(context, message, Toast.LENGTH_LONG);
        msg.show();
    }

}
