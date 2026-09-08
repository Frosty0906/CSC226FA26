package patient_intake;

public class Patient extends Person {
    private String patientID;
    private String chiefComplaint;
    private int triageLevel;
    private String currentStage;
    private String assignedRoom;
    private int arrivalHour;
    private String insuranceID;

    public Patient(String patientID, String firstName, String lastName, int age, 
                   String chiefComplaint, int triageLevel, String currentStage, 
                   String assignedRoom, int arrivalHour, String insuranceID) {
        super(firstName, lastName, age);
        // TODO REQUIRED: Initialize the patient-specific fields.
    }

    // Getters
    public String getPatientID() {
        return null; // TODO REQUIRED: Return the patient ID.
    }

    public String getFirstName() {
        return null; // TODO REQUIRED: Return the first name.
    }

    public String getLastName() {
        return null; // TODO REQUIRED: Return the last name.
    }

    public int getAge() {
        return 0; // TODO REQUIRED: Return the age.
    }

    public String getChiefComplaint() {
        return null; // TODO REQUIRED: Return the chief complaint.
    }

    public int getTriageLevel() {
        return 0; // TODO REQUIRED: Return the triage level.
    }

    public String getCurrentStage() {
        return null; // TODO REQUIRED: Return the current stage.
    }

    public String getAssignedRoom() {
        return null; // TODO REQUIRED: Return the assigned room.
    }

    public int getArrivalHour() {
        return 0; // TODO REQUIRED: Return the arrival hour.
    }

    public String getInsuranceID() {
        return null; // TODO REQUIRED: Return the insurance ID.
    }

    // Setters
    public void setPatientID(String patientID) {
        // TODO REQUIRED: Update the patient ID.
    }

    public void setFirstName(String firstName) {
        // TODO REQUIRED: Update the first name.
    }

    public void setLastName(String lastName) {
        // TODO REQUIRED: Update the last name.
    }

    public void setAge(int age) {
        // TODO REQUIRED: Update the age.
        // OPTIONAL (+5%): Reject ages outside the range 0 through 120.
    }

    public void setChiefComplaint(String chiefComplaint) {
        // TODO REQUIRED: Update the chief complaint.
    }

    public void setTriageLevel(int triageLevel) {
        // TODO REQUIRED: Update the triage level.
    }

    public void setCurrentStage(String currentStage) {
        // TODO REQUIRED: Update the current stage.
    }

    public void setAssignedRoom(String assignedRoom) {
        // TODO REQUIRED: Update the assigned room.
    }

    public void setArrivalHour(int arrivalHour) {
        // TODO REQUIRED: Update the arrival hour.
    }

    public void setInsuranceID(String insuranceID) {
        // TODO REQUIRED: Update the insurance ID.
    }

    @Override
    public String toString() {
        return ""; // TODO REQUIRED: Return a useful representation of a patient.
    }
}