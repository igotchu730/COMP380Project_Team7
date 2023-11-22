import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AdminPage: implements the manager's portal after login. Contains all the administrative functionalities.
 * @since 2023-10-28
 * @author Chris Guevara, Alan Chu
 */

public class AdminPage extends JFrame implements ActionListener {
    private JPanel mainFrame;
    private JPanel mainPanel;
    private JLabel adminTitle;
    private JButton logOutButton;
    private JLabel adminIcon;
    private JButton reportButton;
    private JPanel reportPanel;
    private JScrollPane queryScrollPane;
    private JTable queryTable;
    private JPanel queryPanel;
    private JButton allTransactionsButtons;
    private JButton rosterButton;
    private JLabel repTitle;
    private JButton roomSearchButton;
    private JComboBox roomNumber;
    private JComboBox checkinMonth;
    private JComboBox checkinDay;
    private JComboBox checkinYear;
    private JComboBox checkoutYear;
    private JComboBox checkoutDay;
    private JComboBox checkoutMonth;
    private JPanel roomSearchPanel;
    private JButton homeButton1;
    private JButton backButton;
    private JLabel repLogo;
    private JTextArea textArea1;

    CardLayout cardLayout;

    AdminPage(){
        this.add(mainFrame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Administrator");

        ImageIcon icon = new ImageIcon(HomePage.class.getResource("Icons/windowLogo.png"));
        this.setIconImage(icon.getImage());

        logOutButton.addActionListener(this);
        reportButton.addActionListener(this);
        allTransactionsButtons.addActionListener(this);
        rosterButton.addActionListener(this);
        roomSearchButton.addActionListener(this);
        homeButton1.addActionListener(this);
        backButton.addActionListener(this);

        //For adminIcon
        ImageIcon admIcon = new ImageIcon("src/Icons/adminIcon.png");
        Image image1 = admIcon.getImage();
        Image newimg1 = image1.getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH);
        admIcon = new ImageIcon(newimg1);
        adminIcon.setIcon(admIcon);

        cardLayout = (CardLayout)(mainFrame.getLayout());

        String[] rooms = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18"};
        roomNumber.setModel(new DefaultComboBoxModel(rooms));

        String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        checkinMonth.setModel(new DefaultComboBoxModel(month));
        checkoutMonth.setModel(new DefaultComboBoxModel(month));

        String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                        "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        checkinDay.setModel(new DefaultComboBoxModel(day));
        checkoutDay.setModel(new DefaultComboBoxModel(day));

        // Calculate the range of years (current year and future years)
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int numberOfYears = 5; // Change this to adjust the number of future years to include
        String[] years = new String[numberOfYears];
        for (int i = 0; i < numberOfYears; i++) {
            years[i] = Integer.toString(currentYear + i);
        }
        checkinYear.setModel(new DefaultComboBoxModel(years));
        checkoutYear.setModel(new DefaultComboBoxModel(years));

        //home buttons
        ImageIcon homeIcon = new ImageIcon("src/Icons/homeButtonIcon1.png");
        Image scaledImage = homeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(scaledImage);
        homeButton1.setIcon(homeIcon);

        //back buttons
        ImageIcon backIcon = new ImageIcon("src/Icons/returnIcon.png");
        Image scaledImage2 = backIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        backIcon = new ImageIcon(scaledImage2);
        backButton.setIcon(backIcon);

        //For logo
        ImageIcon repIcon = new ImageIcon("src/Icons/reportLogo2.png");
        Image image2 = repIcon.getImage();
        Image newimg2 = image2.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH);
        repIcon = new ImageIcon(newimg2);
        repLogo.setIcon(repIcon);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logOutButton){
            LoginPage.isLoggedIn = false;
            JOptionPane.showMessageDialog(null,"Logged Out");
            dispose();
        }
        if(e.getSource() == homeButton1){
            cardLayout.show(mainFrame, "Card1");
            this.setResizable(false);
            this.setSize(500,600);
        }
        if(e.getSource() == backButton){
            cardLayout.show(mainFrame, "Card2");
            this.setResizable(false);
            this.setSize(500,600);
            this.setLocationRelativeTo(null);
        }
        if(e.getSource() == reportButton){
            cardLayout.show(mainFrame, "Card2");
        }
        if(e.getSource() == allTransactionsButtons){
            cardLayout.show(mainFrame, "Card3");
            this.setResizable(true);
            this.setSize(1000,1000);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            String sampleQuery = "SELECT * FROM Transactions";
            DatabaseAccess.displayQueryResults(queryTable, sampleQuery);
        }
        if(e.getSource() == rosterButton){
            cardLayout.show(mainFrame, "Card3");
            this.setResizable(true);
            this.setSize(1000,1000);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            String sampleQuery = "SELECT * FROM Rooms_Roster";
            DatabaseAccess.displayQueryResults(queryTable, sampleQuery);
        }
        if(e.getSource() == roomSearchButton){
            cardLayout.show(mainFrame, "Card3");
            this.setResizable(true);
            this.setSize(1000,1000);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);

            String CI = "'" + checkinYear.getSelectedItem() + "-" + checkinMonth.getSelectedItem() + "-" + checkinDay.getSelectedItem() + "'";
            String CO = "'" + checkoutYear.getSelectedItem() + "-" + checkoutMonth.getSelectedItem() + "-" + checkoutDay.getSelectedItem() + "'";

            String sampleQuery = "SELECT *\n" +
                                 "FROM Transactions\n" +
                                 "WHERE room_number = " + roomNumber.getSelectedItem() + "\n" +
                                 "AND checkin_date <= " + CO + "\n" +
                                 "AND checkout_date >= " + CI + ";";
            DatabaseAccess.displayQueryResults(queryTable, sampleQuery);
        }
    }

}
