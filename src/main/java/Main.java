import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        DatabaseAccess dbAccess = new DatabaseAccess(); //test
        dbAccess.queryData(); //test
        dbAccess.getUserName("admin"); //test
        dbAccess.getPassword("admin"); //test

        int x = dbAccess.getRoomAssignment("Single","2023-11-01","2023-11-15");
        //int x = dbAccess.getRoomAssignment("Double","2023-11-01","2023-11-15");
        System.out.println(x);

        System.out.println(dbAccess.getCurrentDateTime());

        /*
        dbAccess.setReservation("TESTTEST05", dbAccess.getCurrentDateTime(), 5, "Single", "2023-4-01",
                    "2023-4-02", 2, "Alan", "Chu", 120.25, "12345",7, 23,"test@gmail.com",
                         "123-321-1234", "USA", "1234 Sesame St.", 91311, "Chatsworth", "CA");
        */

        Reservation reservation = new Reservation();

        HomePage homePage = new HomePage();

    }
}