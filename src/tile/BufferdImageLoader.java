package tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

//kleine Klasse um das Einfügen von Bildern zu erleichtern (genutzt für z.B. levelbuilder)
public class BufferdImageLoader {
    public BufferedImage image;

    public BufferedImage loadImage(String datei) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(datei));
        } catch (IOException e) {
            e.printStackTrace();
        }
    return image;
    }
}
