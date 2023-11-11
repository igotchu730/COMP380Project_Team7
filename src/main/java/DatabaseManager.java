import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://project380hotel.chazaxvumqbx.us-west-1.rds.amazonaws.com:3306/main");
        config.setUsername("hotelManager_7");
        config.setPassword("cr4#Ha5PiFrU8ezlcRoD");

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

}
