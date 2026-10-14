package medical_action_tracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import patient_intake.Patient;

public class StudentCareTests {
    // helper method to build a test patient so I'm not retyping all ten fields
    private Patient patient(String id, int triageLevel) {
        return new Patient(id, "Test", "Patient", 40, "Test", triageLevel, "Waiting", "Room1", 12, "INS1");
    }

    // test 1
    // checks that the queue still works after it goes empty and gets new items
    // why it matters: if rear isn't reset when the last item leaves, the next enqueue links onto an old node
    @Test
    void testQueueReusableAfterBecomingEmpty() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("first");
        queue.dequeue();

        queue.enqueue("second");
        queue.enqueue("third");

        assertEquals("second", queue.peekFront());
        assertEquals(2, queue.size());
        assertEquals("[second, third]", queue.toString());
    }

    // test 2
    // checks the recursive toString with exactly one node, and that calling it twice doesn't change anything
    // why it matters: one node is the edge case where a comma could get added after the last item
    @Test
    void testQueueDisplayOneItemNoCommaAndNoMutation() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("only");

        assertEquals("[only]", queue.toString());
        assertEquals("[only]", queue.toString());
        assertEquals(1, queue.size());
        assertEquals("only", queue.peekFront());
    }

    // test 3
    // checks the stack going from one item to empty and back to one item
    // why it matters: makes sure top is set back to null and a new push becomes the top
    @Test
    void testStackOneItemTransitions() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("a");
        assertEquals("a", stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals("[]", stack.toString());

        stack.push("b");
        assertEquals("b", stack.peek());
        assertEquals(1, stack.size());
        assertEquals("[b]", stack.toString());
    }

    // test 4
    // adds patients from least to most urgent and checks they come out by level, FIFO inside level 2
    // why it matters: the instructor test only uses levels 1 and 3, this checks lanes 2 and 4 too
    @Test
    void testWaitingRoomServesLevelsTwoThreeFourInOrder() {
        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom();
        Patient levelFour = patient("P004", 4);
        Patient firstLevelTwo = patient("P021", 2);
        Patient levelThree = patient("P003", 3);
        Patient secondLevelTwo = patient("P022", 2);

        waitingRoom.addPatient(levelFour);
        waitingRoom.addPatient(firstLevelTwo);
        waitingRoom.addPatient(levelThree);
        waitingRoom.addPatient(secondLevelTwo);

        assertSame(firstLevelTwo, waitingRoom.nextPatient());
        assertSame(secondLevelTwo, waitingRoom.nextPatient());
        assertSame(levelThree, waitingRoom.nextPatient());
        assertSame(levelFour, waitingRoom.nextPatient());
        assertTrue(waitingRoom.isEmpty());
    }

    // test 5
    // checks that a level 0 patient gets rejected without messing up patients already waiting
    // why it matters: a bad triage level shouldn't change the lanes or the patient
    @Test
    void testInvalidLevelDoesNotChangeExistingLanes() {
        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom();
        Patient valid = patient("P001", 2);
        Patient invalid = patient("P000", 0);
        waitingRoom.addPatient(valid);

        assertFalse(waitingRoom.addPatient(invalid));
        assertEquals(0, invalid.getTriageLevel());
        assertEquals(1, waitingRoom.size());
        assertSame(valid, waitingRoom.nextPatient());
        assertNull(waitingRoom.nextPatient());
    }

    // test 6
    // undoes every record and then one more time on the empty log
    // why it matters: undo should keep working down to empty and then safely return null instead of crashing
    @Test
    void testUndoEverythingThenUndoOnEmptyLog() {
        TreatmentHistory history = new TreatmentHistory();
        history.addTreatment("P001", "Assessment", "2026-09-27 09:00");
        history.addTreatment("P002", "X-ray", "2026-09-27 09:05");

        assertEquals("P002", history.undoLastAction().getPatientID());
        assertEquals("[2026-09-27 09:00 | P001 | Assessment]", history.displayHistory());
        assertEquals("P001", history.undoLastAction().getPatientID());
        assertEquals("[]", history.displayHistory());
        assertNull(history.undoLastAction());
    }


    // EXTENSION TESTS

    // extension test 1
    // recursive count on an empty queue should be 0 and match size()
    @Test
    void testCountRecursiveEmptyQueue() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        assertEquals(0, queue.countRecursively());
        assertEquals(queue.size(), queue.countRecursively());
    }

    // extension test 2
    // recursive count on a multi-node queue should match size(), even after a dequeue
    @Test
    void testCountRecursiveMatchesSizeMultiNode() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        assertEquals(3, queue.countRecursively());
        assertEquals(queue.size(), queue.countRecursively());

        queue.dequeue();
        assertEquals(queue.size(), queue.countRecursively());
    }

    // extension test 3
    // fills lane 2 to capacity, then the next level 2 patient gets rejected but other lanes still work
    @Test
    void testFullLaneRejectsWithoutChangingLanes() {
        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom();
        for (int i = 0; i < EmergencyWaitingRoom.triagequeuecapacity; i++) {
            assertTrue(waitingRoom.addPatient(patient("P2" + i, 2)));
        }

        assertFalse(waitingRoom.addPatient(patient("P2X", 2)));
        assertEquals(EmergencyWaitingRoom.triagequeuecapacity, waitingRoom.size());
        assertTrue(waitingRoom.addPatient(patient("P1", 1)));
    }

    // extension test 4
    // array queue keeps FIFO order after wrapping around and resizing
    @Test
    void testArrayQueueWrapsAndResizes() {
        ArrayQueue<String> queue = new ArrayQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.dequeue();
        queue.dequeue();
        // these wrap around to the start of the array and then force a resize
        queue.enqueue("d");
        queue.enqueue("e");
        queue.enqueue("f");
        queue.enqueue("g");

        assertEquals("[c, d, e, f, g]", queue.toString());
        assertEquals(5, queue.size());
        assertEquals("c", queue.dequeue());
    }

}
