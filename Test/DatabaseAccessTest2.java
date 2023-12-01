import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseAccessTest2 {

    @Test
    void getPasswordAdmin() {
        assertEquals("hotel1234", DatabaseAccess.getPassword("admin"));
    }
    @Test
    void getPasswordKami() {
        assertEquals("abcde", DatabaseAccess.getPassword("kami-sama"));
    }

    @Test
    void getNumberOfRoomsSingle() {
        assertEquals(5, DatabaseAccess.getNumberofRooms("Single"));
    }
    @Test
    void getNumberOfRoomsDouble() {
        assertEquals(5, DatabaseAccess.getNumberofRooms("Double"));
    }
    @Test
    void getNumberOfRoomsFamily() {
        assertEquals(5, DatabaseAccess.getNumberofRooms("Family"));
    }
    @Test
    void getNumberOfRoomsLuxury() {
        assertEquals(3, DatabaseAccess.getNumberofRooms("Luxury"));
    }

}