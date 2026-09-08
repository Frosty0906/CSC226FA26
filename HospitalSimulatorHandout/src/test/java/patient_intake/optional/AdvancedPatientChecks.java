package patient_intake.optional;

import org.junit.jupiter.api.Test;
import patient_intake.Patient;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdvancedPatientChecks {

    @Test
    void rejectsAnAgeAbove120() {
        Patient patient = new Patient(
            "P001", "John", "Doe", 45, "Chest pain", 3,
            "Waiting", "Room101", 14, "INS12345"
        );

        assertThrows(IllegalArgumentException.class, () -> patient.setAge(150));
    }

    @Test
    void rejectsANegativeAge() {
        Patient patient = new Patient(
            "P001", "John", "Doe", 45, "Chest pain", 3,
            "Waiting", "Room101", 14, "INS12345"
        );

        assertThrows(IllegalArgumentException.class, () -> patient.setAge(-1));
    }
}
