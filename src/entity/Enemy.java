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

    public Enemy(GamePanel gp, int x, int y){
        super(gp);
        setValues();

        this.x = x * gp.tile;
        this.y = y * gp.tile;
        NpcType = 3;
    }

    private void setValues() {
        speed = 2;

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
        } catch (IOException e) {
        }

        System.out.println("Im an enemy");
    }

    public void fakeUpdate() {

    }





}
