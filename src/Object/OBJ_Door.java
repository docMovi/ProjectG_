package Object;


import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject {

    public OBJ_Door() {

        this.x = x;
        this.y = y;

        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/Door.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }
}