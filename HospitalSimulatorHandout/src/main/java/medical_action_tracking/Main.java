package medical_action_tracking;

import patient_intake.Patient;
import patient_intake.PatientRegistry;
import triage_efficiency.EfficiencyTester;

public class Main {
    public static void main(String[] args) {
        PatientRegistry registry = new PatientRegistry();
        Patient[] samplePatients = {
                patient("P001", 3),
                patient("P002", 1),
                patient("P003", 1)
        };
        for (Patient patient : samplePatients) {
            registry.addPatient(patient);
        }

        Patient[] patients = registry.getPatientRegistry();
        Patient searchedPatient = new EfficiencyTester().linearSearch(patients, "P002");
        System.out.println("Week 2 search found: " + searchedPatient);

        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom();
        for (Patient patient : patients) {
            waitingRoom.addPatient(patient);
        }
        while (!waitingRoom.isEmpty()) {
            Patient nextPatient = waitingRoom.nextPatient();
            System.out.println("Next patient: " + nextPatient.getPatientID()
                    + " (triage level " + nextPatient.getTriageLevel() + ")");
        }

        TreatmentHistory history = new TreatmentHistory();
        history.addTreatment("P002", "Initial assessment", "2026-09-27 09:00");
        history.addTreatment("P001", "X-ray", "2026-09-27 09:10");
        history.addTreatment("P002", "Pain relief", "2026-09-27 09:15");

        System.out.println("Treatment log: " + history.displayHistory());
        TreatmentRecord undone = history.undoLastAction();
        System.out.println("Undid: " + undone);
        System.out.println("Remaining log: " + history.displayHistory());
    }

    private static Patient patient(String patientID, int triageLevel) {
        return new Patient(patientID, "Test", "Patient", 30, "Checkup", triageLevel,
                "Waiting", "ER-001", 9, "INS-001");
    }
}
