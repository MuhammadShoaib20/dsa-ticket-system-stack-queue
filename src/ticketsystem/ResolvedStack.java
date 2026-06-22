/**
 * ResolvedStack - Array-based Stack implementation for resolved support tickets.
 * Capacity is fixed at 6. Provides LIFO (Last-In-First-Out) access for reopening
 * the most recently resolved ticket.
 */
package ticketsystem;

public class ResolvedStack {
    private String[] stack;
    private int top;
    private final int capacity;

    /**
     * Constructor - initializes stack with given capacity (default 6).
     */
    public ResolvedStack(int capacity) {
        this.capacity = capacity;
        this.stack = new String[capacity];
        this.top = -1;
    }

    /**
     * Default constructor with capacity 6.
     */
    public ResolvedStack() {
        this(6);
    }

    /**
     * Pushes a ticket onto the top of the stack.
     * @param ticket The resolved ticket to push
     * @return true if pushed successfully, false if stack is full
     */
    public boolean push(String ticket) {
        if (isFull()) {
            return false;
        }
        stack[++top] = ticket;
        return true;
    }

    /**
     * Removes and returns the top ticket from the stack.
     * @return The popped ticket, or null if stack is empty
     */
    public String pop() {
        if (isEmpty()) {
            return null;
        }
        return stack[top--];
    }

    /**
     * Returns the top ticket without removing it.
     * @return The top ticket, or null if stack is empty
     */
    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return stack[top];
    }

    /**
     * Checks if the stack is full.
     */
    public boolean isFull() {
        return top == capacity - 1;
    }

    /**
     * Checks if the stack is empty.
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Returns the current number of elements in the stack.
     */
    public int getSize() {
        return top + 1;
    }

    /**
     * Returns the top index (or -1 if empty).
     */
    public int getTop() {
        return top;
    }

    /**
     * Returns the total capacity of the stack.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the element at a specific index (for display purposes).
     * Returns null if index is out of bounds or empty.
     */
    public String get(int index) {
        if (index < 0 || index >= capacity) {
            return null;
        }
        return stack[index];
    }
}