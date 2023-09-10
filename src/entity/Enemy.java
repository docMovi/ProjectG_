package entity;


import main.GamePanel;
import tile.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Enemy class (is part of Entities)
//wahrsch nur vorrübergehend -> wenn wir mehr als eine gegner art haben wird das eine übergreifende Klasse sein auf die sich alle gegner beziehen
//
public class Enemy extends NPC {
    Player player;
    int index;
    BufferedImage[] dyingIMG = new BufferedImage[5];

    public Enemy(GamePanel gp, int x, int y, int i){
        super(gp);
        setValues();
        setAnim();

        this.x = x * gp.tile;
        this.y = y * gp.tile;
        NpcType = 3;
        hp = 3;
        index = i;
    }

    private void setValues() {
        speed = 2;
        aggroRange = 4;

        //hier auch bilder einfügen
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/up1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/up2.png"));
            d1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/down1.png"));
            d2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/down2.png"));
            r1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/right1.png"));
            r2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/right2.png"));
            l1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/left1.png"));
            l2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/left2.png"));

            dying1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/dying1.png"));
            dying2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/dying2.png"));
            dying3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/dying3.png"));
            dying4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/dying4.png"));
            dying5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/EnemySmallGoblin/dying5.png"));
        } catch (IOException e) {
        }

        System.out.println("Im an enemy on x: " + x + " and y: " + y);
    }

    void setAnim() {
        walkFrames = 2;
        animUPIMG[0] = up1; animUPIMG[1] = up2;
        walkingUp = new Animation(this, animUPIMG, 10);
        animDIMG[0] = d1; animDIMG[1] = d2;
        walkingD = new Animation(this, animDIMG, 10);
        animRIMG[0] = r1; animRIMG[1] = r2;
        walkingR = new Animation(this, animRIMG, 10);
        animLIMG[0] = l1; animLIMG[1] = l2;
        walkingL = new Animation(this, animLIMG, 10);

        dyingIMG[0] = dying1; dyingIMG[1] = dying2; dyingIMG[2] = dying3; dyingIMG[3] = dying4; dyingIMG[4] = dying5;
        animDie = new Animation(this, dyingIMG, 5);

        currentFrame = d1;
    }

    int timer = 0;
    boolean _timer = false;
    @Override
    public void takeDamage() {
        gp.ui.showMessage("10", 1);
        if(invincible!=true) {
            hp--;
            gp.playSE(2,this);
        }
        invincible = true;
        _timer = true;
    }

    public void Free(){
        collOn = false;
    }

    int deathAnimCounter;

    @Override
    public void fakeUpdate() {
        if(hp <= 0) {
            dead = true;
        }

        if(invincible) {
            invincCounter++;
            if(!followingPlayer){
                gp.pathfinder.FollowPlayer(this);
            }
            if(invincCounter > 60) {
                invincible = false;
                invincCounter = 0;
            }
        }

        if(_timer) {
            if(timer >= 40) {
                Free();
                _timer = false;
                timer = 0;
            }
            else {
                timer++;
            }
        }

        boolean played = false;

        if(dead) {
                Dying();
            }
    }


        void Dying() {
            currentFrame = animDie.play();
            if(!animDie.isAnimRunning()) {
                dead = false;
                gp.destroyNPC(index);
            }
        }


    }


