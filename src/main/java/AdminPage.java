import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame implements ActionListener {
    private JPanel mainFrame;
    private JLabel adminTitle;
    private JButton logOutButton;

    AdminPage(){
        this.add(mainFrame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,500);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        logOutButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logOutButton){
            LoginPage.isLoggedIn = false;
            JOptionPane.showMessageDialog(null,"Logged Out");
            dispose();
        }
    }

}
