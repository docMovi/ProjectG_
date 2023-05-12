package Object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject{

    public OBJ_Key(GamePanel gp, int x, int y){

        this.x = x;
        this.y = y;

        name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/Key.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }

    }



}
