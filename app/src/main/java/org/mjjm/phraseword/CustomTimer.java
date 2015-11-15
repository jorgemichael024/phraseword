package org.mjjm.phraseword;

import android.os.Handler;
import android.os.SystemClock;

public class CustomTimer {

    private Handler handler;
    private long startTime = 0L;

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    private int seconds = 0, minutes = 0, millis = 0;

    public CustomTimer(Handler handler) {
        this.handler = handler;
    }

    public void startTimer() {

        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(updateTimerThread, 0);

    }

    public void pauseTimer() {

        timeSwapBuff += timeInMilliseconds;
        handler.removeCallbacks(updateTimerThread);
    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            seconds = (int) (updatedTime / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;
            millis = (int) (updatedTime % 1000);

            handler.postDelayed(this, 0);

        }
    };

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getMillis() {
        return millis;
    }
}
