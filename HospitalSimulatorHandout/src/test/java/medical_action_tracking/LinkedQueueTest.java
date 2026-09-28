package medical_action_tracking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedQueueTest {
    @Test
    void emptyQueueHasNoFrontOrItems() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peekFront());
        assertNull(queue.dequeue());
        assertEquals("[]", queue.toString());
    }

    @Test
    void queueRemovesItemsInFifoOrderAndResetsAfterLastItem() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("first");
        queue.enqueue("second");

        assertEquals("first", queue.peekFront());
        assertEquals("first", queue.dequeue());
        assertEquals("second", queue.dequeue());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peekFront());
    }

    @Test
    void recursiveDisplayIsFrontToRearAndDoesNotRemoveItems() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("alpha");
        queue.enqueue("beta");

        assertEquals("[alpha, beta]", queue.toString());
        assertEquals(2, queue.size());
        assertEquals("alpha", queue.dequeue());
    }

    @Test
    void rejectsNullItems() {
        LinkedQueue<String> queue = new LinkedQueue<>();

        assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
    }
}
