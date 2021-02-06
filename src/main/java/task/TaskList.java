package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exception.DukeException;
import storage.DataStorage;
import ui.Ui;

public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList wrapper object.
     * @param tasks Array of tasks to wrap around.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists tasks to the standard output.
     */
    public void listTasks(Ui ui) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        ui.setResponse(builder.toString().trim());
    }

    /**
     * Lists tasks to the standard input with a custom list.
     * @param customTaskList custom list of tasks to print.
     */
    public static void listTasks(List<Task> customTaskList, Ui ui) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < customTaskList.size(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, customTaskList.get(i).toString()));
        }
        ui.setResponse(builder.toString().trim());
    }

    /**
     * Adds a Todo.
     * @param commands Commands parsed by the parser.
     * @throws DukeException If todo is not formatted properly.
     */
    public void addTodo(HashMap<String, String> commands, Ui ui) throws DukeException {
        String taskName = commands.get("info");
        if (taskName == "") {
            throw new DukeException("Todo tasks should be formatted as such: todo [task name].");
        }

        Task todoTask = new Todo(taskName);
        tasks.add(todoTask);

        ui.setResponse(String.format("Added a deadline for you:\n%s\n%s", todoTask.toString(), getNumberOfTasksString(tasks)));
    }

    /**
     * Adds an Event.
     * @param commands Commands parsed by the parser.
     * @throws DukeException If Event is not formatted properly.
     */
    public void addEvent(HashMap<String, String> commands, Ui ui) throws DukeException {
        String eventName = commands.get("info");
        String timeStr = commands.get("at");

        if (eventName == "" || timeStr == "") {
            throw new DukeException("Event tasks should be formatted as such: event [event name] /by [event time].");
        }

        LocalDateTime timeOfEvent = parseDateStr(timeStr);
        Event eventTask = new Event(eventName, timeOfEvent);
        tasks.add(eventTask);

        ui.setResponse(String.format("Added a deadline for you:\n%s\n%s",
                eventTask.toString(), getNumberOfTasksString(tasks)));
    }
    /**
     * Adds an Deadline.
     * @param commands Commands parsed by the parser.
     * @throws DukeException If Deadline is not formatted properly.
     */
    public void addDeadline(HashMap<String, String> commands, Ui ui) throws DukeException {
        String deadlineName = commands.get("info");
        String deadlineTimeStr = commands.get("by");

        if (deadlineName == "" || deadlineTimeStr == "") {
            throw new DukeException("Deadline tasks should be formatted as such: deadline [task name] /by [deadline].");
        }

        LocalDateTime deadlineOfTask = parseDateStr(deadlineTimeStr);
        Deadline deadlineTask = new Deadline(deadlineName, deadlineOfTask);

        tasks.add(deadlineTask);

        ui.setResponse(String.format("Added a deadline for you:\n%s\n%s",
                deadlineTask.toString(), getNumberOfTasksString(tasks)));
    }

    /**
     * Deletes a task
     * @param commands Commands parsed by the parser.
     * @throws DukeException If Delete command is not formatted properly.
     */
    public void deleteTask(HashMap<String, String> commands, Ui ui) throws DukeException {
        String taskToDeleteStr = commands.get("info");

        if (taskToDeleteStr == "") {
            throw new DukeException("Deleting a task as done needs to be done like this: "
                    + "done [task number from list]. Task numbers need to be written as digits and not text.");
        }

        try {
            int taskIdx = Integer.parseInt(taskToDeleteStr) - 1;
            Task taskToDelete = tasks.get(taskIdx);
            tasks.remove(taskToDelete);
            ui.setResponse(String.format("I've removed this task from your list\n%s", taskToDelete.toString()));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new DukeException("Please delete a task that exists in the list. "
                    + "Task numbers that are 0 or lesser, "
                    + "or greater than the number of items in the list cannot be deleted.");
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException("Deleting a task as done needs to be done like this: done [task number from list]. "
                    + "Task numbers need to be written as digits and not text.");
        }
    }

    /**
     * Marks a task as done.
     * @param commands Commands parsed by the parser.
     * @throws DukeException If "mark as done" command is not formatted properly.
     */
    public void markTaskAsDone(HashMap<String, String> commands, Ui ui) throws DukeException {
        String taskToMarkAsDoneStr = commands.get("info");

        if (taskToMarkAsDoneStr == "") {
            throw new DukeException("Marking a task as done needs to be done like this: done [task number from list].");
        }

        try {
            int taskIdx = Integer.parseInt(taskToMarkAsDoneStr) - 1;
            tasks.get(taskIdx).setDone();
            ui.setResponse(String.format("Nice! This task is done :)\n%s", tasks.get(taskIdx).toString()));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new DukeException("Please mark a task that exists in the list as done. "
                    + "Task numbers that are 0 or lesser, "
                    + "or greater than the number of items in the list cannot be marked as done.");
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException("Marking a task as done needs to be done like this: done [task number from list]. "
                    + "Task numbers need to be written as digits and not text.");
        }
    }

    /**
     * Finds tasks that match a given string
     * @param commands Commands parsed by the parser.
     * @throws DukeException If find command is not formatted properly.
     */
    public void findTasks(HashMap<String, String> commands, Ui ui) throws DukeException {
        String searchTerm = commands.get("info");

        if (searchTerm == "") {
            throw new DukeException("To find a task, please use enter the following: find [search term]");
        }

        List<Task> foundList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskName().contains(searchTerm)) {
                foundList.add(task);
            }
        }
        ui.setResponse("Here are the tasks we found:");
        listTasks(foundList, ui);
    }

    /**
     * Returns a formatted string indicating how many tasks are in the list.
     * @param tasks List of tasks to count.
     * @return A string formatted to indicated how many tasks are in the list.
     */
    public String getNumberOfTasksString(List<Task> tasks) {
        return String.format("Now you have %d items in your list", tasks.size());
    }

    /**
     * Writes tasks to the given datastorage object.
     * @param storage DataStorage object to persist the tasks to.
     */
    public void persist(DataStorage storage) {
        assert storage != null;
        try {
            storage.saveTasks(tasks);
        } catch (DukeException dukeException) {
            Ui.echo(dukeException.getMessage());
        }
    }

    private static LocalDateTime parseDateStr(String dateStr) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            if (dateTime.isBefore(LocalDateTime.now())) {
                throw new DukeException("Please enter a date/time in the future.");
            }
            return dateTime;
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Please format your date as such: "
                    + "15/01/2021 1845 (day/month/year time in 24H format)");
        }
    }
}
