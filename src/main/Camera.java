package main;

import entity.Entity;

import java.awt.*;

//class für Camera functionalioy
public class Camera {
    private float x, y;

    int smooth; //the smaller the smoother
    GamePanel gp;

    public Camera(GamePanel gp, int x, int y) {
        this.gp = gp;
        this.x = x;
        this.y = y;
        smooth = 5;
    }

    public void update(Entity player) {

        //makes camera "smooth"
        if(x < -player.x + 1920 / 2) {
            x += smooth;
        }
        if(y < -player.y + 1080 / 2) {
            y += smooth;
        }

        if(x > -player.x + 1920 / 2) {
            x -= smooth;
        }
        if(y > -player.y + 1080 / 2) {
            y -= smooth;
        }
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
