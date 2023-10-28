
public class Main {
    public static void main(String[] args) {

        DatabaseAccess dbAccess = new DatabaseAccess(); //test
        dbAccess.queryData(); //test
        dbAccess.getUserName("admin"); //test
        dbAccess.getPassword("admin"); //test

        HomePage homePage = new HomePage();

    }
}