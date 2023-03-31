package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//kleine Klasse der Überklassifizierung von allen Entities im Spiel (z.B Spieler)
public class Entity {
   //main class for every entity in the game
    public int x, y; //x und y koordinate des entities
    public int speed; //bewegungsgeschwindigkeit des entities
    public int hp; //lebensanzeige des entities //tmp: noch nicht genutzt
    public BufferedImage up1, up2, d1, d2, l1, l2, r1, r2; //bilder für jede richtung
    public String dir; //string der richtung um rauszufinden in welcher richtung sich entity bewegt -> siehe Player Class
    public int wait = 0;
    public int num = 1;
    public Rectangle collider;
    public boolean collOn = false;
}
