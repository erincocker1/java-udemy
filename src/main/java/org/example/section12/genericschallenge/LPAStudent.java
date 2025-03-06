package org.example.section12.genericschallenge;

public class LPAStudent extends Student {

    private double percentComplete;

    public LPAStudent() {
        percentComplete = random.nextDouble(0, 100.001);
    }

    @Override
    public String toString() {
        return "%s %8.1f%%".formatted(super.toString(), percentComplete);
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        String fName = fieldName.toUpperCase();
        return switch (fName) {
            case "NAME" -> this.getName().equalsIgnoreCase(value);
            case "COURSE" -> this.getCourse().equalsIgnoreCase(value);
            case "YEARSTARTED" -> this.getYearStarted() == (Integer.parseInt(value));
            case "ID" -> this.getId() == (Integer.parseInt(value));
            case "PERCENTCOMPLETE" -> this.percentComplete <= (Integer.parseInt(value));
            default -> false;
        };
    }
}
