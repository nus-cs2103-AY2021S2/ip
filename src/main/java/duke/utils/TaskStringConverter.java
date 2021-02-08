package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import duke.dukeexceptions.InvalidTaskTypeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class TaskStringConverter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[d/M/yyyy HHmm][d MMM yy HHmm]"
            + "[dd-MM-yy HHmm]");

    /**
     * Returns concatenated String description of all Tasks to save to local storage.
     *
     * @param list List of Tasks to convert and concatenate.
     * @return String description of all Tasks.
     */
    public static String stringTasksForFile(List<Task> list) {
        List<String> stringTasks = listTaskToListStringFile(list);

        StringBuilder stringBuilder = new StringBuilder();
        for (String stringTask : stringTasks) {
            stringBuilder.append(stringTask)
                    .append("\n");
        }

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    private static List<String> listTaskToListStringFile(List<Task> list) {
        List<String> result = list.stream()
                .map(task -> taskToStringFile(task))
                .collect(Collectors.toList());

        return result;
    }

    private static String taskToStringFile(Task task) {
        assert task instanceof ToDo || task instanceof Event || task instanceof Deadline;

        String done = task.isDone() ? "1" : "0";

        if (task instanceof ToDo) {
            return "T | " + done + " | " + task.getDescription();
        } else if (task instanceof Event) {
            return "E | " + done + " | " + task.getDescription() + " | " + ((Event) task).getDateToStore();
        } else if (task instanceof Deadline) {
            return "D | " + done + " | " + task.getDescription() + " | " + ((Deadline) task).getDateToStore();
        } else {
            throw new AssertionError(task);
        }
    }

    /**
     * Returns concatenated String description of all Tasks for display in GUI.
     *
     * @param list List of Tasks to convert and concatenate.
     * @return String description of all Tasks.
     */
    public static String stringTasksForProgram(List<Task> list) {
        List<String> stringTasks = listTaskToListStringProgram(list);

        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        for (String stringTask : stringTasks) {
            stringBuilder.append("\n")
                    .append(index)
                    .append(". ")
                    .append(stringTask);
            index++;
        }

        return stringBuilder.toString();
    }

    private static List<String> listTaskToListStringProgram(List<Task> list) {
        List<String> result = list.stream()
                .map(task -> taskToStringProgram(task))
                .collect(Collectors.toList());

        return result;
    }

    private static String taskToStringProgram(Task task) {
        return task.toString();
    }

    /**
     * Returns a List of Tasks, each Task converted from 1 String in the specified list.
     *
     * @param list List of Strings to convert to Tasks.
     * @return List of Tasks.
     * @throws InvalidTaskTypeException if local storage file contains invalid Task identified, i.e. not [T], [E] or
     *     [D].
     */
    public static List<Task> listStringToListTask(List<String> list) {
        List<Task> result = list.stream()
                                .map(string -> fileStringToTask(string))
                                .collect(Collectors.toList());

        return result;
    }

    private static Task fileStringToTask(String input) {
        String[] separated = input.split(" \\| ");
        assert separated.length >= 3;
        char taskType = separated[0].charAt(0);

        switch (taskType) {
        case 'T':
            assert separated.length == 3;
            ToDo todo = new ToDo(separated[2]);
            if (separated[1].equals("1")) {
                todo.markAsDone();
            }
            return todo;

        case 'E':
            assert separated.length == 4;
            Event event = new Event(separated[2], LocalDateTime.parse(separated[3], formatter));
            if (separated[1].equals("1")) {
                event.markAsDone();
            }
            return event;

        case 'D':
            assert separated.length == 4;
            Deadline deadline = new Deadline(separated[2], LocalDateTime.parse(separated[3], formatter));
            if (separated[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;

        default:
            throw new AssertionError(taskType);
        }
    }
}
