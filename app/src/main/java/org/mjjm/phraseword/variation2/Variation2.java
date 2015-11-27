package org.mjjm.phraseword.variation2;

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

import org.mjjm.phraseword.LockScreenActivity;
import org.mjjm.phraseword.MainActivity;
import org.mjjm.phraseword.R;
import org.mjjm.phraseword.Utilities;
import org.mjjm.phraseword.variation1.VariationOneTestScreenActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Variation2 extends AppCompatActivity {


    public final static String EXTRA_MESSAGE_VAR = "org.mjjm.phraseword.variation1.VAR";
    public final static String EXTRA_MESSAGE_WORDS = "org.mjjm.phraseword.variation2.WORDS";
    public final static String EXTRA_MESSAGE_PASS = "org.mjjm.phraseword.variation2.PASS";
    public final static String EXTRA_MESSAGE_NUMCODE = "org.mjjm.phraseword.variation2.NUMCODE";

    private EditText editNumcode;
    private Context context;

    private TextView textProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variation2);

        context = this.getApplicationContext();
        editNumcode = (EditText) findViewById(R.id.editNumCode);


        textProceed = (TextView) findViewById(R.id.textProceed);

        textProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(context, VariationTwoTestScreenActivity.class);

                Intent intent = new Intent(context, LockScreenActivity.class);

                String numCode = editNumcode.getText().toString();
                if(validateNumCode(numCode)) {

/*                    String randWords = buildWords(numCode.length(), wordArr);

                    if(randWords.equals("")) {
                        Utilities.showMessage("Cannot generate random words. Please try again.", getBaseContext());
                    } else {

                        List<String> words = Arrays.asList(randWords.split(" "));

                        String correctPass = generateCorrectPass(words, numCode);

                        intent.putExtra(EXTRA_MESSAGE_VAR, 2);
                        intent.putExtra(EXTRA_MESSAGE_WORDS, randWords);
                        intent.putExtra(EXTRA_MESSAGE_PASS, correctPass);
                        startActivity(intent);
                    }*/

                    intent.putExtra(EXTRA_MESSAGE_VAR, 2);
                    intent.putExtra(EXTRA_MESSAGE_NUMCODE, numCode);
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
    private boolean validateNumCode(String s) {

        if(s == null || s.equals("")) {
            Utilities.showMessage("Your number code must not be null or empty string.", getBaseContext());
            return false;
        } else {
            if(s.length() < 4 || s.length() > 6) {
                Utilities.showMessage("Your number code must be composed of 4 to 6 digits.", getBaseContext());
                return false;
            }

            try {
                int code = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                Utilities.showMessage("Your code must be composed of numbers only.", getBaseContext());
                return false;
            }

        }

        return true;
    }

    /**
     * generates random phrase
     * @param numOfWords
     * @return
     */
    public static String buildWords(int numOfWords, String[] words) {

        String randWords = "";

        int MIN = 0;
        int MAX = words.length;

        StringBuilder sb = new StringBuilder();

        if(MAX > 0) {

            for(int i = 0; i < numOfWords; i++) {
                randWords += words[Utilities.generateRandomInt(MIN, MAX) - 1];

                if(i != numOfWords - 1) {
                    randWords += " ";
                }
            }
        }


        return randWords;
    }


    /*public String[] initWords(String file) {

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
    }*/

    /**
     * generates the correct pass from list of words and the input code
     * @param words
     * @param inputCode
     * @return string
     */
    public static String generateCorrectPass(List<String> words, String inputCode) {
/*

        String correctPass = "";

        for(int i = 0; i < words.size(); i++) {
            String ind = "" + inputCode.charAt(i);
            int index = Integer.parseInt(ind);
            correctPass += words.get(i).charAt(index - 1); //subtracted 1 because string's index starts from zero
        }
*/

        StringBuilder sb = new StringBuilder();

        int j = 0;
        for(String word : words) {
            String ind = "" + inputCode.charAt(j);
            int index = Integer.parseInt(ind);

            sb.append(word.charAt(index - 1));
            j++;
        }

        return sb.toString();
        //return correctPass;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_variation2, menu);
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
