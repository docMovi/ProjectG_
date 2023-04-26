package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC extends Entity{

    Random random;

    public int actionTimer;
    public BufferedImage[] images;

    public NPC(GamePanel gp) {
        super(gp);

        dir = "down";
        speed = 1;

        this.x = x;
        this.y = y;

        random = new Random();

        collider = new Rectangle(10, 20, 45, 50);

        images = new BufferedImage[8];

    }



    public BufferedImage GetImage(String direction){
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(direction));
        } catch (IOException e) {

        }

        return img;
    }

        public void setAction() {

            actionTimer++;

            if(actionTimer == 180) {
                int i = random.nextInt(100) + 1; //zahl von + x bis klammer

                if(i <= 25) {
                    dir  = "up";
                }

                if(i > 25 && i < 50) {
                    dir  = "down";
                }

                if(i > 50 && i<75) {
                    dir  = "left";
                }

                if(i > 75 && i <= 100) {
                    dir  = "right";
                }

                actionTimer = 0;
            }

        }

        public void update() {

            setAction();
            gp.CDetector.checkTile(this);
            gp.CDetector.checkPlayer(this);

            wait++;
            if(wait > 10) {
                if (num == 1) {
                    num = 2;
                } else if (num == 2) {
                    num = 1;
                }
                wait = 0;
            }

            if (collOn == false) {
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
            } else {
                setAction();
                collOn = false;
            }

    }



        public void draw(Graphics2D g2) {
        //hier wird der npc als bild erstellt
        //spieler npc je nach richtung ändern

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


    

