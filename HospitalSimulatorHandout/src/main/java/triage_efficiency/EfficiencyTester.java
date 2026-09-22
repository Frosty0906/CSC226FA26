package triage_efficiency;

import patient_intake.Patient;

public class EfficiencyTester {

    /**
     * REQUIRED (80%): Implement linear search.
     *
     * Search through the patient array one element at a time until the matching
     * patientID is found. Return the Patient if it exists; otherwise return null.
     *
     * This method must run in O(n) time.
     */
    public Patient linearSearch(Patient[] patients, String pid) {
        // TODO REQUIRED: Implement linear search.
        // Search the entire array in order and return the matching Patient.
        for (int i = 0; i < patients.length; i++) {
            if (patients[i].getPatientID().equals(pid)) {
                return patients[i];
            }
        }
        return null; // no patient found    
    }

    /**
     * REQUIRED (80%): Implement binary search.
     *
     * This method works only on an array that is sorted by patientID.
     * Repeatedly divide the search range in half until the target is found.
     *
     * This method must run in O(log n) time.
     */
    public Patient binarySearch(Patient[] patients, String pid) {
        // TODO REQUIRED: Implement iterative binary search.
        // The array must be sorted by patientID before calling this method.
        int lowBound = 0;
        int highBound = patients.length - 1;

        while (lowBound <= highBound) {
            int midCheck = (lowBound + highBound) / 2;
            if (patients[midCheck].getPatientID().equals(pid)) {
                return patients[midCheck];
            } else if (patients[midCheck].getPatientID().compareTo(pid) < 0) {
                lowBound = midCheck + 1;
            } else {
                highBound = midCheck - 1;
            }
        }
        return null; // no patient found
    }

    /**
     * OPTIONAL (+5%): Implement a different O(log n) search algorithm.
     *
     * Pick one of the following approaches and implement it:
     * - Exponential search
     * - Jump search
     * - Ternary search
     *
     * Add a short comment above the method explaining:
     * - which algorithm you chose
     * - where you learned about it
     * - why it works
     */
    public Patient logNSearch(Patient[] sortedPatients, String pid) {
        // TODO OPTIONAL: Research and implement a second O(log n) algorithm.
        // Cite your source and explain the approach in a comment before the logic.

        // for this one I chose exponential search
        // i learned about it from this helpful github repo https://github.com/MrtitaniumJ/Java-basic-to-advance/tree/main/04-Collections-and-DSA/03-Algorithms/01-Searching/05-Exponential-Search
        // the array is sorted so once doubling overshoots, the target can only be between the previous bound and the current bound
        if (sortedPatients.length == 0) {
            return null;
        }
        if (sortedPatients[0].getPatientID().equals(pid)) {
            return sortedPatients[0];
        }

        int bound = 1;

        while (bound < sortedPatients.length && sortedPatients[bound].getPatientID().compareTo(pid) < 0) {
            bound *= 2;
        }

        // now the same as binary search but with the bounds set to the previous bound and the current bound
        int lowBound = bound / 2;
        int highBound = Math.min(bound, sortedPatients.length - 1);
         
        while (lowBound <= highBound) {
            int midCheck = (lowBound + highBound) / 2;
            if (sortedPatients[midCheck].getPatientID().equals(pid)) {
                return sortedPatients[midCheck];
            } else if (sortedPatients[midCheck].getPatientID().compareTo(pid) < 0) {
                lowBound = midCheck + 1;
            } else {
                highBound = midCheck - 1;
            }
        }
        return null; // no patient found
    }


    // ok im gonna duplicate this and change this so it actually measures the time of the search methods
    public void timeDemo() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            int x = 5 + 5;
        }
        long endTime = System.nanoTime();

        System.out.println("The example addition took: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            int x = 5 * 5;
        }
        endTime = System.nanoTime();
        System.out.println("The example multiplication took: " + (endTime - startTime) + " ns");
    }

    // actual time measure for extension
    public void timeActualMeasure() {
        // set up sizes for the tests
        int[] sizes = {100, 1000, 10000, 100000};

        // loops thru the sizes and generates the patients to get them ready for the search methods
        for (int i = 0; i < sizes.length; i++) {
            // generates the patients and sorts them for searching
            Patient[] generatedPatientList = Main.generatePatients(sizes[i]);
            Patient[] sortedPatientsGenerated = Main.sortByPatientId(generatedPatientList);

            // picks a patient id that is in the middle of the array so it takes a while to find instead of a hardcoded value that would skew the results a bit
            String idPicker = String.format("P%05d", sizes[i] / 2);

            // linear search time measure
            long startTime = System.nanoTime();
            linearSearch(generatedPatientList, idPicker);
            long endTime = System.nanoTime();
            System.out.println("Linear search for " + sizes[i] + " patients took: " + (endTime - startTime) + " ns");

            // binary search time measure
            startTime = System.nanoTime();
            binarySearch(sortedPatientsGenerated, idPicker);
            endTime = System.nanoTime();
            System.out.println("Binary search for " + sizes[i] + " patients took: " + (endTime - startTime) + " ns");

            // exponential search time measure
            startTime = System.nanoTime();
            logNSearch(sortedPatientsGenerated, idPicker);
            endTime = System.nanoTime();
            System.out.println("Exponential search for " + sizes[i] + " patients took: " + (endTime - startTime) + " ns");
        }
    }
}
