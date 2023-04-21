package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC extends Entity{


    public NPC(GamePanel gp) {
        this.gp = gp;

        dir = "down";
        speed = 2;
        getImage();
    }

    public void getImage() {
        try {
            //liest files in res/player folder um bilder zu initialisieren
            up1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/up1.png"));
            up2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/up2.png"));
            d1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/down1.png"));
            d2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/down2.png"));
            r1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right1.png"));
            r2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right2.png"));
            l1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left1.png"));
            l2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left2.png"));

            // tmp https://www.youtube.com/watch?v=wT9uNGzMEM4&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=4

        }catch (IOException e){

        }

    }

    public void draw(Graphics2D g2) {
        //hier wird der spieler als bild erstellt
        //spieler bild je nach richtung ändern

        BufferedImage image = null;

        if(dir == "up") {
            if(num == 1) {
                image = up1;
            }
            else if (num == 2) {
                image = up2;
            }
        }else if(dir == "down") {
            if(num == 1) {
                image = d1;
            }
            else if (num == 2) {
                image = d2;
            }
        }else if(dir == "right") {
            if(num == 1) {
                image = r1;
            }
            else if (num == 2) {
                image = r2;
            }
        }else if(dir == "left") {
            if(num == 1) {
                image = l1;
            }
            else if (num == 2) {
                image = l2;
            }
        }
        g2.drawImage(image, x, y, gp.tile, gp.tile, null);
    }
}


    

