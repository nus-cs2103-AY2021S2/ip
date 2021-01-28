package duke.task;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return list;
    }

    public int getSize() {
        return list.size();
    }

    public void add(Task task){
        list.add(task);
    }

    public Task delete(String num) throws DukeException {
        int index = Integer.valueOf(num) - 1;

        if(index < 0 || index >= list.size()) {
            throw new DukeException("Please enter an appropriate index.");
        }

        return list.remove(index);
    }

    public Task markTaskAsDone(String num) throws DukeException{
        int index = Integer.valueOf(num) - 1;

        if(index < 0 || index >= list.size()) {
            throw new DukeException("Please enter an appropriate index.");
        }

        Task t = list.get(index);
        t.markAsDone();

        return t;
    }

    public String listOutTaskInString() {
        String res = "";

        res += "Done tasks: " + System.lineSeparator();

        for(Task t: list) {
            if(t.getIsDone()) {
                res += t.toFileString() + System.lineSeparator();
            }
        }

        res += "Pending tasks: " + System.lineSeparator();

        for(Task t: list) {
            if(!t.getIsDone()) {
                res += t.toFileString() + System.lineSeparator();
            }
        }

        return res;
    }
}
