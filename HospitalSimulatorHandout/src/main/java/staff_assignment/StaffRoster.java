package staff_assignment;

import java.util.NoSuchElementException;
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
        // gets the tier for the staff member's seniority, creating it if it doesn't exist
        TreeSet<StaffMember> tier = tiers.get(staff.getSeniority());
        if (tier == null) {
            tier = new TreeSet<>();
            tiers.put(staff.getSeniority(), tier);
        }
        // check if a staff member with the same staffId already exists in the roster
        if (findById(staff.getStaffId()) != null) {
            throw new IllegalArgumentException("Duplicate staffId: " + staff.getStaffId());
        }
        tier.add(staff);
    }

    // TODO: Pick the least-loaded staff member at or above "required", raise their load by 1, and return them.
    public StaffMember assign(Seniority required) {
        StaffMember best = null;
        // seniority.values() = TECH, NURSE, RESIDENT, ATTENDING (lowest first)
        for (Seniority seniority : Seniority.values()) {
            // skip levels below the required one
            if (seniority.compareTo(required) >= 0) {
                TreeSet<StaffMember> tier = tiers.get(seniority);
                // tier is null if nobody at this level was ever added
                if (tier != null && !tier.isEmpty()) {
                    StaffMember candidate = tier.first();   // least-loaded in this tier
                    // strict < means ties keep the lower-seniority person found earlier
                    if (best == null || candidate.getCaseLoad() < best.getCaseLoad()) {
                        best = candidate;
                    }
                }
            }
        }
        if (best == null) {
            throw new NoSuchElementException("No qualified staff for " + required);
        }
        changeLoad(best, best.getCaseLoad() + 1);
        return best;
    }




    // TODO: Lower the staff member's load by 1 (never below 0) and keep the tree ordered.
    public void release(StaffMember staff) {
        changeLoad(staff, Math.max(staff.getCaseLoad() - 1, 0));
    }

    // TODO: Return how many staff members are in the roster.
    public int size() {
        int count = 0;
        // iterate over all tiers and sum up the number of staff members in each tier
        for (TreeSet<StaffMember> tier : tiers.values()) {
            count += tier.size();
        }
        return count;
    }

    // TODO: Print each tier, most senior first, listing its staff in order.
    public void displayRoster() {
        // iterate over the tiers in descending order of seniority
        for (Seniority seniority : tiers.descendingKeySet()) {
            TreeSet<StaffMember> tier = tiers.get(seniority);
            System.out.println("Tier: " + seniority);
            // iterate over the staff members in this tier, which are already sorted by caseLoad and staffId
            for (StaffMember staff : tier) {
                System.out.println("  " + staff);
            }
        }
    }

    // TODO: Remove the member, set the new load, then add them back (the only safe way to change a load).
    private void changeLoad(StaffMember member, int newLoad) {
        // get the tier for the staff member's seniority
        Seniority seniority = member.getSeniority();
        // get the TreeSet for this seniority level
        TreeSet<StaffMember> tier = tiers.get(seniority);
        tier.remove(member);
        member.setCaseLoad(newLoad);
        tier.add(member);
    }

    // TODO: Call assign(), record the treatment in history with the chosen staffId, and return that staff member.
    public StaffMember assignTreatment(String patientID, String treatment, String timestamp, Seniority required) {
        StaffMember staff = assign(required);   // assign() already bumps the load
        if (staff != null) {
            history.addTreatment(patientID, treatment, timestamp, staff.getStaffId());
        }
    return staff;
    }

    // TODO: Pop the globally most recent treatment with your Week 3 undo, release whoever performed it, and return them (null if none).
    public StaffMember undoLastAssignment() {
         TreatmentRecord last = history.undoLastAction(); // pop newest record
        if (last == null) {
            return null;  // history was empty
        }
        StaffMember staff = findById(last.getPerformedBy()); // find the staff member who performed the last treatment
        if (staff != null) { 
            release(staff); // release the staff member from their last assignment
        }
        return staff;
    }

    // TODO: Return the staff member with this staffId, or null if they are not in the roster.
    private StaffMember findById(String staffId) {
        // iterate over all tiers and their staff members to find the one with the matching staffId
        for (TreeSet<StaffMember> tier : tiers.values()) {
            for (StaffMember staff : tier) {
                if (staff.getStaffId().equals(staffId)) {
                    return staff;
                }
            }
        }
        return null;
    }
}
