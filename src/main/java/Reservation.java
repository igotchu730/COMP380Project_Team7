import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reservation extends JFrame implements ActionListener {

    private JPanel mainFrame;
    private JPanel mainPanel;
    private JScrollPane mainScrollPane;
    private JPanel ourRoomsTitleContainer;
    private JLabel mainTitle;
    private JPanel infoPanel;
    private JTextField cardNumberField;
    private JLabel totalLabel;
    private JLabel paymentLabel;
    private JLabel cardNumberLabel;
    private JTextField cardMonthField;
    private JTextField cardYearField;
    private JLabel cardYearLabel;
    private JLabel cardMonthLabel;
    private JLabel guestInfoLabel;
    private JLabel spacer1;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JTextField countryField;
    private JTextField addressField;
    private JTextField zipField;
    private JLabel countryLabel;
    private JLabel addressLabel;
    private JLabel zipLabel;
    private JTextField cityField;
    private JTextField stateField;
    private JLabel cityLabel;
    private JLabel stateLabel;
    private JLabel priceLabel;
    private JLabel hotelLabel;
    private JLabel checkDateLabel;
    private JLabel occupantsLabel;
    private JLabel roomNumLabel;
    private JLabel roomTypeLabel;
    private JLabel spacer2;
    private JLabel spacer3;
    private JButton bookReservationButton;
    private JLabel spacer4;

    // Declare a static instance of Reservation
    private static Reservation instance;

    Reservation() {
        this.add(mainFrame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 800);
        this.setTitle("Reservation");
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(18);
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        bookReservationButton.addActionListener(this);
    }

    // Public method to get the instance of Reservation
    public static Reservation getInstance() {
        if (instance == null) {
            // If the instance is null, create a new one
            instance = new Reservation();
        }
        return instance;
    }

    @Override
    public void dispose() {
        // Set the instance to null when the frame is disposed
        instance = null;
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bookReservationButton){
            this.dispose();
        }
    }

}
