package patient_intake;

// got these imports from the other test files
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AustinsTests {
    private PatientRegistry registry;
    private Patient patientToTest;
    // helper method to build a test patient with a given id so I'm not retyping all ten fields
    private Patient patient(String id) {
        return new Patient(id, "Test", "Patient", 40, "Test", 3, "Waiting", "Room1", 12, "INS1");
    }
    
    @BeforeEach
    void setUp() {
        registry = new PatientRegistry();
        patientToTest = new Patient(
            "P002",           // patientID
            "Jane",           // firstName
            "Smith",          // lastName
            30,               // age
            "Headache",       // chief complaint
            2,                // triage level
            "Waiting",        // current stage
            "Room102",        // assigned room
            10,               // arrival hour
            "INS67890"        // insurance ID
        );
    }

    // test 1
    // checks to make sure resizing keeps patients in order
    @Test 
    void testResizeKeepsPatientsInOrder() {
        // add 15 patients to trigger resizing
        // figured out how to do this loop from the other test files and just modified it
        for (int i = 0; i < 15; i++) {
            registry.addPatient(patient("P" + i));
        }
        // Check that patients are still in order after resizing
        Patient[] patients = registry.getPatientRegistry();
        for (int i = 0; i < 15; i++) {
            assertEquals("P" + i, patients[i].getPatientID(), "Patient ID should match after resizing");
        }
    }

    // test 2
    // checks if removing a patient from the middle keeps the order of the rest 
    @Test 
    void testRemovePatientKeepsOrder() {
        // add 5 patients
        for (int i = 0; i < 5; i++) {
            registry.addPatient(patient("P" + i));
        }
        // remove the patient at index 2
        registry.removePatient(2);
        // Check that the remaining patients are still in order
        Patient[] patients = registry.getPatientRegistry();
        assertEquals("P0", patients[0].getPatientID());
        assertEquals("P1", patients[1].getPatientID());
        assertEquals("P3", patients[2].getPatientID());
        assertEquals("P4", patients[3].getPatientID());
    }

    // test 3
    // trying to remove a bad index should return null and shouldn't change the registry
    @Test 
    void testRemovePatientWithInvalidIndex() {
        // add 3 patients
        for (int i = 0; i < 3; i++) {
            registry.addPatient(patient("P" + i));
        }
        // try to remove a patient with an invalid index
        Patient removed = registry.removePatient(5);
        assertNull(removed, "Removing with an invalid index should return null");
        // check that the registry size is still 3
        assertEquals(3, registry.getPatientRegistry().length, "Registry size should remain unchanged after invalid removal");
    }

    // test 4
    // updating an id that doesn't exist returns false and shouldn't change the registry
    @Test
    void testUpdateNonExistentPatient() {
        // add a patient
        registry.addPatient(patient("P001"));
        // create a patient with a non-existent ID
        Patient nonExistent = patient("P999");
        // try to update the non-existent patient
        boolean updated = registry.updatePatient(nonExistent);
        assertFalse(updated, "Updating a non-existent patient should return false");
        // check that the existing patient is still there
        assertEquals("P001", registry.getPatientRegistry()[0].getPatientID(), "Existing patient should remain unchanged");
    }

        // test 5
    // checks to make sure ages 0 and 120 are allowed since those are the edges of the valid range
    @Test
    void testBoundaryAgesAreAccepted() {
        // 0 should work because newborns come into the ER
        patientToTest.setAge(0);
        assertEquals(0, patientToTest.getAge(), "Age 0 should be allowed");

        // 120 is the top of the range so it should still be accepted
        patientToTest.setAge(120);
        assertEquals(120, patientToTest.getAge(), "Age 120 should be allowed");
    }

    // test 6
    // makes sure an empty registry doesn't blow up when toString is called
    @Test
    void testToStringOnEmptyRegistry() {
        String registryString = registry.toString();
        assertNotNull(registryString, "toString should return a non-null string even for an empty registry");
    }

}
