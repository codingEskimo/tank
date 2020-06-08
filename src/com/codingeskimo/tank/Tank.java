package com.codingeskimo.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 3;
    public static final int WIDTH = ResourceMgr.gtankD.getWidth();
    public static final int HEIGHT = ResourceMgr.gtankD.getHeight();
    private boolean moving = true;
    private TankFrame tankFrame = null;
    private boolean living = true;
    private Random random = new Random();
    private Group group = Group.BAD;
    public Rectangle rectangle = new Rectangle();

    public Tank(int x, int y, Dir dir, boolean moving, Group group, TankFrame tankFrame) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.tankFrame = tankFrame;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = this.WIDTH;
        rectangle.height = this.HEIGHT;
    }

    public int getX(){ return this.x; }
    public int getY(){ return this.y; }
    public Group getGroup() { return  this.group; }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.enemies.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.gtankL : ResourceMgr.btankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.gtankR : ResourceMgr.btankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.gtankU : ResourceMgr.btankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.gtankD : ResourceMgr.btankD, x, y, null);
                break;
        }
        moving();
    }

    private void moving() {
        if (!moving) return;
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (this.group == Group.BAD && this.random.nextInt(100) > 90 ) {
            this.fire();
        }
        if (this.group == Group.BAD && this.random.nextInt(100) > 95) {
            this.setRandomDirection();
        }
        boundsCheck();

        rectangle.x = this.x;
        rectangle.y = this.y;
    }
    private void boundsCheck() {
        if (this.x < 2) {
            x = 2;
        }
        if (this.y < 30) {
            y = 30;
        }
        if (this.x > tankFrame.GAME_WIDTH - this.WIDTH - 2) {
            x = tankFrame.GAME_WIDTH - this.WIDTH - 2;
        }

        if (this.y > tankFrame.GAME_HEIGHT - this.HEIGHT - 2) {
            y = tankFrame.GAME_HEIGHT - this.HEIGHT - 2;
        }
    }

    private void setRandomDirection() {
        this.dir = Dir.values()[this.random.nextInt(4)];
    }
    public void fire() {
        // In order to draw the Bullet I new, we need to pass the reference of tankFrame to tank class,
        // see the reference code above
        int bulletX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bulletY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tankFrame.bullets.add(new Bullet(bulletX, bulletY, this.dir, true, this.group, this.tankFrame));
        if(group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        this.living = false;
    }
}
