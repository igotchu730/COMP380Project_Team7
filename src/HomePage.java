import javax.swing.*;

public class HomePage extends JFrame{

    private JPanel mainFrame;
    private JPanel menuPanel;
    private JPanel displayPanel;
    private JLabel Title;
    private JPanel buttonContainer;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JLabel BGImage;

    HomePage(){
        this.add(mainFrame);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setSize(1500,1000);
        this.setVisible(true);
    }

}
