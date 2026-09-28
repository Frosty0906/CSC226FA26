package medical_action_tracking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {
    @Test
    void emptyStackHasNoTopOrItems() {
        LinkedStack<String> stack = new LinkedStack<>();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertNull(stack.peek());
        assertNull(stack.pop());
    }

    @Test
    void peekDoesNotRemoveTopAndPopUsesLifoOrder() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("first");
        stack.push("second");

        assertEquals("second", stack.peek());
        assertEquals(2, stack.size());
        assertEquals("[second, first]", stack.toString());
        assertEquals("second", stack.pop());
        assertEquals("first", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void rejectsNullItems() {
        LinkedStack<String> stack = new LinkedStack<>();

        assertThrows(IllegalArgumentException.class, () -> stack.push(null));
    }
}
