package main;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.event.*;

//Klasse für den input des Spielers -> hier wird für tastenschläge gehört und diese dann weiterverwertet
public class Key implements KeyListener {

    GamePanel gp;

    MouseListener mouseListener;
    public boolean uppress, downpress, leftpress, rightpress, npressed, leftClick;

    public Key(GamePanel gp) {
        this.gp = gp;
        mouse();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            uppress = true;
        }
        if (code == KeyEvent.VK_S) {
            downpress = true;
        }
        if (code == KeyEvent.VK_A) {
            leftpress = true;
        }
        if (code == KeyEvent.VK_D) {
            rightpress = true;
        }
        if (code == KeyEvent.VK_N) {
            npressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
                System.out.println("pause");
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
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

    void mouse() {
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    leftClick = true;
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    leftClick = false;
                }
            }

        });

    }



}
