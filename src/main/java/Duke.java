package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        List<Task> tasks;
        Scanner scanner = new Scanner(System.in);
        boolean shouldRun = true;
        DataStorage storage = new DataStorage();

        tasks = startup(storage);
        Ui.greet();

        while (shouldRun) {
            try {
                String input = Ui.readCommand();
                switch (input.split(" ")[0]) {
                case "list": {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        builder.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
                    }
                    Ui.echo(builder.toString().trim());
                    break;
                }
                case "done": {
                    markTaskAsDone(input, tasks);
                    break;
                }
                case "delete": {
                    deleteTask(input, tasks);
                    break;
                }
                case "todo": {
                    Task todoTask = createTodoTask(input);
                    tasks.add(todoTask);
                    Ui.echo(String.format("Added a deadline for you:\n%s\n%s", todoTask.toString(), getNumberOfTasksString(tasks)));
                    break;
                }
                case "deadline": {
                    Task deadline = createDeadlineTask(input);
                    tasks.add(deadline);
                    Ui.echo(String.format("Added a deadline for you:\n%s\n%s", deadline.toString(), getNumberOfTasksString(tasks)));
                    break;
                }
                case "event": {
                    Task event = createEventTask(input);
                    tasks.add(event);
                    Ui.echo(String.format("Added a deadline for you:\n%s\n%s", event.toString(), getNumberOfTasksString(tasks)));
                    break;
                }
                case "bye": {
                    Ui.echo("Bye. Hope to see you again soon!");
                    shouldRun = false;
                    break;
                }
                default: {
                    Ui.echo(String.format("I'm sorry, I don't know what %s means.", input));
                    break;
                }
                }
            } catch (DukeException dukeException) {
                Ui.echo(String.format("Francis encountered an error while processing your request. Here are the details:\n%s", dukeException.getMessage()));
            } catch (Exception e) {
                Ui.echo(String.format("Francis encountered an unexpected while processing your request. Here are the details:\n%s", e.getMessage()));
            }
        }

        shutdown(storage, tasks);
    }

    public static Task createTodoTask(String input) throws DukeException {
        Pattern p = Pattern.compile("(?<=todo ).*");
        Matcher matcher = p.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Todo tasks should be formatted as such: todo [task name].");
        }
        String taskName = matcher.group();
        return new Todo(taskName);
    }

    public static Deadline createDeadlineTask(String input) throws DukeException {
        // First get the task name
        Pattern p = Pattern.compile("(?<=deadline )(.*)(?= \\/by)");
        Matcher matcher = p.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Deadline tasks should be formatted as such: deadline [task name] /by [deadline].");
        }
        String taskName = matcher.group();

        // The get the deadline
        p = Pattern.compile("(?<=\\/by ).*");
        matcher = p.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Deadline tasks should be formatted as such: deadline [task name] /by [deadline].");
        }

        String deadlineStr = matcher.group();
        LocalDateTime deadlineOfTask = parseDateStr(deadlineStr);

        return new Deadline(taskName, deadlineOfTask);
    }

    public static Event createEventTask(String input) throws DukeException {
        // First get the task name
        Pattern p = Pattern.compile("(?<=event )(.*)(?= \\/at)");
        Matcher matcher = p.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Event tasks should be formatted as such: event [event name] /by [event time].");
        }
        String taskName = matcher.group();

        // The get the time
        p = Pattern.compile("(?<=\\/at ).*");
        matcher = p.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Event tasks should be formatted as such: event [event name] /by [event time].");
        }

        String timeStr = matcher.group();
        LocalDateTime timeOfEvent = parseDateStr(timeStr);

        return new Event(taskName, timeOfEvent);
    }

    public static void markTaskAsDone(String input, List<Task> tasks) throws DukeException {
        int taskIdx;
        String taskIdxStr;
        try {
            taskIdxStr = input.split(" ")[1];
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new DukeException("Marking a task as done needs to be done like this: done [task number from list].");
        }

        try {
            taskIdx = Integer.parseInt(taskIdxStr) - 1;
            tasks.get(taskIdx).setDone();
            Ui.echo(String.format("Nice! This task is done :)\n%s", tasks.get(taskIdx).toString()));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new DukeException("Please mark a task that exists in the list as done. Task numbers that are 0 or lesser, or greater than the number of items in the list cannot be marked as done.");
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException("Marking a task as done needs to be done like this: done [task number from list]. Task numbers need to be written as digits and not text.");
        }
    }

    public static void deleteTask(String input, List<Task> tasks) throws DukeException {
        int taskIdx;
        String taskIdxStr;
        try {
            taskIdxStr = input.split(" ")[1];
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new DukeException("Deleting a task as done needs to be done like this: done [task number from list].");
        }

        try {
            taskIdx = Integer.parseInt(taskIdxStr) - 1;
            Task taskToDelete = tasks.get(taskIdx);
            tasks.remove(taskToDelete);
            Ui.echo(String.format("I've removed this task from your list\n%s", taskToDelete.toString()));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new DukeException("Please delete a task that exists in the list. Task numbers that are 0 or lesser, or greater than the number of items in the list cannot be deleted.");
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException("Deleting a task as done needs to be done like this: done [task number from list]. Task numbers need to be written as digits and not text.");
        }
    }

    public static String getNumberOfTasksString(List<Task> tasks) {
        return String.format("Now you have %d items in your list", tasks.size());
    }

    public static LocalDateTime parseDateStr(String dateStr) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            if (dateTime.isBefore(LocalDateTime.now())) {
                throw new DukeException("Please enter a date/time in the future.");
            }
            return dateTime;
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Please format your date as such: 15/01/2021 1845 (day/month/year time in 24H format)");
        }
    }

    public static List<Task> startup(DataStorage storage) {
        try {
            storage.createBackingStoreIfNotExists();
            return storage.readTasks();
        } catch (DukeException dukeException) {
            Ui.echo(dukeException.getMessage());
            System.exit(0);
        }
        return null;
    }

    public static void shutdown(DataStorage storage, List<Task> tasks) {
        try {
            storage.saveTasks(tasks);
        } catch (DukeException dukeException) {
            Ui.echo(dukeException.getMessage());
        }
    }
}
