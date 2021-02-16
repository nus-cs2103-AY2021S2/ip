package task;

import util.DukeException;

import java.util.ArrayList;
import java.util.List;
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

    public String toSaveString() {
        return taskList.stream()
                .map(Task::toSaveString)
                .collect(Collectors.joining("\n"));
    }
}
