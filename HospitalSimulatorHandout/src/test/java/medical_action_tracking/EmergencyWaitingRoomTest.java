package medical_action_tracking;

import org.junit.jupiter.api.Test;
import patient_intake.Patient;

import static org.junit.jupiter.api.Assertions.*;

class EmergencyWaitingRoomTest {
    @Test
    void servesHighestUrgencyLaneFirstAndPreservesFifoWithinLane() {
        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom();
        Patient levelThree = patient("P003", 3);
        Patient firstLevelOne = patient("P001", 1);
        Patient secondLevelOne = patient("P002", 1);

        assertTrue(waitingRoom.addPatient(levelThree));
        assertTrue(waitingRoom.addPatient(firstLevelOne));
        assertTrue(waitingRoom.addPatient(secondLevelOne));

        assertSame(firstLevelOne, waitingRoom.nextPatient());
        assertSame(secondLevelOne, waitingRoom.nextPatient());
        assertSame(levelThree, waitingRoom.nextPatient());
        assertNull(waitingRoom.nextPatient());
        assertTrue(waitingRoom.isEmpty());
    }

    @Test
    void reportsCombinedSizeAcrossLanes() {
        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom();
        waitingRoom.addPatient(patient("P001", 1));
        waitingRoom.addPatient(patient("P002", 4));

        assertEquals(2, waitingRoom.size());
        assertFalse(waitingRoom.isEmpty());
        waitingRoom.nextPatient();
        assertEquals(1, waitingRoom.size());
    }

    @Test
    void rejectsNullPatientAndInvalidTriageLevel() {
        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom();

        assertFalse(waitingRoom.addPatient(null));
        assertFalse(waitingRoom.addPatient(patient("P999", 5)));
        assertEquals(0, waitingRoom.size());
        assertTrue(waitingRoom.isEmpty());
    }

    private Patient patient(String patientID, int triageLevel) {
        return new Patient(patientID, "Test", "Patient", 30, "Checkup", triageLevel,
                "Waiting", "ER-001", 9, "INS-001");
    }
}
