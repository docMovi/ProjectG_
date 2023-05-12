package main;

import entity.Entity;

public class Pathfinder {
    GamePanel gp;

    public Pathfinder(GamePanel gp) {
        this.gp = gp;
    }

    public boolean isPlayerInRadius(int radius, Entity entity) {
        boolean inRadius = false;

        int radiusX = entity.x / gp.tile + radius;
        int minusRadiusX = entity.x / gp.tile - radius;
        int radiusY = entity.y / gp.tile + radius;
        int minusRadiusY = entity.y / gp.tile - radius;

        if(gp.player.x / gp.tile <= radiusX && gp.player.x / gp.tile >= entity.x / gp.tile) { //RIGHT
            if(gp.player.y / gp.tile >= minusRadiusY && gp.player.y / gp.tile <= entity.y / gp.tile) { //TOP
                gp.ui.showMessage("top right", 2);
                inRadius = true;
            } else if(gp.player.y / gp.tile <= radiusY && gp.player.y / gp.tile >= entity.y / gp.tile){ //DOWN
                gp.ui.showMessage("bottom right", 2);
                inRadius = true;
            }

        } else if(gp.player.x / gp.tile >= minusRadiusX && gp.player.x / gp.tile <= entity.x / gp.tile) { //LEFT
            if(gp.player.y / gp.tile >= minusRadiusY && gp.player.y / gp.tile <= entity.y / gp.tile) { //TOP
                gp.ui.showMessage("top left", 2);
                inRadius = true;
            } else if(gp.player.y / gp.tile <= radiusY && gp.player.y / gp.tile >= entity.y / gp.tile){ //DOWN
                gp.ui.showMessage("bottom left", 2);
                inRadius = true;
            }
        } else {
            inRadius = false;
        }

        return inRadius;
    }

    public void FollowPlayer(Entity entity) {

    }
}
