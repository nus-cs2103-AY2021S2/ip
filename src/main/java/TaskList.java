import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

/**
 * Contains the task list and all operations to be performed on tasks
 * e.g. list tasks, mark as done, delete, add todos, deadlines and events.
 */
public class TaskList {
    public ArrayList<Task> tasks;
    public Storage storage;
    public Ui ui;

    public TaskList(Storage storage, Ui ui) {
        tasks = new ArrayList<>();
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Prints list of tasks saved in file.
     */
    public String listTask() {
        return ui.printList(tasks, storage.numTasks);
    }

    /**
     * Marks task as done by modifying task arraylist and file data.
     *
     * @param taskIndexString String of arraylist index of the selected task.
     * @throws IOException for modifying data.
     */
    public String doneTask(String taskIndexString) throws IOException {
        int taskIndex = Integer.parseInt(taskIndexString);
        assert(taskIndex >= 0 && taskIndex < tasks.size());

        String before = tasks.get(taskIndex).formatData();

        tasks.get(taskIndex).markAsDone();
        String after = tasks.get(taskIndex).formatData();

        storage.modifyFile(before, after);

        return ui.printDone(tasks, taskIndex);
    }

    /**
     * Deletes task by removing from task list and file data.
     * Decrements number of tasks.
     *
     * @param taskIndexString String of arraylist index of the selected task.
     * @throws IOException for deleting from file.
     */
    public String deleteTask(String taskIndexString) throws IOException {
        int taskIndex = Integer.parseInt(taskIndexString);
        assert(taskIndex >= 0 && taskIndex < tasks.size());

        String response;

        Task deletedTask = tasks.get(taskIndex);

        storage.deleteFromFile(deletedTask.formatData());

        tasks.remove(taskIndex);
        response = ui.printDelete(deletedTask.toString());

        storage.numTasks--;
        response += ui.printNumTasks(storage.numTasks);

        return response;
    }

    public String editTask(String taskIndexString, boolean hasDescription, boolean hasDate,
                           String newDescription, String newDate) throws IOException {
        int taskIndex = Integer.parseInt(taskIndexString);

        String response = "";

        Task task = tasks.get(taskIndex);
        String type = task.getType();

        String before = task.formatData();

        switch (type) {
        case "T":
            if (hasDescription) {
                task.editTask(newDescription);
                if (hasDate) {
                    response += ui.printToDoHasNoDateError();
                }
            } else {
                ui.printEmptyDescError("edit");
            }
            break;
        case "D":
        case "E":
            String finalDescription = task.description;
            LocalDate localDate;

            if (hasDescription) {
                finalDescription = newDescription;
            }

            if (hasDate) {
                localDate = LocalDate.parse(newDate);
                tasks.set(taskIndex, type.equals("D")
                    ? new Deadlines(finalDescription, localDate)
                    : new Events(finalDescription, localDate));
            } else {
                task.editTask(finalDescription);
            }
            break;
        }

        String after = tasks.get(taskIndex).formatData();

        storage.modifyFile(before, after);

        response += ui.printEdit(tasks, taskIndex);
        return response;
    }

    /**
     * Creates ToDos object to add to task list and file data.
     * If description is empty, will print prompt to tell user.
     * If task addition is successful, increments number of tasks.
     *
     * @param description description of todo.
     * @throws IOException for adding to file.
     */
    public String addTodo(String description) throws IOException {
        String response;

        ToDos todo = new ToDos(description);
        tasks.add(todo);
        response = ui.printAdd(tasks, storage.numTasks);
        storage.addToFile(todo.formatData());

        storage.numTasks++;
        response += ui.printNumTasks(storage.numTasks);

        return response;
    }

    /**
     * Creates Deadline object to add to task list and file data.
     * If description is empty, will print prompt to tell user.
     * If date is in wrong format, will print prompt to tell user.
     * If task addition is successful, increments number of tasks.
     *
     * @param description description of deadline.
     * @param date in YYYY-MM-DD format.
     * @throws IOException for adding to file.
     */
    public String addDeadline(String description, String date) throws IOException {
        String response;

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
            Deadlines deadline = new Deadlines(description, localDate);

            tasks.add(deadline);
            response = ui.printAdd(tasks, storage.numTasks);
            storage.addToFile(deadline.formatData());

            storage.numTasks++;
            response += ui.printNumTasks(storage.numTasks);
        } catch (DateTimeParseException e) {
            response = ui.printDateError();
        }
        return response;
    }

    /**
     * Creates Deadline object to add to task list and file data.
     * If description is empty, will print prompt to tell user.
     * If date is in wrong format, will print prompt to tell user.
     * If task addition is successful, increments number of tasks.
     *
     * @param description description of event.
     * @param date in YYYY-MM-DD format.
     * @throws IOException for adding to file.
     */
    public String addEvent(String description, String date) throws IOException {
        String response;
        try {
            LocalDate localDate = LocalDate.parse(date);
            Events event = new Events(description, localDate);

            tasks.add(event);
            response = ui.printAdd(tasks, storage.numTasks);
            storage.addToFile(event.formatData());

            storage.numTasks++;
            response += ui.printNumTasks(storage.numTasks);
        } catch (DateTimeParseException e) {
            response = ui.printDateError();
        }
        return response;
    }

    /**
     * Creates a filtered list of tasks
     * whose descriptions contain the specified keyword.
     * Prints the filtered list of tasks.
     *
     * @param keyword keyword
     */
    public String findTasks(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.contains(keyword)) {
                filteredTasks.add(t);
            }
        }
        return ui.printList(filteredTasks, -1);
    }

    public String printEmptyDescError(String command) {
        return ui.printEmptyDescError(command);
    }
}
