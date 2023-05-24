package Object;


import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Door extends SuperObject {

    public OBJ_Door(GamePanel gp, int x, int y) {

        this.x = x;
        this.y = y;

        name = "Door";
        collider = new Rectangle(x, y, gp.tile, gp.tile);
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/Door.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }

}