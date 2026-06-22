# PROJECT REPORT

## SupportDesk Simulator — Customer Support Ticket System

---

## 1. COVER PAGE

---

# SupportDesk Simulator

## Customer Support Ticket System using Queue & Stack Data Structures

**Course:** Data Structures & Algorithms

**Submitted To:** [Sir Name]

**Submitted By:**

| Name | CMS ID |
|---|---|
| [Student Name 1] | [CMS-001] |
| [Student Name 2] | [CMS-002] |
| [Student Name 3] | [CMS-003] |

**Semester:** [Fall/Spring 202X]

**Department:** Computer Science

**University:** Hamdard University

---

*[LOGO — Insert Hamdard University logo here]*

---

## 2. ACKNOWLEDGMENT

We would like to express our sincere gratitude to our instructor, **[Sir Name]**, for their guidance and support throughout the development of this project. Their teachings on data structures, particularly queues and stacks, provided the foundation upon which this application is built.

We also thank Hamdard University for providing the resources and environment necessary for our academic growth.

Finally, we acknowledge the open-source community for the Java Swing framework and Maven build tool, which made the development of this desktop application possible.

---

## 3. TABLE OF CONTENTS

| Section | Page |
|---|---|
| 1. Cover Page | 1 |
| 2. Acknowledgment | 2 |
| 3. Table of Contents | 3 |
| 4. Project Introduction | 4 |
| 5. Project Scope | 5 |
| 6. Project Features | 6 |
| 7. Tools and Languages Used | 7 |
| 8. Working of Project | 8 |
| 9. Flow Chart / UML Diagram | 9 |
| 10. Coding | 10 |
| 11. Output Screenshots | 11 |
| 12. References | 12 |

---

## 4. PROJECT INTRODUCTION

### 4.1 What is SupportDesk Simulator?

**SupportDesk Simulator** is a Java Swing-based desktop application that simulates a customer support ticket management system. It demonstrates the practical implementation of two fundamental data structures:

1. **Circular Queue** — Used to manage pending support tickets (FIFO — First In, First Out)
2. **Array Stack** — Used to store resolved tickets for potential reopening (LIFO — Last In, First Out)

### 4.2 Problem Statement

Customer support centers receive tickets that need to be processed in order. Sometimes a resolved ticket needs to be reopened. A queue naturally handles the "first come, first served" nature of ticket processing, while a stack handles the "most recently resolved, first to reopen" requirement.

### 4.3 Solution

This project provides a visual, interactive GUI that allows users to:
- Add new support tickets to a pending queue
- Process tickets from the queue (moving them to a resolved stack)
- Reopen the most recently resolved ticket back into the queue
- View real-time status of both data structures

### 4.4 Data Structure Details

| Data Structure | Type | Capacity | Use Case |
|---|---|---|---|
| `TicketQueue` | Circular Queue (Array) | 6 | Pending support tickets waiting to be processed |
| `ResolvedStack` | Array Stack | 6 | Resolved tickets available for reopening |

---

## 5. PROJECT SCOPE

### 5.1 In-Scope Features

- Adding support tickets with descriptions
- Processing pending tickets from queue to stack
- Reopening resolved tickets from stack to queue
- Real-time display of queue/stack status (isFull, isEmpty, Front, Rear, Top)
- Slide-out menu for toggling between Queue and Stack views
- Error handling with red error messages for edge cases
- Hover/press/release visual effects on all buttons
- Social media links (YouTube, Facebook, Twitter, Hamdard University)
- Info button showing developer details
- Splash screen on application startup
- Maven build support for NetBeans IDE

### 5.2 Out-of-Scope

- Database persistence (tickets are in-memory only)
- Network/multi-user support
- Authentication/authorization
- Real-time notifications

### 5.3 Target Users

- Students learning data structures (Queue, Stack)
- Instructors teaching practical data structure implementation
- Anyone interested in Java Swing desktop applications

---

## 6. PROJECT FEATURES

### 6.1 Functional Features

