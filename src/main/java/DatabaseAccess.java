import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * DatabaseAccess: Contains all the methods used with access to the database.
 * @since 2023-10-11
 * @author Mohammed Khan, Alan Chu, Chris Guevara
 */

public class DatabaseAccess {
    public static void queryData() {
        DataSource dataSource = DatabaseManager.getDataSource();

        try (Connection connection = dataSource.getConnection()) {

            String sql = "SELECT * FROM test";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String columnValue = resultSet.getString("test_column");
                    System.out.println("Value from test: " + columnValue);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getUserName(String user) {
        DataSource dataSource = DatabaseManager.getDataSource();

        String retrievedUsername = null;
        try (Connection connection = dataSource.getConnection()) {

            String sql = "SELECT username FROM Login WHERE username = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                retrievedUsername = resultSet.getString("username");
                System.out.println("Retrieved username: " + retrievedUsername);
            } else {
                System.out.println("Username not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrievedUsername;
    }

    public static String getPassword(String passWord) {
        DataSource dataSource = DatabaseManager.getDataSource();

        String retrievedPW = null;
        try (Connection connection = dataSource.getConnection()) {

            String sql = "SELECT password FROM Login WHERE username = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, passWord);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                retrievedPW = resultSet.getString("password");
                System.out.println("Retrieved password: " + retrievedPW);
            } else {
                System.out.println("Password not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrievedPW;
    }

    public static int getNumberofRooms(String roomType){
        DataSource dataSource = DatabaseManager.getDataSource();

        int availability = 0;
        try (Connection connection = dataSource.getConnection()) {

            String sql = "SELECT COUNT(*) AS room_count FROM Rooms_Roster WHERE room_type = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, roomType);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                availability = resultSet.getInt("room_count");
            } else {
                System.out.println("Number of rooms not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availability;
    }

    public String[] getRoomNumbers(String roomType) {
        DataSource dataSource = DatabaseManager.getDataSource();
        String tableName = "Rooms_Roster";
        String columnName = "room_number";
        String query = "SELECT " + columnName + " FROM " + tableName + " WHERE room_type = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, roomType);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> results = new ArrayList<>();
            while (resultSet.next()) {
                String value = resultSet.getString(columnName);
                results.add(value);
            }

            return results.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0]; // Handle the exception
        }
    }


    /**
     * getRoomAvailibility: Takes the user input selections of roomtype, checkin and checkout date,
     * and finds if the room is available.
     * @param roomType
     * @param checkin
     * @param checkout
     * @return missingInts[0]
     */

    public static int getRoomAvailibility(String roomType, String checkin, String checkout){
        DataSource dataSource = DatabaseManager.getDataSource();

        int availability = 0;
        try (Connection connection = dataSource.getConnection()) {

            String sql = "SELECT COUNT(*)\n" +
                    "FROM Transactions\n" +
                    "WHERE room_type = ?\n" +
                    "AND checkin_date <= ?\n" +
                    "AND checkout_date >= ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, roomType);
            statement.setString(2, checkout);
            statement.setString(3, checkin);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                availability = resultSet.getInt(1);
            } else {
                System.out.println("Availability not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getNumberofRooms(roomType) - availability;
    }


    /**
     * getRoomAssignment: Takes the user input selections of roomtype, checkin and checkout date,
     * and assigns an available room number to the reservation.
     * @param roomType
     * @param checkin
     * @param checkout
     * @return missingInts[0]
     */

    public static int getRoomAssignment(String roomType, String checkin, String checkout) {
        DataSource dataSource = DatabaseManager.getDataSource();
        DatabaseAccess dbAccess = new DatabaseAccess();

        String sql = "SELECT room_number\n" +
                "FROM Transactions\n" +
                "WHERE room_type = ?\n" +
                "AND checkin_date <= ?\n" +
                "AND checkout_date >= ?;";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, roomType);
            preparedStatement.setString(2, checkout);
            preparedStatement.setString(3, checkin);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> results = new ArrayList<>();
            while (resultSet.next()) {
                String value = resultSet.getString("room_number");
                results.add(value);
            }

            //for rooms taken
            String[] stringArray1 = results.toArray(new String[0]);
            //convert string array to int array
            int[] roomsTaken = new int[stringArray1.length];
            for (int i = 0; i < stringArray1.length; i++) {
                try {
                    roomsTaken[i] = Integer.parseInt(stringArray1[i]);
                } catch (NumberFormatException e) {
                    roomsTaken[i] = 0;
                }
            }

            //for room total
            String[] stringArray2 = dbAccess.getRoomNumbers(roomType);
            //convert string array to int array
            int[] roomsTotal = new int[stringArray2.length];
            for (int i = 0; i < stringArray2.length; i++) {
                try {
                    roomsTotal[i] = Integer.parseInt(stringArray2[i]);
                } catch (NumberFormatException e) {
                    roomsTotal[i] = 0;
                }
            }

            //find rooms that haven't been booked : roomsTotal - roomsTaken
            HashSet<Integer> partialSet = new HashSet<>();
            for (int num : roomsTaken) {
                partialSet.add(num);
            }
            int[] missingInts = new int[roomsTotal.length - roomsTaken.length];
            int index = 0;

            for (int num : roomsTotal) {
                if (!partialSet.contains(num)) {
                    missingInts[index] = num;
                    index++;
                }
            }

            //for testing
            System.out.println(Arrays.toString(missingInts));

            //return first element
            return missingInts[0];

        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Handle the exception
        }
    }

    // Method to generate a random ID code of the specified length with half letters and half numbers
    public static String generateRandomId(int length) {
        // Define the characters allowed in the ID code
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        // Create a StringBuilder to store the generated ID code
        StringBuilder randomId = new StringBuilder();

        // Create an instance of the Random class
        Random random = new Random();

        // Generate half letters
        for (int i = 0; i < length / 2; i++) {
            int index = random.nextInt(letters.length());
            randomId.append(letters.charAt(index));
        }

        // Generate half numbers
        for (int i = length / 2; i < length; i++) {
            int index = random.nextInt(numbers.length());
            randomId.append(numbers.charAt(index));
        }

        // Shuffle the characters to ensure randomness
        for (int i = 0; i < length; i++) {
            int index1 = random.nextInt(length);
            int index2 = random.nextInt(length);
            char temp = randomId.charAt(index1);
            randomId.setCharAt(index1, randomId.charAt(index2));
            randomId.setCharAt(index2, temp);
        }

        // Convert StringBuilder to String and return the generated ID code
        return randomId.toString();
    }

    public static String getCurrentDateTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the desired format for MySQL date-time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time using the defined format
        return currentDateTime.format(formatter);
    }

    /**
     * setReservation: takes user input info during reservation and adds it to the database.
     * @param transaction_id
     * @param transaction_date
     * @param room_number
     * @param room_type
     * @param checkin_date
     * @param checkout_date
     * @param first_name
     * @param last_name
     * @param total_cost
     * @param card_number
     * @param card_month
     * @param card_year
     * @param email
     * @param phone_number
     * @param country
     * @param address
     * @param zip
     * @param city
     * @param state
     */
    public static void setReservation(String transaction_id, String transaction_date, int room_number, String room_type,
                                      String checkin_date, String checkout_date, String first_name, String last_name,
                                      double total_cost, String card_number, int card_month, int card_year, String email,
                                      String phone_number, String country, String address, int zip, String city, String state) {

        DataSource dataSource = DatabaseManager.getDataSource();
        String sql = "INSERT INTO Transactions" +
                     "(transaction_id, transaction_date, room_number, room_type," +
                     "checkin_date, checkout_date, first_name, last_name," +
                     "total_cost, card_number, card_month, card_year, email," +
                     "phone_number, country, address, zip, city, state) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, transaction_id);
            statement.setString(2, transaction_date);
            statement.setInt(3, room_number);
            statement.setString(4, room_type);
            statement.setString(5, checkin_date);
            statement.setString(6, checkout_date);
            //statement.setInt(7, occupants);
            statement.setString(7, first_name);
            statement.setString(8, last_name);
            statement.setDouble(9,total_cost);
            statement.setString(10, card_number);
            statement.setInt(11, card_month);
            statement.setInt(12, card_year);
            statement.setString(13, email);
            statement.setString(14, phone_number);
            statement.setString(15, country);
            statement.setString(16, address);
            statement.setInt(17, zip);
            statement.setString(18, city);
            statement.setString(19, state);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static long getDaysDifference(String startDateString, String endDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static void displayQueryResults(JTable resultTable, String query) {

        DataSource dataSource = DatabaseManager.getDataSource();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            displayResultSet(resultTable, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error
        }
    }

    private static void displayResultSet(JTable resultTable, ResultSet resultSet) throws SQLException {
        DefaultTableModel tableModel = new DefaultTableModel();

        // Get column names
        int columnCount = resultSet.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            tableModel.addColumn(resultSet.getMetaData().getColumnName(i));
        }

        // Get row data
        while (resultSet.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = resultSet.getString(i);
            }
            tableModel.addRow(rowData);
        }

        // Set the table model to the JTable
        resultTable.setModel(tableModel);
    }

}
