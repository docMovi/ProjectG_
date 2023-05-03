package main;

import entity.Entity;
import tile.Tile;
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

        if(entity.dir == "up"){
            eTopRow = (eTopY - entity.speed)/ gp.tile;

            if(gp.lh.list2[eLeftCol][eTopRow] != null || gp.lh.list2[eRightCol][eTopRow] != null) {
                if (gp.lh.list2[eLeftCol][eTopRow].coll || gp.lh.list2[eRightCol][eTopRow].coll) {
                    entity.collOn = true;
                }
            } else {
                System.out.println("Error Code 929");
            }


        }if(entity.dir == "right"){
            eRightCol = (eRightX + entity.speed)/ gp.tile;
            if(gp.lh.list2[eRightCol][eTopRow] != null || gp.lh.list2[eRightCol][eBottRow] != null ) {
                if (gp.lh.list2[eRightCol][eTopRow].coll || gp.lh.list2[eRightCol][eBottRow].coll) {
                    entity.collOn = true;
                    System.out.println("collider righ!");
                }
            }else {
                System.out.println("Error Code 929");
            }

        }if(entity.dir == "left"){
            eLeftCol = (eLeftX - entity.speed)/ gp.tile;
            if(gp.lh.list2[eLeftCol][eTopRow] != null || gp.lh.list2[eLeftCol][eBottRow] != null || gp.lh.list2[eRightCol][eBottRow] != null) {
                if(gp.lh.list2[eLeftCol][eTopRow].coll || gp.lh.list2[eLeftCol][eBottRow].coll){
                    entity.collOn = true;
                    System.out.println("collider left!");
                }
            } else {
                System.out.println("Error Code 929");
            }

        }if(entity.dir == "down"){
            eBottRow = (eBottY + entity.speed)/ gp.tile;
            if(gp.lh.list2[eLeftCol][eBottRow] != null || gp.lh.list2[eRightCol][eBottRow] != null){
                if(gp.lh.list2[eLeftCol][eBottRow].coll || gp.lh.list2[eRightCol][eBottRow].coll){
                    entity.collOn = true;
                }
            }else {
                System.out.println("Error Code 929");
            }

        }

        //getting collider of pos
    }

    public Entity checkEntity(Entity entity, Entity[] other) {
        //berechnung für 'nächsten' tile player moves to
        Entity index = null;

        for (int i = 0; i < other.length; i++) {
            if(other[i] != null) {
                int xDefault = entity.collider.x;
                int yDefault = entity.collider.y;

                int otherXDefault = other[i].collider.x;
                int otherYDefault = other[i].collider.y;

                entity.collider.x = entity.x + entity.collider.x;
                entity.collider.y = entity.y + entity.collider.y;

                other[i].collider.x = other[i].x + other[i].collider.x;
                other[i].collider.y = other[i].y + other[i].collider.y;

                if (entity.dir == "up") {
                    entity.collider.y -= entity.speed;
                    if (entity.collider.intersects(other[i].collider)) {
                        if (other[i].collidable) {
                            entity.collOn = true;
                            index = other[i];
                            System.out.println("index: " + index);
                        }
                    }

                }
                if (entity.dir == "right") {
                    entity.collider.x += entity.speed;
                    if (entity.collider.intersects(other[i].collider)) {
                        if (other[i].collidable) {
                            entity.collOn = true;
                            index = other[i];
                            System.out.println("index: " + index);
                        }
                    }

                }
                if (entity.dir == "left") {
                    entity.collider.x -= entity.speed;
                    if (entity.collider.intersects(other[i].collider)) {
                        if (other[i].collidable) {
                            entity.collOn = true;
                            index = other[i];
                            System.out.println("index: " + index);
                        }
                    }

                }
                if (entity.dir == "down") {
                    entity.collider.y += entity.speed;
                    if (entity.collider.intersects(other[i].collider)) {
                        if (other[i].collidable) {
                            entity.collOn = true;
                            index = other[i];
                            System.out.println("index: " + index);
                        }
                    }

                }
                entity.collider.x = xDefault;
                entity.collider.y = yDefault;

                other[i].collider.x = otherXDefault;
                other[i].collider.y = otherYDefault;
            }
        }


        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        int xDefault = entity.collider.x;
        int yDefault = entity.collider.y;

        int otherXDefault = gp.player.collider.x;
        int otherYDefault = gp.player.collider.y;

        entity.collider.x = entity.x + entity.collider.x;
        entity.collider.y = entity.y + entity.collider.y;

        gp.player.collider.x = gp.player.x + gp.player.collider.x;
        gp.player.collider.y = gp.player.y + gp.player.collider.y;

        if (entity.dir == "up") {
            entity.collider.y -= entity.speed;
        }
        if (entity.dir == "right") {
            entity.collider.x += entity.speed;
        }
        if (entity.dir == "left") {
            entity.collider.x -= entity.speed;
        }
        if (entity.dir == "down") {
            entity.collider.y += entity.speed;
        }

        if (entity.collider.intersects(gp.player.collider)) {
            entity.collOn = true;
            contactPlayer = true;
        }
        entity.collider.x = xDefault;
        entity.collider.y = yDefault;

        gp.player.collider.x = otherXDefault;
        gp.player.collider.y = otherYDefault;

        return contactPlayer;
    }


}
