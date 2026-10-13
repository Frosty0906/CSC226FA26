package medical_action_tracking;

public class TreatmentRecord {
    private String patientID;
    private String treatmentName;
    private String timestamp;

    public TreatmentRecord(String patientID, String treatmentName, String timestamp) {
        this.patientID = patientID;
        this.treatmentName = treatmentName;
        this.timestamp = timestamp;
    }

    public String getPatientID() {
        // TODO: Return the patient ID. 
        return patientID;
    }

    public String getTreatmentName() {
        // TODO: Return the treatment description.

        // there is no description veriable so i did name... i assume this is what you meant
        return treatmentName;
    }

    public String getTimestamp() {
        // TODO: Return the timestamp.
        return timestamp;
    }

    @Override
    public String toString() {
        // TODO: Return "timestamp | patientID | treatmentName".
        return timestamp + " | " + patientID + " | " + treatmentName;
    }
}