| # | Feature | Description |
|---|---|---|
| 1 | **Add Ticket** | Enqueues a ticket description into the circular queue |
| 2 | **Process Ticket** | Dequeues front ticket → pushes onto resolved stack |
| 3 | **Reopen Ticket** | Pops top resolved ticket → enqueues back to queue |
| 4 | **Clear Last Action** | Clears action message and error labels |
| 5 | **Queue View** | Shows pending queue boxes with ticket contents |
| 6 | **Stack View** | Shows resolved stack boxes with ticket contents |
| 7 | **Slide Menu** | Toggle between Queue and Stack views |

### 6.2 Visual Features

| # | Feature | Description |
|---|---|---|
| 1 | **Dark Theme** | Gradient background (dark blue tones) |
| 2 | **Custom Buttons** | JLabel-based buttons with hover/press effects |
| 3 | **Info Labels** | Live-updating Capacity, isFull, isEmpty, Front, Rear, Top |
| 4 | **Error Messages** | Red error text for all edge cases |
| 5 | **Splash Screen** | 3-second startup splash with gradient background |
| 6 | **Social Icons** | Clickable YouTube, Facebook, Twitter, HU icons |

### 6.3 Edge Case Handling

| Scenario | System Response |
|---|---|
| Empty input | "Please describe the issue before adding a ticket." |
| Queue full (add) | "Queue is full! Cannot add more tickets." |
| Queue empty (process) | "Queue is empty! No tickets to process." |
| Stack full (process) | "Resolved stack is full! Reopen a ticket before processing more." |
| Stack empty (reopen) | "Resolved stack is empty! No tickets to reopen." |
| Queue full (reopen) | "Queue is full! Process a ticket before reopening." |

---

## 7. TOOLS AND LANGUAGES USED

### 7.1 Programming Language

| Language | Version | Purpose |
|---|---|---|
| Java | JDK 17+ (compiled with JDK 25) | Core application logic and GUI |

### 7.2 Libraries / Frameworks

| Library | Purpose |
|---|---|
| **Java Swing (javax.swing.*)** | GUI components (JFrame, JPanel, JLabel, JTextField, JWindow) |
| **Java AWT (java.awt.*)** | Graphics, colors, fonts, layout |
| **Java Desktop API** | Opening web URLs in default browser |

### 7.3 Development Tools

| Tool | Version | Purpose |
|---|---|---|
| **VS Code** | Latest | Code editor during development |
| **NetBeans** | 25 | Maven project IDE for users |
| **Maven** | 3.9+ | Build automation and dependency management |

### 7.4 Project Structure

```
Customer_Support_Ticket_System/
├── pom.xml                    — Maven build file
├── README.md                  — Usage instructions
├── TESTING_PROCEDURE.md        — Step-by-step testing guide
├── resources/                 — (optional) bg.jpg for splash
└── src/
    └── ticketsystem/
        ├── TicketQueue.java       — Circular queue (6 slots)
        ├── ResolvedStack.java     — Array stack (6 slots)
        ├── TicketSystemGUI.java   — Main GUI (~650 lines)
        └── TestDataStructures.java — Backend test suite
```

---

## 8. WORKING OF PROJECT

### 8.1 Application Flow

```
[START] → [Splash Screen (3 sec)] → [Main GUI Window]
                                         │
                          ┌──────────────┼──────────────┐
                          ▼              ▼              ▼
                    [Add Ticket]   [Process Ticket] [Reopen Ticket]
                          │              │              │
                          ▼              ▼              ▼
                   Queue.enqueue()  Queue.dequeue()  Stack.pop()
                          │        Stack.push()      Queue.enqueue()
                          ▼              │              │
                   refreshQueueDisplay() │   refreshQueueDisplay()
                          │              ▼              refreshStackDisplay()
                          └──────refreshQueueDisplay()──┘
                                      refreshStackDisplay()
                                            │
                                            ▼
                                   [Queue View or Stack View]
                                   via ☰ Menu toggle
```

### 8.2 Circular Queue Working

