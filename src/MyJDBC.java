import java.sql.*;

public class MyJDBC {

    MyJDBC() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://project380.chazaxvumqbx.us-west-1.rds.amazonaws.com:3306/main", "admin", "password");
            //returns a connection to a specific database

            if (connection != null) {
                System.out.println("MySQL Database Connection Successful");
            } else {
                System.err.println("Failed to connect to the database.");
            }

            //For testing
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from test");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("test_column"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
