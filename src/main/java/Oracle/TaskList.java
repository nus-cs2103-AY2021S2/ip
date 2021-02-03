package Oracle;

import Command.CommandFormatException;
import Entry.Deadline;
import Entry.Event;
import Entry.Task;
import Entry.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasklist;

    public TaskList(List<String> load) {
        this.tasklist = new ArrayList<>();
        for (String s : load) {
            String[] sorted = s.split("\u001E");
            Boolean isDone = sorted[1].equals("T");
            try{
                switch (sorted[0]) {
                    case "T":
                        this.tasklist.add(new Todo(isDone, sorted[2]));
                        break;
                    case "D":
                        this.tasklist.add(new Deadline(isDone, sorted[2], sorted[3]));
                        break;
                    case "E":
                        this.tasklist.add(new Event(isDone, sorted[2], sorted[3]));
                }
            } catch (CommandFormatException ignored) {
            }
        }
    }

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public int size() {
        return this.tasklist.size();
    }

    public Task get(int i) {
        return this.tasklist.get(i);
    }
    public void markDone(int i) {
        tasklist.get(i).markDone();
    }

    public void add(Task task) {
        this.tasklist.add(task);
    }

    public void remove(int i) {
        this.tasklist.remove(i);
    }

    public ArrayList<Task> getTasks() {
        return this.tasklist;
    }
}
