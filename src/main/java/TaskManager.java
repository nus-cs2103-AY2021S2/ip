import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> list = new ArrayList<>(100);

    public void add(String type, String task) throws DukeException{
        if (type.equals("todo")) {
            list.add(new ToDo(task));
        } else {
            String description = task.split("/")[0];
            String deadline = task.split("/")[1].split(" ", 2)[1];
            if (type.equals("deadline")) {
                list.add(new Deadline(description, deadline));
            } else if (type.equals("event")) {
                list.add(new Event(description, deadline));
            }
        }
        System.out.println("Got it. I've added this task:\n" + list.get(list.size() - 1) + "\nNow you have " + list.size() + " tasks in the list.");
        System.out.println("");
    }

    public void delete(int taskId) throws DukeException{
        if (taskId < list.size() && taskId >= 0) {
            Task deletedTask = list.get(taskId);
            list.remove(taskId);
            System.out.println("Noted. I've removed this task:\n" + deletedTask + "\nNow you have " + list.size() + " tasks in the list.");
            System.out.println("");
        } else {
            throw new DukeException("Invalid task number. You only have " + list.size() + " tasks in the list.");
        }
    }

    public void done(int taskId) throws DukeException{
        if (taskId < list.size() && taskId >= 0) {
            Task completedTask = list.get(taskId);
            completedTask.markComplete();
            System.out.println("Nice! I've marked this task as done:\n" + completedTask);
            System.out.println("");
        } else {
            throw new DukeException("Invalid task number. You only have " + list.size() + " tasks in the list.");
        }
    }

    public void printList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        System.out.println("");
    }
}
