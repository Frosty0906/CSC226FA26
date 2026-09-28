# CSC226 Hospital/ER Simulator - Week 3: Patient Flow and Care History

## Scenario

Weeks 1 and 2 created the patient intake registry and triage search tools. Extend that system into a small ER workflow: patients wait in triage lanes, the next patient is selected for care, and medical actions can be reviewed or undone. Use custom linked structures; do not use Java's built-in `Stack`, `Queue`, `LinkedList`, or `PriorityQueue`.

For Java, Maven, package, IDE, and troubleshooting guidance, see [DevelopmentEnvironment.md](../../../../DevelopmentEnvironment.md).

## Required Work: 80%

| Checkpoint | Value | Requirements |
|---|---:|---|
| Linked FIFO queue | 20% | Implement a generic, unbounded linked queue using nodes and front/rear references. Support enqueue, dequeue, front observation, emptiness, size, and recursive display. Handle empty and one-node transitions safely. |
| Recursive linked traversal | 10% | Implement the queue's recursive, non-mutating `toString()`. Return `[]` when empty; otherwise use brackets, comma-space separators, and each item's `toString()` value. Use a null-node base case and preserve front-to-rear order. |
| ER waiting room | 20% | Create four unbounded FIFO lanes for triage levels 1–4. Level 1 is most urgent; serve the first patient from the most urgent non-empty lane. Patients with the same level remain in the order they were added. Reject invalid levels without changing any lane. |
| Linked treatment stack | 15% | Implement a generic linked stack with push, pop, peek, isEmpty, size, and a top-to-bottom string representation. Empty pop/peek return `null`; preserve LIFO order. |
| Treatment history and undo | 10% | Store `TreatmentRecord` objects in one hospital-wide stack. Display the complete log newest-first without changing it. Undo removes and returns only the globally newest record, or returns `null` when empty. |
| Integration, tests, and analysis | 5% | Reuse the Week 1 patient model/registry and Week 2 search. Demonstrate arrivals, patient selection, treatment recording, and undo. Pass the instructor examples, add six unique JUnit tests, and include the specified Big-O table. |

## Starter APIs

### `LinkedQueue<T>`

- `enqueue(T item)`, `dequeue()`, `peekFront()`, `isEmpty()`, `size()`, and `toString()`.
- The queue does not have a capacity limit. `enqueue(null)` throws `IllegalArgumentException`.
- Empty `dequeue()` and `peekFront()` return `null`.
- `toString()` recursively visits linked nodes from front to rear and does not alter the queue. Its format is `[]` or `[item, item]`, using each item object's `toString()` result.
- Enqueue and dequeue should each be O(1); recursive display is O(n).

### `EmergencyWaitingRoom`

- `boolean addPatient(Patient patient)` returns `true` for a patient with triage level 1–4 and `false` for a null patient or invalid level. A rejected patient is not added and the method does not modify the patient.
- `Patient nextPatient()` removes and returns the next patient, or returns `null` when all lanes are empty.
- `boolean isEmpty()` and `int size()` report the combined state of all four lanes.
- Each lane is unbounded. Implement priority by checking the four ordinary FIFO queues in level order. Do not sort patients or use a heap.

### `LinkedStack<T>`, `TreatmentRecord`, and `TreatmentHistory`

- `LinkedStack<T>` uses linked nodes. `push(null)` throws `IllegalArgumentException`; empty `pop()` and `peek()` return `null`. Its `toString()` lists items from top to bottom as `[]` or `[top, next]`, using each item's `toString()` value.
- `TreatmentRecord` stores a patient ID, treatment description, and timestamp (`YYYY-MM-DD HH:MM`). Implement its constructor and getters; no setters or timestamp parsing are required.
- `TreatmentRecord.toString()` returns exactly `timestamp + " | " + patientID + " | " + treatmentName`.
- `TreatmentHistory` provides `addTreatment(String patientID, String treatment, String timestamp)`, `String displayHistory()`, and `TreatmentRecord undoLastAction()`.
- The history is one hospital-wide log, not a separate stack per patient. Each record identifies its patient. `displayHistory()` returns `[]` when empty; otherwise it returns records in the same exact bracketed, comma-space format as `LinkedStack.toString()`, newest first. It does not change the stack.
- `undoLastAction()` removes and returns the most recently added record, regardless of patient ID. It returns `null` when the log is empty.

## Demonstration

In `Main.java`, add several patients to a Week 1 `PatientRegistry`, use Week 2 `EfficiencyTester.linearSearch` to find at least one patient by ID, then add patients to the waiting room. Include at least two patients at the same triage level. Print each selected patient's ID and triage level so the output demonstrates that level 1 is served first and equal-level patients remain FIFO. Record actions for at least two patient IDs, print the full treatment log, undo the newest action, and print the log again to show that only that top record was removed.

## JUnit Tests

The instructor-provided examples are in `src/test/java/medical_action_tracking/`. They compile with the starter, but assertions are expected to fail until the TODOs are completed. Add at least six additional unique tests in `StudentCareTests.java`. Cover empty structures, transitions to and from one element, FIFO order, triage priority and same-lane order, recursive traversal without mutation, LIFO order, full-log display without mutation, and undo of the globally newest record. The examples already cover some of these behaviors; student tests must add distinct cases.

## Suggested Work Order

1. Implement and test `LinkedStack` and `TreatmentRecord`.
2. Implement `LinkedQueue`, including its recursive `toString()`, and pass `LinkedQueueTest`.
3. Build the four-lane `EmergencyWaitingRoom` using the tested queue.
4. Complete `TreatmentHistory` using the tested stack.
5. Finish the driver, student tests, and Big-O table.

## Extensions: 20%

Complete any combination of extensions for up to 20%:

| Extension | Value | Requirement |
|---|---:|---|
| Bounded waiting lanes | 5% | Give each triage lane a documented capacity. `addPatient` returns `false` when the selected lane is full and leaves all lanes unchanged. |
| Recursive queue count | 5% | Add a recursive method that counts the nodes in a queue and compare its result with `size()`; test empty and multi-node queues. |
| Queue implementation comparison | 5% | Implement a second queue representation, such as a circular array, and compare its operations and tradeoffs with the linked queue. |
| Simulation metrics | 5% | In `Main`, assign each patient an integer arrival minute and service duration in minutes. Advance a simulated clock; waiting time is service-start minute minus arrival minute. Exclude service duration and report the average waiting time for all served patients. |

## Test and Run Commands

Run from the project directory containing `pom.xml`:

```text
mvn clean compile
mvn test
```

Run the driver after compiling:

```text
java -cp target/classes medical_action_tracking.Main
```

## Big-O Analysis

Include a four-row table with the operation and its time complexity: queue enqueue/dequeue, `EmergencyWaitingRoom.nextPatient()` across four fixed lanes, stack push/pop/peek, and recursive queue `toString()`. Give one sentence explaining each bound. The waiting-room selection checks exactly four lanes, so its cost does not grow with the number of waiting patients.

## Submission Checklist

- Required TODOs are complete and the provided example tests pass.
- Six additional student-written tests cover distinct behaviors and edge cases.
- The driver demonstrates search, triage priority, FIFO order, treatment history, and undo.
- The Big-O table covers the four specified operations and gives a reason for each bound.
- Optional work is clearly identified. 