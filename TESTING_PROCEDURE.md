# SupportDesk Simulator — Testing Procedure

> Present this step-by-step to your instructor to demonstrate all features.

---

## Step 1: Compile and Launch

```bash
cd src
javac ticketsystem/*.java
java ticketsystem.TicketSystemGUI
```

**Expected Result:**
- A **splash screen** appears for 3 seconds with "SupportDesk Simulator" text
- Then the **main window** opens (1200×700, centered, dark theme)
- Title: "SupportDesk Simulator | Stack & Queue"

---

## Step 2: Verify Initial State (Queue View)

**Look at these labels on screen:**
| Label | Expected Value |
|---|---|
| PENDING QUEUE | Header visible |
| All 6 queue boxes | Show `[Empty]` in blue-bordered boxes |
| `isFull` | `false` |
| `isEmpty` | `true` |
| `Front` | `-1` |
| `Rear` | `-1` |
| RESOLVED STACK | Hidden (not visible) |

---

## Step 3: Add Tickets

**Action:** Type a ticket description and click **"Add Ticket"**

1. Type: `Login page not loading` → Click **Add Ticket**
   - ✅ Box 1 shows `Login page no...` (truncated)
   - ✅ Front=0, Rear=0, isEmpty=false
2. Type: `Password reset failed` → Click **Add Ticket**
   - ✅ Box 2 shows content
   - ✅ Front=0, Rear=1
3. Add 4 more tickets: `Email not sending`, `Account locked`, `Payment error`, `App crash`
   - ✅ After 6 tickets: **isFull=true**, all 6 boxes filled

**Edge Case — Empty Input:**
4. Click **Add Ticket** with empty text field
   - ✅ Red error: "Please describe the issue before adding a ticket."

**Edge Case — Queue Full:**
5. Click **Add Ticket** again
   - ✅ Red error: "Queue is full! Cannot add more tickets."

---

## Step 4: Process Tickets (Queue → Stack)

**Action:** Click **"Process Ticket"**

1. Click once → Box 1 clears, "Login page not loading" moves to stack
   - ✅ Queue: Box 1 now `[Empty]`, Front=1
   - ✅ Last Action: "Processed "Login page not loading" → moved to resolved stack"
2. Click 3 more times → 3 more tickets move to stack
   - ✅ Queue: 2 tickets remain, Front=4

**Edge Case — Queue Empty:**
3. Process remaining 2 tickets, then click **Process Ticket** again
   - ✅ Red error: "Queue is empty! No tickets to process."

---

## Step 5: View Resolved Stack

**Action:** Click **"☰ Menu"** → Click **"Show Resolved Stack"**

- ✅ RESOLVED STACK header appears
- ✅ Stack boxes show the 4 processed tickets (top item = most recently processed)
- ✅ Queue boxes are hidden
- ✅ Info labels show: isFull=false, isEmpty=false, Top=3 (if 4 items)

**Action:** Click **"☰ Menu"** → Click **"Show Pending Queue"**
- ✅ Returns to queue view

---

## Step 6: Reopen Tickets (Stack → Queue)

**Action:** Switch back to Queue View, then click **"Reopen Ticket"**

1. Click **Reopen Ticket** once
   - ✅ Stack top item moves back to queue
   - ✅ Queue shows the reopened ticket in the next available slot
   - ✅ Last Action: "Reopened "..." → moved back to queue"

**Edge Case — Stack Empty:**
2. Switch to Stack View, reopen all tickets, then click **Reopen Ticket** again
   - ✅ Red error: "Resolved stack is empty! No tickets to reopen."

**Edge Case — Queue Full:**
3. Fill queue to 6 tickets, then try reopening
   - ✅ Red error: "Queue is full! Process a ticket before reopening."

---

## Step 7: Clear Last Action

**Action:** Click **"Clear Last Action"**
- ✅ Last Action label becomes empty
- ✅ Error label also clears (if any was showing)

---

## Step 8: Social Media Links

**Action:** Click each social icon at the bottom:
- **YouTube** → Opens youtube.com in browser
- **Facebook** → Opens facebook.com
- **Twitter** → Opens twitter.com
- **HU** → Opens hamdard.edu.pk

---

## Step 9: Info Button

**Action:** Press-and-hold the **"Info"** button (top-right)
- ✅ Yellow text appears: "Developed by Student | CMS: 123456"
**Action:** Release the button
- ✅ Text disappears

---

## Step 10: Hover Effects

**Action:** Hover your mouse over:
- Any action button → Background turns lighter blue
- Menu button → Background changes
- Social icons → Background changes
- Info button → Background changes
- Close (✕) button → Background changes

---

## Summary Checklist for Your Sir

| # | Test Case | Status |
|---|---|---|
| 1 | Splash screen shows for 3 seconds | ✅ |
| 2 | Main window opens correctly | ✅ |
| 3 | Add tickets until full | ✅ |
| 4 | Error on empty input | ✅ |
| 5 | Error on full queue add | ✅ |
| 6 | Process tickets (queue → stack) | ✅ |
| 7 | Error on empty queue process | ✅ |
| 8 | View queue / stack via menu | ✅ |
| 9 | Reopen tickets (stack → queue) | ✅ |
| 10 | Error on empty stack reopen | ✅ |
| 11 | Error on full queue reopen | ✅ |
| 12 | Clear last action | ✅ |
| 13 | Social media links open browser | ✅ |
| 14 | Info button press/release | ✅ |
| 15 | Hover effects on all buttons | ✅ |