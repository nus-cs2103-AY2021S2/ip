package duke.task;

public enum Priority {
    LOW("!", 0), MEDIUM("!!", 1), HIGH("!!!", 2);
    private String priorityString;
    private int value;

    Priority(String string, int value) {
        this.priorityString = string;
        this.value = value;
    }

    /**
     * Returns priority which corresponds to the given priorityInt
     * @param priorityInt
     * @return Priority corresponding to the priorityInt.
     * @throws IllegalArgumentException raised when priorityInt given is out of range.
     */
    public static Priority valueOf(int priorityInt) throws IllegalArgumentException {
        if (priorityInt == 0) {
            return Priority.LOW;
        } else if (priorityInt == 1) {
            return Priority.MEDIUM;
        } else if (priorityInt == 2) {
            return Priority.HIGH;
        } else {
            throw new IllegalArgumentException("Error: Second argument out of range. Accepts only 0-2.");
        }
    }

    /**
     * Returns the integer representation of Priority
     * @return integer representation
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns the string representation of priority.
     * @return String representation of priority !,!! or !!!.
     */
    @Override
    public String toString() {
        return this.priorityString;
    }
}
