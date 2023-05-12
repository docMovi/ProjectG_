package entity;

import main.GamePanel;
import main.Key;
import main.Pathfinder;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.plaf.InsetsUIResource;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Spieler Klasse -> greift auf input management zu und dient zur spieler projektzierung
public class Player extends Entity{
    // tmp https://www.youtube.com/watch?v=wT9uNGzMEM4&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=3
    TileManager tileM;
    Key key; //verbindung zum input management
    int tmp = 0;
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

        normalCollider = new Rectangle(x + 20, y + 20, 45, 50);
        attackCollider = new Rectangle(x - 20, y - 25, 140, 140);
        collider = normalCollider;
    }
    public void setValues() {

        speedNormal = 6; //spieler geschwindigkeit
        speed = speedNormal; //tmp
        dir = "down"; //start richtung des Spielers: nach unten
        hp = 3;
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

            attUp1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/up_attack1.png"));
            attUp2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/up_attack2.png"));
            attUp3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/up_attack3.png"));
            attD1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/down_attack1.png"));
            attD2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/down_attack2.png"));
            attD3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/down_attack3.png"));
            attR1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right_attack1.png"));
            attR2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right_attack2.png"));
            attR3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right_attack3.png"));
            attL1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left_attack1.png"));
            attL2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left_attack2.png"));
            attL3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left_attack3.png"));



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

    public void collEnemy(Entity i){

            if(!attacking) {
                //theoretisch leben abzug

            }
            else {
                gp.ui.showMessage("hit", 2);

                i.takeDamage();
            }

    }

    @Override
    public void takeDamage() {
        if(!attacking) {
            gp.ui.showMessage("-20", 2);
            if(!invincible) {hp--;}
            invincible = true;

        }

    }

    public void update() {


        //move input -> greift auf key class zu
        if(key.uppress || key.downpress || key.leftpress || key.rightpress) {

            if(!attacking) {
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

            }
            //check von collision des tiles
            collOn = false;
            gp.CDetector.checkTile(this);



            //check von collision mit npcs
            if(gp.NPCspawned) {
                Entity npcI = gp.CDetector.checkEntity(this, gp.entities);
                interactNPC(npcI);
            }

            if(!gp.NPCspawned) {

                if(checkPos("x", 12) == 1) {
                    gp.setNPC(12, 14);
                    gp.setObjects(12, 12);
                }
            }else{
                if((gp.entities[tmp] != null && gp.entities[tmp].dead)){
                    if(checkPos("x", 12) == 1) {
                        gp.setNPC(12, 14);
                        gp.setObjects(12, 12);
                        tmp++;
                        System.out.println(tmp);
                    }
                }
            }


        }


        if(key.leftClick) {
            attacking = true;
        }
        if(attacking) {
            attacking();
        }

        if(invincible) {
            invincCounter++;
            if(invincCounter > 90) {
                invincible = false;
                invincCounter = 0;
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

    public void interactNPC(Entity i) {
        if(i != null) {
            collEnemy(i);
            collEnemy(i);
        }
    }

    int attCounter = 0;
    public void attacking() {
        attCounter++;
        collider = attackCollider;

        if(attCounter <= 5) {
            attNum = 1;
        }
        if(attCounter > 5 && attCounter <= 15){
            attNum = 2;
        }
        if(attCounter > 12 && attCounter <= 20) {
            attNum = 3;
        }
        if(attCounter > 20) {
            attNum = 1;
            attacking = false;
            collider = normalCollider;
            attCounter = 0;
        }
    }


    public void draw(Graphics2D g2) {
        //hier wird der spieler als bild erstellt
        //spieler bild je nach richtung ändern

        BufferedImage image = null;

        if(dir == "up") {
            if(attacking == false){
                if(num == 1) {
                    image = up1;
                }
                else if (num == 2) {
                    image = up2;
                }
            } else {
                if(attNum == 1) {
                    image = attUp1;
                } else if(attNum == 2) {
                    image = attUp2;
                } else if (attNum == 3) {
                    image = attUp3;
                }
            }

        }else if(dir == "down") {
            if(attacking == false){
                if(num == 1) {
                    image = d1;
                }
                else if (num == 2) {
                    image = d2;
                }
            } else {
                if(attNum == 1) {
                    image = attD1;
                } else if(attNum == 2) {
                    image = attD2;
                } else if (attNum == 3) {
                    image = attD3;
                }
            }
        }else if(dir == "right") {
            if(attacking == false){
                if(num == 1) {
                    image = r1;
                }
                else if (num == 2) {
                    image = r2;
                }
            } else {
                if(attNum == 1) {
                    image = attR1;
                } else if(attNum == 2) {
                    image = attR2;
                } else if (attNum == 3) {
                    image = attR3;
                }
            }
        }else if(dir == "left") {
            if(attacking == false){
                if(num == 1) {
                    image = l1;
                }
                else if (num == 2) {
                    image = l2;
                }
            } else {
                if(attNum == 1) {
                    image = attL1;
                } else if(attNum == 2) {
                    image = attL2;
                } else if (attNum == 3) {
                    image = attL3;
                }
            }
        }

        if(invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, x, y, gp.tile, gp.tile, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }
}
