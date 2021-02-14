package task;

import util.DukeException;
import util.Formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TaskManager {
    private final ArrayList<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    private String taskCountMsg() {
        return "\nNow you have " + taskList.size() + " task(s) in your list";
    }

    public List<Task> getTasks() {
        return this.taskList;
    }

    public int size() {
        return this.taskList.size();
    }

    public String addTask(Task task) {
        taskList.add(task);

        return "Gotcha. I've added the task: \n"
                + task
                + taskCountMsg();
    }

    public String markTaskDone(int position) throws DukeException {
        try {
            taskList.get(position).markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within the list.");
        }
        return "Nice, another job well done!\n"
                + taskList.get(position).toString();
    }

    public String deleteTask(int position) throws DukeException {
        Task taskToRemove;
        try {
            taskToRemove = taskList.get(position);
            taskList.remove(position);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within the list.");
        }
        return "I've removed the task:\n"
                + taskToRemove.toString()
                + taskCountMsg();
    }

    public String listTasks() {
        return "Here is your list of tasks: \n" + Formatter.formatList(taskList
                .stream()
                .map(Task::toString)
                .collect(Collectors.toList())
        );
    }

    public String findTask(HashMap<String, String> argMap) throws NoSuchElementException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("Search keyword cannot be empty.");
        }

        return "Tasks that match \"" + argMap.get("desc") + "\": \n" + Formatter.formatList(taskList
                .stream()
                .map(Task::toString)
                .filter(t -> t.contains(argMap.get("desc")))
                .collect(Collectors.toList())
        );
    }

    public String toSaveString() {
        return taskList.stream()
                .map(Task::toSaveString)
                .collect(Collectors.joining("\n"));
    }
}
