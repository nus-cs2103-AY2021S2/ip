package TaskList;

import Tasks.DukeTask;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<DukeTask> list;

    public TaskList() {
        this.list = new ArrayList<DukeTask>();
    }

    public TaskList(List<DukeTask> list) {
        this.list = list;
    }

    public void add(String item) {
        this.list.add(new DukeTask(item));
        System.out.println("added: " + item + "\n");
    }

    public void add(DukeTask task) {
        this.list.add(task);
    }

    public void done(int index) {
        DukeTask task = this.list.get(index - 1).markDone();
        this.list.set(index - 1, task);
    }

    public void delete(int index) {
        this.list.remove(index - 1);
    }

    public List<DukeTask> find(String word) {
        List<DukeTask> finder = new ArrayList<>();
        for (DukeTask task : this.list) {
            if (task.getname().contains(word)) {
                finder.add(task);
            }
        }
        return finder;
    }

    public int size() {
        return this.list.size();
    }

    public List<DukeTask> getList() {
        return this.list;
    }

    public DukeTask getTask(int index) {
        return this.list.get(index - 1);
    }

}
