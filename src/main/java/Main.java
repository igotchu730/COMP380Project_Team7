import java.sql.SQLException;

/**
 * Main: Our application starts at main. Main will execute an instance of the MainPage class,
 * which allows for users to access all the functionalities we have developed.
 * @since 2023-09-24
 * @author Alan Chu
 * */

public class Main {
    public static void main(String[] args){

        DatabaseAccess dbAccess = new DatabaseAccess(); //test
        dbAccess.queryTest(); //test

        HomePage homePage = new HomePage();

    }

}