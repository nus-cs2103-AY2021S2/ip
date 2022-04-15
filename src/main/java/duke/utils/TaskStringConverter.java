package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.dukeexceptions.InvalidFileTaskTypeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Models a utility which converts between task and string representations.
 */
public class TaskStringConverter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("[d/M/yyyy HHmm][d MMM yy HHmm]"
            + "[dd-MM-yy HHmm]");

    /**
     * Returns concatenated String description of all Tasks to save to local storage.
     *
     * @param list List of Tasks to convert and concatenate.
     * @return String description of all Tasks.
     */
    public static String stringTasksForFile(List<Task> list) {
        if (list.size() == 0) {
            return "";
        }

        List<String> stringTasks = listTaskToListStringFile(list);

        StringBuilder stringBuilder = new StringBuilder();
        for (String stringTask : stringTasks) {
            stringBuilder.append(stringTask)
                    .append("\n");
        }

        StringBuilder removeBreakLine = stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return removeBreakLine.toString();
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
            return toDoStringForFile(task, done);
        } else if (task instanceof Event) {
            return eventStringForFile(task, done);
        } else if (task instanceof Deadline) {
            return deadlineStringForFile(task, done);
        } else {
            throw new AssertionError(task);
        }
    }

    private static String toDoStringForFile(Task task, String done) {
        assert task instanceof ToDo;
        return "T | " + done + " | " + task.getDescription();
    }

    private static String eventStringForFile(Task task, String done) {
        assert task instanceof Event;
        return "E | " + done + " | " + task.getDescription() + " | " + ((Event) task).getDateForFile();
    }

    private static String deadlineStringForFile(Task task, String done) {
        assert task instanceof Deadline;
        return "D | " + done + " | " + task.getDescription() + " | " + ((Deadline) task).getDateForFile();
    }

    /**
     * Returns concatenated String description of all Tasks for display in graphical user interface.
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
     * @throws InvalidFileTaskTypeException thrown when there is an invalid Task type in an entry in the local
     *     storage file.
     */
    public static List<Task> listStringToListTask(List<String> list) throws InvalidFileTaskTypeException {
        List<Task> result = new ArrayList<>();

        for (String stringTask : list) {
            result.add(fileStringToTask(stringTask));
        }

        return result;
    }

    private static Task fileStringToTask(String input) throws InvalidFileTaskTypeException {
        String[] splitFileInput = input.split(" \\| ");

        assert splitFileInput.length >= 3;

        char taskType = splitFileInput[0].charAt(0);

        switch (taskType) {
        case 'T':
            return generateToDoTask(splitFileInput);

        case 'E':
            return generateEventTask(splitFileInput);

        case 'D':
            return generateDeadlineTask(splitFileInput);

        default:
            throw new InvalidFileTaskTypeException();
        }
    }

    private static ToDo generateToDoTask(String[] splitFileInput) {
        assert splitFileInput.length == 3;
        ToDo todo = new ToDo(splitFileInput[2]);

        if (splitFileInput[1].equals("1")) {
            todo.markAsDone();
        }

        return todo;
    }

    private static Event generateEventTask(String[] splitFileInput) {
        assert splitFileInput.length == 4;
        Event event = new Event(splitFileInput[2], LocalDateTime.parse(splitFileInput[3], FORMATTER));

        if (splitFileInput[1].equals("1")) {
            event.markAsDone();
        }

        return event;
    }

    private static Deadline generateDeadlineTask(String[] splitFileInput) {
        assert splitFileInput.length == 4;
        Deadline deadline = new Deadline(splitFileInput[2], LocalDateTime.parse(splitFileInput[3], FORMATTER));

        if (splitFileInput[1].equals("1")) {
            deadline.markAsDone();
        }

        return deadline;
    }
}
