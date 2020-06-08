package com.codingeskimo.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Load all the needed image into memory at once
 * **/

public class ResourceMgr {
    private static final ResourceMgr INSTANCE = new ResourceMgr();
    public static BufferedImage gtankL, gtankR, gtankU, gtankD;
    public static BufferedImage btankL, btankR, btankU, btankD;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    private ResourceMgr(){}
    public static ResourceMgr getInstance(){
        return INSTANCE;
    }
    static {
        try {
            gtankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images\\GoodTank1.png"));
            gtankL = ImageUtil.rotateImage(gtankU, -90);
            gtankR = ImageUtil.rotateImage(gtankU, 90);
            gtankD = ImageUtil.rotateImage(gtankU, 180);

            btankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images\\BadTank1.png"));
            btankL = ImageUtil.rotateImage(btankU, -90);
            btankR = ImageUtil.rotateImage(btankU, 90);
            btankD = ImageUtil.rotateImage(btankU, 180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images\\bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(
                        ResourceMgr.class.getClassLoader().getResourceAsStream("images\\e" + (i+1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
