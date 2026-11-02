# CSC226 Hospital/ER Simulator - Witty Title Here

---

## Week 4: Staff Assignment (Trees)

Your fourth deliverable is the **Staff Assignment** module. This component uses Java's built-in balanced search tree (`TreeMap` / `TreeSet`) to decide which member of the medical staff handles each case, and it connects back to the treatments you tracked in Week 3.

### Scenario

Not every case can be handled by every staff member. A critical case needs an attending physician, while a routine task can go to a nurse or tech. Among the staff who are *qualified*, the hospital wants to use the person with the **lightest current workload**, so no one is overloaded while others sit idle.

This is a small version of how an operating system assigns processes to CPU cores: keep the workers organized by load, pick the best qualified one, and update the ordering whenever a load changes.

---

## Requirements

| # | Task | Details |
|---|------|---------|
| 1 | **Create Seniority** | Define the `Seniority` enum (`Seniority.java`) |
| 2 | **Create StaffMember** | Define a staff member with a workload and a defined ordering (`StaffMember.java`) |
| 3 | **Build StaffRoster** | Store staff in a tree structure and assign cases to them (`StaffRoster.java`) |
| 4 | **Implement Core Operations** | `addStaff()`, `assign()`, `release()`, `displayRoster()` |
| 5 | **Connect to Week 3** | Record *who* performed each treatment in `TreatmentRecord`, and undo assignments with your Week 3 stack |
| 6 | **Test** | Demonstrate your roster in `Main.java` with sample staff and cases |
| 7 | **JUnit Tests** | Create comprehensive tests for all new classes |

## Builds On

This week depends on your earlier work. Finish the Week 3 stack and history TODOs first. The supplied Week 3 files do **not** yet include the API needed to record which staff member performed a treatment, so you must make the small, backward-compatible additions listed below before implementing the Week 4 connection. Do not remove or change the behavior of the existing three-argument APIs.

| From | Class | Used for |
|------|-------|----------|
| Week 3 | `LinkedStack` | Stores the assignment history (LIFO) |
| Week 3 | `TreatmentHistory` | `undoLastAction()` is called by `undoLastAssignment()` |
| Week 3 | `TreatmentRecord` | Add the staff ID (`performedBy`) to each treatment record |

### Week 3 Prerequisite Changes

Make these additions in the existing Week 3 files before starting the Week 4 roster integration:

1. In `TreatmentRecord`, add a `performedBy` field and a `getPerformedBy()` getter. Add a four-argument constructor `(patientID, treatmentName, timestamp, performedBy)`. Keep the original three-argument constructor and its behavior; it may delegate to the new constructor with `performedBy` set to `null`.
2. In `TreatmentHistory`, add an overloaded four-argument `addTreatment(patientID, treatment, timestamp, performedBy)` method that creates a record with the supplied staff ID and pushes it onto the existing stack. Keep the existing three-argument method working as before.
3. Keep the existing `TreatmentRecord.toString()` format (`timestamp | patientID | treatmentName`) so earlier Week 3 behavior remains unchanged.
4. Run the existing Week 3 tests after these additions. The Week 4 tests also check that the new constructor, getter, and history overload work.

These are student changes to the earlier Week 3 classes, not changes supplied in the Week 4 starter files.

## Starter Code

Starter files are provided in `staff_assignment` (and the existing Week 3 classes in `medical_action_tracking`). Week 4 methods to implement are marked with `TODO` comments. First make the Week 3 prerequisite changes above; then implement the Week 4 TODOs. The provided tests are expected to fail until the corresponding Week 3 and Week 4 work is complete, so use them to check your progress.

| File | What is provided | What you write |
|------|------------------|----------------|
| `Seniority.java` | Complete | Nothing |
| `StaffMember.java` | Fields, constructor, getters, `setCaseLoad` | `compareTo()` |
| `StaffRoster.java` | The `TreeMap` field, the history field, and method stubs | All methods |
| `TreatmentRecord.java` (Week 3 file) | Original three fields and constructor | Add `performedBy`, the 4-argument constructor, and `getPerformedBy()`; preserve the 3-argument constructor and `toString()` |
| `TreatmentHistory.java` (Week 3 file) | Existing 3-argument `addTreatment()` and stack-based history | Add the 4-argument overload that records `performedBy`; preserve the 3-argument overload and implement Week 3 `undoLastAction()` |
| `Main.java` | Empty class | The demo |

---

## Technical Specifications

### Seniority
- An enum with these levels, from least to most senior: `TECH`, `NURSE`, `RESIDENT`, `ATTENDING`
- Enums are `Comparable`, so you can compare levels directly with `compareTo()`

### StaffMember
- Implements `Comparable<StaffMember>` ordering staff by:
  1. **`caseLoad`**, lowest first
  2. **`staffId`**, as the tie-breaker
- `caseLoad` has no public setter. Changing it incorrectly corrupts the tree (see "Updating a Load"), so change it only through `StaffRoster`

### StaffRoster
- Uses a **`TreeMap<Seniority, TreeSet<StaffMember>>`** internally (NOT a hand-built tree, array, or list)
  - The outer map groups staff by seniority level
  - Each inner set is ordered by the `StaffMember` ordering above, so the **first** element is always the least-loaded person at that level
