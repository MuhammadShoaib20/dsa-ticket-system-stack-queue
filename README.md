# SupportDesk Simulator

A **Customer Support Ticket System** built with Java Swing, demonstrating **Queue** (circular queue) and **Stack** (array stack) data structures.

## Features

- **Pending Queue**: Add tickets to a circular queue (capacity 6) for processing
- **Resolved Stack**: Processed tickets are moved to a LIFO stack (capacity 6)
- **Reopen Tickets**: Reopen the most recently resolved ticket back into the queue
- **Slide Menu**: Toggle between Queue and Stack views
- **Error Handling**: Real-time validation for full/empty states with red error messages
- **Info Labels**: Live updates for isFull, isEmpty, Front/Rear (queue) and Top (stack)
- **Social Links**: Clickable YouTube, Facebook, Twitter, and Hamdard University icons
- **Info Button**: Press-and-hold to show developer information
- **Custom Buttons**: Hover/press/release visual effects on all clickable elements
- **Splash Screen**: 3-second splash screen on startup with gradient background

## Requirements

- **JDK 11+** (built and tested with JDK 25)
- Any text editor or IDE (VS Code with Java Extension Pack recommended)

## How to Compile and Run

Open a terminal in the project root directory:

```bash
cd src
javac ticketsystem/*.java
java ticketsystem.TicketSystemGUI
```

Or compile and run the test file to verify data structure operations:

```bash
cd src
javac ticketsystem/*.java
java -ea ticketsystem.TestDataStructures
```

## Project Structure

```
Customer_Support_Ticket_System/
├── resources/
│   └── (optional) bg.jpg — splash screen background
├── src/
│   └── ticketsystem/
│       ├── TicketQueue.java       — Circular queue backend
│       ├── ResolvedStack.java     — Array stack backend
│       ├── TicketSystemGUI.java   — Main GUI application
│       └── TestDataStructures.java — Backend test (optional)
└── README.md
```

## Operations

| Button | Action |
|---|---|
| **Add Ticket** | Enqueues a ticket description into the pending queue |
| **Process Ticket** | Dequeues the front ticket → pushes onto resolved stack |
| **Reopen Ticket** | Pops the top resolved ticket → enqueues back to queue |
| **Clear Last Action** | Clears the action message and error labels |
| **☰ Menu** | Opens slide panel to switch between Queue/Stack views |
| **Info** | Hold to see developer name and CMS ID |

## Data Structures

- **TicketQueue**: Circular queue using a ring buffer array. `front`/`rear` indices wrap around using modular arithmetic (`(index + 1) % capacity`). Both reset to `-1` when empty.
- **ResolvedStack**: Classic array-based stack. `top` index starts at `-1` (empty) and increments/decrements on push/pop.