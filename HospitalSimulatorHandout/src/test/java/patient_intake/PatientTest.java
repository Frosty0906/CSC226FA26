package patient_intake;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {
    
    private Patient testPatient;
    
    @BeforeEach
    void setUp() {
        // Create patient using new constructor
        testPatient = new Patient(
            "P001",           // patientID
            "John",           // firstName
            "Doe",            // lastName
            45,               // age
            "Chest pain",     // chief complaint
            3,                // triage level
            "Waiting",        // current stage
            "Room101",        // assigned room
            14,               // arrival hour
            "INS12345"        // insurance ID
        );
    }
    
    @Test
    void testPatientID() {
        assertEquals("P001", testPatient.getPatientID(), "Patient ID should match");
    }
    
    @Test
    void testFirstName() {
        assertEquals("John", testPatient.getFirstName(), "First name should match");
    }
    
    @Test
    void testLastName() {
        assertEquals("Doe", testPatient.getLastName(), "Last name should match");
    }
    
    @Test
    void testAge() {
        assertEquals(45, testPatient.getAge(), "Age should match");
    }
    
    @Test
    void testChiefComplaint() {
        assertEquals("Chest pain", testPatient.getChiefComplaint(), "Chief complaint should match");
    }
    
    @Test
    void testTriageLevel() {
        assertEquals(3, testPatient.getTriageLevel(), "Triage level should match");
    }
    
    @Test
    void testCurrentStage() {
        assertEquals("Waiting", testPatient.getCurrentStage(), "Current stage should match");
    }
    
    @Test
    void testAssignedRoom() {
        assertEquals("Room101", testPatient.getAssignedRoom(), "Assigned room should match");
    }
    
    @Test
    void testArrivalHour() {
        assertEquals(14, testPatient.getArrivalHour(), "Arrival hour should match");
    }
    
    @Test
    void testInsuranceID() {
        assertEquals("INS12345", testPatient.getInsuranceID(), "Insurance ID should match");
    }
    
    @Test
    void testSetFirstName() {
        testPatient.setFirstName("Jane");
        assertEquals("Jane", testPatient.getFirstName(), "First name should be updated");
    }
    
    @Test
    void testSetLastName() {
        testPatient.setLastName("Smith");
        assertEquals("Smith", testPatient.getLastName(), "Last name should be updated");
    }
    
    @Test
    void testSetAge() {
        testPatient.setAge(30);
        assertEquals(30, testPatient.getAge(), "Age should be updated");
    }
    
    @Test
    void testSetChiefComplaint() {
        testPatient.setChiefComplaint("Headache");
        assertEquals("Headache", testPatient.getChiefComplaint(), "Chief complaint should be updated");
    }
    
    @Test
    void testSetTriageLevel() {
        testPatient.setTriageLevel(1);
        assertEquals(1, testPatient.getTriageLevel(), "Triage level should be updated");
    }
    
    @Test
    void testSetCurrentStage() {
        testPatient.setCurrentStage("In Treatment");
        assertEquals("In Treatment", testPatient.getCurrentStage(), "Current stage should be updated");
    }
    
    @Test
    void testSetAssignedRoom() {
        testPatient.setAssignedRoom("Room202");
        assertEquals("Room202", testPatient.getAssignedRoom(), "Assigned room should be updated");
    }
    
    @Test
    void testSetArrivalHour() {
        testPatient.setArrivalHour(9);
        assertEquals(9, testPatient.getArrivalHour(), "Arrival hour should be updated");
    }
    
    @Test
    void testSetInsuranceID() {
        testPatient.setInsuranceID("INS99999");
        assertEquals("INS99999", testPatient.getInsuranceID(), "Insurance ID should be updated");
    }
    
    @Test
    void testSetPatientID() {
        testPatient.setPatientID("P999");
        assertEquals("P999", testPatient.getPatientID(), "Patient ID should be updated");
    }
    
    void testPatientWithSingleName() {
        Patient patient = new Patient(
            "P002", "Madonna", "", 50, "Back pain", 2, "Waiting", "Room102", 10, "INS54321"
        );
        assertEquals("Madonna", patient.getFirstName(), "First name should be Madonna");
        assertEquals("", patient.getLastName(), "Last name should be empty");
    }

    @Test
    void testToStringContainsPatientIdentity() {
        String result = testPatient.toString();
        assertTrue(result.contains("P001"));
        assertTrue(result.contains("John"));
    }
}