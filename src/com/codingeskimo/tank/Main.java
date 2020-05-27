package com.codingeskimo.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        TankFrame tf  = new TankFrame();

        for (int i = 0; i < 5; i++) {
            tf.enemies.add(new Tank(100 * i, 100, Dir.DOWN, true, Group.BAD, tf));
        }

        while(true) {
            Thread.sleep(50);
            tf.repaint();
        }


    }
}
