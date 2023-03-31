package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

//klasse die sich um unsere sprites kümmert
public class TileManager {
    GamePanel gp;
    public Tile[] tiles; //array von allen sprites -> bilddateien
    String[] places = new String[4]; //vllt nützlich später -> dateilaufpfade als variablen
    public TileManager(GamePanel gp) {
        this.gp = gp; //Zuweisung des GamePanels
        tiles = new Tile[10];

        getTileImage();
        places[0] = "/tile/grass1.png"; //siehe places
        places[1] = "/tile/grass2.png"; //siehe places
        places[2] = "/tile/grass_s1.png"; //siehe places
        places[3] = "/tile/grass_s2.png"; //siehe places

    }

    public void getTileImage() {
        //dateien wird im platz im tileset (sammlung aller sprites) gegeben
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass0.png")); //im ordner res -> tile und dann die datei "grass0.png"

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass1.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass2.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass_s1.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass_s2.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
            tiles[5].coll = true;
        }catch (IOException e) {
            e.printStackTrace(); //fehler vorbeugung
        }

    }


    public void draw(Graphics2D g2) {


    }
}
