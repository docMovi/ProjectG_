package entity;

import main.Camera;
import main.GamePanel;
import main.Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Spieler Klasse -> greift auf input management zu und dient zur spieler projektzierung
public class Player extends Entity{
    // tmp https://www.youtube.com/watch?v=wT9uNGzMEM4&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=3
    GamePanel gp; //Zuweisung des GamePanels

    Key key; //verbindung zum input management

    //int sprintSpeed; //tmp falls wir sprint einbauen sollten

    int speedNormal;
    public Player(GamePanel g, Key k) {
        this.gp = g;
        this.key = k;

        setValues();
        getImage();

        collider = new Rectangle(0, 0, gp.tile - (gp.tile/8), gp.tile/2);

    }
    public void setValues() {
        x = 100; //startposition des Spielers: x-koordinate
        y = 100; //startposition des Spielers: y-koordinate
        speedNormal = 6; //spieler geschwindigkeit
        speed = speedNormal; //tmp
        dir = "down"; //start richtung des Spielers: nach unten
        //sprintSpeed = 8; //tmp falls wir sprint einbauen sollten

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
            e.printStackTrace(); //Error Prävention
        }
    }
    public void update() {


        //move input -> greift auf key class zu
        if(key.uppress || key.downpress || key.leftpress || key.rightpress) {
            if(key.uppress == true) {
                y -= speed;
                dir = "up";
            }
            else if(key.downpress) {
                y += speed;
                dir = "down";
            }
            else if(key.rightpress){
                x += speed;
                dir = "right";
            }
            else if(key.leftpress){
                x -= speed;
                dir = "left";
            }


            wait++;
            if(wait > 10) {
                if(num == 1) {
                    num  = 2;
                }else if(num == 2) {
                    num = 1;
                }
                wait = 0;
            }
        }

        collOn = false;
        gp.CDetector.checkTile(this);
        //gp.collisionDet.checkTile(this);

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
