package org.mjjm.phraseword;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mjjm.phraseword.variation1.Variation1;
import org.mjjm.phraseword.variation2.Variation2;
import org.mjjm.phraseword.variation3.Variation3;
import org.mjjm.phraseword.variation4.Variation4;

public class TestFormActivity extends AppCompatActivity {

    private Context context;
    private TextView textVar1, textVar2, textVar3, textVar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_form);

        context = this.getApplicationContext();

        textVar1 = (TextView) findViewById(R.id.textVar1);
        textVar1.setOnClickListener(onClickListener);
        textVar2 = (TextView) findViewById(R.id.textVar2);
        textVar2.setOnClickListener(onClickListener);
        textVar3 = (TextView) findViewById(R.id.textVar3);
        textVar3.setOnClickListener(onClickListener);
        textVar4 = (TextView) findViewById(R.id.textVar4);
        textVar4.setOnClickListener(onClickListener);


    }


    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.textVar1:
                    goToScreen(Variation1.class);
                    break;
                case R.id.textVar2:
                    goToScreen(Variation2.class);
                    break;
                case R.id.textVar3:
                    goToScreen(Variation3.class);
                    break;
                case R.id.textVar4:
                    goToScreen(Variation4.class);
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
        getMenuInflater().inflate(R.menu.menu_test_form, menu);
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
