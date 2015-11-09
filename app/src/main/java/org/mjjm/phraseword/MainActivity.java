package org.mjjm.phraseword;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.mjjm.phraseword.variation1.Variation1;
import org.mjjm.phraseword.variation2.Variation2;

public class MainActivity extends AppCompatActivity {

    private Button var1_button, var2_button, var3_button, var4_button;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this.getApplicationContext();

        var1_button = (Button) findViewById(R.id.var1_button);
        var1_button.setOnClickListener(onClickListener);
        var2_button = (Button) findViewById(R.id.var2_button);
        var2_button.setOnClickListener(onClickListener);
        var3_button = (Button) findViewById(R.id.var3_button);
        var3_button.setOnClickListener(onClickListener);
        var4_button = (Button) findViewById(R.id.var4_button);
        var4_button.setOnClickListener(onClickListener);


    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.var1_button:
                    goToScreen(Variation1.class);
                    break;
                case R.id.var2_button:
                    goToScreen(Variation2.class);
                    break;
                case R.id.var3_button:
                    break;
                case R.id.var4_button:
                    break;
            }
        }
    };

    public void goToScreen(Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
