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
        this.patientRegistry = new Patient[INITIAL_CAPACITY];
        this.size = 0; // probs good to start at 0 since no patients are added yet
    }

    public void addPatient(Patient patient) {
        // TODO REQUIRED: Add a patient to the registry.
        // TODO OPTIONAL (+5%): Expand the array when it becomes full.
        if (this.size >= this.patientRegistry.length) {
            Patient[] newRegistry = new Patient[this.patientRegistry.length + 1]; // adds one more space to the array 
            for (int i = 0; i < this.patientRegistry.length; i++) {
                newRegistry[i] = this.patientRegistry[i]; // copies the old array into the new one
            }
            this.patientRegistry = newRegistry; // points things to the new array
        }
        this.patientRegistry[this.size] = patient; // if not full adds to the end of the array
        this.size++; // updates size tracking
    }

    /**
     * Returns the patients currently stored in the registry.
     * The optional encapsulation extension requires returning a defensive copy.
     */
    public Patient[] getPatientRegistry() {
        // TODO REQUIRED: Return the patients currently stored.
        // i did the extension so it loops thru and copies the array and then returns the copy... 
        // also only returns the part of the array that has patients in it and not empty spaces
        Patient[] copyToRtrn = new Patient[this.size]; // makes the copy
        for (int i = 0; i < this.size; i++) { // only loops thru the part of the array that has patients in it
            copyToRtrn[i] = this.patientRegistry[i]; // copies the patients into the new array
        }
        return copyToRtrn;

    }

    public Patient getPatientByID(String patientID) {
        // TODO REQUIRED: Search for and return the matching patient.
        for (int i = 0; i < this.size; i++) { // loops thru patient array
            if (this.patientRegistry[i].getPatientID().equals(patientID)) {
                return this.patientRegistry[i]; // returns the patient if found
            }
        }
        return null; // returns null if patient not found
    }

    /**
     * Removes a patient from the registry by patientID.
     * @param patientID The ID of the patient to remove
     * @return true if patient was found and removed, false otherwise
     */
    public boolean removePatient(String patientID) {
        // TODO OPTIONAL (+5%): Remove the patient with this ID.
        // loop thru patient array and find the patient with id, then remove it and shift array
        for (int i = 0; i < this.size; i++) {
            if (this.patientRegistry[i].getPatientID().equals(patientID)) {
                // shift the rest of the array left to fill the gap
                for (int j = i; j < this.size - 1; j++) {
                    this.patientRegistry[j] = this.patientRegistry[j + 1];
                }
                this.patientRegistry[this.size - 1] = null; // clear the last element because it's in there twice now.
                this.size--; // decrease size 
                return true; // patient found and removed
            }
        }
        return false; // patient not found for whatever reason
    }

    /**
     * Removes a patient from the registry by index.
     * @param index The index of the patient to remove
     * @return the removed Patient, or null if index is invalid
     */
    public Patient removePatient(int index) {
        // TODO OPTIONAL (+5%): Remove by index and shift later elements left.
        // basically same thing as above but with index instead of id
        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                Patient removedPatient = this.patientRegistry[i]; // store the patient so we have it to return
                // shift the rest of the array left to fill the gap
                for (int j = i; j < this.size - 1; j++) {
                    this.patientRegistry[j] = this.patientRegistry[j + 1];
                }
                this.patientRegistry[this.size - 1] = null; // clear the last element because it's in there twice now.
                this.size--; // decrease size 
                return removedPatient; // return the removed patient
            }
        }
        return null; // index isn't right or too big so return null
    }

    /**
     * Updates a patient in the registry by matching patientID.
     * @param updatedPatient The patient with updated information
     * @return true if patient was found and updated, false otherwise
     */
    public boolean updatePatient(Patient updatedPatient) {
        // TODO OPTIONAL (+5%): Replace the patient with the same ID.
        // loop thru patient array and find the patient with id, then replace it with the new or updated patient
        for (int i = 0; i < this.size; i++) {
            if (this.patientRegistry[i].getPatientID().equals(updatedPatient.getPatientID())) {
                this.patientRegistry[i] = updatedPatient; // replace the patient with the updated one
                return true; // patient found and updated
            }
        }
        return false; // patient id not found
    }
    
    @Override
    public String toString() {
        // TODO REQUIRED: Return a useful representation of the registry.
        //loops thru array and adds each patient and their info to a nice string to return
        String formattedString = "PatientRegistry (size=" + this.size + "):\n";
            for (int i = 0; i < this.size; i++) {
                formattedString += this.patientRegistry[i].toString() + "\n";
        }
        return formattedString;
    }

}