package duke.task;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.UnknownCommandException;
import duke.utils.Command;
import duke.utils.DateTime;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
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

        taskList.add(task);

        return task;
    }

    public Task markAsDone(int idx) throws InvalidInputException {
        Task task;
        try {
            task = taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(taskList.size());
        }

        task.markAsDone();

        return task;
    }

    public Task delete(int idx) throws InvalidInputException {
        Task task;
        try {
            task = taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                throw new InvalidInputException();
            } else {
                throw new InvalidInputException(taskList.size());
            }
        }

        taskList.remove(idx);

        return task;
    }

    public ArrayList<Task> getFilteredTaskList(String searchString) {
        ArrayList<Task> filteredArr = new ArrayList<>();
        searchString = searchString.toLowerCase();

        for (Task task : taskList) {
            if (task.description.toLowerCase().contains(searchString)) {
                filteredArr.add(task);
            }
        }

        return filteredArr;
    }

    public int getSize() {
        return taskList.size();
    }
}
