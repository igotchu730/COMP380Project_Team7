import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * ImagePanel: scales the background image of the MainPage
 * @since 2023-09-25
 * @author Alan Chu
 */

public class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;

    public ImagePanel() {
        try {
            backgroundImage = ImageIO.read(new File("src/main/java/Images/B2.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            int width = getWidth();
            int height = getHeight();

            // Draw the scaled background image to fill the JPanel
            g.drawImage(backgroundImage, 0, 0, width, height, this);
        }
    }
}
