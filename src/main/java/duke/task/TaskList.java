package duke.task;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.UnknownCommandException;
import duke.utils.Command;
import duke.utils.DateTime;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void setTaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task addTask(Command command, String input) throws ArrayIndexOutOfBoundsException, InvalidDateException, UnknownCommandException {
        String[] tokens;
        Task task;

        switch (command) {
        case TODO:
            task = new Todo(input);
            break;
        case DEADLINE:
            tokens = input.split(" /by ", 2);
            input = tokens[0];
            try {
                task = new Deadline(input, DateTime.parseDate(tokens[1]));
            } catch (DateTimeParseException e) {
                throw new InvalidDateException(tokens[1]);
            }
            break;
        case EVENT:
            tokens = input.split(" /at ", 2);
            input = tokens[0];
            try {
                task = new Event(input, DateTime.parseDate(tokens[1]));
            } catch (DateTimeParseException e) {
                throw new InvalidDateException(tokens[1]);
            }
            break;
        default:
            throw new UnknownCommandException(command.name());
        }

        tasks.add(task);

        return task;
    }

    public Task markAsDone(int idx) throws InvalidInputException {
        Task task;
        try {
            task = tasks.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(tasks.size());
        }

        task.markAsDone();

        return task;
    }

    public Task delete(int idx) throws InvalidInputException {
        Task task;
        try {
            task = tasks.get(idx);
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                throw new InvalidInputException();
            } else {
                throw new InvalidInputException(tasks.size());
            }
        }

        tasks.remove(idx);

        return task;
    }

    public int getSize() {
        return tasks.size();
    }
}
