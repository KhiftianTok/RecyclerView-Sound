package com.example.pest_repellant;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.pest_repellant.databinding.PestRepellantBinding;

public class PestRepellant extends AppCompatActivity {
    private final int duration = 10;
    private final int sampleRate = 8000;
    private final int numSamples = duration * sampleRate;
    private final double sample[] = new double[numSamples];
    private final byte generatedSnd[] = new byte[2 * numSamples];
    private double freqOfTone = 40;
    private Handler handler = new Handler();
    private PestRepellantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.pest_repellant);

        Intent intent = getIntent();
        String message = intent.getStringExtra("Id");

        if (message.equals("0")){
            setCurrentHama("nyamuk");
        }else if(message.equals("1")){
            setCurrentHama("tikus");
        }else{
            setCurrentHama("kadal");
        }

        binding.playRepellant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Thread thread = new Thread(new Runnable() {
                    public void run() {
                        genTone();
                        handler.post(new Runnable() {
                            public void run() {
                                playSound();
                            }
                        });
                    }
                });
                thread.start();

            }
        });
    }

    private void setCurrentHama(String hama) {
        String packageName = getPackageName();
        int resIdDesc = getResources().getIdentifier("desc_" + hama.toLowerCase(), "string", packageName);
        int freq = Integer.parseInt(getResources().getString(getResources().getIdentifier("freq_" + hama.toLowerCase(), "string", packageName)));
        int resIdIcon = getResources().getIdentifier("ic_" + hama.toLowerCase(), "drawable", packageName);
        binding.imgCurrentHama.setImageResource(resIdIcon);
        binding.tCurrentHama.setText(hama);
        binding.tCurrentHamaDesc.setText(getResources().getString(resIdDesc));
        freqOfTone = (double) freq;
    }

    void genTone() {
        for (int i = 0; i < numSamples; ++i)
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate / freqOfTone));
        int idx = 0;
        for (final double dVal : sample) {
            final short val = (short) ((dVal * 32767));
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }
    }

    void playSound() {
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, numSamples,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();

    }
}
