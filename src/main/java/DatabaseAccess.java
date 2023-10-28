import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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


}
