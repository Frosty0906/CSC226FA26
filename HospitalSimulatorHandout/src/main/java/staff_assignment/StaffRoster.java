package staff_assignment;

import java.util.TreeMap;
import java.util.TreeSet;

import medical_action_tracking.TreatmentHistory;
import medical_action_tracking.TreatmentRecord;

public class StaffRoster {
    // Week 3 stack-based history; every assignment is recorded here so it can be undone.
    private final TreatmentHistory history = new TreatmentHistory();

    // Outer map groups staff by seniority; each inner set is sorted by StaffMember's ordering.
    private final TreeMap<Seniority, TreeSet<StaffMember>> tiers = new TreeMap<>();

    // TODO: Add the staff member to the correct tier, and reject a duplicate staffId.
    public void addStaff(StaffMember staff) {
    }

    // TODO: Pick the least-loaded staff member at or above "required", raise their load by 1, and return them.
    public StaffMember assign(Seniority required) {
        return null;
    }

    // TODO: Lower the staff member's load by 1 (never below 0) and keep the tree ordered.
    public void release(StaffMember staff) {
    }

    // TODO: Return how many staff members are in the roster.
    public int size() {
        return 0;
    }

    // TODO: Print each tier, most senior first, listing its staff in order.
    public void displayRoster() {
    }

    // TODO: Remove the member, set the new load, then add them back (the only safe way to change a load).
    private void changeLoad(StaffMember member, int newLoad) {
    }

    // TODO: Call assign(), record the treatment in history with the chosen staffId, and return that staff member.
    public StaffMember assignTreatment(String patientID, String treatment, String timestamp, Seniority required) {
        return null;
    }

    // TODO: Pop the globally most recent treatment with your Week 3 undo, release whoever performed it, and return them (null if none).
    public StaffMember undoLastAssignment() {
        return null;
    }

    // TODO: Return the staff member with this staffId, or null if they are not in the roster.
    private StaffMember findById(String staffId) {
        return null;
    }
}
