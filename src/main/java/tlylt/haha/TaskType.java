package tlylt.haha;

/**
 * Representation of various task types.
 */
public enum TaskType {
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");
    private final String rep;

    /**
     * Constructor of a TaskType.
     *
     * @param rep String representation.
     */
    TaskType(String rep) {
        this.rep = rep;
    }

    /**
     * Returns string representation of task type.
     *
     * @return String task type.
     */
    String getRep() {
        return rep;
    }
}
