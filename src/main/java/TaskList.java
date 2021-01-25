import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> list;
    protected int size;

    public TaskList() {
        this.list = new ArrayList<>();
        this.size = 0;
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.size = list.size();
    }

    public void doneTask(int num) {

        list.get(num).markAsDone();

    }

    public void deleteTask(int num) {

        size = size - 1;
        Task t = list.get(num);
        list.remove(num);

    }

    public void addTask(String action, String task, String time) {

        size = size + 1;

        if (action.equals("todo")) {
            list.add(new Todo(task));
        } else if (action.equals("deadline")) {
            list.add(new Deadline(task, time));
        } else if (action.equals("event")) {
            list.add(new Event(task, time));
        }

    }

    public void listTask() {

        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }

    }

}
