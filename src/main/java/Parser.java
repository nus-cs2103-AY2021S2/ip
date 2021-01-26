import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Task parseTask(String taskString) throws DukeException {
        Task newTask;
        String desc;
        boolean isDone = false;
        if (taskString.startsWith("todo")) {
            String[] taskParts = taskString.split("todo");
            if (taskParts.length == 1) {
                throw new DukeException("Oops! Usage: todo [desc]");
            } else {
                desc = taskParts[1].trim();
            }
            if (desc.startsWith("[isDone]")) {
                desc = desc.split("\\[isDone\\]")[1].trim();
                isDone = true;
            }
            newTask = new Todo(desc);
        } else if (taskString.startsWith("event")) {
            desc = taskString.split("event")[1].trim();
            String[] taskParts = desc.split(" /on ");
            if (taskParts.length == 1) {
                throw new DukeException("Oops! Usage: event [desc] /on [date]");
            } else {
                if (taskParts[0].startsWith("[isDone]")) {
                    taskParts[0] = taskParts[0].split("\\[isDone\\]")[1].trim();
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
            desc = taskString.split("deadline")[1].trim();
            String[] taskParts = desc.split(" /by ");
            if (taskParts.length == 1) {
                throw new DukeException("Oops! Usage: deadline [desc] /by [date]");
            } else {
                if (taskParts[0].startsWith("[isDone]")) {
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