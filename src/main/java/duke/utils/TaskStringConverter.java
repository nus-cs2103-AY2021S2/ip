package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.dukeexceptions.InvalidTaskTypeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class TaskStringConverter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[d/M/yyyy HHmm][d MMM yy HHmm]"
            + "[dd-MM-yy HHmm]");

    /**
     * Returns a List of Strings, each String representing 1 Task in the specified list.
     *
     * @param list List of Tasks to convert to Strings.
     * @return List of Strings.
     */
    public static List<String> allTaskToAllString(List<Task> list) {
        List<String> result = new ArrayList<>();
        for (Task task : list) {
            result.add(taskToString(task));
        }
        return result;
    }

    /**
     * Returns a List of Tasks, each Task converted from 1 String in the specified list.
     *
     * @param list List of Strings to convert to Tasks.
     * @return List of Tasks.
     * @throws InvalidTaskTypeException if local storage file contains invalid Task identified, i.e. not [T], [E] or
     *     [D].
     */
    public static List<Task> allStringToAllTask(List<String> list) throws InvalidTaskTypeException {
        List<Task> result = new ArrayList<>();
        for (String s : list) {
            result.add(stringInputToTask(s));
        }
        return result;
    }

    private static String taskToString(Task task) {
        String done = task.isDone() ? "1" : "0";

        if (task instanceof ToDo) {
            return "T | " + done + " | " + task.getDescription();
        } else if (task instanceof Event) {
            return "E | " + done + " | " + task.getDescription() + " | " + ((Event) task).getDateToStore();
        } else {
            return "D | " + done + " | " + task.getDescription() + " | " + ((Deadline) task).getDateToStore();
        }
    }

    private static Task stringInputToTask(String input) throws InvalidTaskTypeException {
        String[] separated = input.split(" \\| ");
        assert separated.length >= 3;
        char taskType = separated[0].charAt(0);

        if (taskType == 'T') {
            assert separated.length == 3;
            ToDo todo = new ToDo(separated[2]);
            if (separated[1].equals("1")) {
                todo.markAsDone();
            }
            return todo;
        } else if (taskType == 'D') {
            assert separated.length == 4;
            Deadline deadline = new Deadline(separated[2], LocalDateTime.parse(separated[3], formatter));
            if (separated[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        } else if (taskType == 'E') {
            assert separated.length == 4;
            Event event = new Event(separated[2], LocalDateTime.parse(separated[3], formatter));
            if (separated[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        } else {
            throw new InvalidTaskTypeException();
        }
    }
}
