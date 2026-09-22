package triage_efficiency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import patient_intake.Patient;

class SearchAlgorithmTests {

    private final EfficiencyTester tester = new EfficiencyTester();

    private Patient[] buildSortedPatients() {
        Patient[] patients = new Patient[6];
        patients[0] = new Patient("P00001", "Alice", "Smith", 25, "Headache", 2, "Triage", "ER-001", 9, "INS-00001");
        patients[1] = new Patient("P00002", "Bob", "Jones", 35, "Back Pain", 3, "Treatment", "ER-002", 10, "INS-00002");
        patients[2] = new Patient("P00003", "Cara", "Nguyen", 42, "Chest Pain", 1, "Waiting", "ER-003", 11, "INS-00003");
        patients[3] = new Patient("P00004", "Dylan", "Brown", 51, "Shortness of Breath", 2, "Triage", "ER-004", 12, "INS-00004");
        patients[4] = new Patient("P00005", "Emma", "Davis", 27, "Nausea", 4, "Recovery", "ER-005", 13, "INS-00005");
        patients[5] = new Patient("P00006", "Frank", "Miller", 60, "Fractured Arm", 3, "Treatment", "ER-006", 14, "INS-00006");
        return patients;
    }

    @Test
    void testLinearSearchFindsExistingPatient() {
        Patient[] patients = buildSortedPatients();
        Patient result = tester.linearSearch(patients, "P00004");
        assertNotNull(result, "Linear search should find a patient that exists in the array.");
        assertEquals("P00004", result.getPatientID(), "Linear search should return the matching patient record.");
    }

    @Test
    void testLinearSearchReturnsNullForMissingPatient() {
        Patient[] patients = buildSortedPatients();
        Patient result = tester.linearSearch(patients, "P99999");
        assertNull(result, "Linear search should return null when the patient is not in the array.");
    }

    @Test
    void testBinarySearchFindsExistingPatient() {
        Patient[] patients = buildSortedPatients();
        Patient result = tester.binarySearch(patients, "P00005");
        assertNotNull(result, "Binary search should find a patient in a sorted array.");
        assertEquals("P00005", result.getPatientID(), "Binary search should return the correct patient record.");
    }

    @Test
    void testBinarySearchReturnsNullForMissingPatient() {
        Patient[] patients = buildSortedPatients();
        Patient result = tester.binarySearch(patients, "P00007");
        assertNull(result, "Binary search should return null when the identifier is not present.");
    }

    @Test
    void testLogNSearchFindsExistingPatient() {
        Patient[] patients = buildSortedPatients();
        Patient result = tester.logNSearch(patients, "P00003");
        assertNotNull(result, "Alternative logarithmic search should find a valid patient.");
        assertEquals("P00003", result.getPatientID(), "Alternative logarithmic search should return the matching record.");
    }

    @Test
    void testLogNSearchReturnsNullForMissingPatient() {
        Patient[] patients = buildSortedPatients();
        Patient result = tester.logNSearch(patients, "P10000");
        assertNull(result, "Alternative logarithmic search should return null when no match exists.");
    }
}
