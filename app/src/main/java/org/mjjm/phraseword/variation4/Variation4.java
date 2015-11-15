package org.mjjm.phraseword.variation4;

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
import org.mjjm.phraseword.Utilities;
import org.mjjm.phraseword.variation2.VariationTwoTestScreenActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Variation4 extends AppCompatActivity {

    public final static String EXTRA_MESSAGE_PHRASE = "org.mjjm.phraseword.variation4.PHRASE";
    public final static String EXTRA_MESSAGE_PASS = "org.mjjm.phraseword.variation4.PASS";

    private EditText editCharCode;
    private TextView textProceed;
    private Context context;
    private String[] wordArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variation4);

        context = this.getApplicationContext();
        editCharCode = (EditText) findViewById(R.id.editCharCode);

        wordArr = initWords("wordlist.txt");
        textProceed = (TextView) findViewById(R.id.textProceed);
        textProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VariationFourTestScreenActivity.class);

                String charCode = editCharCode.getText().toString();
                if (validateCharChode(charCode)) {

                    String correctPass = generateCorrectPass(charCode.length());

                    String phrase = buildWords(wordArr, correctPass, charCode);

                    intent.putExtra(EXTRA_MESSAGE_PHRASE, phrase);
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
    private boolean validateCharChode(String s) {

        if(s == null || s.equals("")) {
            Utilities.showMessage("Your character code must not be null or empty string.", getBaseContext());
            return false;
        } else {
            if(s.length() < 4 || s.length() > 6) {
                Utilities.showMessage("Your character code must be composed of 4 to 6 characters.", getBaseContext());
                return false;
            }


            if(s.matches("[-+]?\\d*\\.?\\d+")) {
                Utilities.showMessage("Your character code must be composed of letters only.", getBaseContext());
                return false;
            }

        }

        return true;
    }

    private String buildWords(String[] words, String correctPass, String charCode) {

        StringBuilder sb = new StringBuilder();

        int MIN = 0;
        int MAX = words.length;

        if(MAX > 0) {

            int counter = 0;
            while(counter < correctPass.length()) {
                String randomWord = words[Utilities.generateRandomInt(MIN, MAX) - 1];
                String ind = "" + correctPass.charAt(counter);
                int index = Integer.parseInt(ind) - 1;

                if(index < randomWord.length()) {
                    if (randomWord.charAt(index) == charCode.charAt(counter) && countOccurrence(randomWord, ""+charCode.charAt(counter)) == 1) {
                        sb.append(randomWord);
                        sb.append(" ");
                        counter++;
                    }
                }

            }
        }


        return sb.toString();
    }

    private int countOccurrence(String s, String c) {

        return (s.length() - s.replace(c, "").length());
    }


    public String[] initWords(String file) {

        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        //get all the lines from wordlist.txt
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(file)));
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("#");
            }

        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString().split("#");
    }

    private String generateCorrectPass(int length) {

        StringBuilder sb = new StringBuilder();
        int MIN = 0;
        int MAX = 5;

        for(int i = 0; i < length; i++) {
            sb.append(Utilities.generateRandomInt(MIN, MAX));
        }


        return sb.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_variation4, menu);
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
