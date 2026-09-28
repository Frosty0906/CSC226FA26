package medical_action_tracking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreatmentRecordTest {
    @Test
    void constructorStoresPatientTreatmentAndTimestamp() {
        TreatmentRecord record = new TreatmentRecord("P001", "X-ray", "2026-09-27 09:15");

        assertEquals("P001", record.getPatientID());
        assertEquals("X-ray", record.getTreatmentName());
        assertEquals("2026-09-27 09:15", record.getTimestamp());
        assertEquals("2026-09-27 09:15 | P001 | X-ray", record.toString());
    }
}
