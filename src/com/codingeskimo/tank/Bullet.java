package com.codingeskimo.tank;

import java.awt.*;

public class Bullet {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 10;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private boolean living = true;
    private TankFrame tankFrame = null;
    private Group group = Group.BAD;

    public Bullet(int x, int y, Dir dir, boolean living, Group group, TankFrame tankFrame) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.living = living;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public boolean isLive() {
        return living;
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

        moving();
    }

    private void moving() {
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

        //Judge whether the bullet is out of the boundary
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    public void collidWith(Tank tank) {
        if (this.group == tank.getGroup()) { return; }

        Rectangle bRect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle tRect = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (bRect.intersects(tRect)) {
            tank.die();
            this.die();
            int ex = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int ey = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tankFrame.explodes.add(new Explode(ex, ey, tankFrame));
        }
    }

    private void die() {
        this.living = false;
    }
}
