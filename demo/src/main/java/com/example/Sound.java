package com.example;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[10];

    public Sound(){
        soundURL[0] = getClass().getResource("/com/example/sound/battleTheme.wav");
        soundURL[1] = getClass().getResource("/com/example/sound/townTheme.wav");
        soundURL[2] = getClass().getResource("/com/example/sound/doorOpen.wav");
        soundURL[3] = getClass().getResource("/com/example/sound/1BItemMenuItng.wav");
        soundURL[4] = getClass().getResource("/com/example/sound/7DHealingSound.wav");
        soundURL[5] = getClass().getResource("/com/example/sound/DCMenuTing.wav");
        soundURL[6] = getClass().getResource("/com/example/sound/preludeTheme.wav");
        soundURL[7] = getClass().getResource("/com/example/sound/BFBuyingSelling.wav");
    }


    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
      public void setVolume(float volume) {
        if (clip != null) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            control.setValue(dB);
        }
    }
}
