package pason.tasks;

import pason.exceptions.PasonException;
import pason.storage.Storage;

import java.io.IOException;
import java.util.List;

public class TaskList {
    private static List<Task> tasks;
    private static Storage storage;

    public TaskList(Storage storage) throws Exception {
        try {
            tasks = storage.loadTasks();
            this.storage = storage;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void addTask(Task task) throws Exception {
        try {
            tasks.add(task);
            storage.appendTask(task);
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public String doneTask(int index) throws PasonException {
        try {
            if (tasks.get(index - 1).isDone()) {
                throw new PasonException("You've already marked this task as done.");
            }
            tasks.get(index - 1).markAsDone();
            storage.saveAllTasks(this.tasks);
            return "Good job! I've marked this task as done:\n"
                    + tasks.get(index - 1);
        } catch(IndexOutOfBoundsException e) {
            throw new PasonException("We couldn't find this task. "
                    + "Please enter the correct task number.");
        } catch(Exception e) {
            throw new PasonException(e.getMessage());
        }
    }

    public String deleteTask(int index) throws PasonException {
        index = index - 1;
        try {
            if(index > tasks.size() - 1 || index < 0) {
                throw new PasonException("You've entered an invalid task number.");
            } else {
                String removedTask = tasks.get(index).toString();
                tasks.remove(index);
                storage.saveAllTasks(tasks);
                return "Okay! I've removed this task:\n\t" + removedTask
                        + "\nNow there are " + tasks.size()
                        + " tasks in your list.";
            }
        } catch(Exception e) {
            throw new PasonException(e.getMessage());
        }
    }
}
