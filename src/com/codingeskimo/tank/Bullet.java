package com.codingeskimo.tank;

import java.awt.*;

public class Bullet {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 10, WIDTH = 5, HEIGHT = 5;


    public Bullet(int x, int y, Dir dir) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color originColor = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        moving();
        g.setColor(originColor);
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
    }
}
