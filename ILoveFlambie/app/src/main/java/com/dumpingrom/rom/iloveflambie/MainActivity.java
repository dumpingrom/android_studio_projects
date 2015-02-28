package com.dumpingrom.rom.iloveflambie;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends Activity {
    Button btn = null;
    TextView txt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button);
        txt = (TextView)findViewById(R.id.sentence);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(setSentenceContent());
            }
        });
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

    private String setSentenceContent(){
        Resources res = getResources();
        String[] adjectifs = res.getStringArray(R.array.adjectifs);
        String[] complements = res.getStringArray(R.array.complements);

        int mini = 0;
        int maxi = adjectifs.length;

        int minj = 0;
        int maxj = complements.length;

        Random r1 = new Random();
        Random r2 = new Random();
        int i = r1.nextInt(maxi);
        int j = r2.nextInt(maxj);

        String result = "Tu es " + adjectifs[i] + " comme " + complements[j];

        return result;
    }
}
