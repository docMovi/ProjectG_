package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC extends Entity{

    Random random;

    public int actionTimer;
    public BufferedImage[] images;


    int tmpX, tmpY;
    int aggroRange;
    boolean inRange;
    public int NpcType; // 0 = player, 1 = npc, 2 = enemy

    public NPC(GamePanel gp) {
        super(gp);

        dir = "down";
        speed = 1;

        this.x = x;
        this.y = y;

        random = new Random();

        collider = new Rectangle(10, 20, 45, 50);

        images = new BufferedImage[8];

        tmpX = x;
        tmpY = y;

    }



    public BufferedImage GetImage(String direction){
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(direction));
        } catch (IOException e) {

        }

        return img;
    }

        public void setAction() {

            if(!stop) {
                actionTimer++;
                String tmpDir = dir;

                if(actionTimer == 180) {
                    int i = random.nextInt(100) + 1; //zahl von + x bis klammer

                    if(i <= 25) {
                        if(tmpDir != "up") {
                            dir  = "up";
                            collOn = false;
                        }
                    }

                    if(i > 25 && i < 50) {
                        if(tmpDir != "down") {
                            dir = "down";
                            collOn = false;
                        }
                    }

                    if(i > 50 && i<75) {
                        if(tmpDir != "left") {
                            dir = "left";
                            collOn = false;
                        }
                    }

                    if(i > 75 && i <= 100) {
                        if(tmpDir != "up") {
                            dir = "right";
                            collOn = false;
                        }
                    }

                    actionTimer = 0;
                }
            }



        }

        public void update() {
          if(!dead) {
              inRange = gp.pathfinder.isPlayerInRadius(aggroRange, this);

              System.out.println(inRange);
              if(inRange) {
                  gp.pathfinder.FollowPlayer(this);
              }

              if(x > tmpX || x < tmpX || y > tmpY || y < tmpY) {
                  //wenn der NPC sich bewegt
                  tmpX = x;
                  tmpY = y;

                  //NPC collision detecten
                  gp.CDetector.checkTile(this);
                  gp.CDetector.checkPlayer(this);
              }

              setAction();

              wait++;
              if(wait > 10) {
                  if (num == 1) {
                      num = 2;
                  } else if (num == 2) {
                      num = 1;
                  }
                  wait = 0;
              }

              if (collOn == false) {
                  if (dir == "up") {
                      y -= speed;
                  }

                  if (dir == "down") {
                      y += speed;
                  }
                  if (dir == "right") {
                      x += speed;
                  }
                  if (dir == "left") {
                      x -= speed;
                  }
              } else {
                  if(!stop){
                      setAction();
                  } else {
                      gp.pathfinder.Colliding(this);
                      collOn = false;
                  }

              }


              boolean contactPlayer = gp.CDetector.checkPlayer(this);
              if(NpcType == 3 && contactPlayer) {
                  //theoretisch hier schaden
                  if(!gp.player.attacking) {
                      gp.player.takeDamage();
                  }
                  else if(gp.player.attacking) {
                      takeDamage();
                  }
              }
          }else {
              if(deathNum > 5) {
                  x = 0;
                  y = 0;
              }
          }


    }



        public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if(dead) {
            if(deathNum == 1) {
                image = dying1;
            }
            else if (deathNum == 2){
                image = dying2;
            }
            else if (deathNum == 3){
                image = dying3;
            }
            else if (deathNum == 4){
                image = dying4;
            }
            else if (deathNum == 5){
                image = dying5;
            }

           if(deathNum < 6) { g2.drawImage(image, x, y, gp.tile, gp.tile, null);}
        }

        if(!dead) {
            //hier wird der npc als bild erstellt
            //spieler npc je nach richtung ändern
            if(dir == "up") {
                if(num == 1) {
                    image = up1;
                }
                else if (num == 2) {
                    image = up2;
                }
            }else if(dir == "down") {
                if(num == 1) {
                    image = d1;
                }
                else if (num == 2) {
                    image = d2;
                }
            }else if(dir == "right") {
                if(num == 1) {
                    image = r1;
                }
                else if (num == 2) {
                    image = r2;
                }
            }else if(dir == "left") {
                if(num == 1) {
                    image = l1;
                }
                else if (num == 2) {
                    image = l2;
                }
            }

            if(invincible) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
            g2.drawImage(image, x, y, gp.tile, gp.tile, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

          }
        }


}


    

