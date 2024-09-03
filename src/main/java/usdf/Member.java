package usdf;

import usdf.training.Feats;

public class Member {
    public String name;
    public int serviceId;
    public Branch branch;
    public String payGrade;
    public String rank;
    public boolean command;
    public boolean selectee;
    public String motto;
    public String position;
    public Feats featStack;

    // Constructor
    public Member(
            String name,
            int serviceId,
            Branch branch,
            String paygrade,
            String rank,
            boolean command,
            boolean selectee,
            String motto,
            String position,
            String featStack) {
        this.name = name;
        this.serviceId = serviceId;
        this.branch = branch;
        this.payGrade = paygrade;
        this.rank = rank;
        this.command = command;
        this.selectee = selectee;
        this.motto = motto;
        this.position = position;
        this.featStack = new Feats(featStack);
    }

    public String getDefaultAddress() {
        if (branch == Branch.CIVILIAN) {
            return "Mr./Ms. %s %s".formatted(position, name);
        } else if (command) {
            return "%s %s, %s.".formatted(rank, name, position);
        } else {
            return "%s %s".formatted(rank, name);
        }
    }

    public String toStringSingleLine() {
        return String.format(
                "%-5s | %-4s | %-20s | %-45s | %-4s | %-15s | %-15s | %-12s",
                serviceId,
                payGrade,
                name,
                motto,
                branch.getAbbreviation(),
                rank,
                (position.equals("None")) ? "" : position,
                featStack
        );
    }

    @Override
    public String toString() {
        return """
                ========================
                ## %s (%s)
                %s - %s
                %s
                Trainings: {%s}
                ------------------------
                %s
                ========================
                """.formatted(
                name,
                serviceId,
                payGrade,
                rank,
                branch.getFormatted(true),
                featStack,
                motto
        );
    }
}
