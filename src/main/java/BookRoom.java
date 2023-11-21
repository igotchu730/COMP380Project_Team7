import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * BookRoom: The page where the user will select a room and duration of stay for their reservations.
 * @since 2023-11-10
 * @author Chris Guevara, Mohammed Khan, Alan Chu
 */

public class BookRoom extends JFrame implements ActionListener {

    private JPanel mainFrame;
    private JScrollPane mainScrollPane;
    private JPanel mainPanel;
    private JPanel ourRoomsTitleContainer;
    private JLabel mainTitle;
    private JLabel slogan;
    private JPanel singleContainer;
    private JPanel doubleContainer;
    private JPanel familyContainer;
    private JPanel luxContainer;
    private JLabel singleImg;
    private JLabel doubleImg;
    private JLabel famImg;
    private JLabel luxImg;
    private JLabel roomInfo1;
    private JLabel roomInfo2;
    private JLabel roomInfo3;
    private JLabel roomInfo4;
    private JLabel spacer1;
    private JLabel spacer2;
    private JLabel spacer3;
    private JLabel spacer4;
    private JLabel spacer5;
    private JLabel spacer7;
    private JButton homeButton1;
    private JButton bookButton1;
    private JButton bookButton2;
    private JButton bookButton4;
    private JButton bookButton3;
    private JComboBox checkInMonth;
    private JComboBox checkInDay;
    private JComboBox checkInYear;
    private JPanel comboContainer;
    private JComboBox checkOutMonth;
    private JComboBox checkOutDay;
    private JComboBox checkOutYear;
    private JLabel spacer8;
    private JLabel spacer11;
    private JLabel spacer9;
    private JButton searchRooms;
    private JLabel checkInLabel;
    private JLabel checkOutLabel;
    private JLabel dateError;

    public int singleAvailability = 0;
    public int doubleAvailability = 0;
    public int famAvailability = 0;
    public int luxAvailability = 0;

    public static String[]infoForReservation;
    public String checkInDate;
    public String checkOutDate;


