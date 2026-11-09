package staff_assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AustinsStaffRosterTest {
    // 7 JUnit tests for StaffRoster

    @Test
    public void testEmptyRoster() {
        StaffRoster roster = new StaffRoster();
        assertEquals(0, roster.size());
        assertNull(roster.undoLastAssignment());
    }

    @Test
    void neverAssignsBelowRequiredSeniority() {
        StaffRoster roster = new StaffRoster();
        roster.addStaff(new StaffMember("T1", "Tim", Seniority.TECH));
        StaffMember res = new StaffMember("R1", "Rita", Seniority.RESIDENT);
        roster.addStaff(res);
        assertSame(res, roster.assign(Seniority.NURSE));   // Tim is TECH, too junior
    }

    @Test
    void repeatedAssignmentsSpreadWorkEvenly() {
        StaffRoster roster = new StaffRoster();
        StaffMember n1 = new StaffMember("N1", "Nina", Seniority.NURSE);
        StaffMember n2 = new StaffMember("N2", "Ned", Seniority.NURSE);
        roster.addStaff(n1);
        roster.addStaff(n2);
        for (int i = 0; i < 4; i++) {
            roster.assign(Seniority.NURSE);
        }
        assertEquals(2, n1.getCaseLoad());
        assertEquals(2, n2.getCaseLoad());
    }

    @Test
    void tieGoesToLowerSeniority() {
        StaffRoster roster = new StaffRoster();
        StaffMember nurse = new StaffMember("N1", "Nina", Seniority.NURSE);
        StaffMember doc = new StaffMember("D1", "Doc", Seniority.ATTENDING);
        roster.addStaff(nurse);
        roster.addStaff(doc);
        assertSame(nurse, roster.assign(Seniority.NURSE));   // both load 0 → nurse saved senior
    }

    @Test
    void undoLastAssignmentWorks() {
        StaffRoster roster = new StaffRoster();
        StaffMember n1 = new StaffMember("N1", "Nina", Seniority.NURSE);
        StaffMember n2 = new StaffMember("N2", "Ned", Seniority.NURSE);
        roster.addStaff(n1);
        roster.addStaff(n2);
        roster.assignTreatment("P1", "Bandage", "10:00", Seniority.NURSE);  // → N1
        roster.assignTreatment("P2", "IV Drip", "10:05", Seniority.NURSE);  // → N2
        assertSame(n2, roster.undoLastAssignment());   // most recent first (LIFO)
        assertSame(n1, roster.undoLastAssignment());
        assertNull(roster.undoLastAssignment());
        assertEquals(0, n1.getCaseLoad());
        assertEquals(0, n2.getCaseLoad());
    }

    @Test
    void assignThrowsWhenNoStaffAvailable() {
    StaffRoster roster = new StaffRoster();
    assertThrows(java.util.NoSuchElementException.class, () -> roster.assign(Seniority.NURSE));
    }
    
    @Test 
    void assignReturnsCorrectStaffMember() {
        StaffRoster roster = new StaffRoster();
        StaffMember n1 = new StaffMember("N1", "Nina", Seniority.NURSE);
        roster.addStaff(n1);
        assertSame(n1, roster.assign(Seniority.NURSE));
    }

}

