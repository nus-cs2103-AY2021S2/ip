package duke.task;

import duke.exception.DukeException;
import java.util.LinkedList;

public class TaskList {
    LinkedList<Task> list;

    public TaskList() {
        this.list = new LinkedList<>();
    }

    public TaskList(LinkedList<Task> list) {
        this.list = new LinkedList<Task>(list);
    }

    public Task addTask(Task task) {
        list.add(task);
        return task;
    }

    public Task removeTask(int pos) throws DukeException {
        if (pos > list.size() || pos < 0) {
            throw new DukeException(
                    String.format("Tried to delete nothing ????. (Size: %d | Task No: %d)", list.size(), pos));
        }
        Task task = this.list.remove(pos - 1);
        return task;
    }

    public Task markDone(int pos) throws DukeException {
        if (pos > this.list.size() || pos < 0) {
            throw new DukeException(
                    String.format("Tried to mark nothing ????. (Size: %d | Task No: %d)", list.size(), pos));
        }
        this.list.get(pos - 1).setDone();
        return this.list.get(pos - 1);
    }

    public LinkedList<Task> getList() {
        return this.list;
    }

}
