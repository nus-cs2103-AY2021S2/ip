package fakebot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fakebot.task.Deadlines;
import fakebot.task.Events;
import fakebot.task.Task;
import fakebot.task.TaskList;
import fakebot.task.ToDos;

/**
 * Parser class use for processing syntax
 */
public class Parser {

    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String SPLIT_REGEX = "-'@,-@,1'-";

    /**
     * Enum for different task type.
     */
    private enum TaskType {
        TODO, EVENT, DEADLINE
    }

    /**
     * Converts Task to String.
     *
     * @param stringList List of string to be converted to string.
     * @return Return string.
     */
    public static String convertStringListToString(List<String> stringList) {
        StringBuilder builder = new StringBuilder();
        for (String s : stringList) {
            builder.append(s);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    /**
     * Converts Task to String.
     *
     * @param task Task to be parsed to string.
     * @return Return parsed Task.
     */
    public static String convertTaskToString(Task task) {
        StringBuilder currentString = new StringBuilder();
        if (task instanceof ToDos) {
            currentString.append(TaskType.TODO.name());
            currentString.append(SPLIT_REGEX);
            currentString.append(task.isComplete());
            currentString.append(SPLIT_REGEX);
            currentString.append(task.getTaskName());
        } else if (task instanceof Events) {
            Events events = (Events) task;
            currentString.append(TaskType.EVENT.name());
            currentString.append(SPLIT_REGEX);
            currentString.append(task.isComplete());
            currentString.append(SPLIT_REGEX);
            currentString.append(events.getTaskName());
            currentString.append(SPLIT_REGEX);
            currentString.append(events.getStartDate());
            currentString.append(SPLIT_REGEX);
            currentString.append(events.getStartTime());
            currentString.append(SPLIT_REGEX);
            currentString.append(events.getEndDate());
            currentString.append(SPLIT_REGEX);
            currentString.append(events.getEndTime());
        } else if (task instanceof Deadlines) {
            Deadlines deadlines = (Deadlines) task;
            currentString.append(TaskType.DEADLINE.name());
            currentString.append(SPLIT_REGEX);
            currentString.append(task.isComplete());
            currentString.append(SPLIT_REGEX);
            currentString.append(deadlines.getTaskName());
            currentString.append(SPLIT_REGEX);
            currentString.append(deadlines.getDeadlineDate());
            currentString.append(SPLIT_REGEX);
            currentString.append(deadlines.getDeadlineTime());
        }
        return currentString.toString();
    }

    /**
     * Converts List of Task to List of String.
     *
     * @param taskList string of list.
     * @return Return list of String parsed from list of Task.
     */
    public static List<String> convertTasksToStrings(TaskList taskList) {
        List<String> strings = new ArrayList<String>();

        for (int i = 0; i < taskList.getSize(); i++) {
            Task currentTask = taskList.getTask(i);
            strings.add(convertTaskToString(currentTask));
        }
        return strings;
    }

    /**
     * Converts String to Task.
     *
     * @param string Parsed Task.
     * @return Return task parsed from string.
     */
    public static Task parseStringToTask(String string) {
        String[] parts = string.split(SPLIT_REGEX);
        Task currentTask = null;
        switch (TaskType.valueOf(parts[0])) {
        case TODO:
            currentTask = new ToDos(parts[2]);
            break;
        case EVENT:
            currentTask = new Events(parts[2], LocalDate.parse(parts[3]), LocalTime.parse(parts[4]),
                    LocalDate.parse(parts[5]), LocalTime.parse(parts[6]));
            break;
        case DEADLINE:
            currentTask = new Deadlines(parts[2], LocalDate.parse(parts[3]), LocalTime.parse(parts[4]));
            break;
        default: break;
        }

        if (Boolean.parseBoolean(parts[1])) {
            currentTask.markComplete();
        }

        return currentTask;
    }

    /**
     * Converts List of String to List of Task.
     *
     * @param stringList string of list.
     * @return Return list of Task parsed from list of string.
     */
    public static List<Task> parseStringsToTasks(List<String> stringList) {
        List<Task> tasks = new ArrayList<Task>();

        for (String line : stringList) {
            tasks.add(parseStringToTask(line));
        }
        return tasks;
    }



    /**
     * Returns Standard Print Message.
     *
     * @param message Message to print.
     */
    public static String getBotMPrintMessage(String message) {
        String printMessage = DIVIDER + message + "\n" + DIVIDER;
        return printMessage;
    }

    /**
     * Returns List of String Print Message.
     *
     * @param startingMessage Starting message to print before the list.
     * @param messages        List of String to print.
     */
    public static String getStringListPrintMessage(String startingMessage, List<String> messages) {
        StringBuilder printMessage = new StringBuilder(DIVIDER);
        printMessage.append(startingMessage);
        for (int i = 1; i <= messages.size(); i++) {
            printMessage.append(i);
            printMessage.append(".");
            printMessage.append(messages.get(i - 1));
            printMessage.append("\n");
        }
        printMessage.append(DIVIDER);
        return printMessage.toString();
    }

    /**
     * Returns List of Task Print Message.
     *
     * @param taskList List of Task to convert.
     */
    public static String getTaskListPrintMessage(TaskList taskList) {
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            messages.add(taskList.getTask(i).toString());
        }
        return getStringListPrintMessage("Here are the tasks in your list:\n", messages);
    }

}
