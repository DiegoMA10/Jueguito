package com.example;

import java.net.URL;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[25];

    public Sound(){
        soundURL[0] = getClass().getResource("/com/example/sound/battleTheme.wav");
        soundURL[1] = getClass().getResource("/com/example/sound/townTheme.wav");
        soundURL[2] = getClass().getResource("/com/example/sound/doorOpen.wav");
        soundURL[3] = getClass().getResource("/com/example/sound/menuTing.wav");
        soundURL[4] = getClass().getResource("/com/example/sound/goodNight.wav");
        soundURL[5] = getClass().getResource("/com/example/sound/DCMenuTing.wav");
        soundURL[6] = getClass().getResource("/com/example/sound/preludeTheme.wav");
        soundURL[7] = getClass().getResource("/com/example/sound/BFBuyingSelling.wav");
        soundURL[8] = getClass().getResource("/com/example/sound/swordSlash.wav");
        soundURL[9] = getClass().getResource("/com/example/sound/saveTing.wav");
        soundURL[10] = getClass().getResource("/com/example/sound/battleThemeFF6.wav");
        soundURL[11] = getClass().getResource("/com/example/sound/victoryFanfare.wav");
        soundURL[12] = getClass().getResource("/com/example/sound/gameOver.wav");
        soundURL[13] = getClass().getResource("/com/example/sound/piro.wav");
        soundURL[14] = getClass().getResource("/com/example/sound/electro.wav");
        soundURL[15] = getClass().getResource("/com/example/sound/hielo.wav");
        soundURL[16] = getClass().getResource("/com/example/sound/cureInMenu.wav");
        soundURL[17] = getClass().getResource("/com/example/sound/monsterDeath.wav");
        soundURL[18] = getClass().getResource("/com/example/sound/7DHealingSound.wav");
        soundURL[19] = getClass().getResource("/com/example/sound/C1Encounter.wav");
        soundURL[20] = getClass().getResource("/com/example/sound/error.wav");
      
    }


    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
           
        }
    }

  public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void setVolume(float volume) {
        if (clip != null && clip.isOpen()) {
            try {
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                volumeControl.setValue(dB);
            } catch (IllegalArgumentException e) {
                System.err.println("Master Gain control not supported. Available controls: " + Arrays.toString(clip.getControls()));
            }
        }
    }
}
