import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseAccess {
    public void queryData() {
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
}
