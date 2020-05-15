package test;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {
    @Test
    void test() {
        try {
            BufferedImage image = ImageIO.read(
                    new File("D:\\Study\\src\\java_fundation\\tank\\src\\images\\bulletD.gif"));
            assertNotNull(image);

            BufferedImage image2 = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream("images\\bulletD.gif")
            );
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
