package utility;

public enum Branch {
    NAVY("Navy", "USN"),
    MARINES("Marines", "USMC"),
    ARMY("Army", "USA"),
    AIR_FORCE("Air Force", "USAF"),
    CIVILIAN("Civil Service", "USDF");

    private final String formatted;
    private final String abbreviation;

    Branch(String formatted, String abbreviation) {
        this.formatted = formatted;
        this.abbreviation = abbreviation;
    }

    public String getFormatted(boolean includeSignature) {
        return (includeSignature ? "US " : "") + formatted;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static Branch getBranchFromFormat(String format) {
        return switch (format) {
            case "US Navy" -> Branch.NAVY;
            case "US Marines" -> Branch.MARINES;
            case "US Army" -> Branch.ARMY;
            case "US Air Force" -> Branch.AIR_FORCE;
            case "USDF" -> Branch.CIVILIAN;
            default -> throw new IllegalArgumentException("Unexpected value: " + format);
        };
    }
}
