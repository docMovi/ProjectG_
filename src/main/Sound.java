package main;

import entity.Entity;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    GamePanel gp;
    Clip clip;
    Clip clipSE;
    URL soundURL[ ] = new URL[30];

    public Sound (GamePanel gp) {
        this.gp = gp;
        soundURL[0] = getClass().getResource("/sound/BeepBox-Song.wav");
        soundURL[1] = getClass().getResource("/sound/PlayerHit.wav");
        soundURL[2] = getClass().getResource("/sound/EnemyHit.wav");
        soundURL[3] = getClass().getResource("/sound/DoorOpen.wav");
        soundURL[4] = getClass().getResource("/sound/ChestOpen.wav");
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
        System.out.println("666");


    }
    public void loop() {clip.loop(Clip.LOOP_CONTINUOUSLY);}
    public void stop() {
        clip.stop();
    }

}
