public class Deadlines extends Task {
    private String dueBy;
    private static final String DUE_COMMAND = "/by";
    public Deadlines(String input) throws EmptyTaskDukeException {
        super(getTaskNameFromInput(input));
        dueBy = getDueDateFromInput(input);
    }

    public Deadlines(String name, String dueBy) throws EmptyTaskDukeException {
        super(name);
        this.dueBy = dueBy;
    }

    private static String getTaskNameFromInput(String input) {
        if (input.contains(DUE_COMMAND)) {
            return input.split(DUE_COMMAND)[0].trim();
        } else {
            return input;
        }
    }

    private static String getDueDateFromInput(String input) {
        if (input.contains(DUE_COMMAND)) {
            return removeStartingWhiteSpace(input.split(DUE_COMMAND)[1]);
        } else {
            return "not specified";
        }
    }

    private static String removeStartingWhiteSpace(String input) {
        if (input.substring(0, 1).equals(" ")) {
            return input.substring(1);
        }
        return input;
    }

    public String getDueBy() {
        return this.dueBy;
    }

    @Override
    public String setDone() {
        super.setTaskCompleted();
        return "Nice! I've marked this deadline as done:\n" +
                toString();
    }

    @Override
    public String toString() {
        String taskStringCheck = super.getIsTaskCompleted() ? "X" : " ";
        return "[D]" + "[" + taskStringCheck + "] " + super.getTaskName() +
                " (by: " + (dueBy.equals(null) ? "not specified" : dueBy) +  ")";
    }
}
