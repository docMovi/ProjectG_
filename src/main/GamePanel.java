package main;

import entity.Enemy;
import entity.Entity;
import entity.NPC;
import entity.Player;
import tile.BufferdImageLoader;
import tile.TileManager;
import Object.SuperObject;
import Object.OBJ_Key;
import Object.OBJ_Door;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

//klasse die basically alles managed vor allem aber den screen und was darauf muss
public class GamePanel extends JPanel implements Runnable{
    Key key = new Key(this);
    Thread gameThread;
    TileManager tm = new TileManager(this);

    public Player player = new Player(this, key, tm);
    public BufferdImageLoader loader = new BufferdImageLoader();
    BufferedImage img[]  = new BufferedImage[16]; //bindet level image ein
    public LevelHandler lh = new LevelHandler(this, loader.loadImage("/level/level.png"));
    public CollisionDetector CDetector = new CollisionDetector(this, tm);
    public Pathfinder pathfinder = new Pathfinder(this);
    Camera cam = new Camera(this, -player.x + 1920 / 2, -player.y + 1080 / 2);
    Sound sound = new Sound(this);
    Sound music = new Sound(this);
    int tileT = 16;
    int multiply = 5;
    //estimate value for tilesize on screen (for pixel art)
    public int tile = tileT * multiply;
    public SuperObject objects[] = new SuperObject[5];
    public boolean NPCspawned;

    int FPS  = 60;

    public UI ui = new UI(this);

    //states vom game
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    public final int winState = 4;

    //dinge im spiel gespeichert als array
    public NPC entities[] = new NPC[32];
    public int[] enemies = new int[32];
    public int[] keys = new int[16];
    public int[] doors = new int[16];

    int currentlvl;
    //FÜR RESTART
    public boolean restarting = false;

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
        playMusic(0);
        img[0] = loader.loadImage("/level/level.png");
        img[1] = loader.loadImage("/level/level2.png");
        img[2] = loader.loadImage("/level/level3.png");
        img[3] = loader.loadImage("/level/level4.png");
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void GameWin(){
        gameState = winState;
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

    int tmp = 0;
    boolean executed = false;
    public void setNPC() {
       if(!executed) {
               for (int i = 0; i < enemies.length; i++) {
                   if (enemies[i] > 0) {
                       entities[tmp] = new Enemy(this, i, enemies[i], tmp);
                       tmp++;
                   }
               }
               executed = true;
               NPCspawned = true;
           }
       }


    public void destroyNPC(int ind) {
        entities[ind] = null;
    }

    int tmp2 = 0;
    boolean executedKEY;
    public void setKey() {
        if(!executedKEY) {
            for (int i = 0; i < keys.length; i++) {
                if (keys[i] > 0) {
                    objects[tmp2] = new OBJ_Key(this, i, keys[i],"E");
                    tmp2++;
                }
            }
            executedKEY= true;
        }
    }

    boolean executedDOOR;

    public void setDoor() {
        if(!executedDOOR)
        for (int i = 0; i < doors.length; i++) {
            if(doors[i] > 0) {
                objects[tmp2] = new OBJ_Door(this, i, doors[i]);
                tmp2++;
            }
        }
        executedDOOR = true;
    }

    public int deadEnemies(){
        int r = 0;

        for(int i = 0; i < entities.length; i++) {
            if(entities[i] == null){
                r++;
            }
        }
        return r;
    }

    boolean executedLVL = false;

    public void update() {
        if(!restarting) {
            if(gameState == playState) {
                player.update();
                cam.update(player);
                for(int i = 0; i <  entities.length; i++) {
                    if(entities[i] != null) {
                        entities[i].update();
                        entities[i].fakeUpdate();
                    }
                }
                for(int i = 0; i <  objects.length; i++) {
                    if(objects[i] != null) {
                        objects[i].update();
                    }
                }

            }
            if(gameState == gameOverState) {
                if(key.spacebar) {
                    restarting = true;
                    Restart(false);
                }
            }
            if(gameState == winState)
            {
                if(key.spacebar) {
                    restarting = true;
                    Restart(true);
                }
            }
            System.out.println(currentlvl);
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
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
    public void playSE(int i, Entity E) {
        if (!E.dead) {
            sound.setFile(i);
            sound.play();
        }
    }

    boolean sceneClear;

    public void Restart(boolean didWin) {
        if(didWin) {
            if (!executedLVL){
                saveValues();
                executedLVL = true;
            }
        }
        ClearScene();
        if(sceneClear) {
            SetScene();
        }
    }

    int health;
    int points; //tmp
    public void saveValues() {
        health = player.hp;
        currentlvl++;

    }
    public void ClearScene() {
        player = null;
        lh = null;
        ui = null;
        cam = null;
        tmp = 0;
        tmp2 = 0;
        executed = false;
        executedDOOR = false;
        executedKEY = false;
        executedLVL = false;
        NPCspawned = false;

        for(int i = 0; i < entities.length; i++) {
            entities[i] = null;
        }
        for(int i = 0; i < objects.length; i++) {
            objects[i] = null;
        }
        for(int i = 0; i < keys.length; i++) {
            keys[i] = 0;
        }
        for(int i = 0; i < doors.length; i++) {
            doors[i] = 0;
        }
        for(int i = 0; i < enemies.length; i++) {
            enemies[i] = 0;
        }
        sceneClear = true;
    }

    public void SetScene() {
        entities = new NPC[32];
        objects = new SuperObject[32];
        enemies = new int[16];
        keys = new int[16];
        doors = new int[16];
        player = new Player(this, key, tm);
        lh = new LevelHandler(this, img[currentlvl]);
        ui = new UI(this);
        player.hp = health;
        cam = new Camera( this, -player.x + 1920 / 2, -player.y + 1080 / 2);
        gameState = playState;
        sceneClear = false;
    }




}
