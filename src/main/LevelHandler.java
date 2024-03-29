package main;

import tile.BufferdImageLoader;
import tile.ColorPicker;
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
    public Tile[][] list2;
    public int[] tmpenemies;
    public int[] tmpkeys;
    public int[] tmpdoors;

    public Color[] colors = new Color[64];
    public int tiles[] = new int[64];

    public int x, y;

    boolean executed = false;
    int n = 0;

    ColorPicker coP = new ColorPicker(this);


    public LevelHandler(GamePanel gp, BufferedImage image) {
        loader = new BufferdImageLoader();
        lvl = image;
        this.gp = gp; //Zuweisung des GamePanels
        tileM = gp.tm;
    }

    int tmp = 0;

    public void newColor(String c, int t) {
        if(colors[tmp] == null && tiles[tmp] == 0) {
            colors[tmp] = Color.decode(c);
            tiles[tmp] = t;
            tmp++;
        } else {
            System.out.println("Error LHNC01");
        }
    }

    public void draw(Graphics2D g2) {
        if (gp.gameState != gp.menuState) {

            int w = lvl.getWidth();
            int h = lvl.getHeight();
            list = new Tile[w][h]; //index sehr dumm gewählt aber beste option i think
            tmpenemies = new int[w];
            tmpkeys = new int[32];
            tmpdoors = new int[32];

            //loop durch das ganze bild um farben zu erkennen
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {

                    int px = lvl.getRGB(j, i);
                    int r = (px >> 16) & 0xff; //bit-related
                    int g = (px >> 8) & 0xff;  //bit-related -> color value in bits (kompliziert)
                    int b = (px) & 0xff;    //bit-related


                    for(int t = 0; t < colors.length; t++) {
                        if(colors[t] != null) {
                            if (r == colors[t].getRed() && g == colors[t].getGreen() && b == colors[t].getBlue()) {
                                g2.drawImage(tileM.tiles[tiles[t]].image, j * gp.tile, i * gp.tile, gp.tile, gp.tile, null);
                                //add tile to list of tiles
                                list[j][i] = new Tile(j, i, tileM.tiles[tiles[t]].coll); // COULD CAUSE LAG
                        }
                    }




                    } if (r == 0 && g == 0 && b == 255) { //blau -> spawnpoint player
                        g2.drawImage(tileM.tiles[0].image, j * gp.tile, i * gp.tile, gp.tile, gp.tile, null);
                        //add tile to list of tiles
                        list[j][i] = new Tile(j, i, false); // COULD CAUSE LAG

                        x = j * gp.tile;
                        y = i * gp.tile;

                        gp.player.setPos(x, y);
                    } else if (r == 255 && g == 0 && b == 0) { //rot -> spawnpoint gegner
                        g2.drawImage(tileM.tiles[0].image, j * gp.tile, i * gp.tile, gp.tile, gp.tile, null);
                        //add tile to list of tiles
                        list[j][i] = new Tile(j, i, false); // COULD CAUSE LAG
                        tmpenemies[j] = i;

                        x = j * gp.tile;
                        y = i * gp.tile;

                    } else if (r == 255 && g == 255 && b == 0) { //gelb -> spawnpoint key
                        g2.drawImage(tileM.tiles[0].image, j * gp.tile, i * gp.tile, gp.tile, gp.tile, null);
                        //add tile to list of tiles
                        list[j][i] = new Tile(j, i, false); // COULD CAUSE LAG
                        tmpkeys[j] = i;

                        x = j * gp.tile;
                        y = i * gp.tile;

                    } else if (r == 0 && g == 255 && b == 0) { //grün -> spawnpoint tür
                        g2.drawImage(tileM.tiles[5].image, j * gp.tile, i * gp.tile, gp.tile, gp.tile, null);
                        //add tile to list of tiles
                        list[j][i] = new Tile(j, i, false); // COULD CAUSE LAG
                        tmpdoors[j] = i;

                        x = j * gp.tile;
                        y = i * gp.tile;

                    }

                }
            }
            list2 = list;
            gp.enemies = tmpenemies;
            gp.keys = tmpkeys;
            gp.doors = tmpdoors;

        }
    }






    }