```
Initial: Front = -1, Rear = -1

enqueue("Ticket A"):  Front=0, Rear=0
  ┌─────┬─────┬─────┬─────┬─────┬─────┐
  │  A  │     │     │     │     │     │
  └─────┴─────┴─────┴─────┴─────┴─────┘
     F/R

enqueue("Ticket B"):  Front=0, Rear=1
  ┌─────┬─────┬─────┬─────┬─────┬─────┐
  │  A  │  B  │     │     │     │     │
  └─────┴─────┴─────┴─────┴─────┴─────┘
     F         R

dequeue(): returns "Ticket A", Front=1
  ┌─────┬─────┬─────┬─────┬─────┬─────┐
  │     │  B  │     │     │     │     │
  └─────┴─────┴─────┴─────┴─────┴─────┘
           F    R

Circular wrap: enqueue fills from Rear, wrapping around
```

### 8.3 Stack Working (LIFO)

```
Initial: Top = -1

push("Resolved A"): Top=0
  ┌─────┐
  │  A  │ ← Top
  ├─────┤
  │     │
  ├─────┤
  │     │
  ├─────┤
  │     │
  ├─────┤
  │     │
  └─────┘

push("Resolved B"): Top=1
  ┌─────┐
  │  B  │ ← Top
  ├─────┤
  │  A  │
  ├─────┤
  │     │
  ├─────┤
  │     │
  └─────┘

pop(): returns "Resolved B", Top=0
```

---

## 9. FLOW CHART / UML DIAGRAM

### 9.1 System Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                    TicketSystemGUI (JFrame)                  │
│  ┌───────────────────────────────────────────────────────┐  │
│  │                   Main Content Panel                   │  │
│  │                                                       │  │
│  │  ┌─────────────────────────────────────────────────┐  │  │
│  │  │           Title & Subtitle Labels                │  │  │
│  │  └─────────────────────────────────────────────────┘  │  │
│  │                                                       │  │
│  │  ┌─────────────────────────────────────────────────┐  │  │
│  │  │  Queue/Stack Display Boxes (6 JLabels each)     │  │  │
│  │  └─────────────────────────────────────────────────┘  │  │
│  │                                                       │  │
│  │  ┌─────────────────────────────────────────────────┐  │  │
│  │  │  Info Labels (Capacity, isFull, isEmpty, etc)    │  │  │
│  │  └─────────────────────────────────────────────────┘  │  │
│  │                                                       │  │
│  │  ┌─────────────────────────────────────────────────┐  │  │
│  │  │  Input Field + Action Buttons (Add/Process/     │  │  │
│  │  │  Reopen/Clear) + Menu Button                    │  │  │
│  │  └─────────────────────────────────────────────────┘  │  │
│  │                                                       │  │
│  │  ┌─────────────────────────────────────────────────┐  │  │
│  │  │  Error Label + Last Action Label                │  │  │
│  │  └─────────────────────────────────────────────────┘  │  │
│  │                                                       │  │
│  │  ┌─────────────────────┐  ┌────────────────────────┐  │  │
│  │  │  Slide Menu Panel   │  │  Social Icons + Info   │  │  │
│  │  │  (Show Queue/Stack) │  │  Button                │  │  │
│  │  └─────────────────────┘  └────────────────────────┘  │  │
│  └───────────────────────────────────────────────────────┘  │
│                                                             │
│  ┌─────────────────────┐      ┌───────────────────────┐     │
│  │    TicketQueue      │◄────►│    ResolvedStack      │     │
│  │  (Circular Queue)   │      │    (Array Stack)      │     │
│  │  capacity = 6       │      │    capacity = 6       │     │
│  └─────────────────────┘      └───────────────────────┘     │
└─────────────────────────────────────────────────────────────┘
```

### 9.2 Class Diagram (UML)

```
┌──────────────────────────────────────────────┐
│              TicketQueue                      │
├──────────────────────────────────────────────┤
│ - queue: String[]                             │
│ - front: int                                  │
│ - rear: int                                   │
│ - size: int                                   │
│ - capacity: int                               │
├──────────────────────────────────────────────┤
│ + TicketQueue(capacity: int)                  │
│ + TicketQueue()                               │
│ + enqueue(ticket: String): boolean            │
│ + dequeue(): String                           │
│ + peekFront(): String                         │
│ + peekRear(): String                          │
│ + isFull(): boolean                           │
│ + isEmpty(): boolean                          │
│ + getSize(): int                              │
│ + getFront(): int                             │
│ + getRear(): int                              │
│ + getCapacity(): int                          │
│ + get(index: int): String                     │
└──────────────────────────────────────────────┘
                      │
    ┌─────────────────┴────────────────────┐
    │                                      │
    ▼                                      ▼
