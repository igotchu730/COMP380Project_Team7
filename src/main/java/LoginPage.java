import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPage extends JFrame implements ActionListener {

    public static boolean isLoggedIn = false;
    private JPanel mainFrame;

    private JPanel cardPanel;
    private JTextField userField;
    private JPasswordField pwField;
    public JButton loginButton;
    private JLabel loginIcon;
    private JLabel loginTitle;
    private JLabel userLabel;
    private JLabel pwLabel;
    private JPanel loginPanel;
    private JLabel errorMessage;

    LoginPage(){
        this.add(mainFrame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,500);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        errorMessage.setVisible(false);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        KeyAdapter enterKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        };
        userField.addKeyListener(enterKeyAdapter);
        pwField.addKeyListener(enterKeyAdapter);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            String userID = userField.getText();
            String password = String.valueOf(pwField.getPassword());

            if(DatabaseAccess.getUserName(userID) != null && DatabaseAccess.getPassword(userID).equals(password)) {
                isLoggedIn = true;
                JOptionPane.showMessageDialog(null,"Welcome " + userID);
                dispose();
                AdminPage adminPage = new AdminPage();
            }
            else{
                errorMessage.setVisible(true);
            }
        }
    }

}
