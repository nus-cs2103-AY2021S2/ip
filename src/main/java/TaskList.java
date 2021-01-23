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

        size = list.size();
        list.get(num).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n  "
                + list.get(num));

    }

    public void deleteTask(int num) {

        size = list.size() - 1;
        Task t = list.get(num);
        list.remove(num);
        System.out.println("Noted. I've removed this task:\n  "
                + t + "\nNow you have "
                + (size) + " tasks in the list.");

    }

    public void addTask(String action, String info, String time) {

        size = list.size() + 1;

        if (action.equals("todo")) {
            list.add(new Todo(info));
        } else if (action.equals("deadline")) {
            list.add(new Deadline(info, time));
        } else if (action.equals("event")) {
            list.add(new Event(info, time));
        }

        System.out.println("Got it. I've added this task:\n"
                + "  " + list.get(size - 1) + "\nNow you have "
                + size + " tasks in the list.");

    }

    public void listTask() {

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }

    }

}
