package Object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Key extends SuperObject{

    public OBJ_Key(GamePanel gp, int x, int y, String cond){

        this.gp = gp;
        this.x = x;
        this.y = y;

        collider = new Rectangle(x, y, gp.tile, gp.tile);

        condition = cond;
        name = "Key";
        collision = true;


    }

    boolean executed = false;
    @Override
    public void update() {
        if(condition == "E") {
            if(gp.deadEnemies() == gp.entities.length) {
                try {
                    if(!executed) {
                        image = ImageIO.read(getClass().getResourceAsStream("/Objects/Key.png"));
                        gp.ui.showMessage("Klasse! Suche den Schlüssel um zu entkommen!", 5);
                        executed = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                image = null;
            }
        }else {
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/Objects/Key.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
