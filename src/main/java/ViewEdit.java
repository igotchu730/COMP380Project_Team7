import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewEdit extends JFrame implements ActionListener {

    private JPanel mainFrame;
    private JPanel mainPanel;
    private JLabel viewEditTitle;
    private JButton viewButton;
    private JButton homeButton1;
    private JButton editButton;
    private JPanel viewResPanel;
    private JPanel editResPanel;
    private JTable queryViewTable;
    private JScrollPane viewScrollPane;
    private JButton backButton;
    private JButton cancelButton;
    private JLabel resIDTitle;
    private JLabel resIDLabel;
    private JLabel paymentLabel;
    private JTextField cardNumberField;
    private JLabel cardNumberLabel;
    private JTextField cardMonthField;
    private JTextField cardYearField;
    private JLabel guestInfoLabel;
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
    private JButton changeButton;
    private JButton backButton2;
    private JLabel spacer2;
    private JLabel spacer4;


    String userInput;
    CardLayout cardLayout;

    ViewEdit(){

        this.add(mainFrame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("View/Edit Reservation");
        //mainScrollPane.getVerticalScrollBar().setUnitIncrement(18);
        spacer4.setText(" ");

        ImageIcon icon = new ImageIcon(HomePage.class.getResource("Icons/windowLogo.png"));
        this.setIconImage(icon.getImage());

        //home buttons
        ImageIcon homeIcon = new ImageIcon("src/Icons/homeButtonIcon1.png");
        Image scaledImage = homeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(scaledImage);
        homeButton1.setIcon(homeIcon);

        //back button
        ImageIcon backIcon = new ImageIcon("src/Icons/returnIcon.png");
        Image scaledImage2 = backIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        backIcon = new ImageIcon(scaledImage2);
        backButton.setIcon(backIcon);

        //back button2
        ImageIcon backIcon2 = new ImageIcon("src/Icons/returnIcon.png");
        Image scaledImage3 = backIcon2.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        backIcon2 = new ImageIcon(scaledImage3);
        backButton2.setIcon(backIcon2);

        homeButton1.addActionListener(this);
        viewButton.addActionListener(this);
        editButton.addActionListener(this);
        backButton.addActionListener(this);
        backButton2.addActionListener(this);
        cancelButton.addActionListener(this);
        changeButton.addActionListener(this);

        cardLayout = (CardLayout)(mainFrame.getLayout());

        userInput = null;

        while (userInput == null || !DatabaseAccess.isReserveID(userInput)) {
            userInput = JOptionPane.showInputDialog("Enter Reservation ID:");

            if (userInput == null) {
                // User pressed Cancel or closed the dialog
                //System.out.println("User canceled the operation. Stopping input.");
                throw new IllegalArgumentException("");
                //break;
            } else if (!DatabaseAccess.isReserveID(userInput)) {
                // Invalid input, display an error message
                JOptionPane.showMessageDialog(null, "Invalid Reservation ID. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Valid input
                resIDLabel.setText(userInput);
                System.out.println("User entered valid Reservation ID: " + userInput);
            }
        }

        //restrict these fields to only accept numbers
        Reservation.restrictToNumbers(cardMonthField,2);
        Reservation.restrictToNumbers(cardYearField,4);
        Reservation.restrictToNumbers(zipField,10);

        //limit amount of characters
        Reservation.limitTextFieldToCharacters(cardNumberField,40);
        Reservation.limitTextFieldToCharacters(firstNameField,40);
        Reservation.limitTextFieldToCharacters(lastNameField,40);
        Reservation.limitTextFieldToCharacters(emailField,40);
        Reservation.limitTextFieldToCharacters(phoneField,40);
        Reservation.limitTextFieldToCharacters(countryField,40);
        Reservation.limitTextFieldToCharacters(addressField,40);
        Reservation.limitTextFieldToCharacters(cityField,40);
        Reservation.limitTextFieldToCharacters(stateField,40);

        this.setVisible(true);
    }

    // check all fields are filled
    private boolean areAllFieldsFilled() {
        // Add more text fields as needed
        JTextField[] textFields = {cardNumberField, cardMonthField, cardYearField,
                firstNameField, lastNameField, emailField, phoneField,
                countryField, addressField, zipField, cityField, stateField};

        for (JTextField textField : textFields) {
            if (textField.getText().trim().isEmpty()) {
                return false; // At least one text field is empty
            }
        }
        return true; // All text fields are filled
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == homeButton1){
            this.dispose();
        }
        if(e.getSource() == backButton){
            cardLayout.show(mainFrame, "Card1");
            spacer4.setText(" ");
            this.setResizable(false);
            this.setSize(500,400);
            this.setLocationRelativeTo(null);
        }
        if(e.getSource() == backButton2){
            cardLayout.show(mainFrame, "Card1");
            spacer4.setText(" ");
            this.setResizable(false);
            this.setSize(500,400);
            this.setLocationRelativeTo(null);
        }
        if(e.getSource() == viewButton){
            cardLayout.show(mainFrame, "Card2");
            this.setResizable(true);
            this.setSize(1000,1000);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setLocationRelativeTo(null);
            String sampleQuery = "SELECT * FROM Transactions WHERE transaction_id = '" + userInput + "'";
            DatabaseAccess.displayQueryResults(queryViewTable, sampleQuery);
        }
        if(e.getSource() == editButton){
            cardLayout.show(mainFrame, "Card3");
            spacer4.setText(" ");
            this.setResizable(false);
            this.setSize(600,800);
            this.setLocationRelativeTo(null);
        }
        if(e.getSource() == changeButton && areAllFieldsFilled()){

            //record all user inputs
            String first_name = firstNameField.getText();
            String last_name = lastNameField.getText();
            String card_number = cardNumberField.getText();
            int card_month = Integer.parseInt(cardMonthField.getText());
            int card_year = Integer.parseInt(cardYearField.getText());
            String email = emailField.getText();
            String phone_number = phoneField.getText();
            String country = countryField.getText();
            String address = addressField.getText();
            int zip = Integer.parseInt(zipField.getText());
            String city = cityField.getText();
            String state = stateField.getText();
            String transaction_date = DatabaseAccess.getCurrentDateTime();

            DatabaseAccess.executeQuery("UPDATE Transactions SET transaction_date = '" + transaction_date + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET first_name = '" + first_name + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET last_name = '" + last_name + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET card_number = '" + card_number + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET card_month = '" + card_month + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET card_year = '" + card_year + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET email = '" + email + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET phone_number = '" + phone_number + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET country = '" + country + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET address = '" + address + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET zip = '" + zip + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET city = '" + city + "' WHERE transaction_id = '" + userInput + "';");
            DatabaseAccess.executeQuery("UPDATE Transactions SET state = '" + state + "' WHERE transaction_id = '" + userInput + "';");

            JOptionPane.showMessageDialog(null, "Changes Saved");

            cardLayout.show(mainFrame, "Card1");
            this.setResizable(false);
            this.setSize(500,400);
            this.setLocationRelativeTo(null);
        }
        if(e.getSource() == cancelButton){
            int userChoice = JOptionPane.showConfirmDialog(null,"Confirm Cancellation?",
                    null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(userChoice == 0){
                DatabaseAccess.executeQuery("DELETE FROM Transactions WHERE transaction_id = '" + userInput + "';");
                this.dispose();
                JOptionPane.showMessageDialog(null, "Reservation Canceled");
            }
        }
        if(e.getSource() == changeButton && !areAllFieldsFilled()){
            spacer4.setText("Please fill all fields.");
        }

    }

}
