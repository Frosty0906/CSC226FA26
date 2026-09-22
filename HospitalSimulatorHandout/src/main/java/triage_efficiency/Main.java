package triage_efficiency;

// for shuffling arrays and sorting arrays
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import patient_intake.Patient;


public class Main {
    public static void main(String[] args) {
        // TODO REQUIRED: Generate the patient data.
        // TODO REQUIRED: Sort the data by patientID when needed.
        // TODO REQUIRED: Run each search method and print a found and not-found example.
        // TODO OPTIONAL: Call timeDemo() to compare algorithm runtimes.

        // Generate sample patient data
        Patient[] patients = generatePatients(1000); 

        // Sort patients when needed 
        Patient[] sortedPatients = sortByPatientId(patients);

        // fun each serch method and print a found and not-found example
        EfficiencyTester tester = new EfficiencyTester();
        String foundID = "P00010"; // Example of a patient ID that exists
        String notFoundID = "P99999"; // Example of a patient ID that does not exist

        // Linear search
        Patient foundPatientLinear = tester.linearSearch(patients, foundID);
        Patient notFoundPatientLinear = tester.linearSearch(patients, notFoundID);
        System.out.println("Linear Search - Found: " + (foundPatientLinear != null ? foundPatientLinear.getPatientID() : "Not Found"));
        System.out.println("Linear Search - Not Found: " + (notFoundPatientLinear != null ? notFoundPatientLinear.getPatientID() : "Not Found"));

        // Binary search
        Patient foundPatientBinary = tester.binarySearch(sortedPatients, foundID);
        Patient notFoundPatientBinary = tester.binarySearch(sortedPatients, notFoundID);
        System.out.println("Binary Search - Found: " + (foundPatientBinary != null ? foundPatientBinary.getPatientID() : "Not Found"));
        System.out.println("Binary Search - Not Found: " + (notFoundPatientBinary != null ? notFoundPatientBinary.getPatientID() : "Not Found"));

        // exponential search
        Patient foundPatientExponential = tester.logNSearch(sortedPatients, foundID);
        Patient notFoundPatientExponential = tester.logNSearch(sortedPatients, notFoundID);
        System.out.println("Exponential Search - Found: " + (foundPatientExponential != null ? foundPatientExponential.getPatientID() : "Not Found"));
        System.out.println("Exponential Search - Not Found: " + (notFoundPatientExponential != null ? notFoundPatientExponential.getPatientID() : "Not Found"));

        // time demo
        tester.timeDemo();
        tester.timeActualMeasure();

    }

    /**
     * REQUIRED (80%): Generate sample patient data for testing.
     *
     * Build an array of Patient objects with realistic IDs, names, complaints,
     * and triage information so you can test each search method.
     */
    public static Patient[] generatePatients(int count) {
        // TODO REQUIRED: Create the patient array and fill it with sample data.
        
        Patient[] patients = new Patient[count];
        for (int i = 0; i < count; i++) {
            String patientID = String.format("P%05d", i); // P00000, P00001, etc.
            String firstName = "FirstName" + i;
            String lastName = "LastName" + i;
            int age = 3 + (i % 88); // ages between 3 thru 90
            String chiefComplaint = "Complaint" + i;
            patients[i] = new Patient(patientID, firstName, lastName, age, chiefComplaint, 1 + (i % 5), "Waiting", "Room" + i, 8 + (i % 12), "InsuranceID" + i);
        }

        // looked this one up to shuffle the array so it actually can sort and serach a randomly arranged array
        // random seed so it shuffles the same way every time for testing purposes
        Collections.shuffle(Arrays.asList(patients), new Random(42));

        return patients;
    }

    /**
     * REQUIRED (80%): Sort patients by patientID before binary search.
     *
     * The binary-search version only works on an array sorted by patientID.
     */
    public static Patient[] sortByPatientId(Patient[] patients) {
        // TODO REQUIRED: Sort the array by patientID before testing binary search.
        
        Patient[] sortedPatients = Arrays.copyOf(patients, patients.length);
        Arrays.sort(sortedPatients, (p1, p2) -> p1.getPatientID().compareTo(p2.getPatientID()));
        return sortedPatients;
    }
}
