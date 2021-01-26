import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskParser {
    private static final int SPLIT_LIMIT = 2;

    /**
     * Parses a String representation of a Task into a Task object, and returns the resultant Task object.
     *
     * @param taskString String representation of the Task to be parsed.
     * @return Task object parsed from the input String.
     * @throws DukeException If an Exception occurs as a result of the String being malformed.
     */
    public static Task parseTask(String taskString) throws DukeException {
        Task newTask;
        String desc;
        boolean isDone = false;
        if (taskString.startsWith("todo")) {
            String[] taskParts = taskString.split("todo", SPLIT_LIMIT);
            if (taskParts[1].length() == 0) {
                throw new DukeException("Oops! Usage: todo [desc]");
            } else {
                desc = taskParts[1].trim();
            }
            if (desc.startsWith("[isDone]")) {
                desc = desc.split("\\[isDone\\]", SPLIT_LIMIT)[1].trim();
                isDone = true;
            }
            newTask = new Todo(desc);
        } else if (taskString.startsWith("event")) {
            desc = taskString.split("event", SPLIT_LIMIT)[1].trim();
            String[] taskParts = desc.split(" /on ");
            if (taskParts.length == 1) {
                throw new DukeException("Oops! Usage: event [desc] /on [date]");
            } else {
                if (taskParts[0].startsWith("[isDone]")) {
                    taskParts[0] = taskParts[0].split("\\[isDone\\]", SPLIT_LIMIT)[1].trim();
                    isDone = true;
                }
                try {
                    LocalDate datetime = LocalDate.parse(taskParts[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    newTask = new Event(taskParts[0], datetime);
                } catch (Exception e) {
                    throw new DukeException("Looks like your date's formatted incorrectly! Try this: dd/mm/yyyy");
                }
            }
        } else if (taskString.startsWith("deadline")) {
            desc = taskString.split("deadline", SPLIT_LIMIT)[1].trim();
            String[] taskParts = desc.split(" /by ");
            if (taskParts.length == 1) {
                throw new DukeException("Oops! Usage: deadline [desc] /by [date]");
            } else {
                if (taskParts[0].startsWith("[isDone]", SPLIT_LIMIT)) {
                    taskParts[0] = taskParts[0].split("\\[isDone\\]")[1].trim();
                    isDone = true;
                }
                try {
                    LocalDate datetime = LocalDate.parse(taskParts[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    newTask = new Deadline(taskParts[0], datetime);
                } catch (Exception e) {
                    throw new DukeException("Looks like your date's formatted incorrectly! Try this: dd/mm/yyyy");
                }
            }
        } else {
            throw new DukeException("Unknown command!");
        }
        if (isDone) {
            newTask.finish();
        }
        return newTask;
    }


}