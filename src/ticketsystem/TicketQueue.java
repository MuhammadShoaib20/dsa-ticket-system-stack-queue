/**
 * TicketQueue - Circular Queue implementation for pending support tickets.
 * Capacity is fixed at 6. Uses a circular array to efficiently reuse space.
 */
package ticketsystem;

public class TicketQueue {
    private String[] queue;
    private int front;
    private int rear;
    private int size;
    private final int capacity;

    /**
     * Constructor - initializes queue with given capacity (default 6).
     */
    public TicketQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new String[capacity];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    /**
     * Default constructor with capacity 6.
     */
    public TicketQueue() {
        this(6);
    }

    /**
     * Adds a ticket to the rear of the queue.
     * @param ticket The ticket description to add
     * @return true if enqueued successfully, false if queue is full
     */
    public boolean enqueue(String ticket) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = ticket;
        size++;
        return true;
    }

    /**
     * Removes and returns the ticket from the front of the queue.
     * @return The dequeued ticket, or null if queue is empty
     */
    public String dequeue() {
        if (isEmpty()) {
            return null;
        }
        String ticket = queue[front];
        queue[front] = null;
        if (front == rear) {
            // Last element removed
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
        return ticket;
    }

    /**
     * Returns the front ticket without removing it.
     */
    public String peekFront() {
        if (isEmpty()) {
            return null;
        }
        return queue[front];
    }

    /**
     * Returns the rear ticket without removing it.
     */
    public String peekRear() {
        if (isEmpty()) {
            return null;
        }
        return queue[rear];
    }

    /**
     * Checks if the queue is full.
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Checks if the queue is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current number of elements in the queue.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the front index (or -1 if empty).
     */
    public int getFront() {
        return front;
    }

    /**
     * Returns the rear index (or -1 if empty).
     */
    public int getRear() {
        return rear;
    }

    /**
     * Returns the total capacity of the queue.
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
        return queue[index];
    }
}