- Required methods:
  - `addStaff(StaffMember staff)` - Add a staff member to the roster. Throw `IllegalArgumentException` if the `staffId` is already in the roster
  - `assign(Seniority required)` - Find the **least-loaded** staff member whose seniority is **at or above** `required`, increase their `caseLoad` by 1, and return them. Throw `NoSuchElementException` if no one qualifies
  - `release(StaffMember staff)` - A case is finished; decrease that person's `caseLoad` by 1 and restore the ordering. Do not let `caseLoad` go below 0
  - `size()` - Number of staff in the roster
  - `displayRoster()` - Print every staff member grouped by seniority, in order (most senior level first)
  - `assignTreatment(patientID, treatment, timestamp, required)` - Call `assign()`, then record the treatment in the roster's `TreatmentHistory` (Week 3 stack) with the chosen staff member's ID, and return that staff member
  - `undoLastAssignment()` - Use Week 3 `undoLastAction()` to pop the **globally most recent treatment** from the hospital-wide stack, find the staff member named in its `performedBy`, release them, and return them. Return `null` if the history is empty.
- **Tie policy for `assign()`:** when several qualified people have the same load, choose the one at the **lowest seniority level**, so senior staff are saved for harder cases

> **Tree views:** `TreeMap` provides methods such as `tailMap(key, inclusive)`, `ceilingKey(key)`, and `firstEntry()`. Read the Javadoc, you will need some of them.

### Updating a Load (Read This Carefully)
A `TreeSet` decides where an element lives **at the moment it is inserted**. If you change a field that is part of the ordering (like `caseLoad`) while the element is still in the set, the set does not move it. Searches and removals can then silently fail.

The safe pattern is always:

1. **Remove** the staff member from their set
2. **Change** the `caseLoad`
3. **Add** them back

Your `assign()` and `release()` must follow this pattern (the `changeLoad` helper is a good place for it).

### TreatmentRecord (Update from Week 3)
- Follow the Week 3 prerequisite instructions above. The four-argument `addTreatment()` overload must pass the selected staff member's `staffId` to the new `TreatmentRecord` constructor.
- In `Main.java`, use `StaffRoster.assignTreatment()` for your demo cases, then undo the globally most recent assignment with `undoLastAssignment()` and show the staff member's load drop.

---

## Deliverables

Submit the following files:

1. `StaffMember.java` - `compareTo()` completed
2. `StaffRoster.java` - Tree-based roster, case assignment, and undo
3. `TreatmentRecord.java` - Week 3 file updated with `performedBy`, while preserving the original constructor and string representation
4. `TreatmentHistory.java` - Week 3 file updated with the four-argument `addTreatment()` overload, while preserving the three-argument method
5. `Main.java` - Demo program showing assignments, releases, an undo, and the Week 3 connection
6. `src/test/java/staff_assignment/StaffMemberTest.java` - Provided tests for ordering (add your own)
7. `src/test/java/staff_assignment/StaffRosterTest.java` - Provided tests for the roster (add your own)
8. `src/test/java/medical_action_tracking/TreatmentRecordTest.java` and `TreatmentHistoryTest.java` - Tests for the new Week 3 additions and backward compatibility

### Provided JUnit Tests
The provided tests check the key behaviors. Add tests of your own for anything they miss, such as an empty roster or repeated assignments spreading work evenly.
- Ordering: lower `caseLoad` sorts first, and equal loads are broken by `staffId`
- `assign()` returns the least-loaded qualified staff member
- `assign()` never returns someone below the required seniority
- `assign()` with no qualified staff throws
- `release()` makes a staff member eligible again, in the correct position
- `release()` never drops `caseLoad` below 0
- `undoLastAssignment()` releases the staff member who performed the globally most recent treatment (LIFO), and returns `null` when there is no history
- Adding two staff with the same `staffId` is rejected
- `TreatmentRecord` stores `performedBy`

---

## Stretch Goals (Optional)

### Stretch 1: Simulation
- Generate 200 cases using a **seeded** `Random` (so results are repeatable), each with a required seniority and a duration
- Run them through your roster and report the average wait, how busy each staff member was, and how many cases went unassigned
- Compare two policies (for example "most senior first" vs. "least loaded first") and write a short paragraph on which is better and why

### Stretch 2: Tree Traversals
- Your instructor will provide a small `BSTNode` skeleton
- Implement `size()`, `height()`, and the pre-order, in-order, and post-order traversals
- `size()` must be written **both recursively and iteratively**, and the iterative version must use your Week 3 `LinkedStack`

### Stretch 3: Why a Tree?
- Insert *N* staff into (a) a `TreeSet`, (b) an `ArrayList` kept sorted, and (c) a `LinkedList` kept sorted
- Time each at several values of *N*, reusing your Week 2 timing approach
- Write a short analysis connecting your results to the "fast search *or* fast insert" tradeoff from lecture

---

### Success Criteria
- `assign()` always picks the least-loaded qualified staff member
- Staff are never lost or duplicated when loads change
- The remove-change-add pattern is used for every load update
- `TreatmentRecord` records who performed each treatment, and Week 3 tests still pass
- Undoing an assignment uses your Week 3 stack and restores the staff member's load
- All JUnit tests pass
- Code is well-commented and follows Java conventions
