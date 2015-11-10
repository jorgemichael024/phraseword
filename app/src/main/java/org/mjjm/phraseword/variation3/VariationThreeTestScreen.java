package org.mjjm.phraseword.variation3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.mjjm.phraseword.R;
import org.mjjm.phraseword.UnlockedScreenActivity;
import org.mjjm.phraseword.variation2.Variation2;

import java.util.Arrays;
import java.util.List;

public class VariationThreeTestScreen extends AppCompatActivity {

    private EditText editPhraseCode;
    private TextView textRandChars;
    private Context context;
    private Button unlockBtn;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variation_three_test_screen);

        context = this.getApplicationContext();
        intent = getIntent();

        editPhraseCode = (EditText) findViewById(R.id.editPhraseCode);
        textRandChars = (TextView) findViewById(R.id.textRandChars);
        textRandChars.setText(intent.getStringExtra(Variation3.EXTRA_MESSAGE_CHARS));
        unlockBtn = (Button) findViewById(R.id.unlockBtn);

        unlockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePasscode(editPhraseCode.getText().toString())) {
                    Intent intent = new Intent(context, UnlockedScreenActivity.class);
                    startActivity(intent);
                } else {
                    showMessage("Incorrect phrase code.");
                }
            }
        });

    }

    private boolean validatePasscode(String s) {

        if(s != null) {
            String code = intent.getStringExtra(Variation3.EXTRA_MESSAGE_CODE);
            String phraseCode = intent.getStringExtra(Variation3.EXTRA_MESSAGE_CHARS);
            List<String> words = Arrays.asList(s.split(" "));

            for(int i = 0; i < code.length(); i++) {
                String ind = "" + code.charAt(i);
                int index = Integer.parseInt(ind) - 1;

                if(words.get(i).charAt(index) != phraseCode.charAt(i)) {
                    return false;
                }
            }

        } else {
            return false;
        }

        return true;
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
        getMenuInflater().inflate(R.menu.menu_variation_three_test_screen, menu);
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