    BookRoom(){
        this.add(mainFrame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1000,1000);
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(18);
        DatabaseAccess dbAccess = new DatabaseAccess();
        dateError.setVisible(false);


        //For single room
        ImageIcon singleRoom = new ImageIcon("src/main/java/Images/SingleRoom.png");
        Image image1 = singleRoom.getImage();
        Image newimg1 = image1.getScaledInstance(360, 200,  java.awt.Image.SCALE_SMOOTH);
        singleRoom = new ImageIcon(newimg1);
        singleImg.setIcon(singleRoom);

        roomInfo1.setSize(360,200);
        roomInfo1.setFont(new Font("Calibri",Font.PLAIN,18));
        roomInfo1.setText(
                "<html>" + "Sleeps: 2" + "<br/>" +
                "Room Price: $150.00 " + "per night" + "<br/>" +
                "Rooms Available: " + singleAvailability + "</html>"
        );
        bookButton1.setVisible(false);


        //For double room
        ImageIcon doubleRoom = new ImageIcon("src/main/java/Images/DoubleRoom.png");
        Image image2 = doubleRoom.getImage();
        Image newimg2 = image2.getScaledInstance(360, 200,  java.awt.Image.SCALE_SMOOTH);
        doubleRoom = new ImageIcon(newimg2);
        doubleImg.setIcon(doubleRoom);

        roomInfo2.setFont(new Font("Calibri",Font.PLAIN,18));
        roomInfo2.setText(
                "<html>" + "Sleeps: 4" + "<br/>" +
                "Room Price: $200.00 " + "per night" + "<br/>" +
                "Rooms Available: " + doubleAvailability + "</html>"
        );
        bookButton2.setVisible(false);


        //For family room
        ImageIcon familyRoom = new ImageIcon("src/main/java/Images/FamilyRoom.png");
        Image image3 = familyRoom.getImage();
        Image newimg3 = image3.getScaledInstance(360, 200,  java.awt.Image.SCALE_SMOOTH);
        familyRoom = new ImageIcon(newimg3);
        famImg.setIcon(familyRoom);

        roomInfo3.setFont(new Font("Calibri",Font.PLAIN,18));
        roomInfo3.setText(
                "<html>" + "Sleeps: 6" + "<br/>" +
                "Room Price: $300.00 " + "per night" + "<br/>" +
                "Rooms Available: " + famAvailability + "</html>"
        );
        bookButton3.setVisible(false);


        //For luxury room
        ImageIcon luxuryRoom = new ImageIcon("src/main/java/Images/LuxuryRoom.png");
        Image image4 = luxuryRoom.getImage();
        Image newimg4 = image4.getScaledInstance(360, 200,  java.awt.Image.SCALE_SMOOTH);
        luxuryRoom = new ImageIcon(newimg4);
        luxImg.setIcon(luxuryRoom);

        roomInfo4.setFont(new Font("Calibri",Font.PLAIN,18));
        roomInfo4.setText(
                "<html>" + "Sleeps: 2" + "<br/>" +
                "Room Price: $400.00 " + "per night" + "<br/>" +
                "Rooms Available: " + luxAvailability + "</html>"
        );
        bookButton4.setVisible(false);


        //home buttons
        ImageIcon homeIcon = new ImageIcon("src/Icons/homeButtonIcon1.png");
        Image scaledImage = homeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(scaledImage);
        homeButton1.setIcon(homeIcon);
        homeButton1.addActionListener(this);

        //search button
        ImageIcon searchIcon = new ImageIcon("src/Icons/searchIcon.png");
        Image scaledImage2 = searchIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(scaledImage2);
        searchRooms.setIcon(searchIcon);
        searchRooms.addActionListener(this);



        //Making A 'calender'
        checkInMonth.setPreferredSize(new Dimension(100,30));
        checkInDay.setPreferredSize(new Dimension(100,30));
        checkInYear.setPreferredSize(new Dimension(100,30));
        checkOutMonth.setPreferredSize(new Dimension(100,30));
        checkOutDay.setPreferredSize(new Dimension(100,30));
        checkOutYear.setPreferredSize(new Dimension(100,30));

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        // Calculate the range of years (current year and future years)
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int numberOfYears = 5; // Change this to adjust the number of future years to include
        String[] years = new String[numberOfYears];
        for (int i = 0; i < numberOfYears; i++) {
            years[i] = Integer.toString(currentYear + i);
        }

        checkInMonth.setModel(new DefaultComboBoxModel(months));
        checkInMonth.setSelectedItem(null);
        checkInYear.setModel(new DefaultComboBoxModel(years));
        checkOutMonth.setModel(new DefaultComboBoxModel(months));
        checkOutMonth.setSelectedItem(null);
        checkOutYear.setModel(new DefaultComboBoxModel(years));

        checkInMonth.addActionListener(this);
        checkOutMonth.addActionListener(this);


        bookButton1.addActionListener(this);
        bookButton2.addActionListener(this);
        bookButton3.addActionListener(this);
        bookButton4.addActionListener(this);

        this.setVisible(true);

    }

