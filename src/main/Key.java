package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

//Klasse für den input des Spielers -> hier wird für tastenschläge gehört und diese dann weiterverwertet
public class Key implements KeyListener {

    public boolean uppress, downpress, leftpress, rightpress, npressed;

    @Override
    public void keyTyped(KeyEvent e) {
        //unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            uppress = true;
        }if(code == KeyEvent.VK_S) {
            downpress = true;
        }if(code == KeyEvent.VK_A) {
            leftpress = true;
        }if(code == KeyEvent.VK_D) {
            rightpress = true;
        }
        if(code == KeyEvent.VK_N) {
            npressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            uppress = false;
        }if(code == KeyEvent.VK_S) {
            downpress = false;
        }if(code == KeyEvent.VK_A) {
            leftpress = false;
        }if(code == KeyEvent.VK_D) {
            rightpress = false;
        }if(code == KeyEvent.VK_N) {
            npressed = false;
        }
    }
}
