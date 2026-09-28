package medical_action_tracking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreatmentHistoryTest {
    @Test
    void displayReturnsWholeLogNewestFirstWithoutChangingStack() {
        TreatmentHistory history = new TreatmentHistory();
        history.addTreatment("P001", "Assessment", "2026-09-27 09:00");
        history.addTreatment("P002", "X-ray", "2026-09-27 09:05");
        history.addTreatment("P001", "Medication", "2026-09-27 09:10");

        String displayed = history.displayHistory();

        assertEquals("[2026-09-27 09:10 | P001 | Medication, "
            + "2026-09-27 09:05 | P002 | X-ray, "
            + "2026-09-27 09:00 | P001 | Assessment]", displayed);
        assertEquals("Medication", history.undoLastAction().getTreatmentName());
        assertEquals("[2026-09-27 09:05 | P002 | X-ray, "
            + "2026-09-27 09:00 | P001 | Assessment]", history.displayHistory());
    }

    @Test
    void undoRemovesOnlyTheGloballyNewestRecord() {
        TreatmentHistory history = new TreatmentHistory();
        history.addTreatment("P002", "P002 older", "2026-09-27 09:00");
        history.addTreatment("P001", "P001 action", "2026-09-27 09:05");
        history.addTreatment("P003", "P003 newer", "2026-09-27 09:10");

        TreatmentRecord removed = history.undoLastAction();

        assertEquals("P003 newer", removed.getTreatmentName());
        assertEquals("P001 action", history.undoLastAction().getTreatmentName());
        assertEquals("P002 older", history.undoLastAction().getTreatmentName());
    }

    @Test
    void emptyLogDisplaysEmptyAndUndoReturnsNull() {
        TreatmentHistory history = new TreatmentHistory();

        assertEquals("[]", history.displayHistory());
        assertNull(history.undoLastAction());
    }
}
