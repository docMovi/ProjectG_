package entity;


import main.GamePanel;

//Enemy class (is part of Entities)
public class Enemy extends Entity {
    GamePanel gp;
    Player player;

    public Enemy(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;

        setValues();
    }

    public void setValues() {
        speed = 4;
        x = 20;
        y = 20;
        //hier auch bilder einfügen
    }
}
