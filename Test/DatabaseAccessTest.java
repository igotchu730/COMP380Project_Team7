import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseAccessTest {

    @Test
    void getRoomAvailibilitySingle() {
        assertEquals(5, DatabaseAccess.getRoomAvailibility("Single","2025-12-01","2025-12-31"));
    }
    @Test
    void getRoomAvailibilityDouble() {
        assertEquals(5, DatabaseAccess.getRoomAvailibility("Double","2025-12-01","2025-12-31"));
    }
    @Test
    void getRoomAvailibilityFamily() {
        assertEquals(5, DatabaseAccess.getRoomAvailibility("Family","2025-12-01","2025-12-31"));
    }
    @Test
    void getRoomAvailibilityLuxury() {
        assertEquals(3, DatabaseAccess.getRoomAvailibility("Luxury","2025-12-01","2025-12-31"));
    }


    @Test
    void getRoomAssignmentSingle() {
        assertEquals(1, DatabaseAccess.getRoomAssignment("Single","2025-12-01","2025-12-31"));
    }
    @Test
    void getRoomAssignmentDouble() {
        assertEquals(6, DatabaseAccess.getRoomAssignment("Double","2025-12-01","2025-12-31"));
    }
    @Test
    void getRoomAssignmentFamily() {
        assertEquals(11, DatabaseAccess.getRoomAssignment("Family","2025-12-01","2025-12-31"));
    }
    @Test
    void getRoomAssignmentLuxury() {
        assertEquals(16, DatabaseAccess.getRoomAssignment("Luxury","2025-12-01","2025-12-31"));
    }

}