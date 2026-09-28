package medical_action_tracking;

public class TreatmentHistory {
    private LinkedStack<TreatmentRecord> allRecords;

    public TreatmentHistory() {
        allRecords = new LinkedStack<>();
    }

    public void addTreatment(String patientID, String treatment, String timestamp) {
        // This is a small worked example: create the record, then push it on the stack.
        TreatmentRecord record = new TreatmentRecord(patientID, treatment, timestamp);
        allRecords.push(record);
    }

    /**
     * Remove and return the newest record in the hospital-wide log.
     *
     * @return the newest record, or null when the log is empty
     */
    public TreatmentRecord undoLastAction() {
        // TODO: Pop and return the newest record. Empty pop returns null.
        return null;
    }

    /**
     * Return the complete log newest-first without changing the stack.
     */
    public String displayHistory() {
        // TODO: Use the stack's top-to-bottom string representation.
        return "[]";
    }
}
