package main;

import entity.Enemy;
import entity.NPC;
import entity.Player;
import tile.TileManager;
import Object.SuperObject;
import Object.OBJ_Key;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseListener;

//klasse die basically alles managed vor allem aber den screen und was darauf muss
public class GamePanel extends JPanel implements Runnable{
    Key key = new Key(this);
    Thread gameThread;
    TileManager tm = new TileManager(this);

    public Player player = new Player(this, key, tm);
    public LevelHandler lh = new LevelHandler(this);;
    public CollisionDetector CDetector = new CollisionDetector(this, tm);
    public Pathfinder pathfinder = new Pathfinder(this);
    Camera cam = new Camera(this, -player.x + 1920 / 2, -player.y + 1080 / 2);
    Sound sound = new Sound(this);
    int tileT = 16;
    int multiply = 5;
    //estimate value for tilesize on screen (for pixel art)
    public int tile = tileT * multiply;
    public NPC entities[] = new NPC[4];
    public SuperObject objects[] = new SuperObject[5];
    public boolean NPCspawned;

    //player pos
    int playerx = 100;
    int playery = 100;
    int speed = 4;
    int FPS  = 60;

    public UI ui = new UI(this);

    //states vom game
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;




    public GamePanel(){
        this.setBackground(Color.black);
        //this.setBackground(Color.decode("#74ba67")); //nimmt hex code und wandelt ihn in RGB farbe um
        this.setPreferredSize(new Dimension(512, 328));
        this.addKeyListener(key);
        this.setFocusable(true);
        startGameThread();
        Start();
    }

    public void Start() {
        gameState = playState;
        ui.showMessage("Drücke W, A, S oder D um dich zu bewegen!", 5);
        //playMusic(0);
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

    public void setNPC(int x, int y) {

        if(entities[0] == null) {
            entities[0] = new Enemy(this, x, y);
            NPCspawned = true;
        } else if(entities[0].dead) {
            entities[1] = new Enemy(this, x, y);
            NPCspawned = true;
        } else {
            //CANT SPAWN ENEMY
        }

    }

    public void setObjects(int x, int y) {
        objects[0] = new OBJ_Key(this, x, y);
    }

    public void update() {
        if(gameState == playState) {
            player.update();
            cam.update(player);
            for(int i = 0; i <  entities.length; i++) {
                if(entities[i] != null) {
                    entities[i].update();
                    entities[i].fakeUpdate();
                }
            }

        }
        if(gameState == pauseState) {

        }
    }

    //gibt anderen klassen die fähigkeit sprites auf den bildschirm zu projezieren
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //anfang cam
        g2.translate(cam.getX(), cam.getY());
        //alles innerhalb diesen lines wird von der camera influenced //wie: keine ahnung


        tm.draw(g2); //dadurch kann der TileManager drawen

        int n = 0;
        if(n == 0) {
            lh.draw(g2); //dadurch kann der LevelHandler drawen
            n++;
        }if(n == 60) {
            n = 0;
        }



        for(int i = 0; i <  entities.length; i++) {
            if(entities[i] != null) {
                entities[i].draw(g2);
            }
        }

        for(int i = 0; i <  objects.length; i++) {
            if(objects[i] != null) {
                objects[i].draw(g2, this);
            }
        }

        player.draw(g2); //dadurch kann der Spieler (=Player) drawen

        g2.translate(-cam.getX(), -cam.getY());
        //ende cam

        ui.draw(g2); //hier wird die UI gedrawt -> außerhalb des translates da es sich unabhängig von der kamera befindet

        //memory saving ig
        g2.dispose();

        //tmp https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }




}