┌──────────────────────────────┐  ┌──────────────────────────────────┐
│      ResolvedStack            │  │       TicketSystemGUI            │
├──────────────────────────────┤  ├──────────────────────────────────┤
│ - stack: String[]             │  │ - ticketQueue: TicketQueue       │
│ - top: int                    │  │ - resolvedStack: ResolvedStack   │
│ - capacity: int               │  │ - queueBoxes: JLabel[]           │
├──────────────────────────────┤  │ - stackBoxes: JLabel[]           │
│ + ResolvedStack(capacity:int) │  │ - inputField: JTextField         │
│ + ResolvedStack()             │  │ - errorLabel: JLabel             │
│ + push(ticket: String): bool  │  │ - slideMenu: JPanel              │
│ + pop(): String               │  │ - ... (many UI components)       │
│ + peek(): String              │  ├──────────────────────────────────┤
│ + isFull(): boolean           │  │ + TicketSystemGUI()              │
│ + isEmpty(): boolean          │  │ + main(args: String[]): void     │
│ + getSize(): int              │  │ - addTicket(): void              │
│ + getTop(): int               │  │ - processTicket(): void          │
│ + getCapacity(): int          │  │ - reopenTicket(): void           │
│ + get(index: int): String     │  │ - refreshQueueDisplay(): void    │
└──────────────────────────────┘  │ - refreshStackDisplay(): void     │
                                  │ - toggleMenu(): void              │
                                  │ - showQueueView(): void           │
                                  │ - showStackView(): void           │
                                  │ - openWebPage(url: String): void  │
                                  │ - showSplashScreen(): void        │
                                  └──────────────────────────────────┘
```

### 9.3 Use Case Diagram

```
                        ┌─────────────────┐
                        │   SupportDesk    │
                        │    Simulator     │
                        └─────────────────┘
                                │
         ┌──────────────────────┼──────────────────────┐
         │                      │                      │
         ▼                      ▼                      ▼
   ┌──────────────┐     ┌──────────────┐     ┌──────────────┐
   │  Add Ticket   │     │ Process Ticket│     │ Reopen Ticket│
   │  (enqueue)    │     │ (dequeue→push)│     │ (pop→enqueue)│
   └──────────────┘     └──────────────┘     └──────────────┘
         │                      │                      │
         ▼                      ▼                      ▼
   ┌──────────────┐     ┌──────────────┐     ┌──────────────┐
   │ Queue Full?   │     │Queue Empty?  │     │Stack Empty?  │
   │ → Error       │     │Stack Full?   │     │Queue Full?   │
   │   or enqueue  │     │ → Error or   │     │ → Error or   │
   │              │     │   process    │     │   reopen     │
   └──────────────┘     └──────────────┘     └──────────────┘
```

---

## 10. CODING

### 10.1 TicketQueue.java — Circular Queue

```java
package ticketsystem;

public class TicketQueue {
    private String[] queue;
    private int front;
    private int rear;
    private int size;
    private final int capacity;

    public TicketQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new String[capacity];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    public TicketQueue() {
        this(6);
    }

    public boolean enqueue(String ticket) {
        if (isFull()) return false;
        if (isEmpty()) front = 0;
        rear = (rear + 1) % capacity;
        queue[rear] = ticket;
        size++;
        return true;
    }

    public String dequeue() {
        if (isEmpty()) return null;
        String ticket = queue[front];
        queue[front] = null;
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
        return ticket;
    }

