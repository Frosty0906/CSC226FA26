package medical_action_tracking;

import patient_intake.Patient;

public class EmergencyWaitingRoom {
    private LinkedQueue<Patient> level1;
    private LinkedQueue<Patient> level2;
    private LinkedQueue<Patient> level3;
    private LinkedQueue<Patient> level4;

    public EmergencyWaitingRoom() {
        // TODO: Create one empty FIFO queue for each triage level.
    }

    /**
     * Add a patient to the FIFO lane matching their triage level.
     *
     * @return true if added; false for null patients or levels outside 1-4
     */
    public boolean addPatient(Patient patient) {
        // TODO: Validate the patient and place them in the matching lane.
        return false;
    }

    /**
     * Remove the next patient, checking triage level 1 before level 2, and so on.
     */
    public Patient nextPatient() {
        // TODO: Dequeue from the first non-empty lane, or return null if all are empty.
        return null;
    }

    public boolean isEmpty() {
        // TODO: Return true only when all four lanes are empty.
        return false;
    }

    public int size() {
        // TODO: Return the total number of patients across all four lanes.
        return 0;
    }
}
