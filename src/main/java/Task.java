import org.apache.commons.lang3.ArrayUtils;

/**
 * Skeleton class for all tasks.
 */
public abstract class Task {
    String task;
    boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    enum TaskTypes  {
        TODO("T"), EVENT("E"), DEADLINE("D");
        private final String shortForm;

        private TaskTypes(String shortForm) {
            this.shortForm = shortForm;
        }

        public String getShortForm() {
            return this.shortForm;
        }
    };

    private static String TODO_REGEX = "^\\[T\\] \\[(?: |X)\\] ..*$";

    private static String DEADLINE_REGEX = "^\\[D\\] \\[(?: |X)\\] ..* \\(by: ..*\\)$";

    private static String EVENT_REGEX = "^\\[E\\] \\[(?: |X)\\] ..* \\(at: ..*\\)$";

    private static boolean isValidTaskType(String input) {
        if (input.length() != 3) {
            return false;
        }
        boolean isValidTaskType = false;
        try {
            //Check if brackets exist
            if (String.valueOf(input.charAt(0)).equals("[")
                    && String.valueOf(input.charAt(2)).equals("]")) {
                //Check if task type is either E, T, D
                for (TaskTypes type : TaskTypes.values()) {
                    isValidTaskType = isValidTaskType || type.getShortForm().equals(String.valueOf(input.charAt(1)));
                }
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return isValidTaskType;
    }

    private static boolean isProperDoneBrackets(String input) {
        try {
            return String.valueOf(input.charAt(0)).equals("[")
                    && String.valueOf(input.charAt(2)).equals("]")
                    && (String.valueOf(input.charAt(1)).equals(" ")
                    || String.valueOf(input.charAt(1)).equals("X"));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Parses input string as a Task.
     * @param input input string to be parsed.
     * @return Task object corresponding to input string.
     */
    public static Task stringToTask(String input) throws TaskException {
        String[] inputSplitBySpace = input.trim().split("\\s+");
        if (inputSplitBySpace.length < 3) {
            throw new TaskException("Too few fields provided in input.");
        } else {
            if (isValidTaskType(inputSplitBySpace[0])) {
                if (isProperDoneBrackets(inputSplitBySpace[1])) {
                    String taskType = String.valueOf(inputSplitBySpace[0].charAt(1));
                    String taskDescription;
                    String dueDate;
                    if (!taskType.equals("T")) {
                        //Check if task description exists.
                        int byIndex = Helper.arrayIndexOf(inputSplitBySpace, "/by");
                        int atIndex = Helper.arrayIndexOf(inputSplitBySpace, "/at");
                        if (!(byIndex == 2 || atIndex == 2)) {
                            int byOrAtIndex = byIndex == -1 ? atIndex - 1: byIndex - 1;
                            if (atIndex == -2) {
                                throw new TaskException("Missing /at or /by");
                            }
                            if (byOrAtIndex == inputSplitBySpace.length - 1) {
                                throw new TaskException("Missing date for Deadline/Event");
                            }
                            taskDescription = Helper.join(inputSplitBySpace, 2, byOrAtIndex);


                        } else {
                            throw new TaskException("Task description is empty");
                        }
                    } else {

                    }

                } else {
                    throw new TaskException("Invalid done brackets");
                }
            } else {
                throw new TaskException("Invalid task type.");
            }
        }
    }

    void done() {
        this.isDone = true;
        Printer.printWithStyle(new String[] {"Nice! I've marked this task as done:", this.toString()});
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + this.task : "[ ] " + this.task;
    }
}
