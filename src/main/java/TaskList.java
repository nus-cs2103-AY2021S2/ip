import enums.DukeCommand;
import exceptions.DukeExceptionCommandNotFound;
import exceptions.DukeExceptionIllegalDate;
import exceptions.DukeExceptionInvalidTaskString;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks; // composition

    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<String> taskStrings) {
        ArrayList<Task> toImport = new ArrayList<>(); // if fail, none imported
        boolean isImportSuccess = true;
        for (String s: taskStrings) {
            Task t;
            try {
                t = parseTaskString(s);
            } catch (DukeExceptionInvalidTaskString e) {
                isImportSuccess = false;
                break;
            }
            toImport.add(t);
        }
        tasks = (isImportSuccess) ? toImport : new ArrayList<>();
    }

    public void setDone(int taskIndex) {
        tasks.get(taskIndex).setDone();
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    private void addTask(Task t) {
        tasks.add(t);
    }

    public ToDo addTodo(String description) {
        return addTodo(description, false);
    }
    public ToDo addTodo(String description, boolean isDone) {
        ToDo t = new ToDo(description, isDone);
        addTask(t);
        return t;
    }

    public Deadline addDeadline(String description, String by) throws DukeExceptionIllegalDate {
        return addDeadline(description, by, false);
    }
    public Deadline addDeadline(String description, String by, boolean isDone) throws DukeExceptionIllegalDate {
        Deadline t = new Deadline(description, by, isDone);
        addTask(t);
        return t;
    }


    public Event addEvent(String description, String at) throws DukeExceptionIllegalDate {
        return addEvent(description, at, false);
    }
    public Event addEvent(String description, String at, boolean isDone) throws DukeExceptionIllegalDate {
        Event t = new Event(description, at, isDone);
        addTask(t);
        return t;
    }

    public int size() {
        return tasks.size();
    }

    public Task parseTaskString(String s) throws DukeExceptionInvalidTaskString {
        String[] args = s.split(" \\| ");
        boolean isDone = args[1].equals("1");
        DukeCommand dc;
        try {
            dc = DukeCommand.getCommandFromString(args[0]);
        } catch (DukeExceptionCommandNotFound e) {
            throw new DukeExceptionInvalidTaskString("TODO");
        }

        try {
            switch (dc) {
            case EVENT:
                return new Event(args[2], args[3], isDone);
            case TODO:
                return new ToDo(args[2], isDone);
            case DEADLINE:
                return new Deadline(args[2], args[3], isDone);
            default:
                throw new DukeExceptionInvalidTaskString("TODO");
            }
        } catch (DukeExceptionIllegalDate e) {
            throw new DukeExceptionInvalidTaskString("TODO: Wrong date presented");
        }
    }

    public ArrayList<String> asArrayList() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task t: tasks) {
            taskStrings.add(t.toFileString());
        }
        return taskStrings;
    }
}
