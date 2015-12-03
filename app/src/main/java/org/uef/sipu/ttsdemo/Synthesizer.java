package org.uef.sipu.ttsdemo;

import android.content.Context;
import android.content.res.Resources;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by vano on 1.12.2015.
 */
public class Synthesizer {
    final TextToSpeech textToSpeech;
    final Context context;

    public Synthesizer(final Context context, final TextToSpeech.OnInitListener listener){
        this.context = context;
        textToSpeech = new TextToSpeech(context, listener);
        textToSpeech.setPitch(0.8f);
        textToSpeech.setSpeechRate(1.1f);
    }

    public void speak(final String text, final Locale language){
        int result = textToSpeech.setLanguage(language);

        if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            // language is not supported
            Toast.makeText(context, context.getResources().getString(R.string.warning_not_supported_lang),
                    Toast.LENGTH_LONG).show();
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
        }
    }

}
