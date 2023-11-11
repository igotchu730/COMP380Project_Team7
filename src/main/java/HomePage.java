import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {

    private JPanel mainFrame;
    private JPanel menuPanel;
    private JPanel displayPanel;
    private JPanel buttonContainer;
    private JButton ourRoomsButton;
    private JButton bookRoomButton;
    private JButton button3;
    private JButton aboutUsButton;
    private JButton adminButton;
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

        adminButton.addActionListener(this);
        ourRoomsButton.addActionListener(this);
        bookRoomButton.addActionListener(this);

        //For logo
        ImageIcon logo = new ImageIcon("src/Icons/hotelLogo.png");
        Image image1 = logo.getImage();
        Image newimg1 = image1.getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH);
        logo = new ImageIcon(newimg1);
        mainLogo.setIcon(logo);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == adminButton && !LoginPage.isLoggedIn){
            LoginPage loginPage = new LoginPage();
        }
        if(e.getSource() == adminButton && LoginPage.isLoggedIn){
            AdminPage adminPage = new AdminPage();
        }
        if(e.getSource() == ourRoomsButton){
            OurRooms ourRooms = new OurRooms();
        }
        if(e.getSource() == bookRoomButton){
            BookRoom bookRoom = new BookRoom();
        }
    }

}
