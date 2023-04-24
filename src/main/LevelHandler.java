package main;

import tile.BufferdImageLoader;
import tile.Tile;
import tile.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;


//Klasse die den LevelBuilder funktionell macht (nimmt zugriff auf die tiles vom TileManager)
public class LevelHandler {
    public BufferedImage lvl = null;
    private BufferdImageLoader loader; //Verknüpfung mit BufferedImageLoader Class zum laden vom bild

    private TileManager tileM; //Verknüpfung mit dem TileManager (wichtig)

    private GamePanel gp; //Zuweisung des GamePanels

    public Tile[][] list;

    public int x, y;



    public LevelHandler(GamePanel gp) {
        loader = new BufferdImageLoader();
        lvl = loader.loadImage("/level/level.png"); //bindet level image ein
        this.gp = gp; //Zuweisung des GamePanels
        tileM = gp.tm;
    }

    public void draw(Graphics2D g2) {

        int w = lvl.getWidth();
        int h = lvl.getHeight();
        list = new Tile[w] [h]; //index sehr dumm gewählt aber beste option i think
        int n = 0;

        if(n == 0) {
            //loop durch das ganze bild um farben zu erkennen
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {

                    int px = lvl.getRGB(j, i);
                    int r = (px >> 16) & 0xff; //bit-related
                    int g = (px >> 8) & 0xff;  //bit-related -> color value in bits (kompliziert)
                    int b = (px) & 0xff;    //bit-related


                    if (r == 255 && g == 255 && b == 255) { // white --> wall
                        g2.drawImage(tileM.tiles[5].image, j * gp.tile, i * gp.tile, gp.tile, gp.tile, null);
                        //add tile to list of tiles
                        list[j][i]  = new Tile(j, i, true); // COULD CAUSE LAG
                    }
                    else if(r == 0 && g == 0 && b == 0) { //black --> grass
                        g2.drawImage(tileM.tiles[0].image, j * gp.tile, i * gp.tile, gp.tile, gp.tile,null);
                        //add tile to list of tiles
                        list[j][i] = new Tile(j, i, false); // COULD CAUSE LAG
                    }
                    else if(r == 0 && g == 0 && b == 255) { //blau -> spawnpoint player
                        g2.drawImage(tileM.tiles[0].image, j * gp.tile, i * gp.tile, gp.tile, gp.tile,null);
                        //add tile to list of tiles
                        list[j][i] = new Tile(j, i, false); // COULD CAUSE LAG

                        x = j * gp.tile;
                        y = i * gp.tile;

                        gp.player.setPos(x, y);
                    }

                }
            }
        }



    }


}
