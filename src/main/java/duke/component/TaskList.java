package duke.component;

import java.util.ArrayList;

import duke.exception.WrongFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with tasks loaded from a string array.
     * @param lines
     * @throws WrongFormatException
     */
    public TaskList(String[] lines) throws WrongFormatException {
        this.tasks = new ArrayList<>();
        for (String line : lines) {
            String[] parameters = line.split("\\|");
            Task t;
            switch (parameters[0]) {
            case "T": {
                t = new ToDo(parameters[2]);
                break;
            }
            case "D": {
                t = new Deadline(parameters[2], parameters[3]);
                break;
            }
            case "E": {
                t = new Event(parameters[2], parameters[3]);
                break;
            }
            default: {
                throw new WrongFormatException();
            }
            }
            if (parameters[1].equals("1")) {
                t.markAsDone();
            }
            tasks.add(t);
        }
    }

    /**
     * Gets the list of tasks.
     * @return
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
