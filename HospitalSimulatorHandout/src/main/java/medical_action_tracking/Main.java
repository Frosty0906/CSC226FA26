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

        // EXTENSION: simulation metrics
        // each patient gets an arrival minute and how many minutes their service takes (arrivals have to be in order)
        Patient[] simPatients = {
                patient("S001", 3),
                patient("S002", 2),
                patient("S003", 1),
                patient("S004", 2),
                patient("S005", 4)
        };
        int[] arrivalMinutes = {0, 2, 4, 5, 6};
        int[] serviceMinutes = {10, 5, 8, 4, 3};

        EmergencyWaitingRoom simRoom = new EmergencyWaitingRoom();
        int clock = 0;
        // index of the next patient who hasn't shown up yet
        int nextArrival = 0;
        int totalWait = 0;
        int served = 0;

        System.out.println("\nSimulation:");
        while (nextArrival < simPatients.length || !simRoom.isEmpty()) {
            // if nobody is waiting, jump the clock ahead to the next arrival
            if (simRoom.isEmpty() && clock < arrivalMinutes[nextArrival]) {
                clock = arrivalMinutes[nextArrival];
            }
            // everyone who has arrived by now goes into the waiting room
            while (nextArrival < simPatients.length && arrivalMinutes[nextArrival] <= clock) {
                simRoom.addPatient(simPatients[nextArrival]);
                nextArrival++;
            }

            Patient current = simRoom.nextPatient();
            int index = indexOf(simPatients, current.getPatientID());
            // waiting time = minute service starts minus arrival minute, service time doesn't count
            int wait = clock - arrivalMinutes[index];
            totalWait += wait;
            served++;
            System.out.println("Minute " + clock + ": serving " + current.getPatientID()
                    + " (triage level " + current.getTriageLevel() + ", arrived minute "
                    + arrivalMinutes[index] + ", waited " + wait + " min)");

            // doctor is busy until this patient is done
            clock += serviceMinutes[index];
        }
        System.out.println("Average wait time: " + ((double) totalWait / served) + " minutes");

    }

    private static Patient patient(String patientID, int triageLevel) {
        return new Patient(patientID, "Test", "Patient", 30, "Checkup", triageLevel,
                "Waiting", "ER-001", 9, "INS-001");
    }

    // EXTENSION: simulation metrics
    // finds a patient's spot in the sim array so I can look up their arrival and service minutes
    private static int indexOf(Patient[] patients, String patientID) {
        for (int i = 0; i < patients.length; i++) {
            if (patients[i].getPatientID().equals(patientID)) {
                return i;
            }
        }
        // failsafe 
        return -1;
    }

}
