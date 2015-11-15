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
import android.widget.TextView;

import org.mjjm.phraseword.variation1.VariationOneTestScreenActivity;

public class UnlockedScreenActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE_TIME_ARR = "TIME_ARR";
    private Intent intent;
    private Context context;

    private TextView timeText, mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlocked_screen);
        intent = getIntent();
        context = this.getApplicationContext();
        int[] timeArr = intent.getIntArrayExtra(EXTRA_MESSAGE_TIME_ARR);

        int minutes = timeArr[0];
        int seconds = timeArr[1];
        int millis = timeArr[2];

        timeText = (TextView) findViewById(R.id.timeText);
        timeText.setText("" + minutes + ":" + String.format("%02d", seconds) + ":" + String.format("%03d", millis));

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
