package main;

import java.awt.*;

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
    public UI(GamePanel gp) {
        this.gp = gp;
        arial30 = new Font("Arial", Font.BOLD, 30);
        arial40 = new Font("Arial", Font.BOLD, 40);
        arial40_k = new Font("Arial", Font.ITALIC, 40);
        arial50 = new Font("Arial", Font.BOLD, 50);
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
        g2.drawString("x: " + gp.player.x / gp.tile, 50, 50);

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

    }

}
