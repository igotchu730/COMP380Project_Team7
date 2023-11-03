import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame implements ActionListener {
    private JPanel mainFrame;
    private JLabel adminTitle;
    private JButton logOutButton;
    private JLabel adminIcon;

    AdminPage(){
        this.add(mainFrame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,500);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        logOutButton.addActionListener(this);

        //For adminIcon
        ImageIcon admIcon = new ImageIcon("src/Icons/adminIcon.png");
        Image image1 = admIcon.getImage();
        Image newimg1 = image1.getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH);
        admIcon = new ImageIcon(newimg1);
        adminIcon.setIcon(admIcon);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logOutButton){
            LoginPage.isLoggedIn = false;
            JOptionPane.showMessageDialog(null,"Logged Out");
            dispose();
        }
    }

}
