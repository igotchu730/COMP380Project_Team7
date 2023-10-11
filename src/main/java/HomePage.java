import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame{

    private JPanel mainFrame;
    private JPanel menuPanel;
    private JPanel displayPanel;
    private JPanel buttonContainer;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JSplitPane splitPane;
    private JPanel Logo;
    private JLabel mainLogo;
    private JLabel name1;
    private JLabel name2;
    private JPanel backgroundImage;

    ImagePanel backgroundPanel = new ImagePanel();

    HomePage(){
        this.add(mainFrame);
        backgroundImage.add(backgroundPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1500,1000);
        this.setMinimumSize(new Dimension(1000, 500));
        this.setVisible(true);
    }

}
