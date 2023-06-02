package entity;


import main.GamePanel;

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

    public Enemy(GamePanel gp, int x, int y, int i){
        super(gp);
        setValues();

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

        if(dead) {
            deathAnimCounter++;

            if(deathAnimCounter < 5) {
                deathNum = 1;
            }
            if(deathAnimCounter > 5 && deathAnimCounter < 10) {
                deathNum = 2;
            }
            if(deathAnimCounter > 10 && deathAnimCounter < 15) {
                deathNum = 3;
            }
            if(deathAnimCounter > 15 && deathAnimCounter < 20) {
                deathNum = 4;
            }
            if(deathAnimCounter > 20 && deathAnimCounter < 25) {
                deathNum = 5;
            }
            if(deathAnimCounter > 25) {
                deathNum = 6;
            }
            if(deathAnimCounter > 30) {
                 gp.destroyNPC(index);
            }
        }


    }





}