    public String peekFront() {
        if (isEmpty()) return null;
        return queue[front];
    }

    public String peekRear() {
        if (isEmpty()) return null;
        return queue[rear];
    }

    public boolean isFull() { return size == capacity; }
    public boolean isEmpty() { return size == 0; }
    public int getSize() { return size; }
    public int getFront() { return front; }
    public int getRear() { return rear; }
    public int getCapacity() { return capacity; }

    public String get(int index) {
        if (index < 0 || index >= capacity) return null;
        return queue[index];
    }
}
```

### 10.2 ResolvedStack.java — Array Stack

```java
package ticketsystem;

public class ResolvedStack {
    private String[] stack;
    private int top;
    private final int capacity;

    public ResolvedStack(int capacity) {
        this.capacity = capacity;
        this.stack = new String[capacity];
        this.top = -1;
    }

    public ResolvedStack() {
        this(6);
    }

    public boolean push(String ticket) {
        if (isFull()) return false;
        stack[++top] = ticket;
        return true;
    }

    public String pop() {
        if (isEmpty()) return null;
        return stack[top--];
    }

    public String peek() {
        if (isEmpty()) return null;
        return stack[top];
    }

    public boolean isFull() { return top == capacity - 1; }
    public boolean isEmpty() { return top == -1; }
    public int getSize() { return top + 1; }
    public int getTop() { return top; }
    public int getCapacity() { return capacity; }

    public String get(int index) {
        if (index < 0 || index >= capacity) return null;
        return stack[index];
    }
}
```

### 10.3 TicketSystemGUI.java — Main GUI (Key Methods)

```java
// === CORE ACTIONS ===

private void addTicket() {
    String ticket = inputField.getText().trim();
    if (ticket.isEmpty()) {
        errorLabel.setText("Please describe the issue before adding a ticket.");
        return;
    }
    if (ticketQueue.isFull()) {
        errorLabel.setText("Queue is full! Cannot add more tickets.");
        return;
    }
    ticketQueue.enqueue(ticket);
    inputField.setText("");
    errorLabel.setText("");
    lastActionLabel.setText("Last Action: Added \"" + ticket + "\" to queue");
    refreshQueueDisplay();
}

private void processTicket() {
    if (ticketQueue.isEmpty()) {
        errorLabel.setText("Queue is empty! No tickets to process.");
        return;
    }
    if (resolvedStack.isFull()) {
        errorLabel.setText("Resolved stack is full! Reopen a ticket before processing more.");
        return;
    }
    String ticket = ticketQueue.dequeue();
    resolvedStack.push(ticket);
    errorLabel.setText("");
    lastActionLabel.setText("Last Action: Processed \"" + ticket + "\" → moved to resolved stack");
    refreshQueueDisplay();
    refreshStackDisplay();
}

private void reopenTicket() {
    if (resolvedStack.isEmpty()) {
        errorLabel.setText("Resolved stack is empty! No tickets to reopen.");
        return;
    }
    if (ticketQueue.isFull()) {
        errorLabel.setText("Queue is full! Process a ticket before reopening.");
        return;
    }
    String ticket = resolvedStack.pop();
    ticketQueue.enqueue(ticket);
    errorLabel.setText("");
    lastActionLabel.setText("Last Action: Reopened \"" + ticket + "\" → moved back to queue");
    refreshQueueDisplay();
    refreshStackDisplay();
}
```

### 10.4 TicketSystemGUI.java — Display Refresh

```java
private void refreshQueueDisplay() {
    for (int i = 0; i < 6; i++) {
        String content = ticketQueue.get(i);
        if (content != null) {
            queueBoxes[i].setText(truncateText(content, 12));
            queueBoxes[i].setBackground(BOX_FILL_FILLED);
        } else {
            queueBoxes[i].setText("[Empty]");
            queueBoxes[i].setBackground(BOX_FILL_EMPTY);
        }
    }
    queueIsFullLabel.setText("isFull: " + ticketQueue.isFull());
    queueIsEmptyLabel.setText("isEmpty: " + ticketQueue.isEmpty());
    queueFrontLabel.setText("Front: " + ticketQueue.getFront());
    queueRearLabel.setText("Rear: " + ticketQueue.getRear());
}

