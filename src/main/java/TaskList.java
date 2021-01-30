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
    public void listTask() {
        ui.printList(tasks, storage.numTasks);
    }

    /**
     * Marks task as done by modifying task arraylist and file data.
     *
     * @param taskIndex arraylist index of the selected task.
     * @throws IOException for modifying data.
     */
    public void doneTask(int taskIndex) throws IOException {
        String before = tasks.get(taskIndex).formatData();

        tasks.get(taskIndex).markAsDone();
        String after = tasks.get(taskIndex).formatData();

        storage.modifyFile(before, after);

        ui.printDone(tasks, taskIndex);
    }

    /**
     * Deletes task by removing from task list and file data.
     * Decrements number of tasks.
     *
     * @param taskIndex arraylist index of the selected task.
     * @throws IOException for deleting from file.
     */
    public void deleteTask(int taskIndex) throws IOException {
        Task deletedTask = tasks.get(taskIndex);

        storage.deleteFromFile(deletedTask.formatData());

        tasks.remove(taskIndex);
        ui.printDelete(deletedTask.toString());

        storage.numTasks--;
        ui.printNumTasks(storage.numTasks);
    }

    /**
     * Creates ToDos object to add to task list and file data.
     * If description is empty, will print prompt to tell user.
     * If task addition is successful, increments number of tasks.
     *
     * @param description description of todo.
     * @throws IOException for adding to file.
     */
    public void addTodo(String description) throws IOException {
        if (description.equals("emptyDescError")) {
            ui.printEmptyDescError("todo");
        } else {
            ToDos todo = new ToDos(description);
            tasks.add(todo);
            ui.printAdd(tasks, storage.numTasks);
            storage.addToFile(todo.formatData());

            storage.numTasks++;
            ui.printNumTasks(storage.numTasks);
        }
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
    public void addDeadline(String description, String date) throws IOException {
        if (description.equals("emptyDescError")) {
            ui.printEmptyDescError("deadline");
        } else {
            LocalDate localDate;
            try {
                localDate = LocalDate.parse(date);
                Deadlines deadline = new Deadlines(description, localDate);

                tasks.add(deadline);
                ui.printAdd(tasks, storage.numTasks);
                storage.addToFile(deadline.formatData());

                storage.numTasks++;
                ui.printNumTasks(storage.numTasks);
            } catch (DateTimeParseException e) {
                ui.printDateError();
            }
        }
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
    public void addEvent(String description, String date) throws IOException {
        if (description.equals("emptyDescError")) {
            ui.printEmptyDescError("event");
        } else {
            try {
                LocalDate localDate = LocalDate.parse(date);
                Events event = new Events(description, localDate);

                tasks.add(event);
                ui.printAdd(tasks, storage.numTasks);
                storage.addToFile(event.formatData());

                storage.numTasks++;
                ui.printNumTasks(storage.numTasks);
            } catch (DateTimeParseException e) {
                ui.printDateError();
            }
        }
    }
}
