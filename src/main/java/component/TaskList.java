package component;

import exception.WrongFormatException;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

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
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