private void refreshStackDisplay() {
    for (int i = 0; i < 6; i++) {
        String content = resolvedStack.get(i);
        if (content != null) {
            stackBoxes[i].setText(truncateText(content, 12));
            stackBoxes[i].setBackground(BOX_FILL_FILLED);
        } else {
            stackBoxes[i].setText("[Empty]");
            stackBoxes[i].setBackground(BOX_FILL_EMPTY);
        }
    }
    stackIsFullLabel.setText("isFull: " + resolvedStack.isFull());
    stackIsEmptyLabel.setText("isEmpty: " + resolvedStack.isEmpty());
    stackTopLabel.setText("Top: " + resolvedStack.getTop());
}
```

### 10.5 TicketSystemGUI.java — Splash Screen

```java
private static void showSplashScreen() {
    JWindow splash = new JWindow();
    splash.setSize(500, 300);
    splash.setLocationRelativeTo(null);

    JPanel splashPanel = new JPanel(null) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
            GradientPaint gp = new GradientPaint(0, 0,
                new Color(15, 20, 35), 0, getHeight(),
                new Color(25, 35, 60));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };
    splash.setContentPane(splashPanel);

    // Add title, subtitle, loading labels...

    Timer timer = new Timer(3000, e -> {
        splash.setVisible(false);
        splash.dispose();
        SwingUtilities.invokeLater(() -> {
            TicketSystemGUI gui = new TicketSystemGUI();
            gui.setVisible(true);
        });
    });
    timer.setRepeats(false);
    timer.start();
}
```

---

## 11. OUTPUT SCREENSHOTS

---

### 11.1 Splash Screen

**[INSERT SCREENSHOT HERE — Launch the app and capture the 3-second splash screen]**

*Description:* The splash screen appears for 3 seconds when the application starts. It displays "SupportDesk Simulator" title with a gradient background and "Loading..." text.

---

### 11.2 Main Window — Initial State (Queue View)

**[INSERT SCREENSHOT HERE — Capture the main window right after splash screen closes]**

*Description:* The main window shows:
- Title: "SupportDesk Simulator" with subtitle "Customer Support Ticket System | Queue + Stack"
- ☰ Menu button (top-left)
- Info button (top-right)
- "PENDING QUEUE" section header
- 6 empty queue boxes labeled "[Empty]"
- Info labels: Capacity=6, isFull=false, isEmpty=true, Front=-1, Rear=-1
- Input field with "Describe the issue:" label
- Four action buttons: Add Ticket, Process Ticket, Reopen Ticket, Clear Last Action
- Social media icons at bottom: YouTube, Facebook, Twitter, HU

---

### 11.3 Adding Tickets to Queue

**[INSERT SCREENSHOT HERE — Add a few tickets and capture the screen]**

*Description:* After typing "Login page not loading" and clicking "Add Ticket", the first queue box shows the ticket text. Front=0, Rear=0, isEmpty=false. After adding multiple tickets, boxes fill up and info labels update.

---

### 11.4 Queue Full — Error Message

**[INSERT SCREENSHOT HERE — Fill all 6 queue slots and try to add one more]**

*Description:* When all 6 queue boxes are filled and the user clicks "Add Ticket", a red error message appears: "Queue is full! Cannot add more tickets." The isFull label shows "true".

---

### 11.5 Processing Tickets

**[INSERT SCREENSHOT HERE — Click "Process Ticket" and capture the result]**

*Description:* After clicking "Process Ticket", the front ticket disappears from the queue and a "Last Action: Processed" message appears. The info labels update (Front increments).

---

### 11.6 Resolved Stack View

**[INSERT SCREENSHOT HERE — Click ☰ Menu → "Show Resolved Stack"]**

*Description:* After clicking the menu button and selecting "Show Resolved Stack", the view switches to show the resolved stack. The header changes to "RESOLVED STACK", stack boxes show processed tickets, and stack info labels (isFull, isEmpty, Top) are visible.

---

### 11.7 Queue View (via Menu)

**[INSERT SCREENSHOT HERE — Click ☰ Menu → "Show Pending Queue"]**

*Description:* The menu panel is visible sliding from the left side with options "Show Pending Queue" and "Show Resolved Stack". After clicking "Show Pending Queue", the queue view returns.

---

### 11.8 Slide-Out Menu

**[INSERT SCREENSHOT HERE — Click ☰ Menu to open the slide panel]**

*Description:* The slide-out menu panel shows:
- "MENU" title
- ✕ Close button
- "Show Pending Queue" button
- "Show Resolved Stack" button

---

### 11.9 Reopen Ticket

**[INSERT SCREENSHOT HERE — Click "Reopen Ticket" and capture]**

*Description:* After clicking "Reopen Ticket", the top item from the resolved stack moves back to the queue. The last action label confirms the operation.

---

### 11.10 Empty Stack Error

**[INSERT SCREENSHOT HERE — Try to reopen when stack is empty]**

*Description:* When the resolved stack is empty and the user clicks "Reopen Ticket", a red error appears: "Resolved stack is empty! No tickets to reopen."

---

### 11.11 Empty Queue Error

**[INSERT SCREENSHOT HERE — Try to process when queue is empty]**

*Description:* When the queue is empty and the user clicks "Process Ticket", a red error appears: "Queue is empty! No tickets to process."

---

### 11.12 Info Button — Press and Hold

**[INSERT SCREENSHOT HERE — Press and hold the "Info" button]**

*Description:* While pressing and holding the Info button (top-right), yellow text appears: "Developed by Student | CMS: 123456". When released, the text disappears.

---

### 11.13 Social Media Icons

**[INSERT SCREENSHOT HERE — Hover over a social icon]**

*Description:* The social media icons at the bottom (YouTube, Facebook, Twitter, HU) have hover effects — background color changes on mouse enter. Clicking opens the respective URL in the default browser.

---

### 11.14 Button Hover Effects

**[INSERT SCREENSHOT HERE — Hover over any action button]**

*Description:* All custom buttons have hover/press/release effects. On hover, the background changes from dark blue to lighter blue. On press, it becomes darker. On release, it returns to hover state and triggers the action.

---

### 11.15 Backend Test Output (Console)

**[INSERT SCREENSHOT HERE — Run TestDataStructures and capture terminal output]**

*Description:* Running `java -ea ticketsystem.TestDataStructures` in the terminal shows all queue and stack operations passing — including circular wrap-around, empty/full edge cases, and front/rear/top index verification.

---

## 12. REFERENCES

1. **Java Documentation — Swing API**
   - https://docs.oracle.com/javase/tutorial/uiswing/
   - Reference for JFrame, JPanel, JLabel, JTextField, JWindow

2. **Java Documentation — AWT Graphics**
   - https://docs.oracle.com/javase/tutorial/2d/
   - Reference for GradientPaint, Graphics2D, BufferedImage

3. **Data Structures — Circular Queue**
   - https://www.geeksforgeeks.org/circular-queue-set-1-introduction-array-implementation/
   - Reference for circular queue implementation using arrays

4. **Data Structures — Stack (Array Implementation)**
   - https://www.geeksforgeeks.org/stack-data-structure-introduction-program/
   - Reference for stack push/pop operations

5. **Maven — Build Automation**
   - https://maven.apache.org/guides/index.html
   - Reference for pom.xml configuration, compiler plugin, exec plugin

6. **NetBeans IDE — Maven Support**
   - https://netbeans.apache.org/tutorial/main/kb/docs/java/maven/
   - Reference for opening Maven projects in NetBeans

7. **Java Desktop API**
   - https://docs.oracle.com/javase/8/docs/api/java/awt/Desktop.html
   - Reference for Desktop.browse() to open URLs

8. **SwingUtilities — Event Dispatch Thread**
   - https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
   - Reference for SwingUtilities.invokeLater()

---

*End of Report*