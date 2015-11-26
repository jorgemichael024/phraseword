package org.mjjm.phraseword;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.mjjm.phraseword.variation1.VariationOneTestScreenActivity;

public class UnlockedScreenActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE_TIME_ARR = "TIME_ARR";
    public final static String EXTRA_MESSAGE_COUNT = "COUNT";
    public final static String EXTRA_MESSAGE_ACCUM = "ACCUM";
    private Intent intent;
    private Context context;

    private LinearLayout unlockScreen1, unlockScreen2;

    private TextView timeText, mainMenu, lockAndProceed, testCountText, averageTimeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlocked_screen);
        intent = getIntent();
        context = this.getApplicationContext();

        int count = intent.getIntExtra(EXTRA_MESSAGE_COUNT, 0);
        count++;
        intent.putExtra(EXTRA_MESSAGE_COUNT, count);



        int[] timeArr = intent.getIntArrayExtra(EXTRA_MESSAGE_TIME_ARR);

        int minutes = timeArr[0];
        int seconds = timeArr[1];
        int millis = timeArr[2];

        timeText = (TextView) findViewById(R.id.timeText);
        timeText.setText("" + minutes + ":" + String.format("%02d", seconds) + ":" + String.format("%03d", millis));


        int accum = intent.getIntExtra(EXTRA_MESSAGE_ACCUM, 0);
        accum += seconds;
        intent.putExtra(EXTRA_MESSAGE_ACCUM, accum);

        testCountText = (TextView) findViewById(R.id.testCountText);
        testCountText.setText(count + " of 5 \nTEST COMPLETED");

        mainMenu = (TextView) findViewById(R.id.mainMenu);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        lockAndProceed = (TextView) findViewById(R.id.lockAndProceed);
        lockAndProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(intent.getIntExtra(EXTRA_MESSAGE_COUNT, 0) < 5) {
                    intent.setClass(context, LockScreenActivity.class);
                    startActivity(intent);
                } else {
                    unlockScreen2.setVisibility(View.GONE);
                    unlockScreen1.setVisibility(View.VISIBLE);
                }
            }
        });


        unlockScreen1 = (LinearLayout) findViewById(R.id.unlockScreen1);
        unlockScreen2 = (LinearLayout) findViewById(R.id.unlockScreen2);
        unlockScreen1.setVisibility(View.GONE);
        unlockScreen2.setVisibility(View.VISIBLE);

        averageTimeText = (TextView) findViewById(R.id.averageTimeText);

        if(intent.getIntExtra(EXTRA_MESSAGE_COUNT, 0) == 5) {
            lockAndProceed.setText("Proceed to Result");

            int average = accum / 5;
            averageTimeText.setText(average + "");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_unlocked_screen, menu);
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
