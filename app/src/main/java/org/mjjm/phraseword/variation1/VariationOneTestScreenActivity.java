package org.mjjm.phraseword.variation1;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.mjjm.phraseword.R;
import org.mjjm.phraseword.UnlockedScreenActivity;

import java.util.Arrays;
import java.util.List;

public class VariationOneTestScreenActivity extends AppCompatActivity {


    public final static String EXTRA_MESSAGE_TIME_ARR = "TIME_ARR";

    private TextView textRandCode;
    private EditText editPasscode;
    private Intent intent;

    private Context context;

    private long startTime = 0L;
    private Handler handler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    int seconds = 0, minutes = 0, millis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variation_one_test_screen);

        context = this.getApplicationContext();
        intent = getIntent();
        textRandCode = (TextView) findViewById(R.id.textRandCode);
        textRandCode.setText(intent.getStringExtra(Variation1.EXTRA_MESSAGE_CODE));

        editPasscode = (EditText) findViewById(R.id.editPasscode);
        editPasscode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i == EditorInfo.IME_ACTION_DONE) {
                    if (validatePasscode(editPasscode.getText().toString())) {
                        pauseTimer();
                        //Intent intent = new Intent(context, UnlockedScreenActivity.class);
                        intent.setClass(context, UnlockedScreenActivity.class);
                        intent.putExtra(EXTRA_MESSAGE_TIME_ARR, new int[]{minutes, seconds, millis});

                        startActivity(intent);
                    } else {
                        editPasscode.setText("");
                    }

                    return true;
                }

                return false;
            }
        });


        startTimer();
    }

    private void startTimer() {

        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(updateTimerThread, 0);

    }

    private void pauseTimer() {

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

    private boolean validatePasscode(String s) {

        if(s != null && !s.contains(" ")) {
            if(s.equals(intent.getStringExtra(Variation1.EXTRA_MESSAGE_PASS))) {
                return true;
            }
        }

        return false;
    }

    /**
     * convenience method for displaying Toast
     * @param message
     */
    private void showMessage(String message) {
        Toast msg = Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG);
        msg.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_variation_one_test_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
