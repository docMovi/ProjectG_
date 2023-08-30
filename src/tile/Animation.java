package tile;

import entity.Entity;

import java.awt.image.BufferedImage;

public class Animation {
    private Entity ent;
    BufferedImage frame = null;
    private BufferedImage[] images = new BufferedImage[16];
    int time;
    int m = 0;
    int j = 1;
    boolean animRunning;
    BufferedImage idle = null;

    public Animation(Entity ent, BufferedImage images[], int timeBetweenAnimation){
        this.images = images;
        this.ent = ent;
        time = timeBetweenAnimation;
    }
    public Animation(Entity ent, BufferedImage images[], int timeBetweenAnimation, BufferedImage idle){
        this.images = images;
        this.ent = ent;
        time = timeBetweenAnimation;
        this.idle = idle;
    }

    public BufferedImage play(){
        if(m < images.length * time){
            if(m < j * time && m >= (j - 1) * time){
                frame = images[j - 1];
                m++;
            }else {
                j++;
            }

        }else if(m >= images.length * time){
            m = 0;
            j = 1;
        }

        animRunning = true;

        return frame;
    }

    public BufferedImage playWithBuffer(int bufferedFrame, int newTime){
            animRunning = true;
            if(m < (images.length - 1)  * time + newTime) {
                if (m < j * time && m >= (j - 1) * time && j < bufferedFrame) {
                    frame = images[j - 1];
                    m++;
                }else if(m < (j + 1) * time && m >= j * time && j > bufferedFrame) {
                    frame = images[j - 1];
                    m++;
                }
                else if (j == bufferedFrame) {
                    if (m < ((j - 1)* time) + newTime) {
                        frame = images[j - 1];
                        m++;
                    } else {
                        j = bufferedFrame + 1;
                        System.out.println("j: " + j);
                    }
                } else {
                    j++;
                    System.out.println("j: " + j);
                }
                System.out.println("m: " + m);
            }
            else if(m >= (images.length - 1)  * time + newTime){
                m = 0;
                j = 1;
                animRunning = false;
                stop();
            }

        return frame;
    }

    public void stop() {
        if(idle != null){
            frame = idle;
        }
    }

    public boolean isAnimRunning() {
        return animRunning;
    }
}
