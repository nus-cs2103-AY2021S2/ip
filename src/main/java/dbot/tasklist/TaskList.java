package dbot.tasklist;

import dbot.exception.DukeException;
import dbot.task.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task get(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                throw new DukeException("The list is currently empty");
            } else {
                throw new DukeException("Valid indexes are from 1 to " + tasks.size() + ".");
            }
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) throws DukeException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                throw new DukeException("The list is currently empty");
            } else {
                throw new DukeException("Valid indexes are from 1 to " + tasks.size() + ".");
            }
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String encode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            String taskName = task.getClass().getSimpleName().toLowerCase();
            boolean isDone = task.getDone();
            String fullDescription = task.getFullDescription();
            stringBuilder.append(taskName + "|" + isDone + "|" + fullDescription + "\n");
        }
        return stringBuilder.toString();
    }
}
