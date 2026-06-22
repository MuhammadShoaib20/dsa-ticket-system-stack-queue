/**
 * TestDataStructures - Temporary test file for verifying TicketQueue and
 * ResolvedStack behavior. Run from terminal with:
 *   cd src
 *   javac ticketsystem/TestDataStructures.java
 *   java ticketsystem.TestDataStructures
 */
package ticketsystem;

public class TestDataStructures {
    public static void main(String[] args) {
        System.out.println("=== Testing TicketQueue (Circular Queue) ===\n");
        testQueue();
        System.out.println("\n=== Testing ResolvedStack (Array Stack) ===\n");
        testStack();
        System.out.println("\n=== All tests completed ===");
    }

    private static void testQueue() {
        TicketQueue q = new TicketQueue(6);

        // Test initial state
        assert q.isEmpty() : "Queue should be empty initially";
        assert !q.isFull() : "Queue should not be full initially";
        assert q.getSize() == 0 : "Size should be 0";
        assert q.getFront() == -1 : "Front should be -1 when empty";
        assert q.getRear() == -1 : "Rear should be -1 when empty";
        System.out.println("PASS: Initial state correct");

        // Test dequeue on empty queue
        assert q.dequeue() == null : "Dequeue on empty should return null";
        System.out.println("PASS: Dequeue on empty returns null");

        // Enqueue 6 items (fill the queue)
        String[] tickets = {"Ticket A", "Ticket B", "Ticket C", "Ticket D", "Ticket E", "Ticket F"};
        for (int i = 0; i < tickets.length; i++) {
            boolean result = q.enqueue(tickets[i]);
            assert result : "Enqueue should succeed for ticket " + i;
            System.out.println("  Enqueued: " + tickets[i] + " | Front=" + q.getFront() + " Rear=" + q.getRear() + " Size=" + q.getSize());
        }

        // Verify full state
        assert q.isFull() : "Queue should be full after 6 enqueues";
        assert !q.isEmpty() : "Queue should not be empty";
        assert q.getSize() == 6 : "Size should be 6";
        System.out.println("PASS: Queue correctly reports full at size 6");

        // Test enqueue on full queue
        assert !q.enqueue("Extra") : "Enqueue on full should return false";
        System.out.println("PASS: Enqueue on full returns false");

        // Test peekFront and peekRear
        assert q.peekFront().equals("Ticket A") : "Front should be Ticket A";
        assert q.peekRear().equals("Ticket F") : "Rear should be Ticket F";
        System.out.println("PASS: peekFront=" + q.peekFront() + " peekRear=" + q.peekRear());

        // Dequeue 3 items, testing circular behavior
        for (int i = 0; i < 3; i++) {
            String t = q.dequeue();
            System.out.println("  Dequeued: " + t + " | Front=" + q.getFront() + " Rear=" + q.getRear() + " Size=" + q.getSize());
        }
        assert q.getSize() == 3 : "Size should be 3 after 3 dequeues";
        assert q.peekFront().equals("Ticket D") : "Front should now be Ticket D";
        System.out.println("PASS: 3 dequeues successful, front is now " + q.peekFront());

        // Add more items to test circular wrap-around
        q.enqueue("Ticket G");
        q.enqueue("Ticket H");
        q.enqueue("Ticket I");
        System.out.println("  After adding G, H, I | Front=" + q.getFront() + " Rear=" + q.getRear() + " Size=" + q.getSize());
        assert q.isFull() : "Queue should be full again";
        assert q.getSize() == 6 : "Size should be 6";
        System.out.println("PASS: Circular wrap-around works correctly");

        // Empty the queue completely
        while (!q.isEmpty()) {
            System.out.println("  Dequeued: " + q.dequeue() + " | Front=" + q.getFront() + " Rear=" + q.getRear());
        }
        assert q.isEmpty() : "Queue should be empty after dequeuing all";
        assert q.getFront() == -1 : "Front should be -1 when empty";
        assert q.getRear() == -1 : "Rear should be -1 when empty";
        System.out.println("PASS: Queue fully emptied, front & rear reset to -1");
    }

    private static void testStack() {
        ResolvedStack s = new ResolvedStack(6);

        // Test initial state
        assert s.isEmpty() : "Stack should be empty initially";
        assert !s.isFull() : "Stack should not be full initially";
        assert s.getSize() == 0 : "Size should be 0";
        assert s.getTop() == -1 : "Top should be -1 when empty";
        System.out.println("PASS: Initial state correct");

        // Test pop on empty stack
        assert s.pop() == null : "Pop on empty should return null";
        System.out.println("PASS: Pop on empty returns null");

        // Test peek on empty stack
        assert s.peek() == null : "Peek on empty should return null";
        System.out.println("PASS: Peek on empty returns null");

        // Push 6 items (fill the stack)
        String[] tickets = {"Resolved A", "Resolved B", "Resolved C", "Resolved D", "Resolved E", "Resolved F"};
        for (int i = 0; i < tickets.length; i++) {
            boolean result = s.push(tickets[i]);
            assert result : "Push should succeed for ticket " + i;
            System.out.println("  Pushed: " + tickets[i] + " | Top=" + s.getTop() + " Size=" + s.getSize());
        }

        // Verify full state
        assert s.isFull() : "Stack should be full after 6 pushes";
        assert !s.isEmpty() : "Stack should not be empty";
        assert s.getSize() == 6 : "Size should be 6";
        assert s.getTop() == 5 : "Top should be index 5";
        System.out.println("PASS: Stack correctly reports full at size 6");

        // Test push on full stack
        assert !s.push("Extra") : "Push on full should return false";
        System.out.println("PASS: Push on full returns false");

        // Test peek
        assert s.peek().equals("Resolved F") : "Top should be Resolved F";
        System.out.println("PASS: peek() = " + s.peek());

        // Pop 3 items
        for (int i = 0; i < 3; i++) {
            String t = s.pop();
            System.out.println("  Popped: " + t + " | Top=" + s.getTop() + " Size=" + s.getSize());
        }
        assert s.getSize() == 3 : "Size should be 3 after 3 pops";
        assert s.peek().equals("Resolved C") : "Top should now be Resolved C";
        System.out.println("PASS: 3 pops successful, top is now " + s.peek());

        // Empty the stack completely
        while (!s.isEmpty()) {
            System.out.println("  Popped: " + s.pop() + " | Top=" + s.getTop());
        }
        assert s.isEmpty() : "Stack should be empty after popping all";
        assert s.getTop() == -1 : "Top should be -1 when empty";
        System.out.println("PASS: Stack fully emptied, top reset to -1");
    }
}