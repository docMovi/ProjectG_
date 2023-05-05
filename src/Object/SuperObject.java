package Object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x, y;

    public void draw(Graphics2D g2, GamePanel gp) {

                //if (x + gp.tile > gp.player.x &&
                //        x - gp.tile < gp.player.x &&          //BRAUCHEN WIR DAS??
                  //       y + gp.tile > gp.player.y &&
                  //      y - gp.tile < gp.player.y   ) {}

        System.out.println("super x: " + x + ", super y: " + y);
        g2.drawImage(image, x * gp.tile , y * gp.tile, gp.tile, gp.tile, null);
    }
}
