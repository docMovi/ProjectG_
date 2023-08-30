package entity;

import main.GamePanel;
import main.Key;
import main.Pathfinder;
import tile.Animation;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.plaf.InsetsUIResource;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.chrono.ThaiBuddhistChronology;

//Spieler Klasse -> greift auf input management zu und dient zur spieler projektzierung
public class Player extends Entity{
    // tmp https://www.youtube.com/watch?v=wT9uNGzMEM4&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=3
    TileManager tileM;
    Key key; //verbindung zum input management
    int tmp = 0;
    int standCounter = 0;
    //int sprintSpeed; //tmp falls wir sprint einbauen sollten

    int speedNormal;
    int hasKey = 0;
    int x_, y_;
    public BufferedImage win;
    boolean objSpawned = false;

    //ANIM
    Animation attackUpAnim, attackDAnim, attackRAnim, attackLAnim;
    BufferedImage[] attackUpIMG = new BufferedImage[3], attackDIMG = new BufferedImage[3], attackRIMG = new BufferedImage[3], attackLIMG = new BufferedImage[3];

    public Player(GamePanel g, Key k, TileManager tileM) {
        super(g);
        this.gp = g;

        this.key = k;
        this.tileM = tileM;

        setValues();
        getImage();
        setAnim();

        normalCollider = new Rectangle(x + 20, y + 20, 45, 50);
        attackCollider = new Rectangle(x - 20, y - 25, 140, 140);
        collider = normalCollider;
    }
    public void setValues() {
        currentFrame = up1;
        gp.restarting = false;
        speedNormal = 6; //spieler geschwindigkeit
        walkFrames = 2;
        speed = speedNormal; //tmp
        dir = "down"; //start richtung des Spielers: nach unten
        hp = 3;
        //sprintSpeed = 8; //tmp falls wir sprint einbauen sollten
    }

    public void setAnim(){
        animUPIMG[0] = up1; animUPIMG[1] = up2;
        walkingUp = new Animation(this, animUPIMG, 10);
        animDIMG[0] = d1; animDIMG[1] = d2;
        walkingD = new Animation(this, animDIMG, 10);
        animRIMG[0] = r1; animRIMG[1] = r2;
        walkingR = new Animation(this, animRIMG, 10);
        animLIMG[0] = l1; animLIMG[1] = l2;
        walkingL = new Animation(this, animLIMG, 10);

        attackUpIMG[0] = attUp1;
        attackUpIMG[1] = attUp2;
        attackUpIMG[2] = attUp3;
        attackUpAnim = new Animation(this, attackUpIMG, 5, up1);
        attackDIMG[0] = attD1;
        attackDIMG[1] = attD2;
        attackDIMG[2] = attD3;
        attackDAnim = new Animation(this, attackDIMG, 5, d1);
        attackRIMG[0] = attR1;
        attackRIMG[1] = attR2;
        attackRIMG[2] = attR3;
        attackRAnim = new Animation(this, attackRIMG, 5, r1);
        attackLIMG[0] = attL1;
        attackLIMG[1] = attL2;
        attackLIMG[2] = attL3;
        attackLAnim = new Animation(this, attackLIMG, 5, l1);

        currentFrame = d1;
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

            dying1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/dying1.png"));
            dying2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/dying2.png"));
            dying3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/dying3.png"));
            dying4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/dying4.png"));
            dying5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/dying5.png"));

            win = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/win.png"));

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
            x_ = x / gp.tile;
            y_ = y / gp.tile;
            System.out.println("been there, done that");
            System.out.println("x: " + x_ + ", y: " + y_);
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
            gp.ui.showMessage("-1", 2);
            if(!invincible) {hp--;gp.playSE(1,this);}
            if(hp == 0) {
                dead();
            }
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
                        currentFrame = walkingUp.play();
                    }

                    if (dir == "down") {
                        y += speed;
                        currentFrame = walkingD.play();
                    }
                    if (dir == "right") {
                        x += speed;
                        currentFrame = walkingR.play();
                    }
                    if (dir == "left") {
                        x -= speed;
                        currentFrame = walkingL.play();
                    }

                }

            }
            //check von collision des tiles
            collOn = false;
            gp.CDetector.checkTile(this);

            // check von collision der Objects
            int objindex = gp.CDetector.checkObject(this, true);
            pickupObject(objindex);

            //check von collision mit npcs
            if(gp.NPCspawned) {
                Entity npcI = gp.CDetector.checkEntity(this, gp.entities);
                interactNPC(npcI);
            }



            if(!gp.NPCspawned){
                if(checkPos("x", x_ + 2) == 1 || checkPos("x", x_ - 2) == 1 || checkPos("y", y_ + 2) == 1 ||checkPos("x", y_ - 2) == 1) {
                    gp.setNPC();
                    gp.setKey();
                    gp.setDoor();

                    gp.ui.showMessage("Besiege alle Monster!", 5);
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

    public void pickupObject(int i) {

        if(i != 999) {
            String objectName = gp.objects[i].name;

            switch(objectName) {
                case"Key":
                    hasKey++;
                    gp.objects[i] = null;
                    gp.ui.showMessage("Gut gemacht! Suche nur noch den Ausgang und raus hier!", 3);
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.objects[i].image = gp.objects[i].image2;
                        hasKey--;
                        gp.GameWin();
                    }
                    break;
            }

        }
    }

    public void attacking() {
        if(dir == "up") {currentFrame = attackUpAnim.playWithBuffer(2, 10);}
        else if(dir == "down") {currentFrame = attackDAnim.playWithBuffer(2, 10);}
        else if(dir == "right") {currentFrame = attackRAnim.playWithBuffer(2, 10);}
        else if(dir == "left") {currentFrame = attackLAnim.playWithBuffer(2, 10);}

        if(attackUpAnim.isAnimRunning() || attackDAnim.isAnimRunning() || attackRAnim.isAnimRunning() || attackLAnim.isAnimRunning()){
            attacking = true;
            collider = attackCollider;
        } else {
            attacking = false;
            collider = normalCollider;
        }
    }

    public void dead(){
        gp.gameState = gp.gameOverState;
    }

    public void draw(Graphics2D g2) {
        //hier wird der spieler als bild erstellt
        //spieler bild je nach richtung ändern

        if (gp.gameState != gp.menuState) {
            if (invincible) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
            g2.drawImage(currentFrame, x, y, gp.tile, gp.tile, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }
}
