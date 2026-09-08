package patient_intake.optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patient_intake.Patient;
import patient_intake.PatientRegistry;

import static org.junit.jupiter.api.Assertions.*;

public class AdvancedPatientRegistryChecks {

    private PatientRegistry registry;

    @BeforeEach
    void setUp() {
        registry = new PatientRegistry();
    }

    @Test
    void expandsBeyondInitialCapacity() {
        for (int i = 0; i < 15; i++) {
            registry.addPatient(patient("P" + i));
        }

        assertEquals(15, registry.getPatientRegistry().length);
    }

    @Test
    void updatesPatientWithMatchingID() {
        registry.addPatient(patient("P001"));
        Patient replacement = patient("P001");
        replacement.setChiefComplaint("Headache");

        assertTrue(registry.updatePatient(replacement));
        assertEquals("Headache", registry.getPatientByID("P001").getChiefComplaint());
    }

    @Test
    void removesPatientsByIDAndIndex() {
        registry.addPatient(patient("P001"));
        registry.addPatient(patient("P002"));

        assertTrue(registry.removePatient("P001"));
        assertEquals("P002", registry.removePatient(0).getPatientID());
        assertEquals(0, registry.getPatientRegistry().length);
    }

    @Test
    void protectsInternalArrayFromExternalChanges() {
        registry.addPatient(patient("P001"));

        Patient[] exposed = registry.getPatientRegistry();
        exposed[0] = null;

        assertNotNull(registry.getPatientRegistry()[0]);
    }

    private Patient patient(String id) {
        return new Patient(
            id, "Test", "Patient", 40, "Test", 3,
            "Waiting", "Room1", 12, "INS1"
        );
    }
}
