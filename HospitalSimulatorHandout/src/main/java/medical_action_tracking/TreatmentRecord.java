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
        return null;
    }

    public String getTreatmentName() {
        // TODO: Return the treatment description.
        return null;
    }

    public String getTimestamp() {
        // TODO: Return the timestamp.
        return null;
    }

    @Override
    public String toString() {
        // TODO: Return "timestamp | patientID | treatmentName".
        return "";
    }
}
