package entity;

import main.GamePanel;
import tile.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

//kleine Klasse der Überklassifizierung von allen Entities im Spiel (z.B Spieler)
public class Entity {
   //main class for every entity in the game

    public GamePanel gp;
    public BufferedImage currentFrame;
    public int x, y; //x und y koordinate des entities
    public int speed; //bewegungsgeschwindigkeit des entities
    int walkFrames = 2;
    public int hp; //lebensanzeige des entities
    public boolean dead;
    public BufferedImage up1, up2, d1, d2, l1, l2, r1, r2; //bilder für jede richtung
    public BufferedImage attUp1, attUp2, attUp3, attD1, attD2, attD3, attL1, attL2, attL3, attR1, attR2, attR3; //bilder für angriff in jede richtung
    public BufferedImage dying1, dying2, dying3, dying4, dying5; //dying animation
    public String dir; //string der richtung um rauszufinden in welcher richtung sich entity bewegt -> siehe Player Class
    public int wait = 0;
    public int num = 1;
    public int attNum = 1;
    int deathNum = 1;
    public Rectangle normalCollider;
    public Rectangle attackCollider;
    public Rectangle collider;
    public boolean collOn = false;
    public boolean invincible = false;
    public int invincCounter;
    public boolean collidable = true;
    public boolean attacking = false;
    public boolean stop = false;
    public boolean followingPlayer;

    //ANIM
    Animation walkingUp, walkingD, walkingR, walkingL;
    Animation animDie;
    BufferedImage[] animUPIMG = new BufferedImage[walkFrames], animDIMG = new BufferedImage[walkFrames], animRIMG = new BufferedImage[walkFrames], animLIMG = new BufferedImage[walkFrames];
    public void fakeUpdate() {

    }

    public void takeDamage(){

    }

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {

    }

}
