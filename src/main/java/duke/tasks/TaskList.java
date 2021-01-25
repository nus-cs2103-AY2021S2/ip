package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks; // composition

    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<String> taskStrings) throws DukeExceptionIllegalArgument {
        tasks = new ArrayList<>(); // if fail, none imported
        boolean isImportSuccess = true;
        for (String s: taskStrings) {
            Task t = Task.parseFileString(s);
            tasks.add(t);
        }
    }

    public void setDone(int taskIndex) throws DukeExceptionIllegalArgument {
        getTask(taskIndex).setDone();
    }

    public Task getTask(int taskIndex) throws DukeExceptionIllegalArgument {
        if (tasks.size() <= taskIndex || taskIndex < 0) {
            throw new DukeExceptionIllegalArgument("The task number must be valid.");
        }
        return tasks.get(taskIndex);
    }

    public void deleteTask(int taskIndex) throws DukeExceptionIllegalArgument {
        if (tasks.size() <= taskIndex || taskIndex < 0) {
            throw new DukeExceptionIllegalArgument("The task number must be valid.");
        }
        tasks.remove(taskIndex);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<String> asArrayList() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task t: tasks) {
            taskStrings.add(t.toFileString());
        }
        return taskStrings;
    }
}
