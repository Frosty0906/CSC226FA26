package staff_assignment;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StaffRosterTest {
    private StaffRoster roster;
    private StaffMember attending, resident, nurse1, nurse2, tech;

    @BeforeEach
    void setUp() {
        roster = new StaffRoster();
        attending = new StaffMember("S01", "Attending", Seniority.ATTENDING);
        resident = new StaffMember("S02", "Resident", Seniority.RESIDENT);
        nurse1 = new StaffMember("S03", "Nurse1", Seniority.NURSE);
        nurse2 = new StaffMember("S04", "Nurse2", Seniority.NURSE);
        tech = new StaffMember("S05", "Tech", Seniority.TECH);
        for (StaffMember m : new StaffMember[]{attending, resident, nurse1, nurse2, tech}) {
            roster.addStaff(m);
        }
    }

    @Test
    void assignPicksLeastLoadedQualified() {
        // Ties go to the lower level, then staffId
        assertSame(nurse1, roster.assign(Seniority.NURSE));
        assertSame(nurse2, roster.assign(Seniority.NURSE));
        // both nurses now have load 1, so the resident (load 0) is next
        assertSame(resident, roster.assign(Seniority.NURSE));
        assertSame(attending, roster.assign(Seniority.NURSE));
        assertEquals(1, attending.getCaseLoad());
    }

    @Test
    void assignNeverReturnsBelowRequiredSeniority() {
        for (int i = 0; i < 10; i++) {
            assertTrue(roster.assign(Seniority.RESIDENT).getSeniority().compareTo(Seniority.RESIDENT) >= 0);
        }
    }

    @Test
    void assignWithNoQualifiedStaffThrows() {
        StaffRoster techsOnly = new StaffRoster();
        techsOnly.addStaff(new StaffMember("T1", "T", Seniority.TECH));
        assertThrows(NoSuchElementException.class, () -> techsOnly.assign(Seniority.NURSE));
    }

    @Test
    void releaseMakesStaffEligibleAgainInCorrectPosition() {
        for (int i = 0; i < 4; i++) {
            roster.assign(Seniority.NURSE);
        }
        roster.release(attending);
        assertEquals(0, attending.getCaseLoad());
        assertSame(attending, roster.assign(Seniority.NURSE));
    }

    @Test
    void releaseNeverDropsBelowZero() {
        StaffMember assigned = roster.assign(Seniority.TECH);
        roster.release(assigned);
        roster.release(assigned);
        assertEquals(0, assigned.getCaseLoad());
    }

    @Test
    void undoLastAssignmentReleasesPerformerOfGloballyMostRecentTreatment() {
        StaffMember first = roster.assignTreatment("P1", "Sutures", "2026-10-15 09:00", Seniority.TECH);
        StaffMember second = roster.assignTreatment("P2", "Dressing", "2026-10-15 09:05", Seniority.NURSE);
        assertEquals(1, second.getCaseLoad());
        // The hospital-wide stack is LIFO, regardless of patient.
        assertSame(second, roster.undoLastAssignment());
        assertEquals(0, second.getCaseLoad());
        assertEquals(1, first.getCaseLoad());
    }

    @Test
    void undoLastAssignmentWithNoHistoryReturnsNull() {
        assertNull(roster.undoLastAssignment());
    }

    @Test
    void duplicateStaffIdIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> roster.addStaff(new StaffMember("S01", "Imposter", Seniority.TECH)));
        assertEquals(5, roster.size());
    }
}
