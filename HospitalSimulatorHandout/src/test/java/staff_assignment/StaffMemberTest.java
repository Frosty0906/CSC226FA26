package staff_assignment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StaffMemberTest {

    @Test
    void lowerLoadSortsFirst() {
        StaffMember light = new StaffMember("S9", "Light", Seniority.NURSE);
        StaffMember heavy = new StaffMember("S1", "Heavy", Seniority.NURSE);
        heavy.setCaseLoad(3);
        assertTrue(light.compareTo(heavy) < 0);
        assertTrue(heavy.compareTo(light) > 0);
    }

    @Test
    void equalLoadBrokenByStaffId() {
        StaffMember a = new StaffMember("S1", "A", Seniority.NURSE);
        StaffMember b = new StaffMember("S2", "B", Seniority.NURSE);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
    }
}
