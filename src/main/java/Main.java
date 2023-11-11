import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        DatabaseAccess dbAccess = new DatabaseAccess(); //test
        dbAccess.queryData(); //test
        dbAccess.getUserName("admin"); //test
        dbAccess.getPassword("admin"); //test

        String[]x = dbAccess.getRoomNumbers("Luxury");
        for (String row : x) {
            System.out.println(row);
        }

        HomePage homePage = new HomePage();

    }
}