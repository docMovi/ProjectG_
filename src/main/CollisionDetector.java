package main;

import entity.Entity;
import tile.TileManager;

//class to handle collision detection
public class CollisionDetector {
    GamePanel gp;

    TileManager tm;

    public CollisionDetector (GamePanel gp, TileManager tm) {
        this.gp = gp;
        this.tm = tm;
    }

    public void checkTile(Entity entity) {
        //check ob nächstes Tile collider hat oder nicht
         entity.collOn = tm.GetColl(entity.x, entity.y);
         //tmp -> hier weitermachen https://www.youtube.com/watch?v=oPzPpUcDiYY&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=6

        //TMP -> debug
        //System.out.println("pos x: " + entity.x / gp.tile);
        //System.out.println("Berechnung x: " + tm.getX(entity.x, entity.y));
        //System.out.println("pos y: " + entity.y / gp.tile);
        //System.out.println("Berechnung y: " + tm.getY(entity.x, entity.y));
        //System.out.println("coll: " + tm.GetColl(entity.x, entity.y));
    }

}
