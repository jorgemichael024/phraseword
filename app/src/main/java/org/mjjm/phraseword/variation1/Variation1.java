package org.mjjm.phraseword.variation1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.mjjm.phraseword.R;
import org.mjjm.phraseword.UnlockedScreenActivity;
import org.mjjm.phraseword.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Variation1 extends AppCompatActivity {

    public final static String EXTRA_MESSAGE_CODE = "org.mjjm.phraseword.variation1.CODE";
    public final static String EXTRA_MESSAGE_PASS = "org.mjjm.phraseword.variation1.PASS";

    private EditText editPhrase;
    private TextView textProceed;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variation1);

        context = this.getApplicationContext();
        editPhrase = (EditText) findViewById(R.id.editPhrase);

        textProceed = (TextView) findViewById(R.id.textProceed);

        textProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VariationOneTestScreenActivity.class);

                String phrase = editPhrase.getText().toString();
                if(validatePhrase(phrase)) {
                    List<String> words = Arrays.asList(phrase.split(" "));

                    String randCode = generateRandomCode(words);
                    String correctPass = generateCorrectPass(words, randCode);

                    intent.putExtra(EXTRA_MESSAGE_CODE, randCode);
                    intent.putExtra(EXTRA_MESSAGE_PASS, correctPass);

                    startActivity(intent);
                }
            }
        });

    }

    /**
     * validates the user input
     * @param s
     * @return boolean
     */
    private boolean validatePhrase(String s) {

        if(s == null || s.equals("")) {
            Utilities.showMessage("Your phrase must not be null or empty string.", getBaseContext());
            return false;
        }
        else {
            List<String> words = Arrays.asList(s.split(" "));

            if(words.size() < 4 || words.size() > 6) {
                Utilities.showMessage("Your phrase must be composed of 4 to 6 words.", getBaseContext());
                return false;
            }

            for(String word : words) {
                if(word.equals("")) {
                    Utilities.showMessage("You had entered multiple spaces between words.", getBaseContext());
                    return false;
                }
            }

        }

        return true;
    }

    /**
     * generates random code (indices) from a list of words
     * @param words
     * @return string
     */
    private String generateRandomCode(List<String> words) {

        StringBuilder sb = new StringBuilder();

        int MIN = 0;
/*        for(int i = 0; i  < words.size(); i++) {
            int MAX = words.get(i).length();

            int random = Utilities.generateRandomInt(MIN, MAX);
            code += random;
        }*/

        for(String word : words) {
            int MAX = word.length();
            int random = Utilities.generateRandomInt(MIN, MAX);
            sb.append(random);

        }

        return sb.toString();
    }

    /**
     * generates the correct pass from list of words and the generated random code (indices)
     * @param words
     * @param randCode
     * @return string
     */
    private String generateCorrectPass(List<String> words, String randCode) {

/*
        String correctPass = "";

        for (int i = 0; i < words.size(); i++) {
            String ind = "" + randCode.charAt(i);
            int index = Integer.parseInt(ind);
            correctPass += words.get(i).charAt(index - 1); //subtracted 1 because string's index starts from zero
        }
*/

        StringBuilder sb = new StringBuilder();

        int j = 0;
        for(String word : words) {

            String ind = "" + randCode.charAt(j);
            int index = Integer.parseInt(ind);
            sb.append(word.charAt(index -1));
            j++;
        }

        return sb.toString();
        //return correctPass;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_variation1, menu);
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
