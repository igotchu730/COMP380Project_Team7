import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Reservation: The page where users will enter their information (payment, name, etc.) for their reservation.
 * @since 2023-11-19
 * @author Alan Chu, Chris Guevara
 */

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
    private JLabel roomNumLabel;
    private JLabel roomTypeLabel;
    private JLabel spacer2;
    private JLabel spacer3;
    private JButton bookReservationButton;
    private JLabel spacer4;
    private JLabel priceBreakLabel;
    private JLabel detailsLabel;

    // Declare a static instance of Reservation
    private static Reservation instance;

    public static String transaction_id;
    public static String checkin;
    public static String checkout;
    public static String roomType;
    public static int roomNum;
    public static long daysDifference;
    public static double total_cost;

    Reservation() {
        this.add(mainFrame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 800);
        this.setTitle("Reservation");
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(18);
        setLocationRelativeTo(null);
        this.setResizable(false);

        ImageIcon icon = new ImageIcon(HomePage.class.getResource("Icons/windowLogo.png"));
        this.setIconImage(icon.getImage());

        transaction_id = DatabaseAccess.generateRandomId(10);

        //take info from book room
        checkin = BookRoom.infoForReservation[0];
        checkout = BookRoom.infoForReservation[1];
        roomType = BookRoom.infoForReservation[2];
        roomNum = Integer.parseInt(BookRoom.infoForReservation[3]);
        daysDifference = DatabaseAccess.getDaysDifference(checkin, checkout);

        // update labels
        checkDateLabel.setText("Check-in: " + "(" + checkin + ")" + "  -  " + "Check-out: " + "(" + checkout + ")");
        roomTypeLabel.setText("Room Type: " + roomType);
        roomNumLabel.setText("Room Number: " + roomNum);

        if(roomType.equals("Single")){
            total_cost = 150.00 * daysDifference;
            priceLabel.setText("$" + total_cost);
            priceBreakLabel.setText("$150.00 x " + daysDifference + " Nights");
        }
        if(roomType.equals("Double")){
            total_cost = 200.00 * daysDifference;
            priceLabel.setText("$" + total_cost);
            priceBreakLabel.setText("$200.00 x " + daysDifference + " Nights");
        }
        if(roomType.equals("Family")){
            total_cost = 300.00 * daysDifference;
            priceLabel.setText("$" + total_cost);
            priceBreakLabel.setText("$300.00 x " + daysDifference + " Nights");
        }
        if(roomType.equals("Luxury")){
            total_cost = 400.00 * daysDifference;
            priceLabel.setText("$" + total_cost);
            priceBreakLabel.setText("$400.00 x " + daysDifference + " Nights");
        }

        //restrict these fields to only accept numbers
        restrictToNumbers(cardMonthField,2);
        restrictToNumbers(cardYearField,4);
        restrictToNumbers(zipField,10);

        //limit amount of characters
        limitTextFieldToCharacters(cardNumberField,40);
        limitTextFieldToCharacters(firstNameField,40);
        limitTextFieldToCharacters(lastNameField,40);
        limitTextFieldToCharacters(emailField,40);
        limitTextFieldToCharacters(phoneField,40);
        limitTextFieldToCharacters(countryField,40);
        limitTextFieldToCharacters(addressField,40);
        limitTextFieldToCharacters(cityField,40);
        limitTextFieldToCharacters(stateField,40);

        bookReservationButton.addActionListener(this);

        this.setVisible(true);
    }

    // Public method to get the instance of Reservation
    public static Reservation getInstance() {
        if (instance == null) {
            // If the instance is null, create a new one
            instance = new Reservation();
        }
        return instance;
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

    //restrict the fields to only accept numbers
    private void restrictToNumbers(JTextField textField, int characterLimit) {
        AbstractDocument document = (AbstractDocument) textField.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (isNumeric(string) && (fb.getDocument().getLength() + string.length()) <= characterLimit) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength() - length) + text;
                if (isNumeric(text) && newText.length() <= characterLimit) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean isNumeric(String str) {
                return str.matches("\\d*"); // Allows only digits (0-9)
            }
        });
    }

    private void limitTextFieldToCharacters(JTextField textField, int limit) {
        AbstractDocument document = (AbstractDocument) textField.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (fb.getDocument().getLength() + string.length() <= limit) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                int newLength = fb.getDocument().getLength() - length + text.length();
                if (newLength <= limit) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    @Override
    public void dispose() {
        // Set the instance to null when the frame is disposed
        instance = null;
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bookReservationButton && areAllFieldsFilled()){

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

            int userChoice = JOptionPane.showConfirmDialog(null,"Confirm Reservation?",
                        null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(userChoice == 0){
                DatabaseAccess.setReservation(transaction_id, DatabaseAccess.getCurrentDateTime(), roomNum, roomType,
                                              checkin, checkout, first_name, last_name, total_cost, card_number, card_month, card_year, email,
                                              phone_number, country, address, zip, city, state);
                this.dispose();
                JOptionPane.showMessageDialog(null, "Reservation Confirmed:\nTransaction ID: " + transaction_id);
            }
        }
    }

}
