package duke.constants;

/** Priority enum is used to assigned a priority to a task */
public enum Priority {
    
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    UNASSIGNED("Unassigned");
    
    /** value of the enum */
    private String value;
    
    Priority(String value) {
        this.value = value;
    }
    
    /**
     * getValue() returns teh value of the enum
     * @return String representing th value of the Priority Enum
     */
    public String getValue() {
        return this.value;
    }
}
