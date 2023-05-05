package main;

import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[ ] = new URL[30];

    public Sound () {
        soundURL[0] = getClass().getResource("/sound/BeepBox-Song.wav");
    }
    public void setFile() {

    }
    public void play() {

    }
    public void loop() {

    }
    public void stop() {

    }

}
