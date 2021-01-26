import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;
    public Storage storage;
    public Ui ui;

    public TaskList(Storage storage, Ui ui) {
        tasks = new ArrayList<>();
        this.storage = storage;
        this.ui = ui;
    }

    public void listTask() {
        ui.printList(tasks, storage.numTasks);
    }

    public void doneTask(int taskIndex) throws IOException {
        String before = tasks.get(taskIndex).formatData();

        tasks.get(taskIndex).markAsDone();
        String after = tasks.get(taskIndex).formatData();

        storage.modifyFile(before, after);

        ui.printDone(tasks, taskIndex);
    }

    public void deleteTask(int taskIndex) throws IOException {
        Task deletedTask = tasks.get(taskIndex);

        storage.deleteFromFile(deletedTask.formatData());

        tasks.remove(taskIndex);
        ui.printDelete(deletedTask.toString());

        storage.numTasks--;
        ui.printNumTasks(storage.numTasks);
    }

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
