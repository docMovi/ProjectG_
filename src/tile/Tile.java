package tile;

import java.awt.image.BufferedImage;

//kleine Klasse als Überklassifizierung der Tiles (genutzt in TileManager)
public class Tile {

    public BufferedImage image;
    public boolean coll = false;

    public Tile() {

    }

    public int x;
    public int y;
    public Tile(int x, int y, boolean coll) {
            this.coll = coll;
            this.x = x;
            this.y = y;

    }

}
