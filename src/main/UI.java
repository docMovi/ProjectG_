package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial40;
    Font arial40_k;
    Font arial30;

    Font arial50;

    public boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    int time;
    BufferedImage img;
    BufferedImage imgMain;
    public UI(GamePanel gp) {
        this.gp = gp;
        arial30 = new Font("Arial", Font.BOLD, 30);
        arial40 = new Font("Arial", Font.BOLD, 40);
        arial40_k = new Font("Arial", Font.ITALIC, 40);
        arial50 = new Font("Arial", Font.BOLD, 50);

        try {
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/gameover.png"));
            imgMain = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui/projectG.png"));
        } catch (IOException e) {
        }
    }

    public void showMessage(String text, int sec) {
        message = text;
        messageOn = true;
        time = sec * 60;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial40);
        g2.setColor(Color.lightGray);
        if(gp.gameState != gp.menuState) {
            g2.drawString("health: " + gp.player.hp, 50, 30);
            g2.drawString("x: " + gp.player.x / gp.tile, 50, 70);
            g2.drawString("invincible: " + gp.player.invincCounter, 50, 120);
        }


        if(gp.gameState == gp.menuState){
            //menu screen -> am anfang
            g2.setColor(Color.white);
            g2.setFont(arial50);
            String text = "[LEERTASTE] UM ZU BEGINNEN!";

            int x = gp.tile * 7;
            int y = gp.tile * 10;

            g2.drawImage(imgMain, 5* gp.tile, 3*gp.tile, 14* gp.tile, 4* gp.tile, null);
            g2.drawString(text, x, y);
        }else {

        //message system
        if(messageOn) {
            g2.setFont(arial30);
            g2.setColor(Color.white);
            g2.drawString(message, 8 * gp.tile, 125);

            messageTimer++;
            //message verschwindet nach x sekunden (60 frames = 1 sek)
            if(messageTimer > time) {
                messageTimer = 0;
                messageOn = false;
            }

            if(gp.gameState == gp.playState){


                //alles was nur während des spiels gezeigt werden soll
            }

        }



        if(gp.gameState == gp.pauseState){
            //alles was nur während das spiel pausiert is gezeigt werden soll
            g2.setColor(Color.white);
            g2.setFont(arial50);
            String text = "SPIEL PAUSIERT";
            int x = gp.tile * 10;
            int y = gp.tile * 4;

            g2.drawString(text, x, y);
        }

        if(gp.gameState == gp.gameOverState){
            //game over screen
            g2.setColor(Color.white);
            g2.setFont(arial50);
            String text = "GAME OVER";
            String text2 = "[LEERTASTE] UM NEUZUSTARTEN!";
            String text3 = "DANKE FÜR'S SPIELEN!";
            int x = gp.tile * 10;
            int y = gp.tile * 4;
            int x2 = gp.tile * 7;
            int y2 = gp.tile * 10;

            g2.drawImage(img, 0,0,1920,1080, null);
            g2.drawImage(gp.player.d1, x, y,4*gp.tile,4* gp.tile, null);
            g2.drawString(text, x, y);
            g2.drawString(text3, x2, y + 5 * gp.tile);
            g2.drawString(text2, x2, y2);
        }

        if(gp.gameState == gp.winState){
            //game over screen
            g2.setColor(Color.white);
            g2.setFont(arial50);
            String text = "GESCHAFFT!";
            String text2 = "[LEERTASTE] FÜR'S NÄCHSTE LEVEL!";
            int x = gp.tile * 10;
            int y = gp.tile * 4;
            int x2 = gp.tile * 6;
            int y2 = gp.tile * 10;

            g2.drawImage(img, 0,0,1920,1080, null);
            g2.drawImage(gp.player.win, x, y,4*gp.tile,4* gp.tile, null);
            g2.drawString(text, x, y);
            g2.drawString(text2, x2, y2);

        }

            if(gp.gameState == gp.finalState){
                //game over screen
                g2.setColor(Color.white);
                g2.setFont(arial50);
                String text = "ES IST VORBEI! DU HAST ES GESCHAFFT!";
                String text2 = "EIN SPIEL VON TIM, GABRIEL, MARIO & LUCA!";
                int x = gp.tile * 10;
                int y = gp.tile * 4;
                int x2 = gp.tile * 6;
                int y2 = gp.tile * 10;

                g2.drawImage(img, 0,0,1920,1080, null);
                g2.drawImage(gp.player.win, x, y,4*gp.tile,4* gp.tile, null);
                g2.drawString(text, x2, y);
                g2.drawString(text2, x2 - gp.tile, y2);

            }


        }
    }

}
