package Object;

import  main.GamePanel;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image, image2;
    public String name;
    GamePanel gp;
    public boolean collision = false;
    public int x, y, x2, y2;
    public Rectangle collider;

    public int colliderDefaultX = 0;
    public int ColliderDefaultY = 0;


    public void draw(Graphics2D g2, GamePanel gp) {

                //if (x + gp.tile > gp.player.x &&
                //        x - gp.tile < gp.player.x &&          //BRAUCHEN WIR DAS??
                  //       y + gp.tile > gp.player.y &&
                  //      y - gp.tile < gp.player.y   ) {}
        g2.drawImage(image, x * gp.tile , y * gp.tile, gp.tile, gp.tile, null);
        this.gp = gp;
    }

    public void update() {

    }
}
