package org.mjjm.phraseword;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.mjjm.phraseword.variation1.Variation1;
import org.mjjm.phraseword.variation1.VariationOneTestScreenActivity;
import org.mjjm.phraseword.variation2.Variation2;
import org.mjjm.phraseword.variation2.VariationTwoTestScreenActivity;
import org.mjjm.phraseword.variation3.Variation3;
import org.mjjm.phraseword.variation3.VariationThreeTestScreen;
import org.mjjm.phraseword.variation4.Variation4;
import org.mjjm.phraseword.variation4.VariationFourTestScreenActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class LockScreenActivity extends AppCompatActivity {


    public final static String EXTRA_MESSAGE_VAR = "org.mjjm.phraseword.variation1.VAR";
    public final static String EXTRA_MESSAGE_COUNT = "COUNT";

    private int variation;
    private Intent intent;
    private Context context;
    private String[] wordArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        intent = getIntent();
        context = this.getApplicationContext();

        if(intent.getIntExtra(EXTRA_MESSAGE_COUNT, -1) == -1) {
                intent.putExtra(EXTRA_MESSAGE_COUNT, 0);
        }

        variation = intent.getIntExtra(EXTRA_MESSAGE_VAR, 1);

        if(variation == 2 || variation == 4) {

            wordArr = initWords("wordlist.txt");
        }

        ((SlideButton) findViewById(R.id.slideUnlockButton)).setSlideButtonListener(new SlideButton.SlideButtonListener() {
            @Override
            public void handleSlide() {
                switch (variation) {
                    case 1:
                        intent.setClass(context, VariationOneTestScreenActivity.class);
                        String phrase = intent.getStringExtra(Variation1.EXTRA_MESSAGE_PHRASE);
                        List<String> words = Arrays.asList(phrase.split(" "));

                        String randCode = Variation1.generateRandomCode(words);
                        String correctPass = Variation1.generateCorrectPass(words, randCode);
                        intent.putExtra(Variation1.EXTRA_MESSAGE_CODE, randCode);
                        intent.putExtra(Variation1.EXTRA_MESSAGE_PASS, correctPass);

                        break;
                    case 2:
                        intent.setClass(context, VariationTwoTestScreenActivity.class);

                        String numCode = intent.getStringExtra(Variation2.EXTRA_MESSAGE_NUMCODE);
                        String randWords = Variation2.buildWords(numCode.length(), wordArr);

                        List<String> words2 = Arrays.asList(randWords.split(" "));

                        String correctPass2 = Variation2.generateCorrectPass(words2, numCode);

                        intent.putExtra(Variation2.EXTRA_MESSAGE_WORDS, randWords);
                        intent.putExtra(Variation2.EXTRA_MESSAGE_PASS, correctPass2);

                        break;
                    case 3:
                        intent.setClass(context, VariationThreeTestScreen.class);
                        String numCode3 = intent.getStringExtra(Variation3.EXTRA_MESSAGE_CODE);
                        String randChars = Variation3.generateRandomChars(numCode3.length());
                        intent.putExtra(Variation3.EXTRA_MESSAGE_CHARS, randChars);

                        break;
                    case 4:
                        intent.setClass(context, VariationFourTestScreenActivity.class);
                        String charCode = intent.getStringExtra(Variation4.EXTRA_MESSAGE_CHARCODE);
                        String correctPass4 = Variation4.generateCorrectPass(charCode.length());

                        String phrase4 = Variation4.buildWords(wordArr, correctPass4, charCode);

                        intent.putExtra(EXTRA_MESSAGE_VAR, 4);
                        intent.putExtra(Variation4.EXTRA_MESSAGE_PHRASE, phrase4);
                        intent.putExtra(Variation4.EXTRA_MESSAGE_PASS, correctPass4);

                        break;
                }


                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lock_screen, menu);
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
}
