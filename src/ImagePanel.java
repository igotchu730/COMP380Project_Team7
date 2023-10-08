import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;

    public ImagePanel() {
        try {
            backgroundImage = ImageIO.read(new File("src/Images/B2.jpeg"));
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
