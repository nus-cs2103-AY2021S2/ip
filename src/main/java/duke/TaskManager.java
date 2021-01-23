package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeCorruptFileException;
import duke.exception.DukeIndexRangeException;
import duke.exception.DukeTaskAlreadyDoneException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

/**
 * duke.TaskManager manages all duke.tasks given to duke.Duke.
 *
 * @author Yap Jing Kang
 */
public class TaskManager {
    private ArrayList<Task> tasks;

    /**
     * Constructor initialises task list.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     *  Loads pre-existing tasks, from String ArrayList directly from file.
     *
     *  @param arr ArrayList of Task information
     *  @throws DukeCorruptFileException If information given is corrupt in any way.
     */
    public void loadArray(ArrayList<String> arr) throws DukeCorruptFileException {
        try {
            for (String line : arr) {
                String[] params = line.split("\\|");
                String type = params[0];
                boolean isCompleted = params[1].equals("1");
                String name = params[2];

                Task t;
                LocalDate d;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
                if (type.equals("T")) {
                    t = new ToDoTask(name, isCompleted);
                } else if (type.equals("D")) {
                    d = LocalDate.parse(params[3], formatter);
                    t = new DeadlineTask(name, d, isCompleted);
                } else if (type.equals("E")) {
                    d = LocalDate.parse(params[3], formatter);
                    t = new EventTask(name, d, isCompleted);
                } else {
                    throw new DukeCorruptFileException();
                }
                this.tasks.add(t);
            }
        } catch (Exception e) {
            throw new DukeCorruptFileException();
        }
    }

    public void clear() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     *  Given text, finds tasks with name containing text.
     *
     *  @param s Search term
     */
    public ArrayList<Task> findTasks(String s) {
        ArrayList<Task> output = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().contains(s)) {
                output.add(task);
            }
        }
        return output;
    }

    public int getSize() {
        return tasks.size();
    }

    public void addToDoTask(String name) {
        tasks.add(new ToDoTask(name));
    }

    public void addDeadlineTask(String name, LocalDate date) {
        tasks.add(new DeadlineTask(name, date));
    }

    public void addEventTask(String name, LocalDate date) {
        tasks.add(new EventTask(name, date));
    }

    /**
     * Method to mark specified task done.
     *
     * @param x Task index.
     */
    public Task markTaskAsDone(int x) throws DukeIndexRangeException, DukeTaskAlreadyDoneException {
        try {
            Task t = tasks.get(x - 1);
            if (t.markAsDone()) {
                return t;
            } else {
                throw new DukeTaskAlreadyDoneException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexRangeException();
        }
    }

    /**
     * Method to remove specified.
     *
     * @param x Task index.
     */
    public Task deleteTask(int x) throws DukeIndexRangeException {
        try {
            Task t = tasks.get(x - 1);
            tasks.remove(x - 1);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexRangeException();
        }
    }
}
