package entity;

import main.Camera;
import main.GamePanel;
import main.Key;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Spieler Klasse -> greift auf input management zu und dient zur spieler projektzierung
public class Player extends Entity{
    // tmp https://www.youtube.com/watch?v=wT9uNGzMEM4&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=3
    TileManager tileM;
    Key key; //verbindung zum input management

    int standCounter = 0;
    //int sprintSpeed; //tmp falls wir sprint einbauen sollten

    int speedNormal;
    public Player(GamePanel g, Key k, TileManager tileM) {
        super(g);
        this.gp = g;

        this.key = k;
        this.tileM = tileM;

        setValues();
        getImage();

        collider = new Rectangle( x,  y, 45, 50);
    }
    public void setValues() {

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

    boolean executed = false;
    public void setPos(int x, int y) {

        if(!executed) {
            executed = true;
            this.x = x;
            this.y = y;
            System.out.println("been there, done that");
        }
    }

    public void update() {

        //move input -> greift auf key class zu
        if(key.uppress || key.downpress || key.leftpress || key.rightpress) {
            if(key.uppress == true) {
                dir = "up";
            }
            else if(key.downpress) {
                dir = "down";
            }
            else if(key.rightpress){
                dir = "right";
            }
            else if(key.leftpress){
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

            //check von collision des tiles
            collOn = false;
            gp.CDetector.checkTile(this);

            //check von collision mit npcs
            if(gp.NPCspawned) {
                int npcI = gp.CDetector.checkEntity(this, gp.npcs);
                interactNPC(npcI);
            }

            //if collision== false dann kann der player sich bewwegen
            if(collOn == false) {

                if (dir == "up") {
                        y -= speed;
                    }

                if (dir == "down") {
                        y += speed;
                    }
                if (dir == "right") {
                        x += speed;
                    }
                if (dir == "left") {
                        x -= speed;
                    }

                }


            //Tmp
            if(key.npressed) {
                gp.setNPC();
            }
        }

}


    public int checkPos(String coordinate, int number) {
        int value = 0;

        if(coordinate == "x") {
            if(x / gp.tile == number) {
                value = 1;
            }
        }else if (coordinate == "y") {
            if(y / gp.tile == number) {
                value = 1;
            }
        }
        return value;
    }

    public void interactNPC(int i) {
        if(i != 999) {
            System.out.println("Hey here is an NPC!");
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
