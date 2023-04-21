package main;

import entity.Entity;
import tile.TileManager;

//class to handle collision detection
public class CollisionDetector {
    GamePanel gp;
    TileManager tm;

    public CollisionDetector (GamePanel gp, TileManager tm) {
        this.gp = gp; //initialiserung von konstruktor variabeln
        this.tm = tm;
    }

    public void checkTile(Entity entity) {
        //check ob nächstes Tile collider hat oder nicht

        //berechnung für 'nächsten' tile player moves to
        int eLeftX = entity.x + entity.collider.x;
        int eRightX = entity.x + entity.collider.x + entity.collider.width;
        int eTopY = entity.y + entity.collider.y;
        int eBottY = entity.y + entity.collider.y + entity.collider.height;

        int eLeftCol = eLeftX/gp.tile;
        int eRightCol = eRightX/gp.tile;
        int eTopRow = eTopY/ gp.tile;
        int eBottRow = eBottY/ gp.tile;

        System.out.println(entity.dir);

        if(entity.dir == "up"){
            entity.collOn = false;
            eTopRow = (eTopY - entity.speed)/ gp.tile;
            if(gp.lh.list != null || gp.lh.list != null){
                if(gp.lh.list[eLeftCol][eTopRow].coll || gp.lh.list[eRightCol][eTopRow].coll){
                    entity.collOn = true;
                }
            }

        }if(entity.dir == "right"){
            entity.collOn = false;
            eRightCol = (eRightX + entity.speed)/ gp.tile;
            if(gp.lh.list != null || gp.lh.list != null){
                if(gp.lh.list[eRightCol][eTopRow].coll || gp.lh.list[eRightCol][eBottRow].coll){
                    entity.collOn = true;
                    System.out.println("collider righ!");
                }
            }

        }if(entity.dir == "left"){
            entity.collOn = false;
            eLeftCol = (eLeftX - entity.speed)/ gp.tile;
            if(gp.lh.list != null || gp.lh.list != null) {
                if(gp.lh.list[eLeftCol][eTopRow].coll || gp.lh.list[eLeftCol][eBottRow].coll){
                    entity.collOn = true;
                    System.out.println("collider left!");
                }
            }

        }if(entity.dir == "down"){
            entity.collOn = false;
            eBottRow = (eBottY + entity.speed)/ gp.tile;
            if(gp.lh.list != null || gp.lh.list != null){
                if(gp.lh.list[eLeftCol][eBottRow].coll || gp.lh.list[eRightCol][eBottRow].coll){
                    entity.collOn = true;
                }
            }

        }

        //getting collider of pos
    }

}
