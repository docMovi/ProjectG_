package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    GamePanel gp;
    Clip clip;
    URL soundURL[ ] = new URL[30];

    public Sound (GamePanel gp) {
        this.gp = gp;
        soundURL[0] = getClass().getResource("/sound/BeepBox-Song.wav");
    }
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e){}

    }
    public void play() {
        clip.start();
    }
    public void loop() {clip.loop(Clip.LOOP_CONTINUOUSLY);}
    public void stop() {
        clip.stop();
    }

}
