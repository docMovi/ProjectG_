package main;

import entity.Enemy;
import entity.Player;
import tile.Tile;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

//klasse die basically alles managed vor allem aber den screen und was darauf muss
public class GamePanel extends JPanel implements Runnable{
    Key key = new Key();
    Thread gameThread;
    TileManager tm = new TileManager(this);
    public CollisionDetector CDetector = new CollisionDetector(this, tm);
    Player player = new Player(this, key, tm);
    Camera cam = new Camera(this, -player.x + 1920 / 2, -player.y + 1080 / 2);

    Enemy enemy = new Enemy(this, player);

    int tileT = 16;
    int multiply = 5;
    //estimate value for tilesize on screen (for pixel art)
    public int tile = tileT * multiply;

    public LevelHandler lh;



    //player pos
    int playerx = 100;
    int playery = 100;
    int speed = 4;
    int FPS  = 60;




    public GamePanel(){
        this.setBackground(Color.black);
        //this.setBackground(Color.decode("#74ba67")); //nimmt hex code und wandelt ihn in RGB farbe um
        this.setPreferredSize(new Dimension(512, 328));
        this.addKeyListener(key);
        this.setFocusable(true);
        startGameThread();
        lh = new LevelHandler(this);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }



    @Override
    public void run() {
        //Intervall im unendlichen loop (= circa 0.016 sec)
        double drawInterval = 1000000000 / FPS; //FPS = 60
        double nextDraw = System.nanoTime() + drawInterval;

        //erstellt infinite loop
        while(gameThread != null) {
            update();
            //draw screen
            repaint();

            try {
                double remain = nextDraw - System.nanoTime();
                //nano into millisec
                remain = remain / 1000000;

                if(remain < 0) {
                    remain = 0;
                }
                //pauses loop for "remain" sec
                Thread.sleep((long) remain);

                nextDraw += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void update() {
        player.update();
        cam.update(player);
        enemy.update();
    }

    //gibt anderen klassen die fähigkeit sprites auf den bildschirm zu projezieren
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //anfang cam
        g2.translate(cam.getX(), cam.getY());
        //alles innerhalb diesen lines wird von der camera influenced //wie: keine ahnung


        tm.draw(g2); //dadurch kann der TileManager drawen
        lh.draw(g2); //dadurch kann der LevelHandler drawen
        player.draw(g2); //dadurch kann der Spieler (=Player) drawen
        enemy.draw(g2);

        g2.translate(-cam.getX(), -cam.getY());
        //ende cam

        //memory saving ig
        g2.dispose();

        //tmp https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2
    }




}
