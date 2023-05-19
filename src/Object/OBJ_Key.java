package Object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Key extends SuperObject{

    public OBJ_Key(GamePanel gp, int x, int y){

        this.x = x;
        this.y = y;

        collider = new Rectangle(x, y, gp.tile, gp.tile);

        name = "Key";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/Key.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }

    }



}
