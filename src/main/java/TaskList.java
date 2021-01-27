import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void listTasks() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        Ui.echo(builder.toString().trim());
    }

    public void addTodo(HashMap<String, String> commands) throws DukeException {
        String taskName = commands.get("info");
        if (taskName == "") {
            throw new DukeException("Todo tasks should be formatted as such: todo [task name].");
        }

        Task todoTask = new Todo(taskName);
        tasks.add(todoTask);

        Ui.echo(String.format("Added a deadline for you:\n%s\n%s", todoTask.toString(), getNumberOfTasksString(tasks)));
    }

    public void addEvent(HashMap<String, String> commands) throws DukeException {
        String eventName = commands.get("info");
        String timeStr = commands.get("at");

        if (eventName == "" || timeStr == "") {
            throw new DukeException("Event tasks should be formatted as such: event [event name] /by [event time].");
        }

        LocalDateTime timeOfEvent = parseDateStr(timeStr);
        Event eventTask = new Event(eventName, timeOfEvent);
        tasks.add(eventTask);

        Ui.echo(String.format("Added a deadline for you:\n%s\n%s",
                eventTask.toString(), getNumberOfTasksString(tasks)));
    }

    public void addDeadline(HashMap<String, String> commands) throws DukeException {
        String deadlineName = commands.get("info");
        String deadlineTimeStr = commands.get("by");

        if (deadlineName == "" || deadlineTimeStr == "") {
            throw new DukeException("Deadline tasks should be formatted as such: deadline [task name] /by [deadline].");
        }

        LocalDateTime deadlineOfTask = parseDateStr(deadlineTimeStr);
        Deadline deadlineTask = new Deadline(deadlineName, deadlineOfTask);

        tasks.add(deadlineTask);

        Ui.echo(String.format("Added a deadline for you:\n%s\n%s",
                deadlineTask.toString(), getNumberOfTasksString(tasks)));
    }

    public void deleteTask(HashMap<String, String> commands) throws DukeException {
        String taskToDeleteStr = commands.get("info");

        if (taskToDeleteStr == "") {
            throw new DukeException("Deleting a task as done needs to be done like this: "
                    + "done [task number from list]. Task numbers need to be written as digits and not text.");
        }
        try {
            int taskIdx = Integer.parseInt(taskToDeleteStr) - 1;
            Task taskToDelete = tasks.get(taskIdx);
            tasks.remove(taskToDelete);
            Ui.echo(String.format("I've removed this task from your list\n%s", taskToDelete.toString()));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new DukeException("Please delete a task that exists in the list. "
                    + "Task numbers that are 0 or lesser, "
                    + "or greater than the number of items in the list cannot be deleted.");
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException("Deleting a task as done needs to be done like this: done [task number from list]. "
                    + "Task numbers need to be written as digits and not text.");
        }
    }

    public void markTaskAsDone(HashMap<String, String> commands) throws DukeException {
        String taskToMarkAsDoneStr = commands.get("info");

        if (taskToMarkAsDoneStr == "") {
            throw new DukeException("Marking a task as done needs to be done like this: done [task number from list].");
        }

        try {
            int taskIdx = Integer.parseInt(taskToMarkAsDoneStr) - 1;
            tasks.get(taskIdx).setDone();
            Ui.echo(String.format("Nice! This task is done :)\n%s", tasks.get(taskIdx).toString()));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new DukeException("Please mark a task that exists in the list as done. "
                    + "Task numbers that are 0 or lesser, "
                    + "or greater than the number of items in the list cannot be marked as done.");
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException("Marking a task as done needs to be done like this: done [task number from list]. "
                    + "Task numbers need to be written as digits and not text.");
        }
    }

    public String getNumberOfTasksString(List<Task> tasks) {
        return String.format("Now you have %d items in your list", tasks.size());
    }

    public void persist(DataStorage storage) {
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
