package main;

import entity.Entity;

public class Pathfinder {
    GamePanel gp;

    public String dir_x;
    public String dir_y;
    public boolean inColl;
    boolean top,bott,right, left;
    public Pathfinder(GamePanel gp) {
        this.gp = gp;
    }

    public boolean isPlayerInRadius(int radius, Entity entity) {
        boolean inRadius = false;

        int radiusX = entity.x / gp.tile + radius;
        int minusRadiusX = entity.x / gp.tile - radius;
        int radiusY = entity.y / gp.tile + radius;
        int minusRadiusY = entity.y / gp.tile - radius;

        if (gp.player.x / gp.tile <= radiusX && gp.player.x / gp.tile >= entity.x / gp.tile) { if(gp.player.y / gp.tile != entity.y / gp.tile ) {
            if (gp.player.y / gp.tile >= minusRadiusY && gp.player.y / gp.tile <= entity.y / gp.tile && gp.player.x / gp.tile != entity.x / gp.tile) { //TOP
                gp.ui.showMessage("top right", 2);
                dir_x = "right";
                dir_y = "top";
                inRadius = true;

            } else if (gp.player.y / gp.tile <= radiusY && gp.player.y / gp.tile >= entity.y / gp.tile && gp.player.x / gp.tile != entity.x / gp.tile) { //DOWN
                gp.ui.showMessage("bottom right", 2);
                dir_x = "right";
                dir_y = "bottom";
                inRadius = true;
            } if ( gp.player.y / gp.tile <= radiusY && gp.player.y / gp.tile >= entity.y / gp.tile && gp.player.x / gp.tile == entity.x / gp.tile) {
                dir_x = "";
                dir_y = "down";
                inRadius = true;
            } if(gp.player.y / gp.tile >= minusRadiusY && gp.player.y / gp.tile <= entity.y / gp.tile && gp.player.x / gp.tile == entity.x / gp.tile) {
                dir_x = "";
                dir_y = "up";
                inRadius = true;
            }
        }}//RIGHT

        else if (gp.player.x / gp.tile >= minusRadiusX && gp.player.x / gp.tile <= entity.x / gp.tile) { //LEFT

            if(gp.player.y / gp.tile != entity.y / gp.tile ) {
                if (gp.player.y / gp.tile >= minusRadiusY && gp.player.y / gp.tile <= entity.y / gp.tile) { //TOP
                    gp.ui.showMessage("top left", 2);
                    dir_x = "left";
                    dir_y = "top";
                    inRadius = true;
                } else if (gp.player.y / gp.tile <= radiusY && gp.player.y / gp.tile >= entity.y / gp.tile) { //DOWN
                    gp.ui.showMessage("bottom left", 2);
                    dir_x = "left";
                    dir_y = "bottom";
                    inRadius = true;
                }
                if ( gp.player.y / gp.tile <= radiusY && gp.player.y / gp.tile >= entity.y / gp.tile && gp.player.x / gp.tile == entity.x / gp.tile) {
                    dir_x = "";
                    dir_y = "down";
                    inRadius = true;
                }
                if(gp.player.y / gp.tile >= minusRadiusY && gp.player.y / gp.tile <= entity.y / gp.tile && gp.player.x / gp.tile == entity.x / gp.tile) {
                    dir_x = "";
                    dir_y = "up";
                    inRadius = true;
            }


            }


        } else if (gp.player.x / gp.tile == entity.x && gp.player.y == entity.y) {
            entity.y -= entity.speed;
        }
        else{
            inRadius = false;
        }

        return inRadius;
    }

    int timer = 0;

    public void FollowPlayer(Entity entity) {

        if(dir_x == "right") {
            if(entity.dir != "right") {
                entity.dir = "right";
            }
        } else if(dir_x == "left") {
            if(entity.dir != "left") {
                entity.dir = "left";
            }
        }
        if(dir_y == "top") {
            entity.y -= entity.speed;
        } else if(dir_y == "bottom") {
            entity.y += entity.speed;
        }

        if(dir_y == "up") {
            if(entity.dir != "up") {
                entity.dir = "up";
            }
        } else if(dir_y == "down") {
            if(entity.dir != "down") {
                entity.dir = "down";
            }
        }

    }

    public void Colliding(Entity entity) {
        if (dir_y == "top") {
            if (gp.lh.list[entity.x / gp.tile][entity.y / gp.tile - entity.speed].coll == true) {
                entity.stop = false;
            } else {
                entity.collOn = false;
            }
        }
        if (dir_y == "down") {
            if (gp.lh.list[entity.x / gp.tile][entity.y / gp.tile + entity.speed].coll == true) {
                entity.stop = false;
            } else {
                entity.collOn = false;
            }
        }
        if (dir_x == "right") {
            if (gp.lh.list[entity.x / gp.tile + entity.speed][entity.y / gp.tile].coll == true) {
                entity.stop = false;
            } else {
                entity.collOn = false;
            }
        }
        if (dir_y == "left") {
            if (gp.lh.list[entity.x / gp.tile - entity.speed][entity.y / gp.tile].coll == true) {
                entity.stop = false;
            } else {
                entity.collOn = false;
            }

        }
    }
}
