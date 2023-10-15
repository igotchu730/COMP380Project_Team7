import java.sql.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MyJDBC {

    MyJDBC() {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://project380.chazaxvumqbx.us-west-1.rds.amazonaws.com:3306/main");
        config.setUsername("admin");
        config.setPassword("password");

        HikariDataSource dataSource = new HikariDataSource(config);

        try { //testing connection
            String sql = "SELECT test_column FROM test";

            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String columnValue = resultSet.getString("test_column");
                System.out.println("Value from test: " + columnValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
