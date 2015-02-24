package com.example.rom.testgeneratingviews;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

class Product{
    public String name;
    public String description;
    public String price;
}



public class MainActivity extends ActionBarActivity {
    ScrollView scr = null;
    LinearLayout ll = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scr = new ScrollView(this);

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            try {
                InputStream is = getApplicationContext().getAssets().open("products.xml");

                xpp.setInput(is, null);

                scr.addView(parseXml(xpp));
            } catch(IOException e){
                e.printStackTrace();
            }
        } catch(XmlPullParserException e){
            e.printStackTrace();
        }
        setContentView(scr);
    }

    private LinearLayout parseXml(XmlPullParser xpp) throws XmlPullParserException, IOException{

        int e = xpp.getEventType();

        LinearLayout content = new LinearLayout(this);
        TextView txt = null;

        Product currentProduct = null;

        while(e != XmlPullParser.END_DOCUMENT){
            String name = xpp.getName();
            switch(e){
                case XmlPullParser.START_DOCUMENT:
                    txt = new TextView(this);
                    txt.setText("Start Doc");
                    txt.setWidth(500);
                    txt.setHeight(100);
                    txt.setBackgroundColor(Color.YELLOW);
                    txt.setId(View.generateViewId());
                    content.addView(txt);
                    break;
                case XmlPullParser.START_TAG:
                    txt = new TextView(this);
                    txt.setText("Tag: " + name);
                    txt.setWidth(500);
                    txt.setHeight(100);
                    txt.setBackgroundColor(Color.GREEN);
                    txt.setId(View.generateViewId());
                    content.addView(txt);
                    break;
                case XmlPullParser.TEXT:
                    txt = new TextView(this);
                    txt.setText(xpp.getText());
                    txt.setWidth(500);
                    txt.setHeight(100);
                    txt.setBackgroundColor(Color.RED);
                    txt.setId(View.generateViewId());
                    content.addView(txt);
                    break;
                case XmlPullParser.END_TAG:
                    txt = new TextView(this);
                    txt.setText("End Tag :" + name);
                    txt.setWidth(500);
                    txt.setHeight(100);
                    txt.setBackgroundColor(Color.MAGENTA);
                    txt.setId(View.generateViewId());
                    content.addView(txt);
                    break;
            }
            int lastId = txt.getId();
            e = xpp.next();
        }
        txt = new TextView(this);
        txt.setText("End Doc");
        txt.setWidth(500);
        txt.setHeight(30);
        txt.setBackgroundColor(Color.WHITE);
        content.addView(txt);
        return content;
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
