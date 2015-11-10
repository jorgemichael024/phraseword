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
import android.widget.Toast;

import org.mjjm.phraseword.R;
import org.mjjm.phraseword.Utilities;
import org.mjjm.phraseword.variation2.VariationTwoTestScreenActivity;

import java.util.Arrays;
import java.util.List;

public class Variation3 extends AppCompatActivity {

    public final static String EXTRA_MESSAGE_CHARS = "org.mjjm.phraseword.variation3.CHARS";
    public final static String EXTRA_MESSAGE_CODE = "org.mjjm.phraseword.variation3.CODE";

    private EditText editNumcode;
    private Button testBtn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variation3);

        context = this.getApplicationContext();
        editNumcode = (EditText) findViewById(R.id.editNumCode);
        testBtn = (Button) findViewById(R.id.testBtn);

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VariationThreeTestScreen.class);

                String numCode = editNumcode.getText().toString();
                if (validateNumCode(numCode)) {

                    String randChars = generateRandomChars(numCode.length());

                    if (randChars.equals("")) {
                        showMessage("Cannot generate random characters. Please try again.");
                    } else {

                        intent.putExtra(EXTRA_MESSAGE_CHARS, randChars);
                        intent.putExtra(EXTRA_MESSAGE_CODE, numCode);
                        startActivity(intent);
                    }

                }
            }
        });
    }

    /**
     * validates the user input
     * @param s
     * @return boolean
     */
    private boolean validateNumCode(String s) {

        if(s == null || s.equals("")) {
            showMessage("Your number code must not be null or empty string.");
            return false;
        } else {
            if(s.length() < 4 || s.length() > 6) {
                showMessage("Your number code must be composed of 4 to 6 digits.");
                return false;
            }

            try {
                int code = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                showMessage("Your code must be composed of numbers only.");
                return false;
            }

        }

        return true;
    }

    private String generateRandomChars(int length) {

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder s = new StringBuilder();

        int MIN = 0;
        int MAX = alphabet.length() - 1;

        for(int i = 0; i < length; i++) {
            int index = Utilities.generateRandomInt(MIN, MAX) - 1;
            s.append(alphabet.charAt(index));
        }

        return s.toString();
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
        getMenuInflater().inflate(R.menu.menu_variation3, menu);
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
