package com.codingeskimo.tank;

import java.awt.*;

public class Explode {
    private int x, y;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private TankFrame tankFrame = null;
    private int step = 0;

    public Explode(int x, int y,   TankFrame tankFrame) {
        super();
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], this.x, this.y, null);
        if (step >= ResourceMgr.explodes.length) {
            tankFrame.explodes.remove(this);
        }
    }

}
