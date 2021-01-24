import java.util.ArrayList;

public class TaskManager{
    private ArrayList<Task> list = new ArrayList<>(100);
    private final static String TODO = "todo";
    private final static String DELETE = "delete";
    private final static String DONE = "done";
    private final static String EVENT = "event";
    private final static String DEADLINE = "deadline";
    private final static String LIST = "list";

    public void add(String type, String task) throws DukeException {
        if (type.equals(TODO)) {
            list.add(new ToDo(task));
        } else {
            String description = task.split("/")[0];
            String deadline = task.split("/")[1].split(" ", 2)[1];
            if (type.equals(DEADLINE)) {
                list.add(new Deadline(description, deadline));
            } else if (type.equals(EVENT)) {
                list.add(new Event(description, deadline));
            }
        }
        System.out.println("Got it. I've added this task:\n" + list.get(list.size() - 1) +
                "\nNow you have " + list.size() + " tasks in the list.");
        System.out.println("");
    }

    public void delete(int taskId) throws DukeException {
        if (taskId <= list.size() && taskId >= 1) {
            Task deletedTask = list.get(taskId - 1);
            list.remove(taskId - 1);
            System.out.println("Noted. I've removed this task:\n" + deletedTask +
                    "\nNow you have " + list.size() + " tasks in the list.");
            System.out.println("");
        } else {
            throw new DukeException("Invalid task number. You only have " + list.size() + " tasks in the list.");
        }
    }

    public void done(int taskId) throws DukeException {
        if (taskId <= list.size() && taskId >= 1) {
            Task completedTask = list.get(taskId - 1);
            completedTask.markComplete();
            System.out.println("Nice! I've marked this task as done:\n" + completedTask);
            System.out.println("");
        } else {
            throw new DukeException("Invalid task number. You only have " + list.size() + " tasks in the list.");
        }
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        System.out.println("");
    }


    public void manage(String[] parsedAction) throws DukeException {
        String command = parsedAction[0];
        if (command.equals(DEADLINE) || command.equals(EVENT) || command.equals(TODO)) {
            add(command, parsedAction[1]);
        } else if (command.equals(LIST)) {
            printList();
        } else if (command.equals(DONE)) {
            done(Integer.parseInt(parsedAction[1]));
        } else {
            delete(Integer.parseInt(parsedAction[1]));
        }
    }
}
