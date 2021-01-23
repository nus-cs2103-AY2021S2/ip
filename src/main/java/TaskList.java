import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    TaskList() {
         this.list = new ArrayList<>();
    }

    void addJob(Task t) {
        this.list.add(t);
    }

    Task getJob(int index) {
        return this.list.get(index);
    }

    int getSize() {
        return this.list.size();
    }

    void replaceJob(int index, Task newTask) {
        this.list.remove(index);
        this.list.add(index, newTask);
    }

    void deleteJob(int index) {
        this.list.remove(index);
    }
}
