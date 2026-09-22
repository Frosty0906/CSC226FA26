package triage_efficiency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import patient_intake.Patient;

public class AustinsSearchTests {
    private final EfficiencyTester tester = new EfficiencyTester();

    // helper to build a patient with just an id so I'm not retyping all ten fields
    private Patient patient(String id) {
        return new Patient(id, "Test", "Patient", 40, "Test", 3, "Waiting", "Room1", 12, "INS1");
    }

    // test 1
    // test that binary search finds the first patient that exists in an array of 5 
    // first patient is at index 0, so this is a good test to make sure the search is working properly
    @Test
    void testBinarySearchFindsFirstPatient() {
        Patient[] patients = new Patient[5];
        for (int i = 0; i < 5; i++) {
            patients[i] = patient("P" + i);
        }
        Patient result = tester.binarySearch(patients, "P0");
        assertNotNull(result, "Binary search should find the first patient");
        assertEquals("P0", result.getPatientID());
    }

    // test 2
    // tests that binary can find the last patient in an array of 5
    // same idea as the first test but at index 4
    @Test
    void testBinarySearchFindsLastPatient() {
        Patient[] patients = new Patient[5];
        for (int i = 0; i < 5; i++) {
            patients[i] = patient("P" + i);
        }
        Patient result = tester.binarySearch(patients, "P4");
        assertNotNull(result, "Binary search should find the last patient");
        assertEquals("P4", result.getPatientID());
    }

    // test 3
    // tests that exponential search can find the first patient in a array of 5
    // same idea as the first test but using exponential search instead of binary
    @Test
    void testExponentialSearchFindsFirstPatient() {
        Patient[] patients = new Patient[5];
        for (int i = 0; i < 5; i++) {
            patients[i] = patient("P" + i);
        }
        Patient result = tester.logNSearch(patients, "P0");
        assertNotNull(result, "Exponential search should find the first patient");
        assertEquals("P0", result.getPatientID());
    }

    // test 4
    // tests that exponential search can find the last patient in a array of 5
    // same idea as the second test but using exponential search instead of binary
    @Test
    void testExponentialSearchFindsLastPatient() {
        Patient[] patients = new Patient[5];
        for (int i = 0; i < 5; i++) {
            patients[i] = patient("P" + i);
        }
        Patient result = tester.logNSearch(patients, "P4");
        assertNotNull(result, "Exponential search should find the last patient");
        assertEquals("P4", result.getPatientID());
    }

    // test 5 
    // tests that an empty array returns null for both binary and exponential search
    // why it matters: if the array is empty, the search should return null and not explode
    @Test
    void testSearchesReturnNullForEmptyArray() {
        Patient[] patients = new Patient[0];
        Patient binaryResult = tester.binarySearch(patients, "P0");
        Patient exponentialResult = tester.logNSearch(patients, "P0");
        assertNull(binaryResult, "Binary search should return null for an empty array");
        assertNull(exponentialResult, "Exponential search should return null for an empty array");
    }

    // test 6
    // searches for P0 in an array of P1 thru P5, so the id is below everything in the array
    // the already existing tests only check ids above the array, this checks the other direction
    @Test
    void testSearchesReturnNullForNonexistentPatient() {
        Patient[] patients = new Patient[5];
        for (int i = 0; i < 5; i++) {
            patients[i] = patient("P" + (i +1)); // p1 thru p5
        }
        Patient binaryResult = tester.binarySearch(patients, "P0");
        Patient exponentialResult = tester.logNSearch(patients, "P0");
        assertNull(binaryResult, "Binary search should return null for a nonexistent patient");
        assertNull(exponentialResult, "Exponential search should return null for a nonexistent patient");
    }

    // test 7
    // generates 100 patients without sorting and then just do a linear search 
    // checks that linear search can find a patient in an unsorted array
    @Test
    void testLinearSearchFindsPatientInUnsortedArray() {
        Patient[] patients = Main.generatePatients(100);
        Patient result = tester.linearSearch(patients, "P00050");
        assertNotNull(result, "Linear search should find a patient in an unsorted array");
        assertEquals("P00050", result.getPatientID(), "Linear search should return the correct patient record");
    }

    // test 8
    // generates 1000 patients and makes a sorted copy. runs linear on the shuffled array and binary/exponential on the sorted copy
    // just makes sure all 3 methods work on the same data and return the same result
    @Test
    void testAllSearchesReturnSameResult() {
        Patient[] unsortedPatients = Main.generatePatients(1000);
        Patient[] sortedPatients = unsortedPatients.clone();
        java.util.Arrays.sort(sortedPatients, (p1, p2) -> p1.getPatientID().compareTo(p2.getPatientID()));

        String[] testIds = {"P00010", "P00500", "P00999"};
        for (int idindex = 0; idindex < testIds.length; idindex++) {
            String id = testIds[idindex];
            Patient linearResult = tester.linearSearch(unsortedPatients, id);
            Patient binaryResult = tester.binarySearch(sortedPatients, id);
            Patient exponentialResult = tester.logNSearch(sortedPatients, id);

            assertNotNull(linearResult, "Linear search should find the patient");
            assertNotNull(binaryResult, "Binary search should find the patient");
            assertNotNull(exponentialResult, "Exponential search should find the patient");

            assertEquals(linearResult.getPatientID(), binaryResult.getPatientID(), "Linear and Binary search should return the same patient record");
            assertEquals(linearResult.getPatientID(), exponentialResult.getPatientID(), "Linear and Exponential search should return the same patient record");
        }
    }

}