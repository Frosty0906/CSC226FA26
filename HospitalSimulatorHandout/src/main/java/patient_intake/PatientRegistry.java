package patient_intake;
// Switch from using ArrayList to a flat array implementation for patient storage.

public class PatientRegistry {
    // Flat array to store patients and a size field to track the number of stored patients.
    private Patient[] patientRegistry;
    private int size; // track actual number of patients

    // Initial capacity for the flat array. Can be adjusted as needed.
    private static final int INITIAL_CAPACITY = 10;

    public PatientRegistry() {
        // TODO REQUIRED: Create the initial array and set the starting size.
    }

    public void addPatient(Patient patient) {
        // TODO REQUIRED: Add a patient to the registry.
        // TODO OPTIONAL (+5%): Expand the array when it becomes full.
    }

    /**
     * Returns the patients currently stored in the registry.
     * The optional encapsulation extension requires returning a defensive copy.
     */
    public Patient[] getPatientRegistry() {
        return null; // TODO REQUIRED: Return the patients currently stored.
    }

    public Patient getPatientByID(String patientID) {
        return null; // TODO REQUIRED: Search for and return the matching patient.
    }

    /**
     * Removes a patient from the registry by patientID.
     * @param patientID The ID of the patient to remove
     * @return true if patient was found and removed, false otherwise
     */
    public boolean removePatient(String patientID) {
        return false; // TODO OPTIONAL (+5%): Remove the patient with this ID.
    }

    /**
     * Removes a patient from the registry by index.
     * @param index The index of the patient to remove
     * @return the removed Patient, or null if index is invalid
     */
    public Patient removePatient(int index) {
        return null; // TODO OPTIONAL (+5%): Remove by index and shift later elements left.
    }

    /**
     * Updates a patient in the registry by matching patientID.
     * @param updatedPatient The patient with updated information
     * @return true if patient was found and updated, false otherwise
     */
    public boolean updatePatient(Patient updatedPatient) {
        return false; // TODO OPTIONAL (+5%): Replace the patient with the same ID.
    }
    
    @Override
    public String toString() {
        return ""; // TODO REQUIRED: Return a useful representation of the registry.
    }

}


