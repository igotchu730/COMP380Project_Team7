import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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




}