    /**
     * dateChecker: checks if one date exceeds another date.
     * @param year1
     * @param month1
     * @param day1
     * @param year2
     * @param month2
     * @param day2
     * @return
     */
    public static boolean dateChecker(int year1, int month1, int day1, int year2, int month2, int day2) {
        if (year1 > year2) {
            return true;
        } else if (year1 < year2) {
            return false;
        } else {
            if (month1 > month2) {
                return true;
            } else if (month1 < month2) {
                return false;
            } else {
                return day1 >= day2;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //for home button
        if(e.getSource() == homeButton1){
            dispose();
        }

        //for search button success
        if(e.getSource() == searchRooms && checkInMonth.getSelectedIndex() != -1 && checkOutMonth.getSelectedIndex() != -1){

            DatabaseAccess dbaccess = new DatabaseAccess();

            int selectedMonthIndexIn = checkInMonth.getSelectedIndex() + 1 ;
            int selectedDayIn = Integer.parseInt((String) checkInDay.getSelectedItem());
            int selectedYearIn = Integer.parseInt((String) checkInYear.getSelectedItem());

            int selectedMonthIndexOut = checkOutMonth.getSelectedIndex() + 1;
            int selectedDayOut = Integer.parseInt((String) checkOutDay.getSelectedItem());
            int selectedYearOut = Integer.parseInt((String) checkOutYear.getSelectedItem());

            //check if checkin exceeds checkout dates
            boolean isDateValid = dateChecker(selectedYearIn,selectedMonthIndexIn,selectedDayIn,selectedYearOut,selectedMonthIndexOut,selectedDayOut);

            //convert to date format
            LocalDate date1 = LocalDate.of(selectedYearIn, selectedMonthIndexIn, selectedDayIn);
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String checkinDate = date1.format(formatter1);
            this.checkInDate = checkinDate;

            LocalDate date2 = LocalDate.of(selectedYearOut, selectedMonthIndexOut, selectedDayOut);
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String checkOutDate = date2.format(formatter2);
            this.checkOutDate = checkOutDate;

            //update room availabilty
            roomInfo1.setText(
                    "<html>" + "Sleeps: 2" + "<br/>" +
                    "Room Price: $150.00 " + "per night" + "<br/>" +
                    "Rooms Available: " + dbaccess.getRoomAvailibility("Single", checkinDate, checkOutDate) + "</html>"
            );
                    if(dbaccess.getRoomAvailibility("Single", checkinDate, checkOutDate) == 0 || isDateValid == true){
                        bookButton1.setVisible(false);
                    }
                    else{
                        bookButton1.setVisible(true);
                    }
            roomInfo2.setText(
                    "<html>" + "Sleeps: 4" + "<br/>" +
                    "Room Price: $200.00 " + "per night" + "<br/>" +
                    "Rooms Available: " + dbaccess.getRoomAvailibility("Double", checkinDate, checkOutDate) + "</html>"
            );
                    if(dbaccess.getRoomAvailibility("Double", checkinDate, checkOutDate) == 0 || isDateValid == true){
                        bookButton2.setVisible(false);
                    }
                    else{
                        bookButton2.setVisible(true);
                    }
            roomInfo3.setText(
                    "<html>" + "Sleeps: 6" + "<br/>" +
                    "Room Price: $300.00 " + "per night" + "<br/>" +
                    "Rooms Available: " + dbaccess.getRoomAvailibility("Family", checkinDate, checkOutDate) + "</html>"
            );
                    if(dbaccess.getRoomAvailibility("Family", checkinDate, checkOutDate) == 0 || isDateValid == true){
                        bookButton3.setVisible(false);
                    }
                    else{
                        bookButton3.setVisible(true);
                    }
            roomInfo4.setText(
                    "<html>" + "Sleeps: 2" + "<br/>" +
                    "Room Price: $400.00 " + "per night" + "<br/>" +
                    "Rooms Available: " + dbaccess.getRoomAvailibility("Luxury", checkinDate, checkOutDate) + "</html>"
            );
                    if((dbaccess.getRoomAvailibility("Luxury", checkinDate, checkOutDate) == 0 || isDateValid == true)){
                        bookButton4.setVisible(false);
                    }
                    else{
                        bookButton4.setVisible(true);
                    }



            //if checkin exceeds checkout
            if(isDateValid){
                dateError.setText("Check-in date cannot exceed check-out date.");
                dateError.setVisible(true);
                System.out.println(true);
            }

            //if checkin does not exceed checkout
            if(!isDateValid){
                dateError.setVisible(false);
                System.out.println(selectedMonthIndexIn + " " + selectedDayIn + " " + selectedYearIn);
                System.out.println(selectedMonthIndexOut + " " + selectedDayOut + " " + selectedYearOut);
                System.out.println(false);
            }
        }

        //for search button fail
        if(e.getSource() == searchRooms && (checkInMonth.getSelectedIndex() == -1 || checkOutMonth.getSelectedIndex() == -1)){
            dateError.setVisible(true);
        }

        //for check in month
        if(e.getSource() == checkInMonth) {
            int selectedMonthIndex = checkInMonth.getSelectedIndex();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, selectedMonthIndex);
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            // Populate the dayComboBox with the correct number of days for the selected month
            String[] days = new String[maxDay];
            for (int i = 1; i <= maxDay; i++) {
                days[i - 1] = Integer.toString(i);
            }
            checkInDay.setModel(new DefaultComboBoxModel(days));
        }

        //for check out month
        if(e.getSource() == checkOutMonth) {
            int selectedMonthIndex2 = checkOutMonth.getSelectedIndex();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.MONTH, selectedMonthIndex2);
            int maxDay2 = calendar2.getActualMaximum(Calendar.DAY_OF_MONTH);
            // Populate the dayComboBox with the correct number of days for the selected month
            String[] days2 = new String[maxDay2];
            for (int i = 1; i <= maxDay2; i++) {
                days2[i - 1] = Integer.toString(i);
            }
            checkOutDay.setModel(new DefaultComboBoxModel(days2));
        }

        //for book buttons
        if (e.getSource() == bookButton1) {

            String[] temp = new String[4];
            String roomType = "Single";
            String roomNum = String.valueOf(DatabaseAccess.getRoomAssignment(roomType,checkInDate,checkOutDate));
            temp[0] = String.valueOf(checkInDate);
            temp[1] = String.valueOf(checkOutDate);
            temp[2] = roomType;
            temp[3] = roomNum;
            infoForReservation = temp;

            System.out.println(Arrays.toString(infoForReservation));


            SwingUtilities.invokeLater(() -> {
                // Get the instance of Reservation. Only one instance of the frame can open at a time.
                Reservation reservation = Reservation.getInstance();
            });
        }
        if (e.getSource() == bookButton2) {

            String[] temp = new String[4];
            String roomType = "Double";
            String roomNum = String.valueOf(DatabaseAccess.getRoomAssignment(roomType,checkInDate,checkOutDate));
            temp[0] = String.valueOf(checkInDate);
            temp[1] = String.valueOf(checkOutDate);
            temp[2] = roomType;
            temp[3] = roomNum;
            infoForReservation = temp;

            System.out.println(Arrays.toString(infoForReservation));

            SwingUtilities.invokeLater(() -> {
                // Get the instance of Reservation. Only one instance of the frame can open at a time.
                Reservation reservation = Reservation.getInstance();
            });
        }
        if (e.getSource() == bookButton3) {

            String[] temp = new String[4];
            String roomType = "Family";
            String roomNum = String.valueOf(DatabaseAccess.getRoomAssignment(roomType,checkInDate,checkOutDate));
            temp[0] = String.valueOf(checkInDate);
            temp[1] = String.valueOf(checkOutDate);
            temp[2] = roomType;
            temp[3] = roomNum;
            infoForReservation = temp;

            System.out.println(Arrays.toString(infoForReservation));

            SwingUtilities.invokeLater(() -> {
                // Get the instance of Reservation. Only one instance of the frame can open at a time.
                Reservation reservation = Reservation.getInstance();
            });
        }
        if (e.getSource() == bookButton4) {

            String[] temp = new String[4];
            String roomType = "Luxury";
            String roomNum = String.valueOf(DatabaseAccess.getRoomAssignment(roomType,checkInDate,checkOutDate));
            temp[0] = String.valueOf(checkInDate);
            temp[1] = String.valueOf(checkOutDate);
            temp[2] = roomType;
            temp[3] = roomNum;
            infoForReservation = temp;

            System.out.println(Arrays.toString(infoForReservation));

            SwingUtilities.invokeLater(() -> {
                // Get the instance of Reservation. Only one instance of the frame can open at a time.
                Reservation reservation = Reservation.getInstance();
            });
        }

    }

}
