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
import android.widget.Toast;

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


    public final static String EXTRA_MESSAGE_WORDS = "org.mjjm.phraseword.variation2.WORDS";
    public final static String EXTRA_MESSAGE_PASS = "org.mjjm.phraseword.variation2.PASS";

    private EditText editNumcode;
    private Button testBtn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variation2);

        context = this.getApplicationContext();
        editNumcode = (EditText) findViewById(R.id.editNumCode);
        testBtn = (Button) findViewById(R.id.testBtn);

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VariationTwoTestScreenActivity.class);

                String numCode = editNumcode.getText().toString();
                if(validateNumCode(numCode)) {

                    String randWords = buildWords(numCode.length());

                    if(randWords.equals("")) {
                        showMessage("Cannot generate random words. Please try again.");
                    } else {

                        List<String> words = Arrays.asList(randWords.split(" "));

                        String correctPass = generateCorrectPass(words, numCode);

                        intent.putExtra(EXTRA_MESSAGE_WORDS, randWords);
                        intent.putExtra(EXTRA_MESSAGE_PASS, correctPass);
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
            showMessage("Your number code must no be null or empty string.");
            return false;
        } else {
            if(s.length() < 4) {
                showMessage("Your number code must be composed of 4 to 6 digits.");
                return false;
            }

            try {
                int code = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                showMessage("Your number code must contain numbers only.");
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
    private String buildWords(int numOfWords) {

        String randWords = "";

        BufferedReader reader = null;
        List<String> lines = new ArrayList<String>();

        //get all the lines from wordlist.txt
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("wordlist.txt")));

            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
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

        int MIN = 0;
        int MAX = lines.size();

        if(MAX > 0) {

            for(int i = 0; i < numOfWords; i++) {
                randWords += lines.get(Utilities.generateRandomInt(MIN, MAX) - 1);

                if(i != numOfWords - 1) {
                    randWords += " ";
                }
            }
        }


        return randWords;
    }

    /**
     * generates the correct pass from list of words and the input code
     * @param words
     * @param inputCode
     * @return string
     */
    private String generateCorrectPass(List<String> words, String inputCode) {

        String correctPass = "";

        for(int i = 0; i < words.size(); i++) {
            String ind = "" + inputCode.charAt(i);
            int index = Integer.parseInt(ind);
            correctPass += words.get(i).charAt(index - 1); //subtracted 1 because string's index starts from zero
        }

        return correctPass;
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
