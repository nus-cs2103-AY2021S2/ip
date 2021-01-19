package duke;

import exceptions.DukeIndexOutOfRangeException;
import exceptions.DukeTaskAlreadyDoneException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.ToDoTask;

import java.util.ArrayList;

/**
 *  duke.TaskManager manages all tasks given to duke.Duke.
 *
 *  @author Yap Jing Kang
 */
public class TaskManager {
    protected ArrayList<Task> tasks;
    protected Ui ui;

    /**
     *  Constructor initialises task list.
     */
    public TaskManager(Ui ui) {
        this.ui = ui;
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addToDoTask(String name) {
        tasks.add(new ToDoTask(name));
        ui.println("    added: " + name);
        ui.println(String.format("    Now you have %d task(s)",
                tasks.size()));
    }

    public void addDeadlineTask(String name, String flag) {
        tasks.add(new DeadlineTask(name, flag));
        ui.println("    added: " + name);
        ui.println(String.format("    Now you have %d task(s)",
                tasks.size()));
    }

    public void addEventTask(String name, String flag) {
        tasks.add(new EventTask(name, flag));
        ui.println("    added: " + name);
        ui.println(String.format("    Now you have %d task(s)",
                tasks.size()));
    }


    /**
     *  Method to mark specified task done.
     *
     *	@param x tasks.Task index.
     *
     */
    public void markTaskAsDone(int x) throws DukeIndexOutOfRangeException, DukeTaskAlreadyDoneException {
        try {
            Task t = tasks.get(x - 1);
            if (t.markAsDone()) {
                ui.println("    Marked as Done: ");
                ui.println("      " + t.toString());
            } else {
                throw new DukeTaskAlreadyDoneException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfRangeException();
        }
    }

    /**
     *  Method to remove specified.
     *
     *	@param x tasks.Task index.
     *
     */
    public void deleteTask(int x) throws DukeIndexOutOfRangeException{
        try {
            Task t = tasks.get(x - 1);
            tasks.remove(x - 1);
            ui.println("    The following task has been removed: ");
            ui.println("       " + t.toString());
            ui.println(String.format("    Now you have %d task(s)%n",
                    tasks.size()));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfRangeException();
        }
    }
}
