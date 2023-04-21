package entity;


import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Enemy class (is part of Entities)
//wahrsch nur vorrübergehend -> wenn wir mehr als eine gegner art haben wird das eine übergreifende Klasse sein auf die sich alle gegner beziehen
//
public class Enemy extends Entity {
    GamePanel gp;
    Player player;

    public Enemy(GamePanel gp, Player player) {
        super(gp);
        this.gp = gp;
        this.player = player;

        setValues();
        getImage();
        dir = "down";
    }

    private void setValues() {
        speed = 4;
        x = 20;
        y = 20;
        //hier auch bilder einfügen
    }

    void getImage() {
        try{
            up1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/up1.png"));
            up2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/up2.png"));
            d1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/down1.png"));
            d2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/down2.png"));
            r1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/right1.png"));
            r2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/right2.png"));
            l1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/left1.png"));
            l2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemy/left2.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //tmp
    }

    public void draw(Graphics2D g2){
        //hier wird gegner als bild erstellt
        //gegner bild je nach richtung ändern
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
