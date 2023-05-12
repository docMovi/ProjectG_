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
        System.out.println("tester");

        int radiusX = entity.x / gp.tile + radius;
        int minusRadiusX = entity.x / gp.tile - radius;
        int radiusY = entity.y / gp.tile + radius;
        int minusRadiusY = entity.y / gp.tile - radius;

        if (gp.player.x / gp.tile <= radiusX && gp.player.x / gp.tile >= entity.x / gp.tile) { if(gp.player.y / gp.tile != entity.y / gp.tile ) {
            if (gp.player.y / gp.tile >= minusRadiusY && gp.player.y / gp.tile <= entity.y / gp.tile) { //TOP
                gp.ui.showMessage("top right", 2);
                dir_x = "right";
                dir_y = "top";
                inRadius = true;

            } else if (gp.player.y / gp.tile <= radiusY && gp.player.y / gp.tile >= entity.y / gp.tile) { //DOWN
                gp.ui.showMessage("bottom right", 2);
                dir_x = "right";
                dir_y = "bottom";
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

            }

        }else if(gp.player.x / gp.tile > radiusX && gp.player.y / gp.tile > radiusY || gp.player.x / gp.tile < minusRadiusX && gp.player.y < minusRadiusY) {
            inRadius = false;
            System.out.println("tester2");
        } else if (gp.player.x / gp.tile == entity.x && gp.player.y == entity.y) {
            entity.y -= entity.speed;
        }

        return inRadius;
    }

    int timer = 0;

    public void FollowPlayer(Entity entity) {
        System.out.println("y: " + dir_y);

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
