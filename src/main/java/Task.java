import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * Parses input string as a Task.
     * @param input input string to be parsed.
     * @return Task object corresponding to input string.
     */
    public static Task stringToTask(String input) throws TaskException {
        //Check if it is a valid task first
        String TODO_REGEX = "^\\[T\\] \\[(?: |X)\\] ..*$";
        Pattern toDoPattern = Pattern.compile(TODO_REGEX);
        String DEADLINE_REGEX = "^\\[D\\] \\[(?: |X)\\] ..* \\(by: ..*\\)$";
        Pattern deadlinePattern = Pattern.compile(DEADLINE_REGEX);
        String EVENT_REGEX = "^\\[E\\] \\[(?: |X)\\] ..* \\(at: ..*\\)$";
        Pattern eventPattern = Pattern.compile(EVENT_REGEX);
        if (toDoPattern.matcher(input).find()) {
            String[] inputSplitBySpaces = input.trim().split("\\s+");
            String taskDescription = Helper.join(inputSplitBySpaces, 1, inputSplitBySpaces.length - 1);
            return new ToDo(taskDescription);
        } else {
            boolean matchDeadline = deadlinePattern.matcher(input).find();
            boolean matchEvent = eventPattern.matcher(input).find();
            if (matchDeadline || matchEvent) {
                String[] inputSplitBySpaces = input.trim().split("\\s+");
                if (matchDeadline) {
                    //Index of /by
                    int byIndex = Helper.arrayIndexOf(inputSplitBySpaces, "/by");
                    String taskDescription = Helper.join(inputSplitBySpaces, 2, byIndex - 1);
                    String dueDate = Helper.join(inputSplitBySpaces, byIndex + 1,
                            inputSplitBySpaces.length - 1);
                    return new Deadline(taskDescription, dueDate);
                } else {
                    //Index of /at
                    int atIndex = Helper.arrayIndexOf(inputSplitBySpaces, "/at");
                    String taskDescription = Helper.join(inputSplitBySpaces, 2, atIndex - 1);
                    String eventDate = Helper.join(inputSplitBySpaces, atIndex + 1,
                            inputSplitBySpaces.length - 1);
                    return new Event(taskDescription, eventDate);
                }
            } else {
                throw new TaskException("Invalid task entry.");
            }
        }
     }


    /**
     * Marks the task as done and prints out to console that task is done.
     */
     void done() {
        this.isDone = true;
        Printer.printWithStyle(new String[] {"Nice! I've marked this task as done:", this.toString()});
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + this.task : "[ ] " + this.task;
    }
}
