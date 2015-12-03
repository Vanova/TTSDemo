package org.uef.sipu.ttsdemo;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class MainActivity extends ActionBarActivity implements TextToSpeech.OnInitListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button rusLangBtn = (Button) findViewById(R.id.rusLanguage);
        final Button engLangBtn = (Button) findViewById(R.id.engLanguage);
        final Synthesizer synthesizer = new Synthesizer(this, this);
        final Time nowTime = new Time(Time.getCurrentTimezone());
        nowTime.setToNow();

        rusLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = nowTime.hour + " часов " + nowTime.minute + " минут ";
                Locale locale = new Locale("ru");
                synthesizer.speak(text, locale);
            }
        });

        engLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = nowTime.hour + " hours " + nowTime.minute + " minutes ";
                synthesizer.speak(text, Locale.ENGLISH);
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


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Toast.makeText(this,
                    this.getResources().getString(R.string.info_speech_engine_ready), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, this.getResources().getString(R.string.warning_no_speech_engine),
                    Toast.LENGTH_LONG).show();
        }
    }
}
