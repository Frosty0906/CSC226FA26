package staff_assignment;

public class Main {
    //TODO, create a main file to demonstrate your roster: add staff, assign treatments, undo one, release cases,
    //and show each treatment's performedBy staffId

    public static void main(String[] args) {
    StaffRoster roster = new StaffRoster();

    // add some staff
    roster.addStaff(new StaffMember("S1", "Alice", Seniority.TECH));
    roster.addStaff(new StaffMember("S2", "Bob", Seniority.NURSE));
    roster.addStaff(new StaffMember("S3", "Carol", Seniority.RESIDENT));
    roster.addStaff(new StaffMember("S4", "Dan", Seniority.ATTENDING));

    // assign treatments (performedBy = the returned staff's ID)
    StaffMember a = roster.assignTreatment("P1", "Bandage", "10:00", Seniority.TECH);
    System.out.println("P1 Bandage performedBy " + a.getStaffId());
    StaffMember b = roster.assignTreatment("P2", "IV Drip", "10:05", Seniority.NURSE);
    System.out.println("P2 IV Drip performedBy " + b.getStaffId());
    StaffMember c = roster.assignTreatment("P3", "Surgery", "10:10", Seniority.ATTENDING);
    System.out.println("P3 Surgery performedBy " + c.getStaffId());

    System.out.println("\nAfter assignments:");
    roster.displayRoster();

    // undo the most recent one (P3's surgery) and show load drop
    System.out.println("\nBefore undo, " + c.getStaffId() + " load = " + c.getCaseLoad());
    StaffMember undone = roster.undoLastAssignment();
    System.out.println("Undid assignment for " + undone.getStaffId() + ", load now = " + undone.getCaseLoad());

    // release a case normally (P1 finished)
    roster.release(a);
    System.out.println("Released " + a.getStaffId());

    System.out.println("\nFinal roster:");
    roster.displayRoster();
    }
}
